package Berkly;

import java.io.*;
import java.net.Socket;

public class BerkeleyClientFour {
    public static void main(String[] args) throws IOException {
        String host = "localhost"; // server hostname
        int port = 1234; // server port number

        // get the local time
        long t1 = System.currentTimeMillis();

        // connect to the server
        Socket socket = new Socket(host, port);
        System.out.println("Connected to server");

        // send the local time to the server
        OutputStream out = socket.getOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        dataOut.writeLong(t1);
        System.out.println("Sent local time: " + t1);

        // get the offset from the server and adjust the clock
        InputStream in = socket.getInputStream();
        DataInputStream dataIn = new DataInputStream(in);
        long offset = dataIn.readLong();
        System.out.println("Received offset: " + offset);
        long t2 = t1 + offset;
        System.out.println("Adjusted time: " + t2);
        System.out.println("Local time before adjustment: " + t1);

        // close connection
        socket.close();
    }
}
