package com.bisimplex.firebooru.danbooru;
public class Flatten {

    public Flatten()
    {
        return;
    }

    public static String flattenHTML(String p2)
    {
        if (p2 != null) {
            return p2.replaceAll("\\<.*?>", "");
        } else {
            return 0;
        }
    }
}
