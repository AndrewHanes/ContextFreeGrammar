import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahanes on 1/9/16.
 */
public class CFGRegister {
    private Map<String, TerminalPair> transitions;

    private class TerminalPair {
        public String[] choices;
        public boolean isTerminal;

        public TerminalPair(String[] choices, boolean isTerminal) {
            this.choices = choices;
            this.isTerminal = isTerminal;
        }

        public String getRandom() {
            return this.choices[(int) (Math.random() * this.choices.length)];
        }
    }

    public CFGRegister() {
        this.transitions = new HashMap<>();
    }

    public void register(String symbol, String[] choices, boolean isTerminal) {
        if (transitions.containsKey(symbol)) {
            throw new RuntimeException("Cannot overwrite symbols");
        }
        this.transitions.put(symbol, new TerminalPair(choices, isTerminal));
    }

    public String resolve(String symbols) {
        String total = "";
        for (String symbol : symbols.split(" ")) {
            TerminalPair choices = this.transitions.get(symbol);
            String val = choices.getRandom();
            if (choices.isTerminal) {
                //System.err.printf("%s -> %s\n", symbol, val);
                total += val + " ";
            } else {
                String[] s = val.split(" ");
                String tmp = "";
                for (String sym : s) {
                    tmp += this.resolve(sym) + " ";
                }
                total += tmp + " ";
            }
        }
        return total;
    }
}
