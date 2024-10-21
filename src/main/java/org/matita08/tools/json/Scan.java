package org.matita08.tools.json;

@SuppressWarnings("unused")
public class Scan {
    public static JVar parseVar(String s) {
        if(s.startsWith("\"")) return new JString();
        else return new JNum();
    }

    public static JData parseData(String s) {
        if(s.startsWith("{")) return new JMap();
        else if(s.startsWith("[")) return new JArray();
        else if(s.startsWith("\"")) return new JString();
        else if(s.startsWith("true") || s.startsWith("false")) return new JBool();
        else return new JNum();
    }

    public static String removeSpace(String s) {
        while (s.startsWith(" ") || s.startsWith("\n") || s.startsWith("\t")) s = s.substring(1);
        return s;
    }
}
