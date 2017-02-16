import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.in;
import static java.lang.System.out;

public class Server {

    private ServerSocket serverSocket;
    private boolean serverOpen = true;

    /**
    Konstruktor: Ruft die Funktion "runServer" auf. Zu übergeben ist der Port, auf welchem der socket nachher laufen soll
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

        Socket socket = null;

        try {
            socket = serverSocket.accept();
            out.println("Client hat sich verbunden");

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());


            Message input = null;
            do{
                input = (Message) in.readObject();

                //Reagiert auf einen input vom typ 'calc'
                if(input.getType().equals("calc")) {
                    CalcMsg calcMsg = (CalcMsg) input;
                    Float result = Calculation.getResult(calcMsg.getInput());
                    ResultMsg output = new ResultMsg(result);
                    out.writeObject(output);
                }


            }while(!input.equals(null));



            in.close();
            out.close();
            socket.close();

        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

/*
    public void sendCalc(){
        String s = null;
        do {
            s = in.readLine();
            try{
                Float result = Calculation.getResult(s);
                out.println("Folgende Berechnung empfangen: " + s + " = " + result);
                out.println(result);
            }catch(NumberFormatException nfe) {
                out.println("Warnung: Falsche Eingabeparameter empfangen");
                out.println("Falsche Parameter bei der Eingabe");
            }catch(ArrayIndexOutOfBoundsException aioobe){
                out.println("Warnung: Falsche Eingabeparameter empfangen");
                out.println("Falsche Parameter bei der Eingabe (Fehlender Operator, fehlende Zahl?)");
            }
        } while (s != null);
    }
*/

}