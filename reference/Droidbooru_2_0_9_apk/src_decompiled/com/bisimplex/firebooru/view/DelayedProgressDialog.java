package com.bisimplex.firebooru.view;
public class DelayedProgressDialog extends androidx.fragment.app.DialogFragment {
    private static final int DELAY_MILLISECOND = 50;
    private static final int PROGRESS_CONTENT_SIZE_DP = 80;
    private static final int SHOW_MIN_MILLISECOND = 300;
    private android.widget.ProgressBar mProgressBar;
    private long mStartMillisecond;
    private long mStopMillisecond;
    private boolean startedShowing;

    static bridge synthetic long -$$Nest$fgetmStopMillisecond(com.bisimplex.firebooru.view.DelayedProgressDialog p2)
    {
        return p2.mStopMillisecond;
    }

    static bridge synthetic void -$$Nest$mshowDialogAfterDelay(com.bisimplex.firebooru.view.DelayedProgressDialog p0, androidx.fragment.app.FragmentManager p1, String p2)
    {
        p0.showDialogAfterDelay(p1, p2);
        return;
    }

    public DelayedProgressDialog()
    {
        return;
    }

    private void cancelWhenNotShowing()
    {
        new android.os.Handler().postDelayed(new com.bisimplex.firebooru.view.DelayedProgressDialog$3(this), 50);
        return;
    }

    private void cancelWhenShowing()
    {
        if (this.mStopMillisecond >= (this.mStartMillisecond + 350)) {
            this.dismissAllowingStateLoss();
            return;
        } else {
            new android.os.Handler().postDelayed(new com.bisimplex.firebooru.view.DelayedProgressDialog$2(this), 300);
            return;
        }
    }

    private void showDialogAfterDelay(androidx.fragment.app.FragmentManager p2, String p3)
    {
        this.startedShowing = 1;
        androidx.fragment.app.FragmentTransaction v2_1 = p2.beginTransaction();
        v2_1.add(this, p3);
        v2_1.commitAllowingStateLoss();
        return;
    }

    public void cancel()
    {
        this.mStopMillisecond = System.currentTimeMillis();
        if (this.startedShowing) {
            if (this.mProgressBar == null) {
                this.cancelWhenNotShowing();
            } else {
                this.cancelWhenShowing();
                return;
            }
        }
        return;
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p4)
    {
        androidx.appcompat.app.AlertDialog v4_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v4_2.setView(this.getActivity().getLayoutInflater().inflate(2131558474, 0));
        androidx.appcompat.app.AlertDialog v4_1 = v4_2.create();
        v4_1.setCancelable(0);
        v4_1.setCanceledOnTouchOutside(0);
        return v4_1;
    }

    public void onStart()
    {
        super.onStart();
        this.mProgressBar = ((android.widget.ProgressBar) this.getDialog().findViewById(2131362434));
        if (this.getDialog().getWindow() != null) {
            this.getDialog().getWindow().setLayout(((int) (this.getResources().getDisplayMetrics().density * 1117782016)), ((int) (this.getResources().getDisplayMetrics().density * 1117782016)));
            this.getDialog().getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(0));
        }
        return;
    }

    public void show(androidx.fragment.app.FragmentManager p3, String p4)
    {
        this.mStartMillisecond = System.currentTimeMillis();
        this.startedShowing = 0;
        this.mStopMillisecond = 9223372036854775807;
        new android.os.Handler().postDelayed(new com.bisimplex.firebooru.view.DelayedProgressDialog$1(this, p3, p4), 50);
        return;
    }
}
