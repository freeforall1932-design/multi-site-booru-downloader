package com.bisimplex.firebooru.dataadapter.search.holder;
public class TagAutocompeltemHolder extends com.bisimplex.firebooru.dataadapter.search.holder.SingleSelectorItemHolder {
    private static final String defaultSeparator = " ";
    private final com.bisimplex.firebooru.dataadapter.TagClientAdapter adapter;
    private final android.os.Handler autocompleteHandler;
    private String currentText;
    private String serverID;
    private final com.bisimplex.firebooru.network.SourceTag sourceTag;

    static bridge synthetic com.bisimplex.firebooru.dataadapter.TagClientAdapter -$$Nest$fgetadapter(com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder p0)
    {
        return p0.adapter;
    }

    static bridge synthetic String -$$Nest$fgetcurrentText(com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder p0)
    {
        return p0.currentText;
    }

    static bridge synthetic void -$$Nest$fputcurrentText(com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder p0, String p1)
    {
        p0.currentText = p1;
        return;
    }

    static bridge synthetic void -$$Nest$mbeginSearchTags(com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder p0, String p1)
    {
        p0.beginSearchTags(p1);
        return;
    }

    public TagAutocompeltemHolder(android.view.View p3)
    {
        super(p3);
        super.autocompleteHandler = new android.os.Handler(android.os.Looper.getMainLooper());
        super.currentText = "";
        com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder$4 v0_1 = new com.bisimplex.firebooru.dataadapter.TagClientAdapter(p3.getContext());
        super.adapter = v0_1;
        super.sourceTag = ((com.bisimplex.firebooru.network.SourceTag) com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Tag));
        android.widget.AutoCompleteTextView v3_5 = super.getAutocomplete();
        v3_5.setAdapter(v0_1);
        v3_5.addTextChangedListener(new com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder$3(super, v3_5));
        v3_5.setOnItemClickListener(new com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder$4(super, v3_5));
        return;
    }

    private void beginSearchTags(String p4)
    {
        this.stopAutocompleteHandler();
        this.autocompleteHandler.postDelayed(this.searchTagsRunnable(p4), 700);
        return;
    }

    private Runnable searchTagsRunnable(String p2)
    {
        return new com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder$1(this, p2);
    }

    private void stopAutocompleteHandler()
    {
        this.autocompleteHandler.removeCallbacksAndMessages(0);
        return;
    }

    public com.bisimplex.firebooru.dataadapter.TagClientAdapter getAdapter()
    {
        return this.adapter;
    }

    public String getServerID()
    {
        return this.serverID;
    }

    protected String getTagSeparator()
    {
        String v0_0 = this.sourceTag;
        if ((v0_0 != null) && (v0_0.getProvider() != null)) {
            String v0_1 = this.sourceTag.getProvider().getTagSeparator();
            if (android.text.TextUtils.isEmpty(v0_1)) {
                v0_1 = " ";
            }
            String v0_2 = v0_1.trim();
            if (!android.text.TextUtils.isEmpty(v0_2)) {
                return v0_2;
            } else {
                return " ";
            }
        } else {
            return " ";
        }
    }

    public void searchTags(String p5)
    {
        if (!android.text.TextUtils.isEmpty(p5)) {
            com.bisimplex.firebooru.network.SourceTag v5_13 = p5.split(this.getTagSeparator());
            if (v5_13.length != 0) {
                com.bisimplex.firebooru.network.SourceTag v5_1 = v5_13[(v5_13.length - 1)];
                android.widget.AutoCompleteTextView v0_2 = this.getAutocomplete();
                if (v5_1.length() >= v0_2.getThreshold()) {
                    com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder$2 v1_2 = new com.bisimplex.firebooru.network.SourceQuery(v5_1);
                    if ((com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useAutoCompleteFromServer()) && (android.text.TextUtils.isEmpty(this.serverID))) {
                        this.serverID = String.format(java.util.Locale.US, "%d", new Object[] {Integer.valueOf(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().getServerDescription().getServerId())}));
                    }
                    if (!android.text.TextUtils.isEmpty(this.serverID)) {
                        v1_2.getExtraParams().put("SERVER_ID_KEY", this.serverID);
                    }
                    this.sourceTag.setQuery(v1_2);
                    this.sourceTag.loadAnotherPage(new com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder$2(this, v0_2));
                }
            }
        }
        return;
    }

    public void setServerID(String p1)
    {
        this.serverID = p1;
        return;
    }
}
