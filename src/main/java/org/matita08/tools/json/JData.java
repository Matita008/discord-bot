package org.matita08.tools.json;

public interface JData {
    /**
     * Parse a json-formatted string
     *
     * @param s the string to parse
     * @return the remaining string after the parse
     */
    String parse(String s);

    /**
     * Return a json-formatted string
     *
     * @return the json string
     */
    String toJson();

   /* void set(Object o);

    Object get();*/
}
