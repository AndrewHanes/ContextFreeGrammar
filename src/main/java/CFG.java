import java.io.*;
import java.util.LinkedList;

/**
 * Created by ahanes on 1/9/16.
 */
public class CFG {
    public static void main(String[] args) throws IOException {
        //setup terminal nodes
        CFGRegister reg = new CFGRegister();
        reg.register("adjectives", buildTerminalList("data/adjectives.txt"), true);
        reg.register("conjunection", buildTerminalList("data/conjunction.txt"), true);
        reg.register("determiner", buildTerminalList("data/determiner.txt"), true);
        reg.register("nouns", buildTerminalList("data/nouns.txt"), true);
        reg.register("prepositions", buildTerminalList("data/prepositions.txt"), true);
        reg.register("pronouns", buildTerminalList("data/pronouns.txt"), true);
        reg.register("verbs", buildTerminalList("data/verbs.txt"), true);
        reg.register("proper_nouns", buildTerminalList("data/propernouns.txt"), true);
        reg.register("aux", buildTerminalList("data/auxiliary.txt"), true);

        /*
        Setup intermediate nodes
        S -> NP VP
        NP -> Pronoun | Propernoun | Determiner Nominal
        Nominal -> Noun Nominal | Noun
        VP -> Verb | Verb NP | Verb NP PP | Verb PP
        PP -> Preposition NP
        */
        reg.register("S", new String[]{"NP VP"}, false);
        reg.register("NP", new String[]{"pronouns", "proper_nouns", "determiner NOM"}, false);
        reg.register("NOM", new String[]{"nouns NOM", "nouns"}, false);
        reg.register("VP", new String[]{"verbs", "verbs NP", "verbs NP PP", "verbs PP"}, false);
        reg.register("PP", new String[]{"prepositions NP"}, false);
        System.out.println(getQuestion(reg));
        for(int i = 0; i < Math.random() * 3 + 2; ++i) {
            System.out.println(getSentence(reg));
        }
    }

    public static String getQuestion(CFGRegister register) {
        return formatQuestion(register.resolve("aux NP VP"));
    }

    public static String getSentence(CFGRegister register) {
        return formatSentence(register.resolve("S"));
    }

    public static String formatSentence(String s) {
        return ((s.substring(0, 1).toUpperCase() + s.substring(1).trim()) + ".").replaceAll(" +", " ");
    }

    public static String formatQuestion(String s) {
        return ((s.substring(0, 1).toUpperCase() + s.substring(1)).trim() + "?").replaceAll(" +", " ");
    }

    public static String[] buildTerminalList(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String tmp;
        LinkedList<String> tokens = new LinkedList<>();
        while((tmp = reader.readLine()) != null) {
            tokens.add(tmp.trim().toLowerCase());
        }
        return tokens.toArray(new String[tokens.size()]);
    }
}
