package UDPTelusko;

import java.io.IOException;
import java.net.*;

public class UDPClientTwo {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds=new DatagramSocket();
        System.out.println("Client active");
        int i=9;
        byte[] b=String.valueOf(i).getBytes();

        InetAddress ia=InetAddress.getLocalHost();
        DatagramPacket dp=new DatagramPacket(b,b.length,ia,9999);
        ds.send(dp);
        System.out.println("Message is sent to server....");


        byte[] b1=new byte[1024];
        DatagramPacket dp1=new DatagramPacket(b1,b1.length);
        ds.receive(dp1);

        String str=new String(dp1.getData(),0, dp1.getLength());
        System.out.println("Received from server: "+str);
    }
}
