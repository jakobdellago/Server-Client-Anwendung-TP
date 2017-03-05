import java.io.IOException;

/**
 * Created by stdeljak on 31.01.2017.
 */
public class Calculation {

    /**
     * Erhält einen String und teilt ihn (nach leerzeichen) in Zahl1, Operator und Zahl2 auf.
     * Zurück kommt das Ergebnis aus den Zahlen und dem Operator
     * @param input
     * @return
     */
    public static float getResult(String input) throws IOException {

        
        String[] values = input.split("[-+*/]");


        float zahl1 = Float.valueOf(values[0]);
        float zahl2 = Float.valueOf(values[1]);
        String operator = input.substring(values[0].length(), values[0].length()+1);

        if(operator.equals("+")){
            return zahl1+zahl2;
        } else if (operator.equals("-")){
            return zahl1-zahl2;
        } else if (operator.equals("*")){
            return zahl1*zahl2;
        } else if (operator.equals("/")) {
            return zahl1 / zahl2;
        } else throw new IOException("Falsche Eingabe: Wert konnte nicht berechnet werden!");


    }

}
