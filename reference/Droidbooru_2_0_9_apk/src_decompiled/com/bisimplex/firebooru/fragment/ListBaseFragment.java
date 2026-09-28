package com.bisimplex.firebooru.fragment;
public class ListBaseFragment extends androidx.fragment.app.ListFragment implements com.bisimplex.firebooru.fragment.IBaseFragment {

    public ListBaseFragment()
    {
        return;
    }

    public void HideLoading()
    {
        if (!this.isDetached()) {
            com.bisimplex.firebooru.activity.MenuBaseActivity v0_2 = ((com.bisimplex.firebooru.activity.MenuBaseActivity) this.getActivity());
            if (v0_2 != null) {
                v0_2.HideLoading();
            }
        }
        return;
    }

    public void ShowLoading()
    {
        if (!this.isDetached()) {
            com.bisimplex.firebooru.activity.MenuBaseActivity v0_2 = ((com.bisimplex.firebooru.activity.MenuBaseActivity) this.getActivity());
            if (v0_2 != null) {
                v0_2.ShowLoading();
            }
        }
        return;
    }

    public void addNewItem()
    {
        return;
    }

    public com.bisimplex.firebooru.fragment.IBaseFragment getFramentCompanion()
    {
        return 0;
    }

    public boolean getShouldResetStack()
    {
        return 1;
    }

    public boolean getShouldShowMenu()
    {
        return 0;
    }

    public android.view.View getSnackbadAnchorView()
    {
        return 0;
    }

    public String getiOsFragmentName()
    {
        return this.getClass().getName();
    }

    public void goBackInStack()
    {
        if (!this.isDetached()) {
            androidx.fragment.app.FragmentManager v0_2 = ((com.bisimplex.firebooru.activity.MenuBaseActivity) this.getActivity());
            if (v0_2 != null) {
                v0_2.getSupportFragmentManager().popBackStack();
            }
        }
        return;
    }

    public void launchBrowser(String p3)
    {
        if (!this.isDetached()) {
            com.bisimplex.firebooru.activity.MainActivity v0_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
            if (v0_2 != null) {
                v0_2.launchBrowser(p3, 0);
                return;
            }
        }
        return;
    }

    public void menuOpened()
    {
        return;
    }

    public void setTitle(int p2)
    {
        if (!this.isDetached()) {
            androidx.fragment.app.FragmentActivity v0_1 = this.getActivity();
            if (v0_1 != null) {
                v0_1.setTitle(p2);
            }
        }
        return;
    }

    public void setTitle(String p2)
    {
        if (!this.isDetached()) {
            androidx.fragment.app.FragmentActivity v0_1 = this.getActivity();
            if (v0_1 != null) {
                v0_1.setTitle(p2);
            }
        }
        return;
    }

    public void showMessage(int p1, com.bisimplex.firebooru.activity.MessageType p2)
    {
        this.showMessage(this.getString(p1), p2);
        return;
    }

    public void showMessage(String p4, com.bisimplex.firebooru.activity.MessageType p5)
    {
        if (!this.isDetached()) {
            com.bisimplex.firebooru.activity.MenuBaseActivity v0_2 = ((com.bisimplex.firebooru.activity.MenuBaseActivity) this.getActivity());
            if (v0_2 != null) {
                if ((p5 == com.bisimplex.firebooru.activity.MessageType.Minimal) || (p5 == com.bisimplex.firebooru.activity.MessageType.Success)) {
                    com.google.android.material.snackbar.Snackbar v1_0 = this.getSnackbadAnchorView();
                    if (v1_0 != null) {
                        ((com.google.android.material.snackbar.Snackbar) com.google.android.material.snackbar.Snackbar.make(v1_0, p4, 0).setAnchorView(v1_0)).show();
                    }
                }
                v0_2.ShowMessage(p4, p5);
            }
        }
        return;
    }

    public void switchFragment(androidx.fragment.app.Fragment p2)
    {
        if (this.getActivity() != null) {
            ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity()).switchContent(p2);
            return;
        } else {
            return;
        }
    }
}
