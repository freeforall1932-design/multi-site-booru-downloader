package com.bisimplex.firebooru.backup;
public class BackupServerItemTypeAdapter extends com.google.gson.TypeAdapter implements com.google.gson.JsonDeserializer {

    public BackupServerItemTypeAdapter()
    {
        return;
    }

    public com.bisimplex.firebooru.backup.BackupServerItemType deserialize(com.google.gson.JsonElement p1, reflect.Type p2, com.google.gson.JsonDeserializationContext p3)
    {
        return com.bisimplex.firebooru.backup.BackupServerItemType.fromInteger(p1.getAsInt());
    }

    public bridge synthetic Object deserialize(com.google.gson.JsonElement p1, reflect.Type p2, com.google.gson.JsonDeserializationContext p3)
    {
        return this.deserialize(p1, p2, p3);
    }

    public com.bisimplex.firebooru.backup.BackupServerItemType read(com.google.gson.stream.JsonReader p3)
    {
        if (p3.peek() != com.google.gson.stream.JsonToken.NUMBER) {
            return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeNone;
        } else {
            return com.bisimplex.firebooru.backup.BackupServerItemType.fromInteger(p3.nextInt());
        }
    }

    public bridge synthetic Object read(com.google.gson.stream.JsonReader p1)
    {
        return this.read(p1);
    }

    public void write(com.google.gson.stream.JsonWriter p3, com.bisimplex.firebooru.backup.BackupServerItemType p4)
    {
        if (p4 != 0) {
            p3.value(((long) p4.getValue()));
            return;
        } else {
            p3.value(((long) com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeNone.getValue()));
            return;
        }
    }

    public bridge synthetic void write(com.google.gson.stream.JsonWriter p1, Object p2)
    {
        this.write(p1, ((com.bisimplex.firebooru.backup.BackupServerItemType) p2));
        return;
    }
}
