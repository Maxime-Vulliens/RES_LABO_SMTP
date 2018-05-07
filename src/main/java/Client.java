import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Maxime on 06.05.18.
 */
public class Client {

    private Socket socket;
    private int port;

    private String debug;
    private DataInputStream is;
    private DataOutputStream os;

    public Client(int port) {
        this.port = port;
    }

    // Open socket connection and stream with the server
    // Also initiate communication with SMTP server
    public void connect(){

        try {
            socket = new Socket("127.0.0.1",port);

            os = new DataOutputStream(socket.getOutputStream());
            is = new DataInputStream(socket.getInputStream());

            System.out.println("Send helo");
            os.writeBytes("EHLO mockmock\r\n");
            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // Function use to send a mail through SMTP protocol
    public void sendMessage(Mail msg){
        try {
            os.writeBytes("MAIL FROM: " + msg.getSender() + "\r\n");
            os.flush();

            os.writeBytes("RCPT TO: " + msg.getReceiver() + "\r\n");
            os.flush();

            os.writeBytes("DATA\r\n");
            os.flush();

            os.writeBytes("From: " + msg.getSender() + "\r\n" +
                    "To: " + msg.getReceiver() + "\r\n" +
                    "Subject: labo\r\n\r\n" +
                    msg.getMessage() + "\r\n.\r\n");
            os.flush();

            debug = getStringFromInputStream(is);
            System.out.println("debug : " + debug);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    // disconnecct the server
    public void diconnect(){

        System.out.println("close connection");
        try {
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read a string from input stream
    protected String getStringFromInputStream(InputStream is){

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder out = new StringBuilder();
        String line;
        try {
            if ((line = reader.readLine()) != null) {
                out.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
