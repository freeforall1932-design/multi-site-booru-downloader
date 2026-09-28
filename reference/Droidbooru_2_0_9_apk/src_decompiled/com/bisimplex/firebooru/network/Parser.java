package com.bisimplex.firebooru.network;
public abstract class Parser {
    protected java.util.List data;
    protected com.bisimplex.firebooru.network.ParserParams params;
    protected com.bisimplex.firebooru.danbooru.BooruProvider provider;

    public Parser(com.bisimplex.firebooru.network.ParserParams p2)
    {
        this.params = p2;
        this.data = new java.util.ArrayList();
        if (p2 != null) {
            this.provider = this.params.getProvider();
        }
        return;
    }

    public static boolean containsIgnoreCase(String p8, String p9)
    {
        if ((p8 != null) && (p9 != null)) {
            int v6 = p9.length();
            if (v6 != 0) {
                int v3 = (p8.length() - v6);
                while (v3 >= 0) {
                    if (!p8.regionMatches(1, v3, p9, 0, v6)) {
                        v3--;
                    } else {
                        return 1;
                    }
                }
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static java.util.Date dateFromISO8601String(String p5)
    {
        if (!android.text.TextUtils.isEmpty(p5)) {
            com.bisimplex.firebooru.network.Utils v0_2;
            String v2;
            if (!p5.endsWith("Z")) {
                v2 = "yyyy-MM-dd\'T\'HH:mm:ss.SSSZZZZ";
                v0_2 = 0;
            } else {
                v0_2 = java.util.TimeZone.getTimeZone("UTC");
                v2 = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'";
            }
            java.text.SimpleDateFormat v3_1 = new java.text.SimpleDateFormat(v2, java.util.Locale.US);
            if (v0_2 != null) {
                try {
                    v3_1.setTimeZone(v0_2);
                } catch (java.text.ParseException v5_1) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v5_1);
                    return 0;
                }
            }
            return v3_1.parse(p5);
        } else {
            return 0;
        }
    }

    public static com.bisimplex.firebooru.network.Parser fromProvider(com.bisimplex.firebooru.network.ParserParams p3)
    {
        com.bisimplex.firebooru.network.ParserTagAutoEJSON v0_0 = 0;
        if ((p3 != null) && (p3.getProvider() != null)) {
            if (p3.getType() != com.bisimplex.firebooru.network.SourceType.Post) {
                if (p3.getType() != com.bisimplex.firebooru.network.SourceType.Notes) {
                    if (p3.getType() != com.bisimplex.firebooru.network.SourceType.Pool) {
                        if ((p3.getType() != com.bisimplex.firebooru.network.SourceType.Tag) && (p3.getType() != com.bisimplex.firebooru.network.SourceType.FullTag)) {
                            if (p3.getType() == com.bisimplex.firebooru.network.SourceType.BooruTag) {
                                if (!p3.getProvider().getShouldParseAsJson()) {
                                    v0_0 = new com.bisimplex.firebooru.network.ParserTagXML(p3);
                                } else {
                                    return new com.bisimplex.firebooru.network.ParserTagJSON(p3);
                                }
                            }
                            return v0_0;
                        } else {
                            if (!android.text.TextUtils.isEmpty(((CharSequence) p3.getQuery().getExtraParams().get("SERVER_ID_KEY")))) {
                                com.bisimplex.firebooru.network.ParserTagAutoEJSON v0_49 = p3.getProvider().getServerDescription().getType();
                                if (v0_49 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) {
                                    if (v0_49 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
                                        if (v0_49 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) {
                                            if (v0_49 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) {
                                                if (v0_49 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) {
                                                    return new com.bisimplex.firebooru.network.ParserTagAutoEJSON(p3);
                                                }
                                            } else {
                                                return new com.bisimplex.firebooru.network.ParserTagAutoPhiloJSON(p3);
                                            }
                                        } else {
                                            return new com.bisimplex.firebooru.network.ParserTagAutoHydrusJSON(p3);
                                        }
                                    } else {
                                        return new com.bisimplex.firebooru.network.ParserTagAutoGelbooruJSON(p3);
                                    }
                                } else {
                                    return new com.bisimplex.firebooru.network.ParserTagAutoDanbooruJSON(p3);
                                }
                            }
                            v0_0 = new com.bisimplex.firebooru.network.ParserTag(p3);
                        }
                    } else {
                        if (p3.getProvider().getServerDescription().getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) {
                            return new com.bisimplex.firebooru.network.ParserPoolDanbooru2JSON(p3);
                        } else {
                            return new com.bisimplex.firebooru.network.ParserPoolDRPB(p3);
                        }
                    }
                } else {
                    if (p3.getProvider().getServerDescription().getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
                        return new com.bisimplex.firebooru.network.ParserNoteJSON(p3);
                    } else {
                        return new com.bisimplex.firebooru.network.ParserNoteXML(p3);
                    }
                }
            } else {
                switch (com.bisimplex.firebooru.network.Parser$1.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType[p3.getProvider().getServerDescription().getType().ordinal()]) {
                    case 1:
                        return new com.bisimplex.firebooru.network.ParserDanbooru1JSON(p3);
                    case 2:
                        if (!p3.getProvider().getShouldParseAsJson()) {
                            return new com.bisimplex.firebooru.network.ParserGelbooruXML(p3);
                        } else {
                            return new com.bisimplex.firebooru.network.ParserGelbooruJSON(p3);
                        }
                    case 3:
                        if (!p3.getProvider().isParseAsHTML()) {
                            return new com.bisimplex.firebooru.network.ParserShimmie2(p3);
                        } else {
                            return new com.bisimplex.firebooru.network.ParserShimmieHTML(p3);
                        }
                    case 4:
                        return new com.bisimplex.firebooru.network.ParserDanbooru2JSON(p3);
                    case 5:
                        return new com.bisimplex.firebooru.network.ParserGelbooruHTML(p3);
                    case 6:
                        return new com.bisimplex.firebooru.network.ParserDerpibooruJSON(p3);
                    case 7:
                        return new com.bisimplex.firebooru.network.ParserE621JSON(p3);
                    case 8:
                        return new com.bisimplex.firebooru.network.ParserHydrusJSON(p3);
                    case 9:
                        return new com.bisimplex.firebooru.network.ParserBooruIO(p3);
                    case 10:
                        return new com.bisimplex.firebooru.network.ParserBooruOnRails(p3);
                    case 11:
                        return new com.bisimplex.firebooru.network.ParserKemono(p3);
                    default:
                        return 0;
                }
            }
        }
        return v0_0;
    }

    public static java.util.Date getDateIfExist(com.google.gson.JsonObject p4, String p5, String p6)
    {
        if ((p4 != null) && (p4.has(p5))) {
            java.util.Date v4_9 = p4.get(p5);
            if ((v4_9.isJsonNull()) || (!v4_9.isJsonObject())) {
                if (v4_9.isJsonPrimitive()) {
                    java.util.Date v4_1 = v4_9.getAsJsonPrimitive();
                    if (!v4_1.isNumber()) {
                        if (v4_1.isString()) {
                            return com.bisimplex.firebooru.network.Parser.parseDateIfValid(v4_1.getAsString(), p6);
                        }
                    } else {
                        return new java.util.Date((v4_1.getAsLong() * 1000));
                    }
                }
            } else {
                return new java.util.Date((v4_9.getAsJsonObject().get("s").getAsLong() * 1000));
            }
        }
        return 0;
    }

    public static boolean optBoolean(com.google.gson.JsonObject p1, String p2, boolean p3)
    {
        if ((p1) && (p1.has(p2))) {
            boolean v1_2 = p1.get(p2);
            if ((!v1_2.isJsonNull()) && (v1_2.isJsonPrimitive())) {
                return v1_2.getAsBoolean();
            }
        }
        return p3;
    }

    public static int optInt(com.google.gson.JsonObject p1, String p2, int p3)
    {
        if ((p1 != 0) && (p1.has(p2))) {
            int v1_2 = p1.get(p2);
            if ((!v1_2.isJsonNull()) && (v1_2.isJsonPrimitive())) {
                return v1_2.getAsInt();
            }
        }
        return p3;
    }

    public static String optString(com.google.gson.JsonObject p1, String p2, String p3)
    {
        if ((p1 != null) && (p1.has(p2))) {
            String v1_2 = p1.get(p2);
            if ((!v1_2.isJsonNull()) && (v1_2.isJsonPrimitive())) {
                return v1_2.getAsString();
            }
        }
        return p3;
    }

    public static java.util.Date parseDateIfValid(String p3, String p4)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            if (!android.text.TextUtils.isEmpty(p4)) {
                try {
                    return new java.text.SimpleDateFormat(p4, java.util.Locale.US).parse(p3);
                } catch (java.text.ParseException v3_2) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v3_2);
                    return 0;
                }
            } else {
                return com.bisimplex.firebooru.network.Parser.dateFromISO8601String(p3);
            }
        } else {
            return 0;
        }
    }

    public java.util.List getData()
    {
        return this.data;
    }

    public void parse()
    {
        this.data.clear();
        if (!android.text.TextUtils.isEmpty(this.params.getResponseBody())) {
            try {
                this.parse(com.google.gson.JsonParser.parseString(this.params.getResponseBody()));
                return;
            } catch (com.google.gson.JsonSyntaxException v0_3) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_3);
            }
        }
        return;
    }

    protected void parse(com.google.gson.JsonElement p3)
    {
        if (p3 != null) {
            if (!p3.isJsonArray()) {
                if (p3.isJsonObject()) {
                    this.parseElement(p3.getAsJsonObject());
                }
            } else {
                com.google.gson.JsonObject v3_1 = p3.getAsJsonArray();
                int v0_0 = 0;
                while (v0_0 < v3_1.size()) {
                    this.parseElement(v3_1.get(v0_0).getAsJsonObject());
                    v0_0++;
                }
            }
        }
        this.parseFinished();
        return;
    }

    protected abstract void parseElement(com.google.gson.JsonObject p0);

    protected void parseFinished()
    {
        return;
    }

    protected int tryParseInt(String p2)
    {
        try {
            return Integer.parseInt(p2);
        } catch (int v2_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v2_2);
            return 0;
        }
    }
}
