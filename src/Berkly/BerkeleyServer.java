package Berkly;

import java.io.*;
import java.net.*;

public class BerkeleyServer {
    public static void main(String[] args) throws IOException {
        int port = 1234; // port number to listen on
        int N = 5; // number of clients

        ServerSocket serverSocket = new ServerSocket(port);

        // wait for all clients to connect
        Socket[] clients = new Socket[N];
        for (int i = 0; i < N; i++) {
            clients[i] = serverSocket.accept();
            System.out.println("Client " + (i+1) + " connected");
        }

        // get the time of each client
        long[] t = new long[N];
        for (int i = 0; i < N; i++) {
            InputStream in = clients[i].getInputStream();
            DataInputStream dataIn = new DataInputStream(in);
            t[i] = dataIn.readLong();
            System.out.println("Client " + (i+1) + " time: " + t[i]);
        }

        // calculate the average time
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += t[i];
        }
        long avg = sum / N;
        System.out.println("Average time: " + avg);

        // calculate the offset for each client and send it back
        for (int i = 0; i < N; i++) {
            long offset = avg - t[i];
            OutputStream out = clients[i].getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(out);
            dataOut.writeLong(offset);
            System.out.println("Client " + (i+1) + " offset: " + offset);
        }

        // close connections
        for (int i = 0; i < N; i++) {
            clients[i].close();
        }
        serverSocket.close();
    }
}
