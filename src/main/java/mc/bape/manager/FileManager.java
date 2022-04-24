package mc.bape.manager;

import mc.bape.vapu.*;
import mc.bape.module.*;
import mc.bape.values.*;
import java.util.*;
import mc.bape.utils.*;
import java.io.*;
import com.google.gson.*;

public class FileManager
{
    private final File dir;
    private final File modules;
    private final Gson gson;
    
    public FileManager() {
        this.dir = new File(System.getenv("APPDATA"), Client.CLIENT_NAME);
        this.modules = new File(this.dir, Client.CLIENT_CONFIG + ".json");
        this.gson = new Gson();
        this.dir.mkdirs();
    }
    
    public void saveModules() throws IOException {
        if (!this.modules.exists()) {
            this.modules.createNewFile();
        }
        final JsonObject jsonObject = new JsonObject();
        for (final Module module : ModuleManager.getModules()) {
            final JsonObject moduleJson = new JsonObject();
            moduleJson.addProperty("state", Boolean.valueOf(module.getState()));
            moduleJson.addProperty("key", (Number)module.getKey());
            for (final Value value : module.getValues()) {
                if (value.getValue() instanceof Numbers) {
                    moduleJson.addProperty(value.getName(), (Number)value.getValue());
                }
                else if (value.getValue() instanceof Mode) {
                    moduleJson.addProperty(value.getName(), (String)value.getValue());
                }
                else {
                    if (!(value.getValue() instanceof Option)) {
                        continue;
                    }
                }
            }
            jsonObject.add(module.name, (JsonElement)moduleJson);
        }
        final PrintWriter printWriter = new PrintWriter(this.modules);
        printWriter.println(this.gson.toJson((JsonElement)jsonObject));
        printWriter.flush();
        printWriter.close();
    }
    
    public void loadModules() throws IOException {
        if (!this.modules.exists()) {
            Helper.sendMessage("No Configs Found!");
            return;
        }
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(this.modules));
        final JsonElement jsonElement = (JsonElement)this.gson.fromJson((Reader)bufferedReader, (Class)JsonElement.class);
        if (jsonElement instanceof JsonNull) {
            return;
        }
        final JsonObject jsonObject = (JsonObject)jsonElement;
        for (final Module module : ModuleManager.getModules()) {
            if (!jsonObject.has(module.name)) {
                continue;
            }
            final JsonElement moduleElement = jsonObject.get(module.getName());
            if (moduleElement instanceof JsonNull) {
                continue;
            }
            final JsonObject moduleJson = (JsonObject)moduleElement;
            if (moduleJson.has("state")) {
                module.setState(moduleJson.get("state").getAsBoolean());
            }
            if (moduleJson.has("key")) {
                module.setKey(moduleJson.get("key").getAsInt());
            }
            for (final Value value : module.getValues()) {
                if (!moduleJson.has(value.getName())) {
                    continue;
                }
                if (value.getValue() instanceof Option) {
                    value.setValue(moduleJson.get(value.getName()).getAsBoolean());
                }
                else if (value.getValue() instanceof Mode) {
                    value.setValue(moduleJson.get(value.getName()).getAsString());
                }
                else {
                    if (!(value.getValue() instanceof Numbers)) {
                        continue;
                    }
                    value.setValue(moduleJson.get(value.getName()).getAsDouble());
                }
            }
        }
    }
}
