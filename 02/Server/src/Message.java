import java.io.Serializable;

/**
 * Created by stdeljak on 08.02.2017.
 */
public abstract class Message implements Serializable {

    private String type;

    public Message(String type){
        this.type=type;
    }

    public String getType(){
        return this.type;
    }

}
