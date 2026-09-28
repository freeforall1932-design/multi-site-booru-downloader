package com.bisimplex.firebooru.dataadapter;
public class ServerFormDataAdapter extends com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter {
    public static final String APIKEY = "APIKEY";
    public static final String APIKEY_HINT = "APIKEY_HINT";
    public static final String PASSWORD = "PASSWORD";
    public static final String RATING_FILTER_ENABLED = "RATING_FILTER_ENABLED";
    public static final String SAVE_BUTTON = "SAVE_BUTTON";
    private static final String SERVER_ID = "SERVER_ID";
    private static final String TYPE = "TYPE";
    private static final String URL = "URL";
    public static final String USER_NAME = "USER_NAME";
    public static final String VALIDATE_CLIENT = "VALIDATE_CLIENT";
    public static final String VALIDATE_HINT = "VALIDATE_HINT";
    private final com.bisimplex.firebooru.danbooru.ServerItem serverItem;
    private final java.util.List typeOptions;

    public ServerFormDataAdapter(android.content.Context p1, com.bisimplex.firebooru.danbooru.ServerItem p2)
    {
        super(p1);
        super.serverItem = p2;
        java.util.ArrayList v1_2 = new java.util.ArrayList(9);
        super.typeOptions = v1_2;
        v1_2.add(super.optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru));
        v1_2.add(super.optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2));
        v1_2.add(super.optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru));
        v1_2.add(super.optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111));
        v1_2.add(super.optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru));
        v1_2.add(super.optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621));
        v1_2.add(super.optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus));
        v1_2.add(super.optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie));
        v1_2.add(super.optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeKemono));
        v1_2.add(super.optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails));
        super.fillForm();
        return;
    }

    private java.util.List buildExtraOptions()
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        int v1_1 = this.getContext();
        int v2_7 = this.serverItem.getType();
        if ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) || ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) || ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) || ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111) || (v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621))))) {
            v0_1.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("RATING_FILTER_ENABLED", v1_1.getString(2131886657), Boolean.valueOf(this.serverItem.isRatingFilterEnabled()), 1));
        }
        if ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) || ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) || ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) || (v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621)))) {
            v0_1.add(new com.bisimplex.firebooru.dataadapter.search.TextItem("USER_NAME", v1_1.getString(2131886661), this.serverItem.getUserName(), 1));
        }
        if (v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) {
            v0_1.add(new com.bisimplex.firebooru.dataadapter.search.TextItem("PASSWORD", v1_1.getString(2131886656), this.serverItem.getPassword(), 1));
        }
        if ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) || ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) || ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) || ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) || ((v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) || (v2_7 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails)))))) {
            v0_1.add(new com.bisimplex.firebooru.dataadapter.search.TextItem("APIKEY", v1_1.getString(2131886655), this.serverItem.getApiKey(), 1));
        }
        int v1_0;
        if (v2_7 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
            if (v2_7 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) {
                if ((v2_7 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) && (v2_7 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails)) {
                    if (v2_7 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) {
                        if (v2_7 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) {
                            v1_0 = 0;
                        } else {
                            v1_0 = v1_1.getString(2131886605);
                        }
                    } else {
                        v1_0 = v1_1.getString(2131886603);
                    }
                } else {
                    v1_0 = v1_1.getString(2131886602);
                }
            } else {
                v1_0 = v1_1.getString(2131886601);
            }
        } else {
            v1_0 = v1_1.getString(2131886604);
        }
        if (!android.text.TextUtils.isEmpty(v1_0)) {
            v0_1.add(new com.bisimplex.firebooru.dataadapter.search.LabelItem("APIKEY_HINT", 0, v1_0));
        }
        return v0_1;
    }

    private void fillForm()
    {
        this.data.clear();
        String v0_1 = this.getContext();
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance();
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("TYPE", v0_1.getString(2131887224), this.optionIndexOf(this.serverItem.getType()), this.typeOptions, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.TextItem("URL", v0_1.getString(2131886660), this.serverItem.getUrl(), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("VALIDATE_CLIENT", v0_1.getString(2131887244), com.bisimplex.firebooru.dataadapter.search.ActionType.Search, 1));
        java.util.List v1_2 = this.buildExtraOptions();
        if (!v1_2.isEmpty()) {
            this.data.addAll(v1_2);
        }
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("SAVE_BUTTON", v0_1.getString(2131887099), com.bisimplex.firebooru.dataadapter.search.ActionType.Add, 1));
        this.bindValueChangeListener();
        return;
    }

    private com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption optionForServerType(com.bisimplex.firebooru.danbooru.ServerItemType p4)
    {
        return new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(p4.getValue()), com.bisimplex.firebooru.danbooru.ServerItemType.publicName(p4), 0);
    }

    private int optionIndexOf(com.bisimplex.firebooru.danbooru.ServerItemType p5)
    {
        java.util.Iterator v0_1 = this.typeOptions.iterator();
        while (v0_1.hasNext()) {
            com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v1_0 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_1.next());
            if (v1_0.getKey().equalsIgnoreCase(String.valueOf(p5.getValue()))) {
                return this.typeOptions.indexOf(v1_0);
            }
        }
        return 0;
    }

    public void applyChanges()
    {
        boolean v1_13 = ((com.bisimplex.firebooru.dataadapter.search.TextItem) this.findEditableItemByKey("URL"));
        String v2_1 = ((com.bisimplex.firebooru.dataadapter.search.TextItem) this.findEditableItemByKey("USER_NAME"));
        com.bisimplex.firebooru.dataadapter.search.TextItem v3_2 = ((com.bisimplex.firebooru.dataadapter.search.TextItem) this.findEditableItemByKey("PASSWORD"));
        com.bisimplex.firebooru.dataadapter.search.TextItem v4_2 = ((com.bisimplex.firebooru.dataadapter.search.TextItem) this.findEditableItemByKey("APIKEY"));
        com.bisimplex.firebooru.dataadapter.search.CheckboxItem v5_2 = ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) this.findEditableItemByKey("RATING_FILTER_ENABLED"));
        this.serverItem.setType(com.bisimplex.firebooru.danbooru.ServerItemType.fromInteger(Integer.parseInt(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("TYPE")).getValue()).getKey())));
        this.serverItem.setUrl(((String) v1_13.getValue()));
        if (v2_1 != null) {
            this.serverItem.setUserName(((String) v2_1.getValue()));
        } else {
            this.serverItem.setUserName(0);
        }
        if (v3_2 != null) {
            this.serverItem.setPassword(((String) v3_2.getValue()));
        } else {
            this.serverItem.setPassword(0);
        }
        if (v4_2 != null) {
            this.serverItem.setApiKey(((String) v4_2.getValue()));
        } else {
            this.serverItem.setApiKey(0);
        }
        if (v5_2 != null) {
            this.serverItem.setRatingFilterEnabled(((Boolean) v5_2.getValue()).booleanValue());
            return;
        } else {
            this.serverItem.setRatingFilterEnabled(0);
            return;
        }
    }

    protected void notifyValueChanged(com.bisimplex.firebooru.dataadapter.search.Item p3)
    {
        if (((com.bisimplex.firebooru.dataadapter.search.EditableItem) p3).isEnabled()) {
            if (!((com.bisimplex.firebooru.dataadapter.search.EditableItem) p3).getKey().equalsIgnoreCase("TYPE")) {
                if (((com.bisimplex.firebooru.dataadapter.search.EditableItem) p3).getKey().equalsIgnoreCase("URL")) {
                    this.applyChanges();
                    this.updateServerOptions();
                }
            } else {
                this.applyChanges();
                this.updateServerOptions();
            }
            super.notifyValueChanged(((com.bisimplex.firebooru.dataadapter.search.EditableItem) p3));
            return;
        } else {
            return;
        }
    }

    protected void updateServerOptions()
    {
        int v0_2 = (this.data.size() - 1);
        int v1_4 = new java.util.ArrayList();
        java.util.List v3_2 = 3;
        while (v3_2 < v0_2) {
            v1_4.add(((com.bisimplex.firebooru.dataadapter.search.Item) this.data.get(v3_2)));
            v3_2++;
        }
        this.data.removeAll(v1_4);
        this.notifyItemRangeRemoved(3, v1_4.size());
        int v1_1 = this.buildExtraOptions();
        if (!v1_1.isEmpty()) {
            this.data.addAll(3, v1_1);
            this.notifyItemRangeInserted(v0_2, v1_1.size());
        }
        this.bindValueChangeListener();
        return;
    }

    public void updateURL(String p2)
    {
        com.bisimplex.firebooru.dataadapter.search.TextItem v0_2 = ((com.bisimplex.firebooru.dataadapter.search.TextItem) this.findEditableItemByKey("URL"));
        if (v0_2 != null) {
            v0_2.setValue(p2);
            this.notifyItemChanged(this.data.indexOf(v0_2));
        }
        return;
    }
}
