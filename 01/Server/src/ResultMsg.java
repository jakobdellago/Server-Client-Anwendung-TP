/**
 * Created by stdeljak on 13.02.2017.
 */
public class ResultMsg extends Message {

    private Float value;

    public ResultMsg(Float value){
        super("result");
        this.value=value;

    }

    public Float getValue(){
        return this.value;
    }

}
