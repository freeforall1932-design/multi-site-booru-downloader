package com.bisimplex.firebooru.fragment;
public class LockFragment extends com.bisimplex.firebooru.fragment.BaseFragment {
    public static String LOCK_MODE = "LOCK_MODE";
    public static int LOCK_MODE_DISABLE = 2;
    public static int LOCK_MODE_ENABLE = 3;
    public static int LOCK_MODE_VALIDATE = 1;
    private boolean confirmGesture;
    public java.util.List editLockInfo;
    private android.widget.TextView footerTextView;
    private com.bisimplex.firebooru.lock.GestureLock gestureLock;
    private android.widget.TextView titleTextView;

    static LockFragment()
    {
        return;
    }

    public LockFragment()
    {
        return;
    }

    public void closeForm()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.setDrawerEnabled(1);
            this.goBackInStack();
            return;
        } else {
            return;
        }
    }

    protected android.view.View getInsetContentView()
    {
        return this.getView().findViewById(2131361960);
    }

    protected int getMode()
    {
        int v0_0 = this.getArguments();
        if (v0_0 != 0) {
            return v0_0.getInt(com.bisimplex.firebooru.fragment.LockFragment.LOCK_MODE);
        } else {
            return com.bisimplex.firebooru.fragment.LockFragment.LOCK_MODE_VALIDATE;
        }
    }

    public boolean getShouldResetStack()
    {
        if (this.getMode() != com.bisimplex.firebooru.fragment.LockFragment.LOCK_MODE_VALIDATE) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isDisable()
    {
        if (this.getMode() != com.bisimplex.firebooru.fragment.LockFragment.LOCK_MODE_DISABLE) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isEnable()
    {
        if (this.getMode() != com.bisimplex.firebooru.fragment.LockFragment.LOCK_MODE_ENABLE) {
            return 0;
        } else {
            return 1;
        }
    }

    public void lockInputFinished(boolean p5)
    {
        if (!this.isEnable()) {
            if (!this.isDisable()) {
                if (p5 == null) {
                    this.showMessage(2131887014, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                } else {
                    com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().passedLock();
                    com.bisimplex.firebooru.activity.MessageType v5_3 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
                    if (v5_3 != null) {
                        v5_3.ValidateUser();
                        return;
                    } else {
                        return;
                    }
                }
            } else {
                if (p5 == null) {
                    this.showMessage(2131887014, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                } else {
                    com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setLockInfo(0);
                    this.showMessage(2131887193, com.bisimplex.firebooru.activity.MessageType.Success);
                    this.closeForm();
                    return;
                }
            }
        } else {
            if (!this.confirmGesture) {
                this.titleTextView.setText(2131886274);
                this.gestureLock.setMode(0);
                this.gestureLock.setCorrectGesture(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().toIntArray(this.editLockInfo));
                this.gestureLock.resetPath();
                this.confirmGesture = 1;
                this.editLockInfo.clear();
                return;
            } else {
                if (p5 == null) {
                    this.showMessage(2131886495, com.bisimplex.firebooru.activity.MessageType.Error);
                    this.confirmGesture = 0;
                    this.gestureLock.setMode(1);
                    this.editLockInfo.clear();
                    this.titleTextView.setText(2131886486);
                    return;
                } else {
                    com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setLockInfo(this.editLockInfo);
                    this.showMessage(2131887193, com.bisimplex.firebooru.activity.MessageType.Success);
                    this.closeForm();
                    return;
                }
            }
        }
    }

    public void onCreateOptionsMenu(android.view.Menu p1, android.view.MenuInflater p2)
    {
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        android.view.View v2_1 = p2.inflate(2131558436, p3, 0);
        this.gestureLock = ((com.bisimplex.firebooru.lock.GestureLock) v2_1.findViewById(2131362135));
        this.editLockInfo = new java.util.ArrayList(0);
        this.titleTextView = ((android.widget.TextView) v2_1.findViewById(2131362651));
        this.footerTextView = ((android.widget.TextView) v2_1.findViewById(2131362127));
        this.gestureLock.setColorLine(androidx.core.content.ContextCompat.getColor(this.getContext(), com.bisimplex.firebooru.skin.SkinManager.getInstance().getAccentColorRes()));
        this.gestureLock.setColorError(androidx.core.content.ContextCompat.getColor(this.getContext(), com.bisimplex.firebooru.skin.SkinManager.getInstance().getPrimaryColorRes()));
        this.gestureLock.setOnGestureEventListener(new com.bisimplex.firebooru.fragment.LockFragment$1(this));
        this.setTitle(2131886156);
        android.widget.TextView v3_13 = this.isEnable();
        this.gestureLock.setMode(v3_13);
        if (v3_13 == null) {
            this.gestureLock.setCorrectGesture(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getLockInfo());
            if (this.isDisable()) {
                this.titleTextView.setText(2131886487);
            }
            return v2_1;
        } else {
            this.titleTextView.setText(2131886485);
            return v2_1;
        }
    }
}
