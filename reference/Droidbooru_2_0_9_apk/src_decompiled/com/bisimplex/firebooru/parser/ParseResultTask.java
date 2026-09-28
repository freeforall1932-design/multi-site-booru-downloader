package com.bisimplex.firebooru.parser;
public class ParseResultTask extends android.os.AsyncTask {
    private com.bisimplex.firebooru.parser.ParseResultTask$IParseResultTaskListener mListener;

    public ParseResultTask()
    {
        return;
    }

    protected varargs com.bisimplex.firebooru.parser.Parser doInBackground(com.bisimplex.firebooru.parser.ParsePack[] p4)
    {
        com.google.gson.JsonArray v1_0 = 0;
        if (p4.length > 0) {
            Exception v4_5 = p4[0];
            switch (com.bisimplex.firebooru.parser.ParseResultTask$1.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType[v4_5.provider.getServerDescription().getType().ordinal()]) {
                case 1:
                    return new com.bisimplex.firebooru.parser.DanbooruParser(v4_5.data, v4_5.provider);
                case 2:
                    v1_0 = new com.bisimplex.firebooru.parser.GelbooruParser(v4_5.provider);
                    try {
                        android.util.Xml.parse(v4_5.getHtml(), v1_0);
                    } catch (Exception v4_9) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v4_9);
                    }
                    break;
                case 3:
                    com.bisimplex.firebooru.network.Utils v0_13 = new nl.matshofman.saxrssreader.RssHandler();
                    try {
                        android.util.Xml.parse(v4_5.getHtml(), v0_13);
                        return new com.bisimplex.firebooru.parser.ShimmieParser(v0_13.getResult(), v4_5.provider);
                    } catch (Exception v4_7) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v4_7);
                    }
                case 4:
                    return new com.bisimplex.firebooru.parser.Danbooru2Parser(v4_5.data, v4_5.provider);
                case 5:
                    return new com.bisimplex.firebooru.parser.GelbooruXPATHParser(v4_5.getHtml(), v4_5.provider);
                case 6:
                    return new com.bisimplex.firebooru.parser.IBSearchParser(v4_5.data, v4_5.provider);
                case 7:
                    return new com.bisimplex.firebooru.parser.DerpibooruParser(v4_5.data, v4_5.provider);
                default:
            }
        }
        return v1_0;
    }

    protected bridge synthetic Object doInBackground(Object[] p1)
    {
        return this.doInBackground(((com.bisimplex.firebooru.parser.ParsePack[]) p1));
    }

    protected void onPostExecute(com.bisimplex.firebooru.parser.Parser p2)
    {
        com.bisimplex.firebooru.parser.ParseResultTask$IParseResultTaskListener v0 = this.mListener;
        if (v0 != null) {
            v0.finishedParsing(p2);
        }
        return;
    }

    protected bridge synthetic void onPostExecute(Object p1)
    {
        this.onPostExecute(((com.bisimplex.firebooru.parser.Parser) p1));
        return;
    }

    protected varargs void onProgressUpdate(Integer[] p1)
    {
        return;
    }

    protected bridge synthetic void onProgressUpdate(Object[] p1)
    {
        this.onProgressUpdate(((Integer[]) p1));
        return;
    }

    public void setListener(com.bisimplex.firebooru.parser.ParseResultTask$IParseResultTaskListener p1)
    {
        this.mListener = p1;
        return;
    }
}
