package com.bisimplex.firebooru.fragment;
public class ProxyFormFragment extends com.bisimplex.firebooru.fragment.FormBaseFragment {
    private android.widget.EditText hostEditText;
    private android.widget.EditText passEditText;
    private android.widget.EditText portEditText;
    private android.widget.RadioGroup typeGroup;
    private android.widget.EditText userEditText;

    static bridge synthetic android.widget.RadioGroup -$$Nest$fgettypeGroup(com.bisimplex.firebooru.fragment.ProxyFormFragment p0)
    {
        return p0.typeGroup;
    }

    static bridge synthetic void -$$Nest$menableControllers(com.bisimplex.firebooru.fragment.ProxyFormFragment p0, boolean p1)
    {
        p0.enableControllers(p1);
        return;
    }

    public ProxyFormFragment()
    {
        return;
    }

    private void enableControllers(boolean p2)
    {
        this.userEditText.setEnabled(p2);
        this.hostEditText.setEnabled(p2);
        this.portEditText.setEnabled(p2);
        this.passEditText.setEnabled(p2);
        return;
    }

    protected android.view.View getInsetContentView()
    {
        return this.getView().findViewById(2131362492);
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        android.view.View v2_1 = p2.inflate(2131558614, p3, 0);
        this.userEditText = ((android.widget.EditText) v2_1.findViewById(2131362686));
        this.passEditText = ((android.widget.EditText) v2_1.findViewById(2131362410));
        this.hostEditText = ((android.widget.EditText) v2_1.findViewById(2131362164));
        this.portEditText = ((android.widget.EditText) v2_1.findViewById(2131362426));
        this.typeGroup = ((android.widget.RadioGroup) v2_1.findViewById(2131362673));
        this.setTitle(2131887040);
        v2_1.findViewById(2131362481).setOnClickListener(new com.bisimplex.firebooru.fragment.ProxyFormFragment$2(this));
        return v2_1;
    }

    public void onResume()
    {
        int v0_2;
        super.onResume();
        if (this.typeGroup.getCheckedRadioButtonId() == 2131362005) {
            v0_2 = 0;
        } else {
            v0_2 = 1;
        }
        this.enableControllers(v0_2);
        return;
    }

    public void onViewCreated(android.view.View p2, android.os.Bundle p3)
    {
        super.onViewCreated(p2, p3);
        android.widget.RadioGroup v2_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getProxyConfiguration();
        if (v2_2 != null) {
            if (android.text.TextUtils.isEmpty(this.userEditText.getText().toString())) {
                this.userEditText.setText(v2_2.getUser());
            }
            if (android.text.TextUtils.isEmpty(this.passEditText.getText().toString())) {
                this.passEditText.setText(v2_2.getPass());
            }
            if (android.text.TextUtils.isEmpty(this.hostEditText.getText().toString())) {
                this.hostEditText.setText(v2_2.getHost());
            }
            if (android.text.TextUtils.isEmpty(this.portEditText.getText().toString())) {
                this.portEditText.setText(String.valueOf(v2_2.getPort()));
            }
            android.widget.RadioGroup v2_3 = v2_2.getType();
            if (v2_3 != 1) {
                if (v2_3 == 2) {
                    this.typeGroup.check(2131362545);
                }
            } else {
                this.typeGroup.check(2131362166);
            }
        }
        this.typeGroup.setOnCheckedChangeListener(new com.bisimplex.firebooru.fragment.ProxyFormFragment$1(this));
        return;
    }

    public void saveItem()
    {
        com.bisimplex.firebooru.fragment.ProxyFormFragment$3 v0_0 = this.getActivity();
        if (v0_0 != null) {
            if (!this.userEditText.isFocused()) {
                if (!this.passEditText.isFocused()) {
                    if (!this.hostEditText.isFocused()) {
                        if (this.portEditText.isFocused()) {
                            this.hideKeyboardFrom(this.portEditText);
                        }
                    } else {
                        this.hideKeyboardFrom(this.hostEditText);
                    }
                } else {
                    this.hideKeyboardFrom(this.passEditText);
                }
            } else {
                this.hideKeyboardFrom(this.userEditText);
            }
            com.google.android.material.dialog.MaterialAlertDialogBuilder v1_11 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getProxyConfiguration();
            if (v1_11 == null) {
                v1_11 = new com.bisimplex.firebooru.model.ProxyConfiguration();
            }
            int v2_4;
            int v2_3 = this.typeGroup.getCheckedRadioButtonId();
            int v4 = 0;
            if (v2_3 != 2131362166) {
                if (v2_3 != 2131362545) {
                    v2_4 = 0;
                } else {
                    v2_4 = 2;
                }
            } else {
                v2_4 = 1;
            }
            String v3_5 = this.hostEditText.getText().toString().trim();
            String v5_3 = this.passEditText.getText().toString().trim();
            String v6_3 = this.userEditText.getText().toString().trim();
            int v7_3 = this.portEditText.getText().toString().trim();
            boolean v8 = android.text.TextUtils.isDigitsOnly(v7_3);
            if (v2_4 != 0) {
                if (!android.text.TextUtils.isEmpty(v3_5)) {
                    if (!v8) {
                        this.showMessage(2131887043, com.bisimplex.firebooru.activity.MessageType.Error);
                        return;
                    }
                } else {
                    this.showMessage(2131887042, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                }
            }
            if ((!android.text.TextUtils.isEmpty(v7_3)) && (v8)) {
                v4 = Integer.parseInt(v7_3);
            }
            if ((v4 <= 65535) && (v4 >= 0)) {
                v1_11.setHost(v3_5);
                v1_11.setUser(v6_3);
                v1_11.setPass(v5_3);
                v1_11.setPort(v4);
                v1_11.setType(v2_4);
                com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setProxyConfiguration(v1_11);
                com.google.android.material.dialog.MaterialAlertDialogBuilder v1_17 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(v0_0);
                v1_17.setMessage(2131887047).setTitle(2131887087);
                v1_17.setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.ProxyFormFragment$3(this));
                v1_17.show();
                return;
            } else {
                this.showMessage(2131887043, com.bisimplex.firebooru.activity.MessageType.Error);
                return;
            }
        } else {
            return;
        }
    }
}
