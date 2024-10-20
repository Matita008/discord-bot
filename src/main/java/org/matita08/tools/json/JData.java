package org.matita08.tools.json;

public interface JData {
    static String format(String s) {
        boolean skip = false;
        StringBuilder r = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (skip) {
                skip = false;
                r = new StringBuilder(switch (c) {
                    case '"' -> "\"";
                    case 'n' -> "\n";
                    case 't' -> "\t";
                    case '\\' -> "\\";
                    case 'b' -> "\b";
                    case 'f' -> "\f";
                    case 'r' -> "\r";
                    default -> "\\" + c;
                });
                continue;
            }
            if (c == '\\') {
                skip = true;
            } else if (c == '"') {
                break;
            }
            r.append(c);
        }
        return r.toString();
    }

    void load(String s);
}
