import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private boolean serverOpen = true;

    /*
    Konstruktor: Ruft die Funktion "startServer" auf. Zu übergeben ist der Port, auf welchem der socket nachher laufen soll
     */
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server läuft auf port " + port);
            startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startServer() {

        Socket socket = null;

        try {
            socket = serverSocket.accept();
            System.out.println("Client hat sich verbunden");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String s = null;
            do{
                s = in.readLine();
                System.out.println("Folgende Berechnung empfangen: " + s + " = " + Calc.getResult(s));
                out.println(Calc.getResult(s));

            }while(s!=null);


            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}