import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private Socket clientSocket;
    private int port;
    private String ipAdress;
    private boolean running=true;


    public Client(String ipAdress, int port) {
        this.port=port;
    	this.ipAdress=ipAdress;
        connect();
    }

    public void connect(){

        try {
            clientSocket = new Socket(ipAdress, port);


            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            //Authentifizierung:
            System.out.print("Passwort:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String password = reader.readLine();
            out.writeObject(new AuthMsg(password));
            out.flush();

            //Wenn die AuthMsg mit null als passwort zurückkommt dann ist die Anmeldung fehlgeschlagen
            AuthMsg authMsg = (AuthMsg) in.readObject();
            while(authMsg.getPassword()==null){
                System.out.println("Falsches Passwort");
                System.out.print("Passwort:");
                out.writeObject(new AuthMsg(reader.readLine()));
                out.flush();
                authMsg = (AuthMsg) in.readObject();
            }

            output("verbindung");

            while(running){
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    String input = br.readLine();
                    CalcMsg calcMsg = new CalcMsg(input);

                    out.writeObject(calcMsg);
                    out.flush();

                    try {
                        ResultMsg result = (ResultMsg) in.readObject();
                        System.out.println(result.getValue());
                    } catch (NullPointerException npe) {
                        System.out.println("Falsches Eingabeformat");
                    }


                }catch (SocketException se){
                    System.out.println("Server wurde geschlossen.. ");
                    in.close();
                    out.close();
                    clientSocket.close();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }catch (IOException e) {
            System.out.println("Es existiert kein Server, bzw. kein Port");
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void output(String inhalt){
        if(inhalt.equals("verbindung")){
            System.out.println("Client auf port " + port + " erfolgreich verbunden");
            System.out.println("Du kannst nun Berechnungen im folgenden Format an den Server versenden:");
            System.out.println("-> (zahl1/operator/zahl2) Beispiel: 1+5 oder 3426/54");
            System.out.println("Gib nun deine Berechnungen ein:");
        }

    }

    
}    
