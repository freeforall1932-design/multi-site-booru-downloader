package com.bisimplex.firebooru.dialog;
public class HistoryDialog extends androidx.fragment.app.DialogFragment {
    private com.bisimplex.firebooru.fragment.TagHistoryFragment$SimpleListDataAdapter adapter;
    private android.widget.ListView list;

    public HistoryDialog()
    {
        return;
    }

    public static com.bisimplex.firebooru.dialog.HistoryDialog newInstance()
    {
        return new com.bisimplex.firebooru.dialog.HistoryDialog();
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        android.view.View v2_1 = p2.inflate(2131558446, p3, 0);
        this.list = ((android.widget.ListView) v2_1.findViewById(2131362209));
        return v2_1;
    }
}
