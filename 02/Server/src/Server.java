import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.System.out;

public class Server {

    private ServerSocket serverSocket;
    private boolean open = true;
    private ArrayList<Connection> connectionsArrayList = new ArrayList<>();

    /**
     * Konstruktor: Ruft die Funktion "runServer" auf. Zu übergeben ist der Port, auf welchem der socket nachher laufen soll
     **/
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            out.println("Server läuft auf port " + port);
            runServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void runServer() {
        try {
            while (open) {
                Socket s = serverSocket.accept();
                Connection c = new Connection(s);
                Thread t = new Thread(c);
                t.start();
                connectionsArrayList.add(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}