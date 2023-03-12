package even_odd_TCP;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket=new ServerSocket(22222);
        System.out.println("Server Started");
        while(true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {

                //read from client
                Object cMsg = ois.readObject();
                System.out.println("From client " + (String) cMsg);

                String[] messages = ((String) cMsg).split(" ");
                if (Objects.equals(messages[0], "Even")){
                    int num=Integer.parseInt(messages[1]);
                    boolean isEven=isEven(num);
                    if (isEven){
                        oos.writeObject("Client Even your request for an even is accepted");
                        System.out.println("Request is accepted for client Even");
                    }
                    else{
                        oos.writeObject("Client even your request for an odd is not accepted");
                        System.out.println("Request is not accepted for Client Even");
                    }

                }

                if (Objects.equals(messages[0], "Odd")){
                    int num=Integer.parseInt(messages[1]);
                    boolean isEven=isEven(num);
                    if (!isEven){
                        oos.writeObject("Client odd your request for an odd is accepted");
                        System.out.println("Request is accepted for client Odd");
                    }
                    else{
                        oos.writeObject("Client odd your request for even is not accepted");
                        System.out.println("Request is not accepted for client Odd");
                    }

                }

                String serverMessage = (String) cMsg;
                serverMessage = serverMessage.toUpperCase();

                //send to client
                oos.writeObject(serverMessage);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    private static boolean isEven(int num){
        if(num % 2 == 0)
           return true;
        return false;
    }
}