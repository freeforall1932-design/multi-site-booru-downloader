package com.bisimplex.firebooru.dataadapter.search;
public class ServerSearchDialogAdapter extends com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter {
    protected static final String RESET_BUTTON = "RESET_BUTTON";
    protected static final String TEXT = "TEXT";
    protected static final String TYPE_SELECTOR = "TYPE_SELECTOR";
    private com.bisimplex.firebooru.network.SourceQuery query;
    private final java.util.List serverTypeOptions;

    public ServerSearchDialogAdapter(android.content.Context p5, com.bisimplex.firebooru.network.SourceQuery p6)
    {
        super(p5);
        super.query = p6;
        if (p6 == null) {
            super.query = new com.bisimplex.firebooru.network.SourceQuery();
        }
        java.util.ArrayList v6_4 = new java.util.ArrayList();
        super.serverTypeOptions = v6_4;
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", p5.getString(2131886984), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru.getValue()), p5.getString(2131886291), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru.getValue()), p5.getString(2131886618), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2.getValue()), p5.getString(2131886292), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111.getValue()), p5.getString(2131886619), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie.getValue()), p5.getString(2131887157), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeIBSearch.getValue()), p5.getString(2131886704), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru.getValue()), p5.getString(2131887017), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621.getValue()), p5.getString(2131886447), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus.getValue()), p5.getString(2131886666), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO.getValue()), p5.getString(2131886182), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails.getValue()), p5.getString(2131886181), 0));
        v6_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeKemono.getValue()), p5.getString(2131886735), 0));
        super.fillForm();
        return;
    }

    protected void fillForm()
    {
        int v1_16;
        java.util.List v0_0 = this.getContext();
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.TitleItem("title", v0_0.getString(2131886592), 0));
        int v1_8 = new com.bisimplex.firebooru.dataadapter.search.TextItem("TEXT", v0_0.getString(2131886967), this.query.getText(), 1);
        v1_8.setEnterKeyAction(com.bisimplex.firebooru.dataadapter.search.ActionType.Search);
        this.data.add(v1_8);
        if (!this.query.getExtraParams().containsKey("serve_type")) {
            v1_16 = 0;
        } else {
            int v1_15 = ((String) this.query.getExtraParams().get("serve_type"));
            if (android.text.TextUtils.isEmpty(v1_15)) {
            } else {
                String v2_11 = this.serverTypeOptions.iterator();
                while (v2_11.hasNext()) {
                    com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v3_5 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_11.next());
                    if (v3_5.getKey().equalsIgnoreCase(v1_15)) {
                        v1_16 = this.serverTypeOptions.indexOf(v3_5);
                    }
                }
            }
        }
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("TYPE_SELECTOR", v0_0.getString(2131887145), v1_16, this.serverTypeOptions, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem("RESET_BUTTON", v0_0.getString(2131887111), com.bisimplex.firebooru.dataadapter.search.ActionType.Search, v0_0.getString(2131887086), com.bisimplex.firebooru.dataadapter.search.ActionType.Reset, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty1"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty2"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty3"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty4"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty5"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty6"));
        this.bindValueChangeListener();
        return;
    }

    public com.bisimplex.firebooru.network.SourceQuery getResultQuery()
    {
        com.bisimplex.firebooru.network.SourceQuery v1_1 = new com.bisimplex.firebooru.network.SourceQuery(((String) ((com.bisimplex.firebooru.dataadapter.search.TextItem) this.findEditableItemByKey("TEXT")).getValue()));
        String v0_4 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("TYPE_SELECTOR")).getValue());
        if (!android.text.TextUtils.isEmpty(v0_4.getKey())) {
            v1_1.getExtraParams().put("serve_type", v0_4.getKey());
        }
        return v1_1;
    }
}
