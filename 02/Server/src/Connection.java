import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Kids on 2/21/2017.
 */
public class Connection implements Runnable{

    private Socket socket;

    public Connection(Socket socket){
        this.socket=socket;
    }


    //Kümmert sich um die existierenden Verbindungen
    @Override
    public void run() {
        System.out.println("Client hat sich verbunden");
        ObjectOutputStream out=null;
        ObjectInputStream in=null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            Message input = null;

            //Authentifizierung:
            String password = "1234";
            AuthMsg authMsg = (AuthMsg) in.readObject();
            while(!authMsg.getPassword().equals(password)){
                out.writeObject(new AuthMsg(null));
                authMsg = (AuthMsg) in.readObject();
            }

            out.writeObject(authMsg);
            out.flush();


            do {
                input = (Message) in.readObject();
                //Reagiert auf eine Message vom typ 'calc'
                //Schickt null durch den Outputstream falls die Eingabe nicht dem richtigem Format entspricht
                if (input.getType().equals("calc")) {
                    CalcMsg calcMsg = (CalcMsg) input;
                    try {
                        Float result = Calculation.getResult(calcMsg.getInput());
                        ResultMsg output = new ResultMsg(result);
                        out.writeObject(output);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Falsches Eingabeformat(numberFormatException)");
                        out.writeObject(null);
                    } catch (ArrayIndexOutOfBoundsException aioobe) {
                        System.out.println("Falsches Eingabeformat, Zeichen oder Operator vergessen(ArrayIndexOutOfBoundsException)");
                        out.writeObject(null);
                    }
                }
            } while (!input.equals(null));

        }catch (SocketException se){
            System.out.println("Client hat sich abgemeldet");
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
