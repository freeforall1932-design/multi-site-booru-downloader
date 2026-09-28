package com.bisimplex.firebooru.backup;
public class DanbooruContentTypeAdapter extends com.google.gson.TypeAdapter implements com.google.gson.JsonDeserializer {

    public DanbooruContentTypeAdapter()
    {
        return;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPostContentType deserialize(com.google.gson.JsonElement p1, reflect.Type p2, com.google.gson.JsonDeserializationContext p3)
    {
        return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.fromInteger(p1.getAsInt());
    }

    public bridge synthetic Object deserialize(com.google.gson.JsonElement p1, reflect.Type p2, com.google.gson.JsonDeserializationContext p3)
    {
        return this.deserialize(p1, p2, p3);
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPostContentType read(com.google.gson.stream.JsonReader p1)
    {
        return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Static;
    }

    public bridge synthetic Object read(com.google.gson.stream.JsonReader p1)
    {
        return this.read(p1);
    }

    public void write(com.google.gson.stream.JsonWriter p3, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p4)
    {
        p3.value(0);
        return;
    }

    public bridge synthetic void write(com.google.gson.stream.JsonWriter p1, Object p2)
    {
        this.write(p1, ((com.bisimplex.firebooru.danbooru.DanbooruPostContentType) p2));
        return;
    }
}
