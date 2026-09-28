package com.bisimplex.firebooru.network;
public class ParserTask extends android.os.AsyncTask {
    private com.bisimplex.firebooru.network.ParserTaskListener listener;

    public ParserTask(com.bisimplex.firebooru.network.ParserTaskListener p1)
    {
        this.listener = p1;
        return;
    }

    protected varargs com.bisimplex.firebooru.network.Parser doInBackground(com.bisimplex.firebooru.network.ParserParams[] p2)
    {
        if ((p2 != null) && (p2.length > 0)) {
            com.bisimplex.firebooru.network.Parser v2_2 = com.bisimplex.firebooru.network.Parser.fromProvider(p2[0]);
            if (v2_2 != null) {
                v2_2.parse();
                return v2_2;
            }
        }
        return 0;
    }

    protected bridge synthetic Object doInBackground(Object[] p1)
    {
        return this.doInBackground(((com.bisimplex.firebooru.network.ParserParams[]) p1));
    }

    protected void onPostExecute(com.bisimplex.firebooru.network.Parser p2)
    {
        com.bisimplex.firebooru.network.ParserTaskListener v0 = this.listener;
        if (v0 != null) {
            v0.finishedParsing(p2);
        }
        return;
    }

    protected bridge synthetic void onPostExecute(Object p1)
    {
        this.onPostExecute(((com.bisimplex.firebooru.network.Parser) p1));
        return;
    }
}
