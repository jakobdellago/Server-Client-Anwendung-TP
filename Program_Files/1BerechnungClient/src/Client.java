import java.io.*;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private int port;
    private String ipAdress;
    private boolean running=true;

    /*
    Ru
     */
    public Client(String ipAdress, int port) {
        this.port=port;
    	this.ipAdress=ipAdress;
        connect();
    }

    public void connect(){

        try {
            clientSocket = new Socket(ipAdress, port);

            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());


            output("verbindung");

            while(running){
                try {
                    String input = systemInput.readLine();
                    CalcMsg calcMsg = new CalcMsg(input);

                    out.writeObject(calcMsg);

                    in.readObject();


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
            return;
        }


    }

    public void output(String inhalt){
        if(inhalt.equals("verbindung")){
            System.out.println("Client auf port " + port + " erfolgreich verbunden");
            System.out.println("Du kannst nun Berechnungen im folgenden Format an den Server versenden");
            System.out.println("-> (zahl1/operator/zahl2) Beispiel: 1+5 oder 3426/54");
            System.out.println("Gib nun deine Berechnungen ein:");
        }
    }

    public float calc(){

    }

    
}    
