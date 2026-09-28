package com.bisimplex.firebooru.model;
public class ObjectBox {
    private static io.objectbox.BoxStore boxStore;

    public ObjectBox()
    {
        return;
    }

    public static io.objectbox.BoxStore get()
    {
        return com.bisimplex.firebooru.model.ObjectBox.boxStore;
    }

    public static void init(android.content.Context p1)
    {
        com.bisimplex.firebooru.model.ObjectBox.boxStore = com.bisimplex.firebooru.model.MyObjectBox.builder().androidContext(p1.getApplicationContext()).build();
        return;
    }
}
