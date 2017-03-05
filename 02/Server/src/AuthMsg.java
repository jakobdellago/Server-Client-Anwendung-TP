/**
 * Created by Kids on 2/21/2017.
 */
public class AuthMsg extends Message {

    private String password;

    public AuthMsg(String password) {
        super("auth");
        this.password=password;
    }

    public String getPassword(){
        return password;
    }


}
