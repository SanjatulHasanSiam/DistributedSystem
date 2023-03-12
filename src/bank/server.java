package bank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private account acc;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ServerSocket serve;

    public server( int port) {

        try {
            serve=new ServerSocket(port);
            System.out.println("Server started");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void service(){

        System.out.println("waiting for connection....");
        while (true){
            try {
                Socket socket=serve.accept();
                System.out.println("client connected");
                this.ois=new ObjectInputStream(socket.getInputStream());
                this.oos=new ObjectOutputStream(socket.getOutputStream());
                System.out.println("client says "+(String)ois.readObject());
                this.acc=(account)ois.readObject();
                System.out.println("initial amount "+this.acc.balance());
                Transaction trans=(Transaction) ois.readObject();
                System.out.println(trans.getTransactionType());
                oos.writeObject(performAction(trans));

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
    public Object performAction(Transaction trans){
        if (trans.getTransactionType().equals("deposit")){
           return this.acc.deposit(trans.getTransAmount());
        }
        if (trans.getTransactionType().equals("withdraw")){
            return  this.acc.withdraw(trans.getTransAmount());
        }
        if (trans.getTransactionType().equals("balance")){
            return this.acc.balance();
        }
        else
            return "Invalid input";
    }
}
