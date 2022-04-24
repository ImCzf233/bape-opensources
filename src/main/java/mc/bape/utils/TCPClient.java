// L Bape, Decompiled by ImCzf233

package mc.bape.utils;

import net.minecraft.client.*;
import java.net.*;
import java.io.*;

public class TCPClient
{
    static int port;
    static InetAddress ip;
    static OutputStream os;
    Socket socket;
    Minecraft mc;
    
    public TCPClient(final int port, final String ip) {
        this.socket = null;
        this.mc = Minecraft.getMinecraft();
        TCPClient.port = port;
        try {
            TCPClient.ip = InetAddress.getByName(ip);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    
    public void SendMessageToServer() {
        try {
            this.socket = new Socket(TCPClient.ip, TCPClient.port);
            (TCPClient.os = this.socket.getOutputStream()).write((this.mc.getSession().getUsername() + " Login in | The obtained qq is ").getBytes());
        }
        catch (Exception e) {
            e.printStackTrace();
            if (TCPClient.os != null) {
                try {
                    TCPClient.os.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            if (TCPClient.os != null) {
                try {
                    TCPClient.os.close();
                }
                catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                }
                catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
    
    static {
        TCPClient.os = null;
    }
}
