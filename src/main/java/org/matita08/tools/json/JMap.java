package org.matita08.tools.json;

import java.util.*;

public class JMap implements JData {
    HashMap<JVar, JData> data = new HashMap<>();

    @Override
    public String parse(String s) {
        return "";
    }

    @Override
    public String toJson() {
        return "";
    }

    /**
     * IMPORTANT!
     *
     * @param d the HashMap to replace the stored data with
     */
    public void set(HashMap<String, JData> d) {
        data = d;
    }

    public void add(String key, JData value) {
        data.put(key, value);
    }

    public void addall(HashMap<String, JData> h) {
        data.putAll(h);
    }

    /**
     * @return the HashMap of stored data
     */
    public HashMap<String, JData> get() {
        return data;
    }
}
