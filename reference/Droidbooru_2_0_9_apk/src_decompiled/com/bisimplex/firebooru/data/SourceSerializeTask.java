package com.bisimplex.firebooru.data;
public class SourceSerializeTask extends android.os.AsyncTask {
    protected com.bisimplex.firebooru.data.SourceSerializeTask$SerializeTransactionAction mListener;
    protected String uniqueId;

    public SourceSerializeTask(String p1, com.bisimplex.firebooru.data.SourceSerializeTask$SerializeTransactionAction p2)
    {
        this.uniqueId = p1;
        this.mListener = p2;
        return;
    }

    protected bridge synthetic Object doInBackground(Object[] p1)
    {
        return this.doInBackground(((java.util.ArrayList[]) p1));
    }

    protected varargs Void doInBackground(java.util.ArrayList[] p5)
    {
        com.bisimplex.firebooru.network.Utils v0_1 = new com.google.gson.Gson();
        if ((p5.length != 0) && ((this.uniqueId != null) && (this.mListener != null))) {
            java.io.IOException v5_2 = v0_1.toJson(p5[0]);
            if (!android.text.TextUtils.isEmpty(v5_2)) {
                com.bisimplex.firebooru.network.Utils v0_6 = this.getTempFile(com.bisimplex.firebooru.DroidBooruApplication.getAppContext(), String.format("%s.json", new Object[] {this.uniqueId})));
                if (v0_6 != null) {
                    try {
                        java.io.FileWriter v3_3 = new java.io.FileWriter(v0_6, 0);
                        v3_3.write(v5_2);
                        v3_3.flush();
                        v3_3.close();
                    } catch (java.io.IOException v5_3) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v5_3);
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    public java.io.File getTempFile(android.content.Context p2, String p3)
    {
        return new java.io.File(p2.getCacheDir(), p3);
    }

    protected bridge synthetic void onPostExecute(Object p1)
    {
        this.onPostExecute(((Void) p1));
        return;
    }

    protected void onPostExecute(Void p1)
    {
        p1 = this.mListener;
        if (p1 != null) {
            p1.serializeFinished(this);
        }
        return;
    }
}
