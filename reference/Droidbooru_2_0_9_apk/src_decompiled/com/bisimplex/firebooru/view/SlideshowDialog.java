package com.bisimplex.firebooru.view;
public class SlideshowDialog extends androidx.fragment.app.DialogFragment {
    private static int MIN_SECONDS = 2;
    private com.bisimplex.firebooru.view.SlideshowDialog$SlideshowDialogListener mListener;
    private android.widget.SeekBar seekBar;
    private android.widget.TextView timeTextView;

    static bridge synthetic com.bisimplex.firebooru.view.SlideshowDialog$SlideshowDialogListener -$$Nest$fgetmListener(com.bisimplex.firebooru.view.SlideshowDialog p0)
    {
        return p0.mListener;
    }

    static bridge synthetic android.widget.SeekBar -$$Nest$fgetseekBar(com.bisimplex.firebooru.view.SlideshowDialog p0)
    {
        return p0.seekBar;
    }

    static bridge synthetic void -$$Nest$mupdateTimeLabel(com.bisimplex.firebooru.view.SlideshowDialog p0)
    {
        p0.updateTimeLabel();
        return;
    }

    static bridge synthetic int -$$Nest$sfgetMIN_SECONDS()
    {
        return com.bisimplex.firebooru.view.SlideshowDialog.MIN_SECONDS;
    }

    static SlideshowDialog()
    {
        return;
    }

    public SlideshowDialog()
    {
        return;
    }

    private void updateTimeLabel()
    {
        String v0_0 = this.seekBar;
        if (v0_0 != null) {
            android.widget.TextView v1 = this.timeTextView;
            if (v1 != null) {
                v1.setText(this.getString(2131887123, new Object[] {Integer.valueOf((v0_0.getProgress() + com.bisimplex.firebooru.view.SlideshowDialog.MIN_SECONDS))})));
            }
        }
        return;
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.SlideshowDialog$SlideshowDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public void onCreate(android.os.Bundle p1)
    {
        super.onCreate(p1);
        return;
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p4)
    {
        androidx.appcompat.app.AlertDialog v4_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        com.bisimplex.firebooru.view.SlideshowDialog$3 v0_10 = this.getActivity().getLayoutInflater();
        v4_1.setTitle(2131887167);
        com.bisimplex.firebooru.view.SlideshowDialog$3 v0_0 = v0_10.inflate(2131558639, 0);
        v4_1.setView(v0_0);
        int v1_3 = ((android.widget.SeekBar) v0_0.findViewById(2131362512));
        this.seekBar = v1_3;
        v1_3.setMax((60 - com.bisimplex.firebooru.view.SlideshowDialog.MIN_SECONDS));
        this.seekBar.setProgress((10 - com.bisimplex.firebooru.view.SlideshowDialog.MIN_SECONDS));
        this.timeTextView = ((android.widget.TextView) v0_0.findViewById(2131362640));
        this.updateTimeLabel();
        this.seekBar.setOnSeekBarChangeListener(new com.bisimplex.firebooru.view.SlideshowDialog$1(this));
        return v4_1.setPositiveButton(2131886997, new com.bisimplex.firebooru.view.SlideshowDialog$2(this)).setNegativeButton(2131886205, new com.bisimplex.firebooru.view.SlideshowDialog$3(this)).create();
    }

    public void onDestroyView()
    {
        android.app.Dialog v0 = this.getDialog();
        if ((v0 != null) && (this.getRetainInstance())) {
            v0.setDismissMessage(0);
        }
        super.onDestroyView();
        return;
    }
}
