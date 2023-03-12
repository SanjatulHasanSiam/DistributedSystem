package bank;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
    private Socket clientSocket;
    private account acc;
private  int clientId;
    public client( int clientID,int port,double initialAmount) {
        this.clientId=clientID;
        this.acc = new account(initialAmount);
        System.out.println("account created");
        try {
            this.clientSocket = new Socket("127.0.0.1",port);
            System.out.println("Client "+clientID+" connected");
            ObjectOutputStream oos=new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(clientSocket.getInputStream());

            System.out.println("HHHHHHHHHH");
            oos.writeObject("hello");
            oos.writeObject((account)acc);
            oos.writeObject(transaction());
           String fromServer=(String) ois.readObject();
            System.out.println("Server says to client : " +this.clientId+"  "+fromServer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Object transaction(){
        System.out.println("Enter transaction type: ");
        Scanner sc=new Scanner(System.in);
        String type= sc.nextLine();
        double amount=0;
        if(!type.equals("balance")){

            System.out.println("Enter transaction amount:");
          amount= sc.nextDouble();

        }

      Transaction trans=new Transaction(type,amount) ;
        return trans;
    }

    public static void main(String[] args) {
        client c=new client(1,6666,1000);
    }
}
