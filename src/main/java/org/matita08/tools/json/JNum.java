package org.matita08.tools.json;

public class JNum implements JData, JVar {
    private final long num = 0;
    private final int pow = 0;

    @Override
    public String parse(String s) {
        return "";
    }

    @Override
    public String toJson() {
StringBuilder b=new StringBuilder();
if(pow<0){
return num;//TODO think for more than a second on this
}
b.append(num);
for (int i=0;i<pow;i++) {
b.append('0');
}
        return b.toString;
    }

    @Override
    public String getRaw() {
        return toJson();
    }

}
