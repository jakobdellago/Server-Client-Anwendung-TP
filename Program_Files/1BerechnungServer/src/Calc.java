import java.io.IOException;

/**
 * Created by stdeljak on 31.01.2017.
 */
public class Calc {

    /**
     * Erhält einen String und teilt ihn (nach leerzeichen) in Zahl1, Operator und Zahl2 auf.
     * Zurück kommt das Ergebnis aus den Zahlen und dem Operator
     * @param input
     * @return
     */
    public static float getResult(String input) throws IOException {

        
        String[] values = input.split("[-+*/]");


        float zahl1 = Float.valueOf(values[0]);
        float zahl2 = Float.valueOf(values[2]);
        String operator = values[1];

        if(operator.equals("+")){
            return zahl1+zahl2;
        } else if (operator.equals("-")){
            return zahl1-zahl2;
        } else if (operator.equals("*")){
            return zahl1*zahl2;
        } else if (operator.equals("/")) {
            return zahl1 / zahl2;
        } else throw new IOException("Operator existiert nicht!");


    }

}
