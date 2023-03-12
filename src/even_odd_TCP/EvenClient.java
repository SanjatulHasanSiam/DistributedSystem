package even_odd_TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EvenClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Client Even started");
        Socket socket=new Socket("127.0.0.1",22222);
        System.out.println("Client Even Connected...");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        System.out.println("Write your message Even: ");
        Scanner sc=new Scanner(System.in);
        String message=sc.nextLine();
        message="Even "+message;
        System.out.println();

        //sent message to server....
        oos.writeObject(message);

        try {
            //received from server
            Object reply=ois.readObject();
            System.out.println("From server: "+(String)reply);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
