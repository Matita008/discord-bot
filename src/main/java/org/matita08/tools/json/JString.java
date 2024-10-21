package org.matita08.tools.json;

import static org.matita08.tools.Utilities.format;

@SuppressWarnings("unused")
public class JString implements JData, JVar {
    private String data = "";

    public JString() {}

    @Override
    public String parse(String s) {
        data = format(s);
        return s.substring(data.length() + 1);
    }

    @Override
    public String toJson() {
        return '"' + data + '"';
    }

    @Override
    public String getRaw() {
        return data;
    }

    @Override
    public String toString() {
        return data;
    }
}
