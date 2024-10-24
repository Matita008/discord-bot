package org.matita08.tools.json;

public interface JType{
    /**
     Parse a json-formatted string

     @param s the string to parse
     @return the remaining string after the parse
     */
    String parse(String s);
}