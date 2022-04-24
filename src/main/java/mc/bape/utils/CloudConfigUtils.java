package mc.bape.utils;

import java.net.*;
import java.io.*;

public class CloudConfigUtils
{
    public static void downloadConfig(final String filePath, final String link) {
        try {
            final URL url = new URL(link);
            final HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            final int responsecode = urlConnection.getResponseCode();
            if (responsecode == 200) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "GBK"));
                String line;
                while ((line = reader.readLine()) != null) {
                    WriteStringToFile(filePath, line);
                }
            }
            else {
                Helper.sendMessage("Failed get config file. Check your net state, Http code: " + responsecode);
            }
        }
        catch (Exception ex) {}
    }
    
    public static void WriteStringToFile(final String filePath, final String strings) {
        try {
            final File file = new File(filePath);
            final PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.append(strings);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
