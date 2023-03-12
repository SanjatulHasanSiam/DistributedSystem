package UDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.Scanner;

public class Client {
    public static void main(String args[]) throws Exception
    {
        Scanner sc =new Scanner(System.in);

        DatagramSocket clientSocket = new DatagramSocket();
        //Client Socket is created

        InetAddress IPAddress = InetAddress.getByName("localhost");
        //Gets the IP Address

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentence = sc.nextLine();

        sendData = sentence.getBytes();
        //sends data

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();
    }
}

