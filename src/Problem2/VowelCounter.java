package Problem2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class VowelCounter {
    public static void main(String[] args) throws IOException {
        System.out.println("Vowel Client started");
        Socket socket=new Socket("127.0.0.1",22222);
        System.out.println("Client Connected...");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Write a sentence to count number of words starting with vowel: ");
        Scanner sc=new Scanner(System.in);
        String message=sc.nextLine();

        int counter=countWordsStartingWithVowel(message);
        System.out.println(counter);
        message=message+" has = "+counter+" words starting with vowels";
        oos.writeObject(message);
        counter=0;
    }
    public static int countWordsStartingWithVowel(String input) {
        String[] words = input.split("\\s+");
        int count = 0;
        for (String word : words) {

            char firstChar = Character.toLowerCase(word.charAt(0));
            if (firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u') {
                System.out.println("Word stating with vowel: "+word);
                count++;
            }
        }

        return count;
    }
}

