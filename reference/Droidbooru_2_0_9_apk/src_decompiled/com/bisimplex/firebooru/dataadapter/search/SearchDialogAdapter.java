package com.bisimplex.firebooru.dataadapter.search;
public class SearchDialogAdapter extends com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter {
    protected static final String AUTOCOMPLETE_FROM_SERVER = "AUTOCOMPLETE_FROM_SERVER";
    public static final String DANBOORU2_IS_HAS = "danbooru2_is_has";
    public static final String DANBOORU2_SORT_BY = "danbooru2_order";
    public static final String DERPIBOORU_DIRECTION = "sd";
    public static final String DERPIBOORU_SORT_BY = "sf";
    protected static final String DIRECTION = "DIRECTION";
    public static final String E621_SORT_BY = "e621_order";
    public static final String FALSE = "false";
    public static final String FILTER_ID = "FILTER_ID";
    public static final String GELBOORU_SORT_BY = "gelbooru_order";
    public static final String HYDRUS_FILE_SORT_ASC = "file_sort_asc";
    public static final String HYDRUS_FILE_SORT_TYPE = "file_sort_type";
    protected static final String INCLUDE_BLACKLISTED = "INCLUDE_BLACKLISTED";
    protected static final String IS_HAS = "IS_HAS";
    protected static final String JUMP_PAGE = "JUMP_PAGE";
    public static final String RAILS_FILTER_ID = "filter_id";
    protected static final String RESET_BUTTON = "RESET_BUTTON";
    protected static final String SERVER_HEADER = "SERVER_HEADER";
    protected static final String SERVER_OPTIONS_END = "RESET_BUTTON";
    protected static final String SERVER_OPTIONS_HEADER = "SERVER_OPTIONS_HEADER";
    protected static final String SERVER_SELECTOR_PREFIX = "SERVER_SELECTOR_";
    protected static final String SORT_BY = "SORT_BY";
    protected static final String TAGS = "TAGS";
    public static final String TRUE = "true";
    protected boolean allowServerListModification;
    protected java.util.List fullServerList;
    protected java.util.List fullServerOptions;
    protected com.bisimplex.firebooru.network.SourceQuery query;
    protected java.util.List selectedServers;

    public SearchDialogAdapter(android.content.Context p5, com.bisimplex.firebooru.network.SourceQuery p6, java.util.List p7, boolean p8)
    {
        super(p5);
        super.allowServerListModification = p8;
        super.fullServerList = super.loadFullListOfServers();
        Integer v8_10 = new java.util.ArrayList(super.fullServerList.size());
        super.fullServerOptions = v8_10;
        v8_10.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", p5.getString(2131886984), p5.getString(2131886984)));
        java.util.ArrayList v5_3 = super.fullServerList.iterator();
        while (v5_3.hasNext()) {
            Integer v8_8 = ((com.bisimplex.firebooru.danbooru.ServerItem) v5_3.next());
            super.fullServerOptions.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(v8_8.getServerId()), v8_8.getServerName(), v8_8.getExtraInfo()));
        }
        java.util.ArrayList v5_5 = new java.util.ArrayList();
        if (p7 != null) {
            java.util.Iterator v7_1 = p7.iterator();
            while (v7_1.hasNext()) {
                Integer v8_5 = ((Integer) v7_1.next());
                java.util.Iterator v0_3 = super.fullServerList.iterator();
                while (v0_3.hasNext()) {
                    com.bisimplex.firebooru.danbooru.ServerItem v1_4 = ((com.bisimplex.firebooru.danbooru.ServerItem) v0_3.next());
                    if (v1_4.getServerId() == v8_5.intValue()) {
                        v5_5.add(v1_4);
                    }
                }
            }
        }
        super.setData(p6, v5_5);
        return;
    }

    private void initializeForm()
    {
        this.data.clear();
        this.fillForm();
        this.bindValueChangeListener();
        this.notifyDataSetChanged();
        return;
    }

    private void setAutocompleteHint(com.bisimplex.firebooru.danbooru.ServerItemType p2, com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem p3)
    {
        if ((p2 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) && (p2 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru)) {
            p3.setHint(0);
            return;
        } else {
            p3.setHint(this.getContext().getString(2131887240));
            return;
        }
    }

    protected void addExtraAutocompleteOption(java.util.List p5, android.content.Context p6)
    {
        Boolean v0_0 = this.fetchSelectedServers();
        if ((v0_0.size() == 1) && (this.allowsAutocompleteOnSite(((com.bisimplex.firebooru.danbooru.ServerItem) v0_0.get(0))))) {
            p5.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("AUTOCOMPLETE_FROM_SERVER", p6.getString(2131886163), Boolean.valueOf(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useAutoCompleteFromServer()), 1));
        }
        return;
    }

    protected java.util.List addServerOptionsWithType(com.bisimplex.firebooru.danbooru.ServerItemType p30, int p31)
    {
        String v1_4;
        com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener v4_12;
        int v3_13;
        java.util.ArrayList v0_4;
        java.util.List v2_0 = this.getContext();
        int v18 = 1;
        java.util.ArrayList v20 = new java.util.ArrayList();
        switch (com.bisimplex.firebooru.dataadapter.search.SearchDialogAdapter$1.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType[p30.ordinal()]) {
            case 1:
                v0_4 = v20;
                v1_4 = this;
                v4_12 = v2_0.getString(2131886666);
                int v5_56 = com.bisimplex.firebooru.model.HydrusSortType.all();
                String v10_15 = new java.util.ArrayList(0);
                int v6_28 = v5_56.iterator();
                while (v6_28.hasNext()) {
                    boolean v8_34;
                    com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v7_85 = ((com.bisimplex.firebooru.model.HydrusSortType) v6_28.next());
                    switch (com.bisimplex.firebooru.dataadapter.search.SearchDialogAdapter$1.$SwitchMap$com$bisimplex$firebooru$model$HydrusSortType[v7_85.ordinal()]) {
                        case 1:
                            v8_34 = v2_0.getString(2131886673);
                            break;
                        case 2:
                            v8_34 = v2_0.getString(2131886671);
                            break;
                        case 3:
                            v8_34 = v2_0.getString(2131886683);
                            break;
                        case 4:
                            v8_34 = v2_0.getString(2131886675);
                            break;
                        case 5:
                            v8_34 = v2_0.getString(2131886697);
                            break;
                        case 6:
                            v8_34 = v2_0.getString(2131886702);
                            break;
                        case 7:
                            v8_34 = v2_0.getString(2131886681);
                            break;
                        case 8:
                            v8_34 = v2_0.getString(2131886698);
                            break;
                        case 9:
                            v8_34 = v2_0.getString(2131886693);
                            break;
                        case 10:
                            v8_34 = v2_0.getString(2131886695);
                            break;
                        case 11:
                            v8_34 = v2_0.getString(2131886687);
                            break;
                        case 12:
                            v8_34 = v2_0.getString(2131886700);
                            break;
                        case 13:
                            v8_34 = v2_0.getString(2131886669);
                            break;
                        case 14:
                            v8_34 = v2_0.getString(2131886678);
                            break;
                        case 15:
                            v8_34 = v2_0.getString(2131886689);
                            break;
                        case 16:
                            v8_34 = v2_0.getString(2131886676);
                            break;
                        case 17:
                            v8_34 = v2_0.getString(2131886691);
                            break;
                        case 18:
                            v8_34 = v2_0.getString(2131886685);
                            break;
                        case 19:
                            v8_34 = v2_0.getString(2131886667);
                            break;
                        case 20:
                            v8_34 = v2_0.getString(2131886680);
                            break;
                        case 21:
                            v8_34 = v2_0.getString(2131886674);
                            break;
                        case 22:
                            v8_34 = v2_0.getString(2131886672);
                            break;
                        case 23:
                            v8_34 = v2_0.getString(2131886684);
                            break;
                        case 24:
                            v8_34 = v2_0.getString(2131886703);
                            break;
                        case 25:
                            v8_34 = v2_0.getString(2131886682);
                            break;
                        case 26:
                            v8_34 = v2_0.getString(2131886699);
                            break;
                        case 27:
                            v8_34 = v2_0.getString(2131886694);
                            break;
                        case 28:
                            v8_34 = v2_0.getString(2131886696);
                            break;
                        case 29:
                            v8_34 = v2_0.getString(2131886688);
                            break;
                        case 30:
                            v8_34 = v2_0.getString(2131886701);
                            break;
                        case 31:
                            v8_34 = v2_0.getString(2131886670);
                            break;
                        case 32:
                            v8_34 = v2_0.getString(2131886679);
                            break;
                        case 33:
                            v8_34 = v2_0.getString(2131886690);
                            break;
                        case 34:
                            v8_34 = v2_0.getString(2131886677);
                            break;
                        case 35:
                            v8_34 = v2_0.getString(2131886692);
                            break;
                        case 36:
                            v8_34 = v2_0.getString(2131886686);
                            break;
                        case 37:
                            v8_34 = v2_0.getString(2131886668);
                            break;
                        default:
                            v8_34 = "";
                    }
                    v10_15.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(v7_85.toString(), v8_34, 0));
                }
                int v3_21 = com.bisimplex.firebooru.model.HydrusSortType.ImportTime;
                int v6_32 = ((String) this.query.getExtraParams().get("file_sort_type"));
                com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v7_81 = ((String) this.query.getExtraParams().get("file_sort_asc"));
                if ((!android.text.TextUtils.isEmpty(v6_32)) && ((android.text.TextUtils.isDigitsOnly(v6_32)) && (!android.text.TextUtils.isEmpty(v7_81)))) {
                    v3_21 = com.bisimplex.firebooru.model.HydrusSortType.fromValue(Integer.parseInt(v6_32), v7_81.equalsIgnoreCase("true"));
                }
                v0_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SORT_BY", v2_0.getString(2131887170), v5_56.indexOf(v3_21), v10_15, 1));
                this.addExtraAutocompleteOption(v0_4, v2_0);
                v3_13 = v4_12;
                break;
            case 2:
                boolean v14_0;
                v0_4 = v20;
                v1_4 = this;
                v4_12 = v2_0.getString(2131886397);
                java.util.ArrayList v15_1 = new java.util.ArrayList(0);
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", v2_0.getString(2131886984), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.first_seen_at.getValue(), v2_0.getString(2131886406), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.id.getValue(), v2_0.getString(2131886408), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.updated_at.getValue(), v2_0.getString(2131886414), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.aspect_ratio.getValue(), v2_0.getString(2131886400), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.faves.getValue(), v2_0.getString(2131886405), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.upvotes.getValue(), v2_0.getString(2131886415), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.downvotes.getValue(), v2_0.getString(2131886403), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.score.getValue(), v2_0.getString(2131886411), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.wilson_score.getValue(), v2_0.getString(2131886417), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType._score.getValue(), v2_0.getString(2131886398), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.width.getValue(), v2_0.getString(2131886416), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.height.getValue(), v2_0.getString(2131886407), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.comment_count.getValue(), v2_0.getString(2131886401), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.width.getValue(), v2_0.getString(2131886416), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.tag_count.getValue(), v2_0.getString(2131886413), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.pixels.getValue(), v2_0.getString(2131886409), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.size.getValue(), v2_0.getString(2131886412), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(com.bisimplex.firebooru.model.DerpibooruSortType.duration.getValue(), v2_0.getString(2131886404), 0));
                v15_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.format("random:%s", new Object[] {com.bisimplex.firebooru.danbooru.BooruProvider.randomString(10)})), v2_0.getString(2131886410), 0));
                int v5_51 = ((String) this.query.getExtraParams().get("sf"));
                if (android.text.TextUtils.isEmpty(v5_51)) {
                    v14_0 = 0;
                } else {
                    int v6_20;
                    if (!v5_51.contains("random")) {
                        v6_20 = 0;
                    } else {
                        v6_20 = (v15_1.size() - 1);
                    }
                    com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v7_68 = v15_1.iterator();
                    while (v7_68.hasNext()) {
                        boolean v8_25 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v7_68.next());
                        if (v8_25.getKey().equalsIgnoreCase(v5_51)) {
                            v6_20 = v15_1.indexOf(v8_25);
                        }
                    }
                    v14_0 = v6_20;
                }
                v0_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SORT_BY", v2_0.getString(2131887170), v14_0, v15_1, 1));
                int v5_53 = com.bisimplex.firebooru.model.DerpibooruDirectionType.all();
                String v10_13 = new java.util.ArrayList(v5_53.size());
                int v6_24 = v5_53.iterator();
                while (v6_24.hasNext()) {
                    boolean v8_22;
                    com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v7_73 = ((com.bisimplex.firebooru.model.DerpibooruDirectionType) v6_24.next());
                    boolean v8_20 = com.bisimplex.firebooru.dataadapter.search.SearchDialogAdapter$1.$SwitchMap$com$bisimplex$firebooru$model$DerpibooruDirectionType[v7_73.ordinal()];
                    if (v8_20 == 1) {
                        v8_22 = v2_0.getString(2131886402);
                    } else {
                        if (v8_20 == 2) {
                            v8_22 = v2_0.getString(2131886399);
                        } else {
                            v8_22 = "";
                        }
                    }
                    v10_13.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(v7_73.getValue(), v8_22, 0));
                }
                v0_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("DIRECTION", v2_0.getString(2131886423), v5_53.indexOf(com.bisimplex.firebooru.model.DerpibooruDirectionType.fromString(((String) this.query.getExtraParams().get("sd")))), v10_13, 1));
                this.addExtraAutocompleteOption(v0_4, v2_0);
                break;
            case 3:
                String v10_0;
                v0_4 = v20;
                v1_4 = this;
                int v5_179 = v2_0.getString(2131886181);
                int v11_19 = new java.util.ArrayList(0);
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", v2_0.getString(2131886984), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("created_at", v2_0.getString(2131887052), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("updated_at", v2_0.getString(2131887067), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("first_seen_at", v2_0.getString(2131887061), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("score", v2_0.getString(2131887065), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("wilson", v2_0.getString(2131887069), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("relevance", v2_0.getString(2131887064), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("width", v2_0.getString(2131887068), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("height", v2_0.getString(2131887062), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("comments", v2_0.getString(2131887051), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("tag_count", v2_0.getString(2131887066), 0));
                v11_19.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.format("random:%s", new Object[] {com.bisimplex.firebooru.danbooru.BooruProvider.randomNumberString(7)})), v2_0.getString(2131887063), 0));
                com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v7_2 = ((String) this.query.getExtraParams().get("sf"));
                if (android.text.TextUtils.isEmpty(v7_2)) {
                    v10_0 = 0;
                } else {
                    int v6_1;
                    if (!v7_2.contains("random")) {
                        v6_1 = 0;
                    } else {
                        v6_1 = (v11_19.size() - 1);
                    }
                    boolean v8_2 = v11_19.iterator();
                    while (v8_2.hasNext()) {
                        String v9_11 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v8_2.next());
                        if (v9_11.getKey().equalsIgnoreCase(v7_2)) {
                            v6_1 = v11_19.indexOf(v9_11);
                        }
                    }
                    v10_0 = v6_1;
                }
                v0_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SORT_BY", v2_0.getString(2131887170), v10_0, v11_19, 1));
                int v6_4 = com.bisimplex.firebooru.model.DerpibooruDirectionType.all();
                int v11_1 = new java.util.ArrayList(v6_4.size());
                com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v7_6 = v6_4.iterator();
                while (v7_6.hasNext()) {
                    String v9_8;
                    boolean v8_14 = ((com.bisimplex.firebooru.model.DerpibooruDirectionType) v7_6.next());
                    String v9_6 = com.bisimplex.firebooru.dataadapter.search.SearchDialogAdapter$1.$SwitchMap$com$bisimplex$firebooru$model$DerpibooruDirectionType[v8_14.ordinal()];
                    if (v9_6 == v18) {
                        v9_8 = v2_0.getString(2131886402);
                    } else {
                        if (v9_6 == 2) {
                            v9_8 = v2_0.getString(2131886399);
                        } else {
                            v9_8 = "";
                        }
                    }
                    v11_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(v8_14.getValue(), v9_8, 0));
                    v18 = 1;
                }
                int v11_2;
                v0_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("DIRECTION", v2_0.getString(2131886423), v6_4.indexOf(com.bisimplex.firebooru.model.DerpibooruDirectionType.fromString(((String) this.query.getExtraParams().get("sd")))), v11_1, 1));
                int v12_3 = new java.util.ArrayList(0);
                v12_3.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", v2_0.getString(2131886984), " "));
                v12_3.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("1", v2_0.getString(2131887053), v2_0.getString(2131887054)));
                v12_3.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("2", v2_0.getString(2131887055), v2_0.getString(2131887058)));
                v12_3.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("6", v2_0.getString(2131887059), v2_0.getString(2131887060)));
                v12_3.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("8", v2_0.getString(2131887056), v2_0.getString(2131887057)));
                int v3_11 = ((String) this.query.getExtraParams().get("filter_id"));
                if (android.text.TextUtils.isEmpty(v3_11)) {
                    v11_2 = 0;
                } else {
                    com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener v4_10 = v12_3.iterator();
                    int v6_16 = 0;
                    while (v4_10.hasNext()) {
                        com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v7_23 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v4_10.next());
                        if (v7_23.getKey().equalsIgnoreCase(v3_11)) {
                            v6_16 = v12_3.indexOf(v7_23);
                        }
                    }
                    v11_2 = v6_16;
                }
                v0_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("FILTER_ID", v2_0.getString(2131886591), v11_2, v12_3, 1));
                v3_13 = v5_179;
                break;
            case 4:
                boolean v24_1;
                String v17_1 = v2_0.getString(2131886447);
                String v1_48 = String.format("order:random randseed:%s", new Object[] {com.bisimplex.firebooru.danbooru.BooruProvider.randomString(4), com.bisimplex.firebooru.danbooru.BooruProvider.randomString(4)}));
                int v5_171 = new java.util.ArrayList(0);
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", v2_0.getString(2131886984), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:id", v2_0.getString(2131886458), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:score", v2_0.getString(2131886464), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:favcount", v2_0.getString(2131886454), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:tagcount", v2_0.getString(2131886466), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:comment_count", v2_0.getString(2131886451), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:comment_bumped", v2_0.getString(2131886449), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:mpixels", v2_0.getString(2131886460), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:filesize", v2_0.getString(2131886456), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:landscape", v2_0.getString(2131886459), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:portrait", v2_0.getString(2131886462), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:change", v2_0.getString(2131886448), 0));
                java.util.ArrayList v0_72 = v5_171.size();
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(v1_48, v2_0.getString(2131886463), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:score_asc", v2_0.getString(2131886465), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:favcount_asc", v2_0.getString(2131886455), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:tagcount_asc", v2_0.getString(2131886467), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:comment_count_asc", v2_0.getString(2131886452), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:comment_bumped_asc", v2_0.getString(2131886450), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:mpixels_asc", v2_0.getString(2131886461), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:filesize_asc", v2_0.getString(2131886457), 0));
                v5_171.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:duration_asc", v2_0.getString(2131886453), 0));
                v1_4 = this;
                int v3_124 = ((String) this.query.getExtraParams().get("e621_order"));
                if (android.text.TextUtils.isEmpty(v3_124)) {
                    v24_1 = 0;
                } else {
                    if (!v3_124.contains("random")) {
                        java.util.ArrayList v0_75 = v5_171.iterator();
                        com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener v4_81 = 0;
                        while (v0_75.hasNext()) {
                            int v6_111 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_75.next());
                            if (v6_111.getKey().equalsIgnoreCase(v3_124)) {
                                v4_81 = v5_171.indexOf(v6_111);
                            }
                        }
                        v24_1 = v4_81;
                    } else {
                        v24_1 = v0_72;
                    }
                }
                v0_4 = v20;
                v0_4.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SORT_BY", v2_0.getString(2131887170), v24_1, v5_171, 1));
                this.addExtraAutocompleteOption(v0_4, v2_0);
                v3_13 = v17_1;
                break;
            case 5:
                String v10_17;
                String v1_41 = v20;
                v3_13 = v2_0.getString(2131886618);
                int v11_17 = new java.util.ArrayList(0);
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", v2_0.getString(2131886984), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:id:desc", v2_0.getString(2131886627), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:score:desc", v2_0.getString(2131886633), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:rating:desc", v2_0.getString(2131886631), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:user:desc", v2_0.getString(2131886642), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:height:desc", v2_0.getString(2131886625), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:width:desc", v2_0.getString(2131886644), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:source:desc", v2_0.getString(2131886635), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:updated:desc", v2_0.getString(2131886639), v2_0.getString(2131886641)));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:random", v2_0.getString(2131886629), v2_0.getString(2131886630)));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:id:asc", v2_0.getString(2131886628), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:score:asc", v2_0.getString(2131886634), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:rating:asc", v2_0.getString(2131886632), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:user:asc", v2_0.getString(2131886643), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:height:asc", v2_0.getString(2131886626), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:width:asc", v2_0.getString(2131886645), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:source:asc", v2_0.getString(2131886636), " "));
                v11_17.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("sort:updated:asc", v2_0.getString(2131886640), " "));
                com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener v4_60 = ((String) this.query.getExtraParams().get("gelbooru_order"));
                if (android.text.TextUtils.isEmpty(v4_60)) {
                    v10_17 = 0;
                } else {
                    int v5_165 = v11_17.iterator();
                    int v6_106 = 0;
                    while (v5_165.hasNext()) {
                        com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v7_118 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v5_165.next());
                        if (v7_118.getKey().equalsIgnoreCase(v4_60)) {
                            v6_106 = v11_17.indexOf(v7_118);
                        }
                    }
                    v10_17 = v6_106;
                }
                v1_41.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SORT_BY", v2_0.getString(2131887170), v10_17, v11_17, 1));
                this.addExtraAutocompleteOption(v1_41, v2_0);
                v1_4 = this;
                v0_4 = v1_41;
                break;
            case 6:
                boolean v24_0;
                int v16_2 = v2_0.getString(2131886292);
                int v3_55 = new java.util.ArrayList(0);
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", v2_0.getString(2131886984), " "));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:id_desc", v2_0.getString(2131886351), v2_0.getString(2131886354)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:score", v2_0.getString(2131886372), v2_0.getString(2131886375)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:favcount", v2_0.getString(2131886344), v2_0.getString(2131886346)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:change", v2_0.getString(2131886329), v2_0.getString(2131886331)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:comment", v2_0.getString(2131886332), v2_0.getString(2131886337)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:comment_bumped", v2_0.getString(2131886334), v2_0.getString(2131886336)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:note", v2_0.getString(2131886363), v2_0.getString(2131886365)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:artcomm", v2_0.getString(2131886326), v2_0.getString(2131886328)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:mpixels", v2_0.getString(2131886357), v2_0.getString(2131886360)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:portrait", v2_0.getString(2131886366), v2_0.getString(2131886367)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:landscape", v2_0.getString(2131886355), v2_0.getString(2131886356)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:filesize", v2_0.getString(2131886347), v2_0.getString(2131886350)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:rank", v2_0.getString(2131886370), v2_0.getString(2131886371)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:downvotes", v2_0.getString(2131886342), v2_0.getString(2131886343)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:upvotes", v2_0.getString(2131886376), v2_0.getString(2131886377)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:curated", v2_0.getString(2131886340), v2_0.getString(2131886341)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("random:100", v2_0.getString(2131886368), v2_0.getString(2131886369)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:none", v2_0.getString(2131886361), v2_0.getString(2131886362)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:id", v2_0.getString(2131886352), v2_0.getString(2131886353)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:score_asc", v2_0.getString(2131886373), v2_0.getString(2131886374)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:mpixels_asc", v2_0.getString(2131886358), v2_0.getString(2131886359)));
                v3_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("order:filesize_asc", v2_0.getString(2131886348), v2_0.getString(2131886349)));
                String v1_38 = ((String) this.query.getExtraParams().get("danbooru2_order"));
                if (android.text.TextUtils.isEmpty(v1_38)) {
                    v24_0 = 0;
                } else {
                    int v5_82 = v3_55.iterator();
                    int v6_46 = 0;
                    while (v5_82.hasNext()) {
                        com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption v7_92 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v5_82.next());
                        if (v7_92.getKey().equalsIgnoreCase(v1_38)) {
                            v6_46 = v3_55.indexOf(v7_92);
                        }
                    }
                    v24_0 = v6_46;
                }
                boolean v8_71;
                String v1_40 = v20;
                v1_40.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SORT_BY", v2_0.getString(2131887003), v24_0, v3_55, 1));
                String v9_55 = new java.util.ArrayList(0);
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("", v2_0.getString(2131886984), " "));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:parent", v2_0.getString(2131886314), v2_0.getString(2131886315)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:child", v2_0.getString(2131886300), v2_0.getString(2131886301)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:general", v2_0.getString(2131886304), v2_0.getString(2131886305)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:sensitive", v2_0.getString(2131886320), v2_0.getString(2131886321)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:questionable", v2_0.getString(2131886318), v2_0.getString(2131886319)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:explicit", v2_0.getString(2131886302), v2_0.getString(2131886303)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:sfw", v2_0.getString(2131886322), v2_0.getString(2131886323)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:nsfw", v2_0.getString(2131886312), v2_0.getString(2131886313)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:jpg", v2_0.getString(2131886308), v2_0.getString(2131886309)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:png", v2_0.getString(2131886316), v2_0.getString(2131886317)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:gif", v2_0.getString(2131886306), v2_0.getString(2131886307)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:mp4", v2_0.getString(2131886310), v2_0.getString(2131886311)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("is:webm", v2_0.getString(2131886324), v2_0.getString(2131886325)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("has:source", v2_0.getString(2131886298), v2_0.getString(2131886299)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("has:comments", v2_0.getString(2131886294), v2_0.getString(2131886295)));
                v9_55.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption("has:notes", v2_0.getString(2131886296), v2_0.getString(2131886297)));
                int v3_76 = ((String) this.query.getExtraParams().get("danbooru2_is_has"));
                if (android.text.TextUtils.isEmpty(v3_76)) {
                    v8_71 = 0;
                } else {
                    com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener v4_56 = v9_55.iterator();
                    int v5_119 = 0;
                    while (v4_56.hasNext()) {
                        int v6_68 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v4_56.next());
                        if (v6_68.getKey().equalsIgnoreCase(v3_76)) {
                            v5_119 = v9_55.indexOf(v6_68);
                        }
                    }
                    v8_71 = v5_119;
                }
                v1_40.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("IS_HAS", v2_0.getString(2131886080), v8_71, v9_55, 1));
                this.addExtraAutocompleteOption(v1_40, v2_0);
                v1_4 = this;
                v0_4 = v1_40;
                v3_13 = v16_2;
                break;
            default:
                return v20;
        }
        v0_4.add(0, new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("SERVER_OPTIONS_HEADER", v2_0.getString(2131887143, new Object[] {v3_13})), 0));
        java.util.List v2_2 = v0_4.iterator();
        while (v2_2.hasNext()) {
            int v3_29 = ((com.bisimplex.firebooru.dataadapter.search.Item) v2_2.next());
            if ((v3_29 instanceof com.bisimplex.firebooru.dataadapter.search.EditableItem)) {
                ((com.bisimplex.firebooru.dataadapter.search.EditableItem) v3_29).setListener(v1_4.editableItemListener);
            }
        }
        if (p31 < 0) {
            v1_4.data.addAll(v0_4);
            return v0_4;
        } else {
            v1_4.data.addAll(p31, v0_4);
            return v0_4;
        }
    }

    public boolean allowsAutocompleteOnSite(com.bisimplex.firebooru.danbooru.ServerItem p3)
    {
        com.bisimplex.firebooru.danbooru.ServerItemType v0 = p3.getType();
        if (((v0 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) && ((v0 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) || (!com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase("gelbooru.com", p3.getUrl())))) && ((v0 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) && ((v0 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) && (v0 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621)))) {
            return 0;
        } else {
            return 1;
        }
    }

    public java.util.List fetchSelectedServers()
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        java.util.HashMap v1_1 = new java.util.HashMap();
        java.util.Iterator v2_1 = this.data.iterator();
        while (v2_1.hasNext()) {
            java.util.Iterator v3_1 = ((com.bisimplex.firebooru.dataadapter.search.Item) v2_1.next());
            if (v3_1.getKey().startsWith("SERVER_SELECTOR_")) {
                java.util.Iterator v3_5 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) v3_1).getValue()).getKey();
                if (!android.text.TextUtils.isEmpty(v3_5)) {
                    int v4_3 = Integer.parseInt(v3_5);
                    if (!v1_1.containsKey(v3_5)) {
                        v1_1.put(v3_5, v3_5);
                        java.util.Iterator v3_7 = this.fullServerList.iterator();
                        while (v3_7.hasNext()) {
                            com.bisimplex.firebooru.danbooru.ServerItem v5_4 = ((com.bisimplex.firebooru.danbooru.ServerItem) v3_7.next());
                            if (v5_4.getServerId() == v4_3) {
                                v0_1.add(v5_4);
                            }
                        }
                    }
                }
            }
        }
        return v0_1;
    }

    protected void fillForm()
    {
        android.content.Context v0 = this.getContext();
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.TitleItem("title", v0.getString(2131887111), 0));
        java.util.List v1_5 = new com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem("TAGS", v0.getString(2131886470), this.query.getText(), 1);
        this.data.add(v1_5);
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("SERVER_HEADER", v0.getString(2131887138), 0));
        int v4_8 = 0;
        if ((this.selectedServers.size() == 1) && ((this.allowsAutocompleteOnSite(((com.bisimplex.firebooru.danbooru.ServerItem) this.selectedServers.get(0)))) && (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useAutoCompleteFromServer()))) {
            v1_5.setServerID(String.valueOf(((com.bisimplex.firebooru.danbooru.ServerItem) this.selectedServers.get(0)).getServerId()));
        }
        if (this.selectedServers.size() == 1) {
            this.setAutocompleteHint(((com.bisimplex.firebooru.danbooru.ServerItem) this.selectedServers.get(0)).getType(), v1_5);
        }
        java.util.List v1_7 = this.selectedServers.iterator();
        while(true) {
            int v5_1 = -1;
            if (!v1_7.hasNext()) {
                break;
            }
            java.util.List v2_8 = ((com.bisimplex.firebooru.danbooru.ServerItem) v1_7.next());
            com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem v8_5 = this.fullServerOptions.iterator();
            while (v8_5.hasNext()) {
                String v9_3 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v8_5.next());
                if (v9_3.getKey().equalsIgnoreCase(String.valueOf(v2_8.getServerId()))) {
                    v5_1 = this.fullServerOptions.indexOf(v9_3);
                    break;
                }
            }
            String v11_2 = v5_1;
            String v9_4 = new StringBuilder("SERVER_SELECTOR_").append(v4_8).toString();
            v4_8++;
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem(v9_4, v0.getString(2131887126), v11_2, this.fullServerOptions, 1));
        }
        if (this.allowServerListModification) {
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem("addServer", v0.getString(2131886133), com.bisimplex.firebooru.dataadapter.search.ActionType.Add, v0.getString(2131886392), com.bisimplex.firebooru.dataadapter.search.ActionType.Delete, 1));
        }
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("subtitle2", v0.getString(2131887002), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.NumberItem("JUMP_PAGE", v0.getString(2131886733), Long.valueOf(this.query.getInitialPage()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("INCLUDE_BLACKLISTED", v0.getString(2131886714), Boolean.valueOf(this.query.isIncludeBlacklisted()), 1));
        this.addServerOptionsWithType(this.getSelectedServerItemType(), -1);
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem("RESET_BUTTON", v0.getString(2131887111), com.bisimplex.firebooru.dataadapter.search.ActionType.Search, v0.getString(2131887086), com.bisimplex.firebooru.dataadapter.search.ActionType.Reset, 1));
        return;
    }

    public com.bisimplex.firebooru.network.SourceQuery getResultQuery()
    {
        String v2_14;
        com.bisimplex.firebooru.network.SourceQuery v1_1 = new com.bisimplex.firebooru.network.SourceQuery(((String) ((com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem) this.findEditableItemByKey("TAGS")).getValue()));
        String v0_4 = ((com.bisimplex.firebooru.dataadapter.search.NumberItem) this.findEditableItemByKey("JUMP_PAGE"));
        if (v0_4 != null) {
            v2_14 = ((Long) v0_4.getValue()).longValue();
        } else {
            v2_14 = 0;
        }
        String v0_50;
        v1_1.setInitialPage(v2_14);
        String v0_34 = ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) this.findEditableItemByKey("INCLUDE_BLACKLISTED"));
        if (v0_34 != null) {
            v0_50 = ((Boolean) v0_34.getValue()).booleanValue();
        } else {
            v0_50 = 0;
        }
        v1_1.setIncludeBlacklisted(v0_50);
        String v0_51 = this.getSelectedServerItemType();
        if (v0_51 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) {
            if (v0_51 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) {
                if (v0_51 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails) {
                    if (v0_51 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) {
                        if (v0_51 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
                            if (v0_51 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) {
                                String v0_53 = new StringBuilder("");
                                String v2_38 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SORT_BY"));
                                if ((v2_38 != null) && (!android.text.TextUtils.isEmpty(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_38.getValue()).getKey()))) {
                                    v1_1.getExtraParams().put("danbooru2_order", ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_38.getValue()).getKey());
                                    v0_53.append(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_38.getValue()).getKey());
                                    v0_53.append(" ");
                                }
                                String v2_44 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("IS_HAS"));
                                if ((v2_44 != null) && (!android.text.TextUtils.isEmpty(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_44.getValue()).getKey()))) {
                                    v1_1.getExtraParams().put("danbooru2_is_has", ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_44.getValue()).getKey());
                                    v0_53.append(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_44.getValue()).getKey());
                                    v0_53.append(" ");
                                }
                                String v0_3 = v0_53.toString().trim();
                                if (!android.text.TextUtils.isEmpty(v0_3)) {
                                    v1_1.setExtraTags(v0_3);
                                }
                            }
                        } else {
                            String v0_6 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SORT_BY"));
                            if ((v0_6 != null) && (!android.text.TextUtils.isEmpty(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_6.getValue()).getKey()))) {
                                v1_1.getExtraParams().put("gelbooru_order", ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_6.getValue()).getKey());
                                v1_1.setExtraTags(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_6.getValue()).getKey());
                                return v1_1;
                            }
                        }
                    } else {
                        String v0_12 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SORT_BY"));
                        if ((v0_12 != null) && (!android.text.TextUtils.isEmpty(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_12.getValue()).getKey()))) {
                            v1_1.getExtraParams().put("e621_order", ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_12.getValue()).getKey());
                            v1_1.setExtraTags(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_12.getValue()).getKey());
                            return v1_1;
                        }
                    }
                } else {
                    String v0_18 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SORT_BY"));
                    String v2_16 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("DIRECTION"));
                    String v3_8 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("FILTER_ID"));
                    if ((v0_18 != null) && ((v2_16 != null) && (!android.text.TextUtils.isEmpty(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_18.getValue()).getKey())))) {
                        v1_1.getExtraParams().put("sf", ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_18.getValue()).getKey());
                        v1_1.getExtraParams().put("sd", ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_16.getValue()).getKey());
                    }
                    if ((v3_8 != null) && ((v3_8.getValue() != null) && (!android.text.TextUtils.isEmpty(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v3_8.getValue()).getKey())))) {
                        v1_1.getExtraParams().put("filter_id", ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v3_8.getValue()).getKey());
                        return v1_1;
                    }
                }
            } else {
                String v0_33 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SORT_BY"));
                String v2_24 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("DIRECTION"));
                if ((v0_33 != null) && ((v2_24 != null) && (!android.text.TextUtils.isEmpty(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_33.getValue()).getKey())))) {
                    v1_1.getExtraParams().put("sf", ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_33.getValue()).getKey());
                    v1_1.getExtraParams().put("sd", ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v2_24.getValue()).getKey());
                    return v1_1;
                }
            }
        } else {
            String v0_40 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) this.findEditableItemByKey("SORT_BY"));
            if (v0_40 != null) {
                String v0_48;
                String v0_45 = com.bisimplex.firebooru.model.HydrusSortType.fromString(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v0_40.getValue()).getKey());
                v1_1.getExtraParams().put("file_sort_type", String.valueOf(v0_45.getValue()));
                String v2_29 = v1_1.getExtraParams();
                if (!v0_45.isAsc()) {
                    v0_48 = "false";
                } else {
                    v0_48 = "true";
                }
                v2_29.put("file_sort_asc", v0_48);
                return v1_1;
            }
        }
        return v1_1;
    }

    public com.bisimplex.firebooru.danbooru.ServerItemType getSelectedServerItemType()
    {
        return this.getSelectedServerItemType(this.fetchSelectedServers());
    }

    public com.bisimplex.firebooru.danbooru.ServerItemType getSelectedServerItemType(java.util.List p3)
    {
        if (!p3.isEmpty()) {
            com.bisimplex.firebooru.danbooru.ServerItemType v0_4 = ((com.bisimplex.firebooru.danbooru.ServerItem) p3.get(0)).getType();
            com.bisimplex.firebooru.danbooru.ServerItemType v3_1 = p3.iterator();
            while (v3_1.hasNext()) {
                if (v0_4 != ((com.bisimplex.firebooru.danbooru.ServerItem) v3_1.next()).getType()) {
                    return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone;
                }
            }
            return v0_4;
        } else {
            return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone;
        }
    }

    protected java.util.List loadFullListOfServers()
    {
        return com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServers();
    }

    protected void notifyAction(int p10, com.bisimplex.firebooru.dataadapter.search.ActionType p11)
    {
        com.bisimplex.firebooru.dataadapter.search.ActionType v0_6 = this.data.indexOf(this.findEditableItemByKey("SERVER_HEADER"));
        if (p11 != com.bisimplex.firebooru.dataadapter.search.ActionType.Add) {
            if (p11 != com.bisimplex.firebooru.dataadapter.search.ActionType.Delete) {
                if (p11 != com.bisimplex.firebooru.dataadapter.search.ActionType.Reset) {
                    super.notifyAction(p10, p11);
                    return;
                } else {
                    this.resetData();
                    return;
                }
            } else {
                int v11_1 = (p10 - 1);
                if ((((com.bisimplex.firebooru.dataadapter.search.Item) this.data.get(v11_1)).getKey().startsWith("SERVER_SELECTOR_")) && ((p10 - 2) != v0_6)) {
                    this.data.remove(v11_1);
                    this.notifyItemRemoved(v11_1);
                    this.updateServerOptions();
                    return;
                } else {
                    return;
                }
            }
        } else {
            com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem v3_1 = new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem(new StringBuilder("SERVER_SELECTOR_").append((p10 - v0_6)).toString(), this.getContext().getString(2131887126), 0, this.fullServerOptions, 1);
            this.data.add(p10, v3_1);
            v3_1.setListener(this.editableItemListener);
            this.notifyItemInserted(p10);
            return;
        }
    }

    protected void notifyValueChanged(com.bisimplex.firebooru.dataadapter.search.Item p4)
    {
        if (!p4.getKey().startsWith("SERVER_SELECTOR_")) {
            if ((!p4.getKey().equalsIgnoreCase("SORT_BY")) && ((!p4.getKey().equalsIgnoreCase("IS_HAS")) && (!p4.getKey().equalsIgnoreCase("FILTER_ID")))) {
                if (p4.getKey().equalsIgnoreCase("AUTOCOMPLETE_FROM_SERVER")) {
                    com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setUseAutocompleteFromServer(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p4).getValue()).booleanValue());
                    this.updateTapAutocompleteServer(1);
                }
            } else {
                this.notifyItemChanged(this.data.indexOf(p4));
            }
        } else {
            this.notifyItemChanged(this.data.indexOf(p4));
            this.updateServerOptions();
            this.updateTapAutocompleteServer(1);
        }
        super.notifyValueChanged(p4);
        return;
    }

    public void resetData()
    {
        this.selectedServers.clear();
        this.selectedServers.add(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer());
        com.bisimplex.firebooru.network.SourceQuery v0_3 = new com.bisimplex.firebooru.network.SourceQuery();
        this.query = v0_3;
        this.setData(v0_3, this.selectedServers);
        return;
    }

    protected void setData(com.bisimplex.firebooru.network.SourceQuery p1, java.util.List p2)
    {
        this.query = p1;
        if (p1 == null) {
            this.query = new com.bisimplex.firebooru.network.SourceQuery();
        }
        this.selectedServers = p2;
        if (p2.isEmpty()) {
            this.selectedServers.add(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer());
        }
        this.initializeForm();
        return;
    }

    protected void updateServerOptions()
    {
        int v2_0;
        int v0_0 = this.getSelectedServerItemType();
        int v1_3 = this.findEditableItemByKey("SERVER_OPTIONS_HEADER");
        int v2_3 = this.findEditableItemByKey("RESET_BUTTON");
        if (v2_3 != 0) {
            v2_0 = this.data.indexOf(v2_3);
        } else {
            v2_0 = this.data.size();
        }
        if (v1_3 != 0) {
            int v1_0 = this.data.indexOf(v1_3);
            if (v1_0 < v2_0) {
                int v3_1 = (v2_0 - v1_0);
                java.util.ArrayList v4_1 = new java.util.ArrayList(v3_1);
                java.util.List v5_0 = v1_0;
                while (v5_0 < v2_0) {
                    v4_1.add(((com.bisimplex.firebooru.dataadapter.search.Item) this.data.get(v5_0)));
                    v5_0++;
                }
                this.data.removeAll(v4_1);
                this.notifyItemRangeRemoved(v1_0, v3_1);
                int v0_1 = this.addServerOptionsWithType(v0_0, v1_0);
                if (v0_1.size() > 0) {
                    this.notifyItemRangeInserted(v2_0, v0_1.size());
                }
            }
        } else {
            int v0_3 = this.addServerOptionsWithType(v0_0, v2_0);
            if (v0_3.size() > 0) {
                this.notifyItemRangeInserted(v2_0, v0_3.size());
                return;
            }
        }
        return;
    }

    public void updateTapAutocompleteServer(boolean p6)
    {
        String v0_6 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useAutoCompleteFromServer();
        if ((v0_6 == null) && (p6 == null)) {
            return;
        } else {
            com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem v6_2 = ((com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem) this.data.get(1));
            if (v0_6 == null) {
                v6_2.setServerID(0);
                this.setAutocompleteHint(this.getSelectedServerItemType(), v6_2);
            } else {
                String v0_3 = this.fetchSelectedServers();
                com.bisimplex.firebooru.danbooru.ServerItemType v2 = this.getSelectedServerItemType(v0_3);
                if (v2 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone) {
                    v6_2.setServerID(String.format(java.util.Locale.US, "%d", new Object[] {Integer.valueOf(((com.bisimplex.firebooru.danbooru.ServerItem) v0_3.get(0)).getServerId())})));
                } else {
                    v6_2.setServerID("0");
                }
                this.setAutocompleteHint(v2, v6_2);
            }
            this.notifyItemChanged(1);
            return;
        }
    }
}
