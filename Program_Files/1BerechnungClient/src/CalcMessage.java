/**
 * Created by stdeljak on 31.01.2017.
 * Erhält einen String und teilt ihn in Zahl1, Operator und Zahl2 auf
 */
public class CalcMessage {

    private String[] chars;

    public CalcMessage(String s){
        chars = s.split(" ");
    }

    public String[] getArray(){
        if(chars.length==3){
            return chars;
        } else {
            return null;
        }
    }




}
