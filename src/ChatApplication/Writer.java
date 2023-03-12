
package ChatApplication;

import java.util.Scanner;


public class Writer implements Runnable{
    public NetworkConnection netConnection;
    
    public Writer(NetworkConnection nc){
        netConnection=nc;
    }

    
    @Override
    public void run() {
        
        Data data=new Data();
        
        while(true){
            
            Scanner in=new Scanner(System.in);
            
            data.message=in.nextLine();            
            try{
                netConnection.write(data.clone());
            }
            catch(Exception ex){
                System.out.println("sending failed");
            }
        }
    }
    
}