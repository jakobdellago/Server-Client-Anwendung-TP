import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private boolean running=true;

    /*
    Ru
     */
    public Client(int port) {
    	connect("127.0.0.1", port);
    }

    public void connect(String ipAdress, int port){

        try {
            clientSocket = new Socket(ipAdress, port);
            System.out.println("Client auf port " + port + " verbunden");
            System.out.println("Du kannst nun Berechnungen im folgenden Format an den Server versenden");
            System.out.println("-> (zahl1/operator/zahl2) Beispiel: 1+5 oder 3426/54");
            System.out.println("Gib nun deine Berechnungen ein:");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader systemInput = new BufferedReader(isr);

            while(running){
                try {
                    String input = systemInput.readLine();
                    out.println(input);
                    System.out.println(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
            return;
        }


    }
    
}    
