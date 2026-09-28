package com.bisimplex.firebooru.network;
public class ParserGelbooruJSON extends com.bisimplex.firebooru.network.ParserPosts {

    public ParserGelbooruJSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    protected void parse(com.google.gson.JsonElement p3)
    {
        if (p3 != null) {
            if (!p3.isJsonObject()) {
                if (!p3.isJsonArray()) {
                    if ((p3.isJsonPrimitive()) && (!p3.isJsonNull())) {
                        this.setServerMessage(p3.getAsString());
                    }
                } else {
                    super.parse(p3.getAsJsonArray());
                    return;
                }
            } else {
                String v3_3 = p3.getAsJsonObject();
                if (v3_3.has("post")) {
                    super.parse(v3_3.get("post").getAsJsonArray());
                    return;
                }
            }
        }
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p8)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v0_1.setPostIdAndUrl(com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "id", ""), this.provider.getServerDescription().getUrl(), this.provider.getPostFormat());
        v0_1.setScore(com.bisimplex.firebooru.network.ParserGelbooruJSON.optInt(p8, "score", 0));
        v0_1.setMd5(com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "md5", ""));
        if (android.text.TextUtils.isEmpty(v0_1.getMd5())) {
            v0_1.setMd5(com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "hash", ""));
        }
        v0_1.setRating(com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "rating", ""));
        v0_1.setSource(com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "source", ""));
        v0_1.setAuthor(com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "owner", ""));
        v0_1.setTags(com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "tags", ""));
        v0_1.setHas_notes(com.bisimplex.firebooru.network.ParserGelbooruJSON.optBoolean(p8, "has_notes", 0));
        v0_1.setHas_children(com.bisimplex.firebooru.network.ParserGelbooruJSON.optBoolean(p8, "has_children", 0));
        v0_1.setCreated_at(com.bisimplex.firebooru.network.ParserGelbooruJSON.getDateIfExist(p8, "created_at", "E MMM d HH:mm:ss Z yyyy"));
        v0_1.setParent_id(com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "parent_id", ""));
        if ((!android.text.TextUtils.isEmpty(v0_1.getTags())) && (v0_1.getTags().contains("#"))) {
            v0_1.setTags(android.text.Html.fromHtml(v0_1.getTags(), 63).toString());
        }
        if ((!android.text.TextUtils.isEmpty(v0_1.getSource())) && (v0_1.getSource().contains("&amp"))) {
            v0_1.setSource(android.text.Html.fromHtml(v0_1.getSource(), 63).toString());
        }
        v0_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "preview_url", ""), com.bisimplex.firebooru.network.ParserGelbooruJSON.optInt(p8, "preview_width", 0), com.bisimplex.firebooru.network.ParserGelbooruJSON.optInt(p8, "preview_height", 0)));
        v0_1.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "file_url", ""), com.bisimplex.firebooru.network.ParserGelbooruJSON.optInt(p8, "width", 0), com.bisimplex.firebooru.network.ParserGelbooruJSON.optInt(p8, "height", 0)));
        v0_1.setJpeg(v0_1.getFile());
        String v1_7 = com.bisimplex.firebooru.network.ParserGelbooruJSON.optString(p8, "sample_url", "");
        if (!android.text.TextUtils.isEmpty(v1_7)) {
            v0_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v1_7, com.bisimplex.firebooru.network.ParserGelbooruJSON.optInt(p8, "sample_width", 0), com.bisimplex.firebooru.network.ParserGelbooruJSON.optInt(p8, "sample_height", 0)));
        } else {
            v0_1.setSample(v0_1.getFile());
        }
        if ((v0_1.getFile().isAnimated()) && (!v0_1.getSample().isAnimated())) {
            v0_1.setSample(v0_1.getFile());
        }
        if (this.shouldAddToResults(v0_1)) {
            if (this.provider.shouldAvoidPolish()) {
                this.fixURLSForPolish(v0_1);
            }
            this.data.add(v0_1);
        }
        return;
    }
}
