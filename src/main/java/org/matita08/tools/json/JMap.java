package org.matita08.tools.json;

import java.util.HashMap;

import static org.matita08.tools.json.Scan.*;

@SuppressWarnings("unused")
public class JMap implements JData {
    private HashMap<JVar, JData> data = new HashMap<>();

    public JMap() {}

    @Override
    public String parse(String s) {
        do {
            s = removeSpace(s);
            if(!s.startsWith("{")) throw new IllegalArgumentException(s + " doesn't start with '{'");
            s = s.substring(1);
            JVar key = parseVar(removeSpace(s));
            s = removeSpace(key.parse(s));
            if(!s.startsWith(":")) throw new IllegalArgumentException("Expected ':' between key and value");
            JData value = parseData(s);
            s = removeSpace(value.parse(s));
            put(key, value);
        } while (!s.startsWith("}"));
        return s;
    }

    @Override
    public String toJson() {
        if(data.isEmpty()) return "{}";
        StringBuilder b = new StringBuilder("{");
        data.forEach((k, v)->{
            b.append('\n');
            b.append(k.getRaw());
            b.append('=');
            b.append(v.toJson());
            b.append(',');
        });
        return b.substring(0, b.length() - 1) + "}";
    }

    public void put(JVar key, JData value) {
        data.put(key, value);
    }

    /**
     IMPORTANT! it will override the saved data

     @param d the HashMap to replace the stored data with
     */
    public void set(HashMap<JVar, JData> d) {
        data = d;
    }

    public void reset() {
        data = new HashMap<>();
    }

    public void add(JVar key, JData value) {
        data.put(key, value);
    }

    public void addall(HashMap<JVar, JData> h) {
        data.putAll(h);
    }

    /**
     @return the HashMap of stored data
     */
    public HashMap<JVar, JData> get() {
        return data;
    }
}
