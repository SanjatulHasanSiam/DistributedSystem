package bank;

public class main {
    public static void main(String[] args) {
        server s=new server(6666);
        s.service();
    }
}
