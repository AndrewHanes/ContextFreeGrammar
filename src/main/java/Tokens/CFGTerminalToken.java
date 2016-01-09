package Tokens;

/**
 * Created by ahanes on 1/9/16.
 */
public class CFGTerminalToken implements CFGToken {
    private String rep;
    public CFGTerminalToken(String s) {
        this.rep = s;
    }
    public CFGToken resolve() {
        return this;
    }

    @Override
    public String toString() {
        return this.rep;
    }
}
