package mc.bape.manager;

import java.util.*;
import java.io.*;
import mc.bape.vapu.*;

public class ConfigManager
{
    public static String ConfigFile;
    private static final File dir;
    public static ArrayList<String> Configs;
    
    public static void getFiles(final String path) {
        final File file = new File(path);
        if (file.isDirectory()) {
            final File[] files = file.listFiles();
            for (int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    ConfigManager.Configs.add(files[i].getName());
                }
            }
        }
    }
    
    public static File getConfigFile(final String name) {
        final File file = new File(ConfigManager.dir, ConfigManager.ConfigFile + String.format("%s.txt", name));
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException ex) {}
        }
        return file;
    }
    
    public static void init() {
        if (!ConfigManager.dir.exists()) {
            ConfigManager.dir.mkdir();
        }
    }
    
    public static List<String> read(final String file) {
        List<String> out = null;
        try {
            out = new ArrayList<String>();
            final File d = new File(ConfigManager.dir + ConfigManager.ConfigFile);
            final File f = new File(d, file);
            if (!d.exists()) {
                d.mkdir();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            Throwable t = null;
            try {
                final FileInputStream fis = new FileInputStream(f);
                try {
                    final InputStreamReader isr = new InputStreamReader(fis);
                    try {
                        final BufferedReader br = new BufferedReader(isr);
                        try {
                            String line = "";
                            while ((line = br.readLine()) != null) {
                                out.add(line);
                            }
                        }
                        finally {
                            if (br != null) {
                                br.close();
                            }
                        }
                        if (isr != null) {
                            isr.close();
                        }
                    }
                    finally {
                        if (t == null) {
                            final Throwable t2 = t = null;
                        }
                        else {
                            final Throwable t2 = null;
                            if (t != t2) {
                                t.addSuppressed(t2);
                            }
                        }
                        if (isr != null) {
                            isr.close();
                        }
                    }
                    if (fis != null) {
                        fis.close();
                        return out;
                    }
                }
                finally {
                    if (t == null) {
                        final Throwable t3 = t = null;
                    }
                    else {
                        final Throwable t3 = null;
                        if (t != t3) {
                            t.addSuppressed(t3);
                        }
                    }
                    if (fis != null) {
                        fis.close();
                    }
                }
            }
            finally {
                if (t == null) {
                    final Throwable t4 = t = null;
                }
                else {
                    final Throwable t4 = null;
                    if (t != t4) {
                        t.addSuppressed(t4);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
    
    public static void save(final String file, final String content, final boolean append) {
        try {
            if (!ConfigManager.dir.exists()) {
                ConfigManager.dir.mkdir();
            }
            final File d = new File(ConfigManager.dir + ConfigManager.ConfigFile);
            final File f = new File(ConfigManager.dir + ConfigManager.ConfigFile, file);
            if (!d.exists()) {
                d.mkdir();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            Throwable t = null;
            try {
                final FileWriter writer = new FileWriter(f, append);
                try {
                    writer.write(content);
                }
                finally {
                    if (writer != null) {
                        writer.close();
                    }
                }
            }
            finally {
                if (t == null) {
                    final Throwable t2 = t = null;
                }
                else {
                    final Throwable t2 = null;
                    if (t != t2) {
                        t.addSuppressed(t2);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveroot(final String file, final String content, final boolean append) {
        try {
            if (!ConfigManager.dir.exists()) {
                ConfigManager.dir.mkdir();
            }
            final File f = new File(ConfigManager.dir, file);
            if (!f.exists()) {
                f.createNewFile();
            }
            Throwable t = null;
            try {
                final FileWriter writer = new FileWriter(f, append);
                try {
                    writer.write(content);
                }
                finally {
                    if (writer != null) {
                        writer.close();
                    }
                }
            }
            finally {
                if (t == null) {
                    final Throwable t2 = t = null;
                }
                else {
                    final Throwable t2 = null;
                    if (t != t2) {
                        t.addSuppressed(t2);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static {
        ConfigManager.ConfigFile = "/" + Client.CLIENT_CONFIG + "/";
        dir = new File(System.getenv("APPDATA"), Client.CLIENT_NAME);
        Client.INSTANCE.getClass();
        ConfigManager.Configs = new ArrayList<String>();
    }
}
