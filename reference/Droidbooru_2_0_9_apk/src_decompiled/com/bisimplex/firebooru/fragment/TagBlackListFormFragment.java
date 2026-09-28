package com.bisimplex.firebooru.fragment;
public class TagBlackListFormFragment extends com.bisimplex.firebooru.fragment.FormBaseFragment {
    public static final String RULE_ID = "RULE_ID";
    private static final String SERVERS_JSON = "SERVERS_JSON";
    private com.bisimplex.firebooru.model.BannedTag data;
    private long ruleID;
    private com.google.android.material.button.MaterialButton saveButton;
    private java.util.List serverItems;
    private android.view.ViewGroup serversLayout;
    private android.widget.TextView serversTextView;
    private android.widget.EditText tagText;

    static bridge synthetic java.util.List -$$Nest$fgetserverItems(com.bisimplex.firebooru.fragment.TagBlackListFormFragment p0)
    {
        return p0.serverItems;
    }

    static bridge synthetic void -$$Nest$mselectServers(com.bisimplex.firebooru.fragment.TagBlackListFormFragment p0)
    {
        p0.selectServers();
        return;
    }

    static bridge synthetic void -$$Nest$mupdateButton(com.bisimplex.firebooru.fragment.TagBlackListFormFragment p0)
    {
        p0.updateButton();
        return;
    }

    public TagBlackListFormFragment()
    {
        this.ruleID = -1;
        return;
    }

    private void selectServers()
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_0 = this.serverItems;
        if ((v0_0 != null) && (!v0_0.isEmpty())) {
            com.google.android.material.dialog.MaterialAlertDialogBuilder v0_3 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireActivity());
            com.google.android.material.dialog.MaterialAlertDialogBuilder v1_1 = new String[this.serverItems.size()];
            com.bisimplex.firebooru.fragment.TagBlackListFormFragment$4 v2_2 = new boolean[this.serverItems.size()];
            int v3_2 = new boolean[this.serverItems.size()];
            com.google.android.material.dialog.MaterialAlertDialogBuilder v4_0 = 0;
            while (v4_0 < this.serverItems.size()) {
                com.bisimplex.firebooru.fragment.TagBlackListFormFragment$7 v5_6 = ((com.bisimplex.firebooru.danbooru.ServerItem) this.serverItems.get(v4_0));
                v1_1[v4_0] = v5_6.getServerName();
                com.bisimplex.firebooru.fragment.TagBlackListFormFragment$7 v5_7 = v5_6.isSelected();
                v2_2[v4_0] = v5_7;
                v3_2[v4_0] = v5_7;
                v4_0++;
            }
            v0_3.setTitle(2131886159).setMultiChoiceItems(v1_1, v2_2, new com.bisimplex.firebooru.fragment.TagBlackListFormFragment$7(this, v3_2)).setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.TagBlackListFormFragment$6(this, v3_2)).setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.TagBlackListFormFragment$5(this)).setNeutralButton(2131886145, new com.bisimplex.firebooru.fragment.TagBlackListFormFragment$4(this));
            v0_3.show();
        }
        return;
    }

    private void updateButton()
    {
        int v0_1 = new StringBuilder();
        java.util.Iterator v1_1 = this.serverItems.iterator();
        int v2 = 0;
        while (v1_1.hasNext()) {
            String v3_1 = ((com.bisimplex.firebooru.danbooru.ServerItem) v1_1.next());
            if (v3_1.isSelected()) {
                v2++;
                v0_1.append(v3_1.getServerName());
                v0_1.append("\n");
            }
        }
        if (v2 <= 0) {
            this.updateButton(0);
            return;
        } else {
            this.updateButton(v0_1.toString().trim());
            return;
        }
    }

    private void updateButton(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            this.serversTextView.setText(p2);
            return;
        } else {
            this.serversTextView.setText(2131886145);
            return;
        }
    }

    protected android.view.View getInsetContentView()
    {
        return this.requireView().findViewById(2131362492);
    }

    public String getiOsFragmentName()
    {
        return "NewServer3ViewController";
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        return p2.inflate(2131558659, p3, 0);
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        if (this.serverItems != null) {
            p3.putString("SERVERS_JSON", com.bisimplex.firebooru.network.HttpClient.getGson().toJson(this.serverItems));
        }
        return;
    }

    public void onViewCreated(android.view.View p7, android.os.Bundle p8)
    {
        super.onViewCreated(p7, p8);
        int v0_8 = this.getArguments();
        if (v0_8 != 0) {
            this.ruleID = v0_8.getLong("RULE_ID", -1);
        }
        this.data = new com.bisimplex.firebooru.model.BannedTag();
        this.tagText = ((android.widget.EditText) p7.findViewById(2131362596));
        this.serversTextView = ((android.widget.TextView) p7.findViewById(2131362524));
        this.setTitle(2131886137);
        if (this.serverItems == null) {
            if (p8 == null) {
                int v0_13 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServers();
                this.serverItems = new java.util.ArrayList(v0_13.size());
                int v0_14 = v0_13.iterator();
                while (v0_14.hasNext()) {
                    int v1_7 = ((com.bisimplex.firebooru.danbooru.ServerItem) v0_14.next());
                    if (!v1_7.isDefault()) {
                        String v2_4 = new com.bisimplex.firebooru.danbooru.ServerItem();
                        v2_4.setServerId(((long) v1_7.getServerId()));
                        v2_4.setUrl(v1_7.getUrl());
                        this.serverItems.add(v2_4);
                    }
                }
            } else {
                this.serverItems = ((java.util.List) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(p8.getString("SERVERS_JSON"), new com.bisimplex.firebooru.fragment.TagBlackListFormFragment$1(this).getType()));
            }
        }
        int v0_22 = ((android.view.ViewGroup) p7.findViewById(2131362522));
        this.serversLayout = v0_22;
        v0_22.setOnClickListener(new com.bisimplex.firebooru.fragment.TagBlackListFormFragment$2(this));
        if ((p8 == null) && (this.ruleID >= 0)) {
            String[] v8_7 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadBannedTag(this.ruleID);
            if (v8_7 != null) {
                this.setTitle(2131886474);
                int v0_28 = new com.bisimplex.firebooru.model.BlacklistRule(v8_7.tagText, v8_7.getId().longValue());
                this.tagText.setText(v0_28.getTagString());
                String[] v8_1 = v0_28.getSites();
                if ((v8_1 != null) && (v8_1.length > 0)) {
                    int v0_2 = v8_1.length;
                    int v1_1 = 0;
                    while (v1_1 < v0_2) {
                        String v2_0 = v8_1[v1_1];
                        java.util.Iterator v3_1 = this.serverItems.iterator();
                        while (v3_1.hasNext()) {
                            com.bisimplex.firebooru.danbooru.ServerItem v4_2 = ((com.bisimplex.firebooru.danbooru.ServerItem) v3_1.next());
                            if (!v4_2.isSelected()) {
                                v4_2.setSelected(v4_2.getServerName().equalsIgnoreCase(v2_0));
                            }
                        }
                        v1_1++;
                    }
                }
                this.updateButton();
            }
        }
        com.google.android.material.button.MaterialButton v7_2 = ((com.google.android.material.button.MaterialButton) p7.findViewById(2131362481));
        this.saveButton = v7_2;
        v7_2.setOnClickListener(new com.bisimplex.firebooru.fragment.TagBlackListFormFragment$3(this));
        this.updateButton();
        return;
    }

    public void saveItem()
    {
        if (this.serverItems != null) {
            this.data.tagText = this.tagText.getText().toString().trim();
            if (!android.text.TextUtils.isEmpty(this.data.tagText)) {
                android.widget.EditText v0_5 = new java.util.ArrayList();
                com.bisimplex.firebooru.danbooru.DatabaseHelper v1_1 = this.serverItems.iterator();
                while (v1_1.hasNext()) {
                    String v2_6 = ((com.bisimplex.firebooru.danbooru.ServerItem) v1_1.next());
                    if (v2_6.isSelected()) {
                        v0_5.add(v2_6);
                    }
                }
                if (this.ruleID <= 0) {
                    com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().addBannedTag(this.data.tagText, v0_5);
                } else {
                    com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().updateBannedTag(this.data.tagText, v0_5, this.ruleID);
                }
                com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().updateBannedTags();
                this.hideKeyboardFrom(this.tagText);
                this.goBackInStack();
                return;
            }
        }
        return;
    }
}
