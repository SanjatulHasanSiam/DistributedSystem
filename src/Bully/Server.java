package Bully;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IOException {
        ServerSocket serverSocket = new ServerSocket(3456);
        List<String> idList = new ArrayList<>();
        List<ObjectOutputStream> oosList = new ArrayList<>();
        List<Socket> socketList = new ArrayList<>();

        ObjectOutputStream oos = null;
        ObjectInputStream ois;

        for (int i = 0; i < 5; i++){
            Socket server = serverSocket.accept();

            oos = new ObjectOutputStream(server.getOutputStream());
            ois = new ObjectInputStream(server.getInputStream());

            String message = (String) ois.readObject();
            System.out.println("ID : " + message);

            if (message.equals("NO")) {
                oos.writeObject("Client is dead");
            } else {
                idList.add(message);
                oosList.add(oos);
                socketList.add(server);
            }
        }

        int max = 0;
        for (String id : idList) {
            int givenID = Integer.parseInt(id);

            if (givenID > max) {
                max = givenID;
            }
        }

       //System.out.println("Election Initiated By : " + socketList.get(0).getLocalAddress().getHostAddress());
        for (ObjectOutputStream os : oosList) {

            os.writeObject("New Coordinator: " + max+"\nElection Initiated By : " +idList.get(0));
        }
    }
}