package com.bisimplex.firebooru.fragment;
public class FormBaseFragment extends com.bisimplex.firebooru.fragment.BaseFragment {
    private android.os.Handler handler;

    public FormBaseFragment()
    {
        this.handler = new android.os.Handler();
        return;
    }

    public com.bisimplex.firebooru.fragment.IBaseFragment getFramentCompanion()
    {
        return 0;
    }

    public boolean getShouldResetStack()
    {
        return 0;
    }

    public String getStringFromControl(int p2)
    {
        String v2_4 = this.getView().findViewById(p2);
        if (!(v2_4 instanceof android.widget.EditText)) {
            return "";
        } else {
            return ((android.widget.EditText) v2_4).getText().toString().trim();
        }
    }

    public boolean goBackInSuccess()
    {
        return 1;
    }

    public void hideKeyboardFrom(android.widget.EditText p3)
    {
        android.view.inputmethod.InputMethodManager v0_2 = ((android.view.inputmethod.InputMethodManager) this.getActivity().getSystemService("input_method"));
        if ((v0_2 != null) && (p3 != null)) {
            v0_2.hideSoftInputFromWindow(p3.getWindowToken(), 0);
        }
        return;
    }

    public void saveItem()
    {
        return;
    }

    public void showMessage(int p3, com.bisimplex.firebooru.activity.MessageType p4)
    {
        super.showMessage(p3, p4);
        if ((p4 == com.bisimplex.firebooru.activity.MessageType.Success) && (this.goBackInSuccess())) {
            this.handler.postDelayed(new com.bisimplex.firebooru.fragment.FormBaseFragment$1(this), 1500);
        }
        return;
    }

    public void successFinished()
    {
        this.goBackInStack();
        return;
    }
}
