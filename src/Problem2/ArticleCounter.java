package Problem2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ArticleCounter {



    public static void main(String[] args) throws IOException {
        System.out.println("Article Client started");
        Socket socket=new Socket("127.0.0.1",22222);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Write a sentence to find Article in it: ");
        Scanner sc=new Scanner(System.in);
        String message=sc.nextLine();

        String[] words = message.split("\\s+");
        int counter=0;
        for (String word : words) {
            if (word.equalsIgnoreCase("a") || word.equalsIgnoreCase("an") || word.equalsIgnoreCase("the")) {
                System.out.println("Article found: " + word);
                counter++;
            }
        }

        System.out.println(counter);
        message=message+" has = "+counter+" articles";
        oos.writeObject(message);
        counter=0;
    }
}
