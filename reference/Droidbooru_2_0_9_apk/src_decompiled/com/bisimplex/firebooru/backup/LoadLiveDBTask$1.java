package com.bisimplex.firebooru.backup;
 class LoadLiveDBTask$1 implements com.google.gson.JsonDeserializer {
    final synthetic com.bisimplex.firebooru.backup.LoadLiveDBTask this$0;

    LoadLiveDBTask$1(com.bisimplex.firebooru.backup.LoadLiveDBTask p1)
    {
        this.this$0 = p1;
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
}
