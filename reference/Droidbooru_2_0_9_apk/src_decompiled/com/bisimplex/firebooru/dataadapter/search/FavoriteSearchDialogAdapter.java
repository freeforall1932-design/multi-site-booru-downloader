package com.bisimplex.firebooru.dataadapter.search;
public class FavoriteSearchDialogAdapter extends com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter {
    protected static final String EXCLUDE_TAGS = "EXCLUDE_TAGS";
    protected static final String EXT_SELECTOR = "EXT_SELECTOR";
    protected static final String RATING_SELECTOR = "RATING_SELECTOR";
    protected static final String RESET_BUTTON = "RESET_BUTTON";
    protected static final String SERVER_SELECTOR = "SERVER_SELECTOR";
    protected static final String SORT_SELECTOR = "SORT_SELECTOR";
    protected static final String SOURCE_FIELD = "SOURCE_FIELD";
    protected static final String TAGS = "TAGS";
    private final java.util.List fileTypeOptions;
    protected final java.util.List fullServerList;
    private final java.util.List fullServerOptions;
    private com.bisimplex.firebooru.network.SourceQuery query;
    private final java.util.List ratingOptions;
    private final java.util.List sortOptions;

    public FavoriteSearchDialogAdapter(android.content.Context p8, com.bisimplex.firebooru.network.SourceQuery p9)
    {
        super(p8);
        super.query = p9;
        java.util.ArrayList v9_9 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServers();
        super.fullServerList = v9_9;
        com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v0_23 = new java.util.ArrayList();
        super.fullServerOptions = v0_23;
        v0_23.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", p8.getString(2131886145), p8.getString(2131886984)));
        java.util.ArrayList v9_1 = v9_9.iterator();
        while (v9_1.hasNext()) {
            com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v0_7 = ((com.bisimplex.firebooru.danbooru.ServerItem) v9_1.next());
            super.fullServerOptions.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(v0_7.getServerId()), v0_7.getServerName(), v0_7.getExtraInfo()));
        }
        super.sortOptions = new java.util.ArrayList();
        java.util.ArrayList v9_6 = p8.getResources().getStringArray(2130903059);
        com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v0_5 = 0;
        while (v0_5 < v9_6.length) {
            super.sortOptions.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(v0_5), v9_6[v0_5], 0));
            v0_5++;
        }
        java.util.ArrayList v9_8 = new java.util.ArrayList();
        super.ratingOptions = v9_8;
        v9_8.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", p8.getString(2131886142), 0));
        v9_8.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("g", p8.getString(2131886647), 0));
        v9_8.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("s", p8.getString(2131887137), 0));
        v9_8.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("q", p8.getString(2131887050), 0));
        v9_8.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("e", p8.getString(2131886572), 0));
        java.util.ArrayList v9_11 = new java.util.ArrayList();
        super.fileTypeOptions = v9_11;
        v9_11.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", p8.getString(2131886142), 0));
        v9_11.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(".jpg", p8.getString(2131886732), 0));
        v9_11.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(".png", p8.getString(2131887027), 0));
        v9_11.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(".gif", p8.getString(2131886648), 0));
        v9_11.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(".webm", p8.getString(2131887264), 0));
        v9_11.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(".mp4", p8.getString(2131886902), 0));
        super.fillForm();
        super.bindValueChangeListener();
        return;
    }

    protected void fillForm()
    {
        int v12_0;
        java.util.List v0_0 = this.getContext();
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.TitleItem("title", v0_0.getString(2131887113), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem("TAGS", v0_0.getString(2131886470), this.query.getText(), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem("EXCLUDE_TAGS", v0_0.getString(2131886516), ((String) this.query.getExtraParams().get("FILTER_EXCLUDE_TAGS")), 1));
        java.util.List v1_37 = ((String) this.query.getExtraParams().get("FILTER_SERVER_URL"));
        com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v2_22 = ((String) this.query.getExtraParams().get("FILTER_SORT_ID"));
        String v3_12 = ((String) this.query.getExtraParams().get("FILTER_SOURCE"));
        String v4_12 = ((String) this.query.getExtraParams().get("FILTER_RATING"));
        String v6_8 = ((String) this.query.getExtraParams().get("FILTER_EXT"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("subtitle2", v0_0.getString(2131886591), 0));
        int v8_0 = 0;
        if (android.text.TextUtils.isEmpty(v1_37)) {
            v12_0 = 0;
        } else {
            boolean v5_11 = this.fullServerOptions.iterator();
            while (v5_11.hasNext()) {
                com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem v9_14 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v5_11.next());
                if (v9_14.getLabel().equalsIgnoreCase(v1_37)) {
                    v12_0 = this.fullServerOptions.indexOf(v9_14);
                }
            }
        }
        int v12_1;
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SERVER_SELECTOR", v0_0.getString(2131887138), v12_0, this.fullServerOptions, 1));
        if (android.text.TextUtils.isEmpty(v2_22)) {
            v12_1 = 0;
        } else {
            java.util.List v1_7 = this.sortOptions.iterator();
            while (v1_7.hasNext()) {
                boolean v5_2 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v1_7.next());
                if (v5_2.getKey().equalsIgnoreCase(v2_22)) {
                    v12_1 = this.sortOptions.indexOf(v5_2);
                }
            }
        }
        int v12_2;
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SORT_SELECTOR", v0_0.getString(2131887170), v12_1, this.sortOptions, 1));
        if (android.text.TextUtils.isEmpty(v6_8)) {
            v12_2 = 0;
        } else {
            java.util.List v1_14 = this.fileTypeOptions.iterator();
            while (v1_14.hasNext()) {
                com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v2_3 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v1_14.next());
                if (v2_3.getKey().equalsIgnoreCase(v6_8)) {
                    v12_2 = this.fileTypeOptions.indexOf(v2_3);
                }
            }
        }
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("EXT_SELECTOR", v0_0.getString(2131886590), v12_2, this.fileTypeOptions, 1));
        if (!android.text.TextUtils.isEmpty(v4_12)) {
            java.util.List v1_21 = this.ratingOptions.iterator();
            while (v1_21.hasNext()) {
                com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v2_6 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v1_21.next());
                if (v2_6.getKey().equalsIgnoreCase(v4_12)) {
                    v8_0 = this.ratingOptions.indexOf(v2_6);
                    break;
                }
            }
        }
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("RATING_SELECTOR", v0_0.getString(2131887070), v8_0, this.ratingOptions, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.TextItem("SOURCE_FIELD", v0_0.getString(2131887173), v3_12, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem("RESET_BUTTON", v0_0.getString(2131887111), com.bisimplex.firebooru.dataadapter.search.ActionType.Search, v0_0.getString(2131887086), com.bisimplex.firebooru.dataadapter.search.ActionType.Reset, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty1"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty2"));
        return;
    }

    public com.bisimplex.firebooru.network.SourceQuery getResultQuery()
    {
        com.bisimplex.firebooru.network.SourceQuery v1_1 = new com.bisimplex.firebooru.network.SourceQuery(((String) ((com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem) this.findEditableItemByKey("TAGS")).getValue()));
        String v0_7 = ((String) ((com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem) this.findEditableItemByKey("EXCLUDE_TAGS")).getValue());
        if (!android.text.TextUtils.isEmpty(v0_7)) {
            v1_1.getExtraParams().put("FILTER_EXCLUDE_TAGS", v0_7.trim());
        }
        String v0_15 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SERVER_SELECTOR")).getValue());
        if (!android.text.TextUtils.isEmpty(v0_15.getKey())) {
            v1_1.getExtraParams().put("FILTER_SERVER_URL", v0_15.getLabel());
        }
        String v0_21 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SORT_SELECTOR")).getValue());
        if (!android.text.TextUtils.isEmpty(v0_21.getKey())) {
            v1_1.getExtraParams().put("FILTER_SORT_ID", v0_21.getKey());
        }
        String v0_28 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("EXT_SELECTOR")).getValue());
        if (!android.text.TextUtils.isEmpty(v0_28.getKey())) {
            v1_1.getExtraParams().put("FILTER_EXT", v0_28.getKey());
        }
        String v0_35 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("RATING_SELECTOR")).getValue());
        if (!android.text.TextUtils.isEmpty(v0_35.getKey())) {
            v1_1.getExtraParams().put("FILTER_RATING", v0_35.getKey());
        }
        String v0_3 = ((String) ((com.bisimplex.firebooru.dataadapter.search.TextItem) this.findEditableItemByKey("SOURCE_FIELD")).getValue());
        if (v0_3 != null) {
            v0_3 = v0_3.trim();
        }
        if (!android.text.TextUtils.isEmpty(v0_3)) {
            v1_1.getExtraParams().put("FILTER_SOURCE", v0_3);
        }
        return v1_1;
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getSelectedServer()
    {
        int v0_6 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SERVER_SELECTOR")).getValue());
        if (!android.text.TextUtils.isEmpty(v0_6.getKey())) {
            int v0_2 = Integer.parseInt(v0_6.getKey());
            java.util.Iterator v1_2 = this.fullServerList.iterator();
            while (v1_2.hasNext()) {
                com.bisimplex.firebooru.danbooru.ServerItem v3_2 = ((com.bisimplex.firebooru.danbooru.ServerItem) v1_2.next());
                if (v3_2.getServerId() == v0_2) {
                    return v3_2;
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    protected void notifyValueChanged(com.bisimplex.firebooru.dataadapter.search.Item p3)
    {
        if (p3.getKey().startsWith("SERVER_SELECTOR")) {
            this.notifyItemChanged(this.data.indexOf(p3));
        }
        super.notifyValueChanged(p3);
        return;
    }
}
