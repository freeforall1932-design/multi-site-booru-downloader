package com.bisimplex.firebooru.fragment;
public class ServerFormFragment extends com.bisimplex.firebooru.fragment.FormBaseFragment implements okhttp3.Callback {
    public static final String ServerFormFragmentData = "ServerFormFragment_Data";
    private com.google.android.material.textfield.TextInputLayout apiKeyInputLayout;
    private android.widget.EditText apiKeyText;
    private okhttp3.Call currentOperation;
    private com.bisimplex.firebooru.danbooru.ServerItem data;
    private android.widget.TextView infoTextView;
    private com.google.android.material.textfield.TextInputLayout passInputLayout;
    private android.widget.EditText passText;
    private android.widget.CheckBox ratingCheck;
    private android.widget.Button saveButton;
    private android.widget.Spinner typeSpinner;
    private android.widget.EditText urlText;
    private com.google.android.material.textfield.TextInputLayout userInputLayout;
    private android.widget.EditText userNameText;
    private android.widget.Button validateButton;

    static bridge synthetic void -$$Nest$mvalidateClient(com.bisimplex.firebooru.fragment.ServerFormFragment p0)
    {
        p0.validateClient();
        return;
    }

    public ServerFormFragment()
    {
        return;
    }

    private void checkURLField()
    {
        return;
    }

    private void pingToService(com.bisimplex.firebooru.danbooru.BooruProvider p5)
    {
        okhttp3.Request$Builder v0_0 = p5.getPingServiceUrl();
        okhttp3.OkHttpClient v1 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient();
        okhttp3.Request$Builder v0_1 = new okhttp3.Request$Builder().url(v0_0).header("User-Agent", p5.getUserAgent());
        if (p5.getServerDescription().getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO) {
            v0_1.addHeader("Accept-Encoding", "identity");
        }
        okhttp3.Call v5_5 = v1.newCall(v0_1.build());
        this.currentOperation = v5_5;
        v5_5.enqueue(this);
        return;
    }

    private void validateClient()
    {
        String v0_3 = this.urlText.getText().toString();
        if (!this.isValidUrl()) {
            if ((!v0_3.startsWith("https://")) && (!v0_3.startsWith("http://"))) {
                v0_3 = String.format("https://%s", new Object[] {v0_3}));
            }
            this.urlText.setText(v0_3);
        }
        if (this.validateUrl()) {
            String v0_7 = this.urlText.getText().toString();
            String v1_6 = new android.os.Bundle(1);
            v1_6.putString("SERVER_URL", v0_7);
            String v0_9 = new com.bisimplex.firebooru.view.ValidateClientDialog();
            v0_9.setArguments(v1_6);
            v0_9.show(this.getParentFragmentManager(), "ValidateClientDialog");
            return;
        } else {
            return;
        }
    }

    protected void confirmSaveServerWithMIME(String p5)
    {
        com.bisimplex.firebooru.activity.MessageType v0_3 = new com.bisimplex.firebooru.danbooru.BooruProvider(this.data);
        if (this.data.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
            com.bisimplex.firebooru.danbooru.ServerItem v1_3 = this.data;
            v1_3.setPasswordKey(v0_3.encriptPasswordWithServiceUrl(v1_3.getUrl(), this.data.getPassword()));
        }
        if ((android.text.TextUtils.isEmpty(p5)) || ((!p5.equalsIgnoreCase("text/html")) || ((this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111) || (this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie)))) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().addServer(this.data);
            this.showMessage(2131887193, com.bisimplex.firebooru.activity.MessageType.Success);
            if (this.data.isSelected()) {
                com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().setServerDescription(this.data);
            }
            return;
        } else {
            this.showMessage(2131886622, com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        }
    }

    public boolean getBooleanFromCheck(int p2)
    {
        android.view.View v0 = this.getView();
        if (v0 != null) {
            return ((android.widget.CheckBox) v0.findViewById(p2)).isChecked();
        } else {
            return 0;
        }
    }

    protected android.view.View getInsetContentView()
    {
        return this.getView().findViewById(2131362492);
    }

    public com.bisimplex.firebooru.danbooru.ServerItemType getType()
    {
        switch (this.typeSpinner.getSelectedItemPosition()) {
            case 0:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru;
            case 1:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru;
            case 2:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2;
            case 3:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111;
            case 4:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie;
            case 5:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeIBSearch;
            case 6:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru;
            case 7:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621;
            case 8:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus;
            case 9:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO;
            case 10:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails;
            default:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone;
        }
    }

    public String getiOsFragmentName()
    {
        return "NewServer3ViewController";
    }

    public boolean isValidUrl()
    {
        boolean v0_4 = this.urlText.getText().toString();
        if ((!v0_4.isEmpty()) && ((!v0_4.equalsIgnoreCase("http://")) && (!v0_4.equalsIgnoreCase("https://")))) {
            return android.webkit.URLUtil.isValidUrl(v0_4);
        } else {
            return 0;
        }
    }

    public android.view.View onCreateView(android.view.LayoutInflater p4, android.view.ViewGroup p5, android.os.Bundle p6)
    {
        com.bisimplex.firebooru.fragment.ServerFormFragment$3 v0_5;
        android.view.View v4_1 = p4.inflate(2131558633, p5, 0);
        int v5_30 = ((android.widget.Spinner) v4_1.findViewById(2131362674));
        com.bisimplex.firebooru.fragment.ServerFormFragment$3 v0_3 = android.widget.ArrayAdapter.createFromResource(this.getActivity(), 2130903057, 17367048);
        v0_3.setDropDownViewResource(17367049);
        v5_30.setAdapter(v0_3);
        com.bisimplex.firebooru.fragment.ServerFormFragment$3 v0_4 = this.getArguments();
        if (v0_4 == null) {
            v0_5 = 0;
        } else {
            v0_5 = v0_4.getString("ServerFormFragment_Data");
        }
        if (!android.text.TextUtils.isEmpty(v0_5)) {
            this.data = ((com.bisimplex.firebooru.danbooru.ServerItem) new com.google.gson.Gson().fromJson(v0_5, com.bisimplex.firebooru.danbooru.ServerItem));
        } else {
            this.data = new com.bisimplex.firebooru.danbooru.ServerItem();
        }
        this.typeSpinner = v5_30;
        this.apiKeyText = ((android.widget.EditText) v4_1.findViewById(2131361887));
        this.urlText = ((android.widget.EditText) v4_1.findViewById(2131362682));
        this.passText = ((android.widget.EditText) v4_1.findViewById(2131362412));
        this.userNameText = ((android.widget.EditText) v4_1.findViewById(2131362688));
        this.ratingCheck = ((android.widget.CheckBox) v4_1.findViewById(2131362112));
        this.infoTextView = ((android.widget.TextView) v4_1.findViewById(2131362185));
        this.apiKeyInputLayout = ((com.google.android.material.textfield.TextInputLayout) v4_1.findViewById(2131361888));
        this.userInputLayout = ((com.google.android.material.textfield.TextInputLayout) v4_1.findViewById(2131362687));
        this.passInputLayout = ((com.google.android.material.textfield.TextInputLayout) v4_1.findViewById(2131362411));
        int v5_41 = ((android.widget.Button) v4_1.findViewById(2131362481));
        this.saveButton = v5_41;
        v5_41.setOnClickListener(new com.bisimplex.firebooru.fragment.ServerFormFragment$1(this));
        this.typeSpinner.setOnItemSelectedListener(new com.bisimplex.firebooru.fragment.ServerFormFragment$2(this));
        int v5_45 = ((android.widget.Button) v4_1.findViewById(2131362689));
        this.validateButton = v5_45;
        v5_45.setOnClickListener(new com.bisimplex.firebooru.fragment.ServerFormFragment$3(this));
        if (!p6) {
            this.setType(this.data.getType());
            this.apiKeyText.setText(this.data.getApiKey());
            this.urlText.setText(this.data.getUrl());
            this.userNameText.setText(this.data.getUserName());
            this.passText.setText(this.data.getPassword());
            this.ratingCheck.setChecked(this.data.isRatingFilterEnabled());
            this.typeChanged();
        }
        this.setTitle(2131886861);
        return v4_1;
    }

    public void onFailure(okhttp3.Call p1, java.io.IOException p2)
    {
        this.showMessage(p2.getLocalizedMessage(), com.bisimplex.firebooru.activity.MessageType.Error);
        return;
    }

    public void onResponse(okhttp3.Call p3, okhttp3.Response p4)
    {
        if (!p4.isSuccessful()) {
            if (p4.code() == 401) {
                int v3_11 = this.data;
                if ((v3_11 != 0) && (v3_11.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2)) {
                    this.showMessage(2131886293, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                }
            }
            this.showMessage(2131886490, com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        } else {
            com.bisimplex.firebooru.activity.MessageType v4_4;
            int v3_4 = p4.body();
            if (v3_4 == 0) {
                v4_4 = 0;
            } else {
                v4_4 = v3_4.string();
                v3_4.close();
            }
            String v0 = "";
            if (!android.text.TextUtils.isEmpty(v4_4)) {
                if (java.util.regex.Pattern.compile("!DOCTYPE html", 2).matcher(v4_4).find()) {
                    v0 = "text/html";
                }
                this.confirmSaveServerWithMIME(v0);
                return;
            } else {
                this.confirmSaveServerWithMIME("");
                return;
            }
        }
    }

    public void saveItem()
    {
        boolean v0_16 = this.urlText.getText().toString();
        if (!this.isValidUrl()) {
            if ((!v0_16.startsWith("https://")) && (!v0_16.startsWith("http://"))) {
                v0_16 = String.format("https://%s", new Object[] {v0_16}));
            }
            this.urlText.setText(v0_16);
        }
        if (this.validateUrl()) {
            boolean v0_20 = this.urlText.getText().toString();
            if (v0_20.endsWith("/")) {
                this.urlText.setText(v0_20.substring(0, (v0_20.length() - 1)));
            }
            this.data.setApiKey(this.getStringFromControl(2131361887).trim());
            this.data.setUrl(this.getStringFromControl(2131362682).trim());
            this.data.setPassword(this.getStringFromControl(2131362412).trim());
            this.data.setRatingFilterEnabled(this.getBooleanFromCheck(2131362112));
            this.data.setUserName(this.getStringFromControl(2131362688).trim());
            boolean v0_27 = this.data;
            v0_27.setServerName(v0_27.getUrl());
            this.data.setType(this.getType());
            if ((this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie) || ((this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) || (this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails))) {
                this.data.setRatingFilterEnabled(0);
            }
            if ((this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) && (!android.text.TextUtils.isEmpty(this.data.getApiKey()))) {
                boolean v0_7 = this.data.getApiKey();
                if ((!com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase("api_key", v0_7)) || ((!com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase("user_id", v0_7)) || (!v0_7.startsWith("&")))) {
                    this.showMessage(2131886620, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                }
            }
            this.showMessage("", com.bisimplex.firebooru.activity.MessageType.Loading);
            this.hideKeyboardFrom(this.urlText);
            this.pingToService(new com.bisimplex.firebooru.danbooru.BooruProvider(this.data));
            return;
        } else {
            return;
        }
    }

    public void setType(com.bisimplex.firebooru.danbooru.ServerItemType p2)
    {
        if (this.typeSpinner != null) {
            switch (com.bisimplex.firebooru.fragment.ServerFormFragment$4.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType[p2.ordinal()]) {
                case 1:
                    this.typeSpinner.setSelection(1);
                    break;
                case 2:
                    this.typeSpinner.setSelection(3);
                    return;
                case 3:
                    this.typeSpinner.setSelection(0);
                    return;
                case 4:
                    this.typeSpinner.setSelection(2);
                    return;
                case 5:
                    this.typeSpinner.setSelection(4);
                    return;
                case 6:
                    this.typeSpinner.setSelection(5);
                    return;
                case 7:
                    this.typeSpinner.setSelection(6);
                    return;
                case 8:
                    this.typeSpinner.setSelection(7);
                    return;
                case 9:
                    this.typeSpinner.setSelection(8);
                    return;
                case 10:
                    this.typeSpinner.setSelection(9);
                    return;
                case 11:
                    this.typeSpinner.setSelection(10);
                    return;
                default:
            }
        }
        return;
    }

    public void typeChanged()
    {
        com.bisimplex.firebooru.danbooru.ServerItemType v0 = this.getType();
        switch (com.bisimplex.firebooru.fragment.ServerFormFragment$4.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType[v0.ordinal()]) {
            case 1:
                this.apiKeyInputLayout.setVisibility(0);
                this.passInputLayout.setVisibility(8);
                this.userInputLayout.setVisibility(0);
                this.ratingCheck.setVisibility(0);
                this.infoTextView.setText(2131886604);
                break;
            case 2:
                this.apiKeyInputLayout.setVisibility(8);
                this.passInputLayout.setVisibility(8);
                this.userInputLayout.setVisibility(8);
                this.ratingCheck.setVisibility(0);
                this.infoTextView.setText("");
                break;
            case 3:
                this.passInputLayout.setVisibility(0);
                this.userInputLayout.setVisibility(0);
                this.apiKeyInputLayout.setVisibility(8);
                this.ratingCheck.setVisibility(0);
                this.infoTextView.setText("");
                break;
            case 4:
                this.apiKeyInputLayout.setVisibility(0);
                this.passInputLayout.setVisibility(8);
                this.userInputLayout.setVisibility(0);
                this.ratingCheck.setVisibility(0);
                this.infoTextView.setText(2131886601);
                break;
            case 5:
            case 10:
                this.apiKeyInputLayout.setVisibility(8);
                this.passInputLayout.setVisibility(8);
                this.userInputLayout.setVisibility(8);
                this.ratingCheck.setVisibility(8);
                this.infoTextView.setText("");
                break;
            case 6:
                this.apiKeyInputLayout.setVisibility(0);
                this.passInputLayout.setVisibility(8);
                this.userInputLayout.setVisibility(8);
                this.ratingCheck.setVisibility(0);
                this.infoTextView.setText("");
                break;
            case 7:
                this.apiKeyInputLayout.setVisibility(0);
                this.passInputLayout.setVisibility(8);
                this.userInputLayout.setVisibility(8);
                this.ratingCheck.setVisibility(8);
                this.infoTextView.setText(2131886602);
                break;
            case 8:
                this.apiKeyInputLayout.setVisibility(0);
                this.passInputLayout.setVisibility(8);
                this.userInputLayout.setVisibility(0);
                this.ratingCheck.setVisibility(0);
                this.infoTextView.setText(2131886603);
                break;
            case 9:
                this.apiKeyInputLayout.setVisibility(0);
                this.passInputLayout.setVisibility(8);
                this.userInputLayout.setVisibility(8);
                this.ratingCheck.setVisibility(8);
                this.infoTextView.setText(2131886605);
                break;
            case 11:
                this.apiKeyInputLayout.setVisibility(0);
                this.passInputLayout.setVisibility(8);
                this.userInputLayout.setVisibility(8);
                this.ratingCheck.setVisibility(8);
                this.infoTextView.setText("");
                break;
            default:
        }
        this.data.setType(v0);
        return;
    }

    public boolean validateUrl()
    {
        if (this.isValidUrl()) {
            return 1;
        } else {
            this.showMessage(2131887239, com.bisimplex.firebooru.activity.MessageType.Error);
            return 0;
        }
    }
}
