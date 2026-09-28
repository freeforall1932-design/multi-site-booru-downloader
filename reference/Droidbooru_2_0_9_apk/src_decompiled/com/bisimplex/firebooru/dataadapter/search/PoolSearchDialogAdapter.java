package com.bisimplex.firebooru.dataadapter.search;
public class PoolSearchDialogAdapter extends com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter {
    protected static final String RESET_BUTTON = "RESET_BUTTON";
    protected static final String SERVER_SELECTOR = "SERVER_SELECTOR";
    protected static final String TEXT = "TEXT";
    protected final java.util.List fullServerList;
    private final java.util.List fullServerOptions;
    private com.bisimplex.firebooru.network.SourceQuery query;

    public PoolSearchDialogAdapter(android.content.Context p5, com.bisimplex.firebooru.network.SourceQuery p6)
    {
        super(p5);
        super.query = p6;
        java.util.Iterator v5_1 = super.loadFullListOfServers();
        super.fullServerList = v5_1;
        super.fullServerOptions = new java.util.ArrayList();
        java.util.Iterator v5_2 = v5_1.iterator();
        while (v5_2.hasNext()) {
            String v6_3 = ((com.bisimplex.firebooru.danbooru.ServerItem) v5_2.next());
            super.fullServerOptions.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(v6_3.getServerId()), v6_3.getServerName(), v6_3.getExtraInfo()));
        }
        super.fillForm();
        return;
    }

    protected void fillForm()
    {
        java.util.List v0_0 = this.getContext();
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.TitleItem("title", v0_0.getString(2131887118), 0));
        com.bisimplex.firebooru.dataadapter.search.SeparatorItem v1_2 = new com.bisimplex.firebooru.dataadapter.search.TextItem("TEXT", v0_0.getString(2131887207), this.query.getText(), 1);
        v1_2.setEnterKeyAction(com.bisimplex.firebooru.dataadapter.search.ActionType.Search);
        this.data.add(v1_2);
        com.bisimplex.firebooru.dataadapter.search.SeparatorItem v1_4 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer();
        String v2_6 = this.fullServerOptions.iterator();
        while (v2_6.hasNext()) {
            String v3_4 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_6.next());
            if (v3_4.getKey().equalsIgnoreCase(String.valueOf(v1_4.getServerId()))) {
                com.bisimplex.firebooru.dataadapter.search.SeparatorItem v1_5 = this.fullServerOptions.indexOf(v3_4);
            }
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SERVER_SELECTOR", v0_0.getString(2131887148), v1_5, this.fullServerOptions, 1));
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem("RESET_BUTTON", v0_0.getString(2131887111), com.bisimplex.firebooru.dataadapter.search.ActionType.Search, v0_0.getString(2131887086), com.bisimplex.firebooru.dataadapter.search.ActionType.Reset, 1));
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty1"));
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty2"));
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty3"));
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty4"));
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty5"));
            this.bindValueChangeListener();
            return;
        }
        v1_5 = 0;
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SERVER_SELECTOR", v0_0.getString(2131887148), v1_5, this.fullServerOptions, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem("RESET_BUTTON", v0_0.getString(2131887111), com.bisimplex.firebooru.dataadapter.search.ActionType.Search, v0_0.getString(2131887086), com.bisimplex.firebooru.dataadapter.search.ActionType.Reset, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty1"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty2"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty3"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty4"));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SeparatorItem("empty5"));
        this.bindValueChangeListener();
        return;
    }

    public com.bisimplex.firebooru.network.SourceQuery getResultQuery()
    {
        com.bisimplex.firebooru.network.SourceQuery v1_1 = new com.bisimplex.firebooru.network.SourceQuery(((String) ((com.bisimplex.firebooru.dataadapter.search.TextItem) this.findEditableItemByKey("TEXT")).getValue()));
        String v0_4 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SERVER_SELECTOR")).getValue());
        if (!android.text.TextUtils.isEmpty(v0_4.getKey())) {
            v1_1.getExtraParams().put("FILTER_SERVER_URL", v0_4.getLabel());
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

    protected java.util.List loadFullListOfServers()
    {
        return com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServersWithPools();
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
