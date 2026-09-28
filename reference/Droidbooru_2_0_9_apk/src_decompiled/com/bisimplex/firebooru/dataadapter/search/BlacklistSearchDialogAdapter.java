package com.bisimplex.firebooru.dataadapter.search;
public class BlacklistSearchDialogAdapter extends com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter {
    protected static final String RESET_BUTTON = "RESET_BUTTON";
    protected static final String SERVER_SELECTOR = "SERVER_SELECTOR";
    protected static final String TEXT = "TEXT";
    protected final java.util.List fullServerList;
    private final java.util.List fullServerOptions;
    private com.bisimplex.firebooru.network.SourceQuery query;

    public BlacklistSearchDialogAdapter(android.content.Context p5, com.bisimplex.firebooru.network.SourceQuery p6)
    {
        super(p5);
        super.query = p6;
        if (p6 == null) {
            super.query = new com.bisimplex.firebooru.network.SourceQuery();
        }
        String v6_7 = super.loadFullListOfServers();
        super.fullServerList = v6_7;
        java.util.List v0_0 = new java.util.ArrayList();
        super.fullServerOptions = v0_0;
        v0_0.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", p5.getString(2131886984), p5.getString(2131886145)));
        java.util.Iterator v5_2 = v6_7.iterator();
        while (v5_2.hasNext()) {
            String v6_4 = ((com.bisimplex.firebooru.danbooru.ServerItem) v5_2.next());
            super.fullServerOptions.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(v6_4.getUrl(), v6_4.getServerName(), v6_4.getExtraInfo()));
        }
        super.fillForm();
        return;
    }

    protected void fillForm()
    {
        int v1_10;
        java.util.List v0_0 = this.getContext();
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.TitleItem("title", v0_0.getString(2131887112), 0));
        int v1_5 = new com.bisimplex.firebooru.dataadapter.search.TextItem("TEXT", v0_0.getString(2131887203), this.query.getText(), 1);
        v1_5.setEnterKeyAction(com.bisimplex.firebooru.dataadapter.search.ActionType.Search);
        this.data.add(v1_5);
        int v1_9 = ((String) this.query.getExtraParams().get("SERVER_URL"));
        if (android.text.TextUtils.isEmpty(v1_9)) {
            v1_10 = 0;
        } else {
            String v2_10 = this.fullServerOptions.iterator();
            while (v2_10.hasNext()) {
                com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v3_5 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_10.next());
                if (v3_5.getKey().equalsIgnoreCase(v1_9)) {
                    v1_10 = this.fullServerOptions.indexOf(v3_5);
                }
            }
        }
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SERVER_SELECTOR", v0_0.getString(2131887138), v1_10, this.fullServerOptions, 1));
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
        String v0_4 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SERVER_SELECTOR")).getValue());
        if (!android.text.TextUtils.isEmpty(v0_4.getKey())) {
            v1_1.getExtraParams().put("SERVER_URL", v0_4.getKey());
        }
        return v1_1;
    }

    protected java.util.List loadFullListOfServers()
    {
        return com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServers();
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
