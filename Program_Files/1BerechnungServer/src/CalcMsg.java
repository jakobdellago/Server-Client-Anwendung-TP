/**
 * Created by stdeljak on 08.02.2017.
 */
public class CalcMsg extends Message {

    private String input;

    public CalcMsg(String input) {
        super("calc");
        this.input=input;
    }

    public String getInput() {
        return input;
    }

}
