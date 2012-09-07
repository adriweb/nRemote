import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaIRC {

    ServerListener listener;
    ServerSender sender;
    String channel;

    public JavaIRC(String nick, String chan, String host, int port) throws Exception {
        Socket ircSocket;
        channel = chan;

        try {
            SocketAddress addr = new InetSocketAddress("127.0.0.1", 1080);
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
            ircSocket = new Socket(proxy);
            InetSocketAddress final_addr = new InetSocketAddress(host, port);
            ircSocket.connect(final_addr);
        } catch (Exception e) {
            ircSocket = new Socket(host, port);
        }

        listener = new ServerListener(this, ircSocket);
        (new Thread(listener)).start();

        System.out.println("Listener started!");

        sender = new ServerSender(this, ircSocket);
        (new Thread(sender)).start();

        System.out.println("Sender started!");

        sender.sendSomething("NICK " + nick);
        sender.sendSomething("USER " + nick + " 0 * " + nick);

        Thread.sleep(500);
        sender.sendSomething("JOIN " + chan);
    }

    public void sendMessage(String msg) {
        System.out.println("sending message : " + msg);
        sender.sendMsg(channel, msg);
    }

    public void pingResponse(String input) {
        sender.sendSomething("PONG " + input.substring(5));
    }
}

class ServerListener implements Runnable {

    Socket ircSocket;
    String serverAnswer = null;
    BufferedReader in = null;
    JavaIRC parent;

    ServerListener(JavaIRC parentClass, Socket irc) throws IOException {
        ircSocket = irc;
        parent = parentClass;
        in = new BufferedReader(new InputStreamReader(irc.getInputStream()));
    }

    public void run() {
        while (true) {
            try {
                serverAnswer = in.readLine();
                if (serverAnswer != null) {
                    if (serverAnswer.toLowerCase().startsWith("ping")) {
                        // We must respond to PINGs to avoid being disconnected.
                        parent.pingResponse(serverAnswer);
                    } else {
                        System.out.println(serverAnswer);
                    }
                }
            } catch (IOException ex) {
                System.out.println("-------------Can't read line...");
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class ServerSender implements Runnable {

    Socket ircSocket;
    String serverCommand = null;
    BufferedWriter writer = null;
    BufferedReader reader = null;
    JavaIRC parent;

    ServerSender(JavaIRC parentClass, Socket irc) throws IOException {
        ircSocket = irc;
        parent = parentClass;
        writer = new BufferedWriter(new OutputStreamWriter(irc.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(System.in));

        writer.flush();
    }

    public void run() {
        while (true) {
            try {
                serverCommand = reader.readLine();
                if (serverCommand != null) {
                    writer.write(serverCommand + "\n");
                    writer.flush();
                }
            } catch (IOException e) {
                System.out.println("Server fed up");
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Sleep failed!");
            }
        }
    }

    public void sendMsg(String who, String msg) {
        try {
            writer.write("PRIVMSG " + who + " " + msg + "\r\n");
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendSomething(String something) {
        try {
            writer.write(something + "\r\n");
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
