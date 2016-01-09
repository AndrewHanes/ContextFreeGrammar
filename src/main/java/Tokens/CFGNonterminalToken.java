package Tokens;

import java.util.Arrays;

/**
 * Created by ahanes on 1/9/16.
 */
public class CFGNonterminalToken implements CFGToken {
    private CFGToken[] outputs;
    public CFGNonterminalToken(CFGToken[] outputs) {
        this.outputs = Arrays.copyOf(outputs, outputs.length);
    }
    public CFGToken resolve() {
        int index = (int) (Math.random() * this.outputs.length);
        return this.outputs[index].resolve();
    }
}
