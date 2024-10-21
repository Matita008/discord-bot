package org.matita08.tools.json;

import static java.lang.Math.abs;

@SuppressWarnings("unused")
public class JNum implements JData, JVar {
    private long num = 0;
    private int pow = 0;

    public JNum() {}

    @Override
    public String parse(String s) {
        return "";
    }

    @Override
    public String toJson() {
        if(pow < 0) {
            return "0." + "0".repeat(abs(pow) - 1) + num;
        }
        return num + "0".repeat(pow);
    }

    @Override
    public String getRaw() {
        return toJson();
    }

    public void set(double n) {
        num = (long) (n * 100);
        pow = -2;
    }
}
