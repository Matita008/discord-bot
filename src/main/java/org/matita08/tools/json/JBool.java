package org.matita08.tools.json;

@SuppressWarnings("unused")
public class JBool implements JData {
    private boolean val;

    public JBool() {
        val = false;
    }

    public JBool(boolean value) {
        val = value;
    }

    public void set(boolean value) {
        val = value;
    }

    public boolean get() {
        return val;
    }

    @Override
    public String parse(String s) {
        if(s.startsWith("true")) val = true;
        else if(s.startsWith("false")) val = false;
        else throw new RuntimeException(s + " doesn't start with a valid value\n accepted value: true,false");
        return s.substring(val ? 4 : 5);
    }

    @Override
    public String toJson() {
        return val ? "true" : "false";
    }


    @Override
    public String toString() {
        return '"' + toJson() + '"';
    }

}
