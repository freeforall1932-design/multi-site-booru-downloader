package com.bisimplex.firebooru.fragment;
public class DynamicServerFormFragment extends com.bisimplex.firebooru.fragment.BaseFragment {
    public static final String ServerFormFragmentData = "ServerFormFragment_Data";
    com.bisimplex.firebooru.dataadapter.ServerFormDataAdapter adapter;
    private final com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener adapterListener;
    private com.bisimplex.firebooru.custom.BottomPaddingDecoration bottomPaddingDecoration;
    private okhttp3.Call currentOperation;
    private com.bisimplex.firebooru.danbooru.ServerItem data;
    private final okhttp3.Callback pingCallback;
    androidx.recyclerview.widget.RecyclerView recyclerView;

    public static synthetic void $r8$lambda$BQaXsP2DlCRihjgWIEBo1wwGsmI(com.bisimplex.firebooru.fragment.DynamicServerFormFragment p0)
    {
        p0.lambda$showMessage$0();
        return;
    }

    public static synthetic void $r8$lambda$_0BLGuDH-8aHkfYbzUw1ZCf-sBk(com.bisimplex.firebooru.fragment.DynamicServerFormFragment p0)
    {
        p0.lambda$showMessage$2();
        return;
    }

    public static synthetic void $r8$lambda$cN_menFZhB2Q2OHkbkxnxahOkj0(com.bisimplex.firebooru.fragment.DynamicServerFormFragment p0)
    {
        p0.lambda$showMessage$1();
        return;
    }

    static bridge synthetic com.bisimplex.firebooru.custom.BottomPaddingDecoration -$$Nest$fgetbottomPaddingDecoration(com.bisimplex.firebooru.fragment.DynamicServerFormFragment p0)
    {
        return p0.bottomPaddingDecoration;
    }

    static bridge synthetic com.bisimplex.firebooru.danbooru.ServerItem -$$Nest$fgetdata(com.bisimplex.firebooru.fragment.DynamicServerFormFragment p0)
    {
        return p0.data;
    }

    static bridge synthetic void -$$Nest$maskValidateClient(com.bisimplex.firebooru.fragment.DynamicServerFormFragment p0)
    {
        p0.askValidateClient();
        return;
    }

    static bridge synthetic void -$$Nest$msave(com.bisimplex.firebooru.fragment.DynamicServerFormFragment p0)
    {
        p0.save();
        return;
    }

    static bridge synthetic void -$$Nest$mvalidateClient(com.bisimplex.firebooru.fragment.DynamicServerFormFragment p0)
    {
        p0.validateClient();
        return;
    }

    public DynamicServerFormFragment()
    {
        this.adapterListener = new com.bisimplex.firebooru.fragment.DynamicServerFormFragment$1(this);
        this.pingCallback = new com.bisimplex.firebooru.fragment.DynamicServerFormFragment$3(this);
        return;
    }

    private void askValidateClient()
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_0 = this.adapter;
        if ((v0_0 != null) && (this.data != null)) {
            v0_0.applyChanges();
            if (!android.text.TextUtils.isEmpty(this.data.getUrl())) {
                com.google.android.material.dialog.MaterialAlertDialogBuilder v0_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireActivity());
                v0_2.setTitle(2131887244).setMessage(2131887247);
                v0_2.setPositiveButton(2131887243, new com.bisimplex.firebooru.fragment.DynamicServerFormFragment$2(this)).setNegativeButton(2131886205, 0);
                v0_2.show();
            } else {
                this.showMessage(2131886488, com.bisimplex.firebooru.activity.MessageType.Error);
                return;
            }
        }
        return;
    }

    private synthetic void lambda$showMessage$0()
    {
        this.adapter.setControlsEnabled(0);
        return;
    }

    private synthetic void lambda$showMessage$1()
    {
        this.adapter.setControlsEnabled(1);
        return;
    }

    private synthetic void lambda$showMessage$2()
    {
        this.HideLoading();
        this.goBackInStack();
        return;
    }

    private void pingToService(com.bisimplex.firebooru.danbooru.BooruProvider p5)
    {
        try {
            com.bisimplex.firebooru.activity.MessageType v0_0 = p5.getPingServiceUrl();
            okhttp3.OkHttpClient v1 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient();
            com.bisimplex.firebooru.activity.MessageType v0_1 = new okhttp3.Request$Builder().url(v0_0).header("User-Agent", p5.getUserAgent());
        } catch (String v5_4) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v5_4);
            String v5_5 = v5_4.getLocalizedMessage();
            if (android.text.TextUtils.isEmpty(v5_5)) {
                v5_5 = this.getString(2131887239);
            }
            this.showMessage(v5_5, com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        }
        if (p5.getServerDescription().getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO) {
            v0_1.addHeader("Accept-Encoding", "identity");
        }
        String v5_8 = v1.newCall(v0_1.build());
        this.currentOperation = v5_8;
        v5_8.enqueue(this.pingCallback);
        return;
    }

    private void save()
    {
        this.adapter.applyChanges();
        boolean v0_3 = this.data.getUrl();
        if (!android.text.TextUtils.isEmpty(v0_3)) {
            String v1_4;
            int v2 = 1;
            if ((this.isValidUrl(v0_3)) || ((v0_3.startsWith("https://")) || (v0_3.startsWith("http://")))) {
                v1_4 = 0;
            } else {
                v0_3 = String.format("https://%s", new Object[] {v0_3}));
                v1_4 = 1;
            }
            if (this.validateUrl(v0_3)) {
                if (!v0_3.endsWith("/")) {
                    v2 = v1_4;
                } else {
                    v0_3 = v0_3.substring(0, (v0_3.length() - 1));
                }
                if (v2 != 0) {
                    this.data.setUrl(v0_3);
                }
                if ((this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie) || ((this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) || (this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails))) {
                    this.data.setRatingFilterEnabled(0);
                }
                if ((this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) && (!android.text.TextUtils.isEmpty(this.data.getApiKey()))) {
                    boolean v0_17 = this.data.getApiKey();
                    if ((!com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase("api_key", v0_17)) || ((!com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase("user_id", v0_17)) || (!v0_17.startsWith("&")))) {
                        this.showMessage(2131886620, com.bisimplex.firebooru.activity.MessageType.Error);
                        return;
                    }
                }
                this.showMessage("", com.bisimplex.firebooru.activity.MessageType.Loading);
                this.hideKeyboard();
                this.pingToService(new com.bisimplex.firebooru.danbooru.BooruProvider(this.data));
                return;
            }
        }
        return;
    }

    private void validateClient()
    {
        this.adapter.applyChanges();
        String v0_3 = this.data.getUrl();
        if (!android.text.TextUtils.isEmpty(v0_3)) {
            if ((!this.isValidUrl(v0_3)) && ((!v0_3.startsWith("https://")) && (!v0_3.startsWith("http://")))) {
                v0_3 = String.format("https://%s", new Object[] {v0_3}));
                this.data.setUrl(v0_3);
            }
            if (this.validateUrl(v0_3)) {
                com.bisimplex.firebooru.danbooru.ServerItem v1_8 = new android.os.Bundle(1);
                v1_8.putString("SERVER_URL", v0_3);
                String v0_5 = new com.bisimplex.firebooru.view.ValidateClientDialog();
                v0_5.setArguments(v1_8);
                v0_5.show(this.getParentFragmentManager(), "ValidateClientDialog");
                return;
            }
        }
        return;
    }

    protected void askToValidateClient()
    {
        this.validateClient();
        return;
    }

    protected void confirmSaveServerWithMIME(String p5)
    {
        com.bisimplex.firebooru.activity.MessageType v0_3 = new com.bisimplex.firebooru.danbooru.BooruProvider(this.data);
        if (this.data.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
            com.bisimplex.firebooru.danbooru.ServerItem v1_3 = this.data;
            v1_3.setPasswordKey(v0_3.encriptPasswordWithServiceUrl(v1_3.getUrl(), this.data.getPassword()));
        }
        if ((android.text.TextUtils.isEmpty(p5)) || ((!p5.equalsIgnoreCase("text/html")) || ((this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111) || (this.data.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie)))) {
            int v5_6 = this.data;
            v5_6.setServerName(v5_6.getUrl());
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

    protected android.view.View getInsetContentView()
    {
        return this.recyclerView;
    }

    public boolean getShouldResetStack()
    {
        return 0;
    }

    public boolean isValidUrl(String p2)
    {
        if ((!android.text.TextUtils.isEmpty(p2)) && ((!p2.equalsIgnoreCase("http://")) && (!p2.equalsIgnoreCase("https://")))) {
            return android.webkit.URLUtil.isValidUrl(p2);
        } else {
            return 0;
        }
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        return p2.inflate(2131558634, p3, 0);
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        if (this.data != null) {
            String v0_2 = com.bisimplex.firebooru.network.HttpClient.getGson().toJson(this.data);
            if (!android.text.TextUtils.isEmpty(v0_2)) {
                p3.putString("ServerFormFragment_Data", v0_2);
            }
        }
        return;
    }

    public void onViewCreated(android.view.View p4, android.os.Bundle p5)
    {
        super.onViewCreated(p4, p5);
        if (this.data == null) {
            com.bisimplex.firebooru.danbooru.ServerItem v5_2;
            com.bisimplex.firebooru.danbooru.ServerItem v5_13 = this.getArguments();
            if (v5_13 == null) {
                v5_2 = 0;
            } else {
                v5_2 = v5_13.getString("ServerFormFragment_Data");
            }
            if (!android.text.TextUtils.isEmpty(v5_2)) {
                this.data = ((com.bisimplex.firebooru.danbooru.ServerItem) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v5_2, com.bisimplex.firebooru.danbooru.ServerItem));
            } else {
                this.data = new com.bisimplex.firebooru.danbooru.ServerItem();
            }
        }
        androidx.appcompat.widget.Toolbar v4_2 = ((androidx.recyclerview.widget.RecyclerView) p4.findViewById(2131362449));
        this.recyclerView = v4_2;
        v4_2.setItemAnimator(0);
        this.recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this.requireContext()));
        com.bisimplex.firebooru.danbooru.ServerItem v5_12 = new com.bisimplex.firebooru.custom.BottomPaddingDecoration(((int) android.util.TypedValue.applyDimension(1, 1132068864, this.getResources().getDisplayMetrics())));
        this.bottomPaddingDecoration = v5_12;
        this.recyclerView.addItemDecoration(v5_12);
        androidx.appcompat.widget.Toolbar v4_10 = new com.bisimplex.firebooru.dataadapter.ServerFormDataAdapter(this.requireContext(), this.data);
        this.adapter = v4_10;
        v4_10.setListener(this.adapterListener);
        this.recyclerView.setAdapter(this.adapter);
        this.getVisibleBar().setTitle(2131887138);
        return;
    }

    public void onViewStateRestored(android.os.Bundle p3)
    {
        super.onViewStateRestored(p3);
        if ((p3 != null) && (this.data == null)) {
            com.bisimplex.firebooru.danbooru.ServerItem v3_3 = p3.getString("ServerFormFragment_Data");
            if (!android.text.TextUtils.isEmpty(v3_3)) {
                this.data = ((com.bisimplex.firebooru.danbooru.ServerItem) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v3_3, com.bisimplex.firebooru.danbooru.ServerItem));
            }
        }
        return;
    }

    public void showMessage(String p3, com.bisimplex.firebooru.activity.MessageType p4)
    {
        super.showMessage(p3, p4);
        if (this.adapter != null) {
            if (p4 != com.bisimplex.firebooru.activity.MessageType.Loading) {
                if (p4 != com.bisimplex.firebooru.activity.MessageType.Error) {
                    if (p4 == com.bisimplex.firebooru.activity.MessageType.Success) {
                        this.recyclerView.postDelayed(new com.bisimplex.firebooru.fragment.DynamicServerFormFragment$$ExternalSyntheticLambda2(this), 1500);
                    }
                } else {
                    this.recyclerView.post(new com.bisimplex.firebooru.fragment.DynamicServerFormFragment$$ExternalSyntheticLambda1(this));
                    return;
                }
            } else {
                this.recyclerView.post(new com.bisimplex.firebooru.fragment.DynamicServerFormFragment$$ExternalSyntheticLambda0(this));
                return;
            }
        }
        return;
    }

    public boolean validateUrl(String p2)
    {
        if (this.isValidUrl(p2)) {
            return 1;
        } else {
            this.showMessage(2131887239, com.bisimplex.firebooru.activity.MessageType.Error);
            return 0;
        }
    }
}
