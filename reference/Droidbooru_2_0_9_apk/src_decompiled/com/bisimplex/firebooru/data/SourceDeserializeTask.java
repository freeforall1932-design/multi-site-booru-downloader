package com.bisimplex.firebooru.data;
public class SourceDeserializeTask extends com.bisimplex.firebooru.data.SourceSerializeTask {

    public SourceDeserializeTask(String p1, com.bisimplex.firebooru.data.SourceSerializeTask$SerializeTransactionAction p2)
    {
        super(p1, p2);
        return;
    }

    protected bridge synthetic Object doInBackground(Object[] p1)
    {
        return this.doInBackground(((java.util.ArrayList[]) p1));
    }

    protected varargs Void doInBackground(java.util.ArrayList[] p7)
    {
        java.util.ArrayList v0_1 = new com.google.gson.Gson();
        if ((p7.length != 0) && ((this.uniqueId != null) && (this.mListener != null))) {
            java.util.ArrayList v7_1 = p7[0];
            int v1_4 = this.getTempFile(com.bisimplex.firebooru.DroidBooruApplication.getAppContext(), String.format("%s.json", new Object[] {this.uniqueId})));
            if (v1_4 != 0) {
                if (v1_4.exists()) {
                    reflect.Type v3_4 = new StringBuilder();
                    try {
                        com.bisimplex.firebooru.network.Utils v4_1 = new java.io.BufferedReader(new java.io.FileReader(v1_4));
                    } catch (int v1_6) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v1_6);
                        java.util.ArrayList v0_3 = ((java.util.ArrayList) v0_1.fromJson(v3_4.toString(), new com.bisimplex.firebooru.data.SourceDeserializeTask$1(this).getType()));
                        if ((v0_3 != null) && (v0_3.size() > 0)) {
                            v7_1.addAll(v0_3);
                        }
                    }
                    while(true) {
                        int v1_5 = v4_1.readLine();
                        if (v1_5 == 0) {
                            break;
                        }
                        v3_4.append(v1_5);
                    }
                    v4_1.close();
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    protected bridge synthetic void onPostExecute(Object p1)
    {
        this.onPostExecute(((Void) p1));
        return;
    }

    protected void onPostExecute(Void p1)
    {
        if (this.mListener != null) {
            this.mListener.deSerializeFinished(this);
        }
        return;
    }
}
