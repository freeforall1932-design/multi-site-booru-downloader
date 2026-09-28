package com.bisimplex.firebooru.view;
public class SearchDialog extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    public static final String HIDE_PAGE_SELECTOR = "HIDE_PAGE_SELECTOR";
    public static final String MULTI_SERVER_SELECTED_IDS = "MULTI_SERVER_SELECTED_IDS";
    public static final String MULTI_SERVER_SELECTOR = "MULTI_SERVER_SELECTOR";
    public static final String QUERY_JSON = "QUERY_JSON";
    public static final String SERVER_FILTER_TYPE_ID = "SERVER_FILTER_TYPE_ID";
    public static final String SERVER_ID = "SERVER_ID";
    public static final String TAG = "SearchDialog_TAG";
    protected android.widget.AutoCompleteTextView autoCompleteTextView;
    private final android.os.Handler autocompleteHandler;
    private android.widget.CheckBox blacklistedCheckBox;
    private String currentText;
    private final int hydrusSortLenght;
    protected com.bisimplex.firebooru.view.SearchDialog$OnSearchDialogListener listener;
    private Runnable mShowImeRunnable;
    private android.widget.EditText numberEditText;
    private final android.view.View$OnClickListener onCloseChipListener;
    private java.util.List selectedServers;
    private com.google.android.material.chip.ChipGroup serverChipGroup;
    protected android.widget.Spinner serverSpinner;
    private android.widget.Spinner sortSpinner;
    private com.bisimplex.firebooru.network.SourceQuery sourceQuery;
    private com.bisimplex.firebooru.network.SourceTag sourceTag;
    private com.bisimplex.firebooru.dataadapter.TagClientAdapter tagAdapter;

    static bridge synthetic String -$$Nest$fgetcurrentText(com.bisimplex.firebooru.view.SearchDialog p0)
    {
        return p0.currentText;
    }

    static bridge synthetic java.util.List -$$Nest$fgetselectedServers(com.bisimplex.firebooru.view.SearchDialog p0)
    {
        return p0.selectedServers;
    }

    static bridge synthetic com.google.android.material.chip.ChipGroup -$$Nest$fgetserverChipGroup(com.bisimplex.firebooru.view.SearchDialog p0)
    {
        return p0.serverChipGroup;
    }

    static bridge synthetic com.bisimplex.firebooru.dataadapter.TagClientAdapter -$$Nest$fgettagAdapter(com.bisimplex.firebooru.view.SearchDialog p0)
    {
        return p0.tagAdapter;
    }

    static bridge synthetic void -$$Nest$fputcurrentText(com.bisimplex.firebooru.view.SearchDialog p0, String p1)
    {
        p0.currentText = p1;
        return;
    }

    static bridge synthetic void -$$Nest$maddSelectedServer(com.bisimplex.firebooru.view.SearchDialog p0)
    {
        p0.addSelectedServer();
        return;
    }

    static bridge synthetic void -$$Nest$mbeginSearchTags(com.bisimplex.firebooru.view.SearchDialog p0, String p1)
    {
        p0.beginSearchTags(p1);
        return;
    }

    static bridge synthetic void -$$Nest$msetImeVisibility(com.bisimplex.firebooru.view.SearchDialog p0, boolean p1)
    {
        p0.setImeVisibility(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mupdateSort(com.bisimplex.firebooru.view.SearchDialog p0)
    {
        p0.updateSort();
        return;
    }

    public SearchDialog()
    {
        this.sourceQuery = new com.bisimplex.firebooru.network.SourceQuery();
        this.selectedServers = new java.util.ArrayList();
        this.hydrusSortLenght = 21;
        this.onCloseChipListener = new com.bisimplex.firebooru.view.SearchDialog$9(this);
        this.autocompleteHandler = new android.os.Handler();
        this.mShowImeRunnable = new com.bisimplex.firebooru.view.SearchDialog$12(this);
        com.google.android.material.bottomsheet.BottomSheetBehavior v0_8 = ((com.google.android.material.bottomsheet.BottomSheetDialog) this.getDialog());
        if (v0_8 != null) {
            com.google.android.material.bottomsheet.BottomSheetBehavior v0_9 = v0_8.getBehavior();
            v0_9.setSaveFlags(-1);
            v0_9.setState(3);
        }
        return;
    }

    private void addSelectedServer()
    {
        this.addServerToList(((com.bisimplex.firebooru.danbooru.ServerItem) this.serverSpinner.getSelectedItem()));
        return;
    }

    private void addServerToChipGroup(com.bisimplex.firebooru.danbooru.ServerItem p6)
    {
        if ((p6 != null) && (this.serverChipGroup != null)) {
            String v0_6 = this.requireContext();
            com.google.android.material.chip.Chip v1_1 = new com.google.android.material.chip.Chip(v0_6);
            v1_1.setChipDrawable(com.google.android.material.chip.ChipDrawable.createFromAttributes(v0_6, 0, 0, 2131952715));
            v1_1.setTextAppearance(2131952187);
            v1_1.setId(androidx.core.view.ViewCompat.generateViewId());
            String v0_5 = p6.getRealURL().getHost();
            if (p6.isDefault()) {
                v0_5 = p6.getServerName();
            }
            v1_1.setText(v0_5);
            v1_1.setTag(String.valueOf(p6.getServerId()));
            v1_1.setOnCloseIconClickListener(this.onCloseChipListener);
            v1_1.setCheckable(0);
            this.serverChipGroup.addView(v1_1);
        }
        return;
    }

    private void addServerToList(com.bisimplex.firebooru.danbooru.ServerItem p2)
    {
        if (!this.serverIsOnList(p2)) {
            this.selectedServers.add(p2);
            this.addServerToChipGroup(p2);
            return;
        } else {
            return;
        }
    }

    private void beginSearchTags(String p4)
    {
        this.stopAutocompleteHandler();
        this.autocompleteHandler.postDelayed(this.runnable(p4), 500);
        return;
    }

    private java.util.HashMap getAdditionalParameters(com.bisimplex.firebooru.danbooru.ServerItem p4)
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        if ((this.sortSpinner.getVisibility() == 0) && (p4.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus)) {
            String v4_5;
            String v4_3 = ((com.bisimplex.firebooru.model.HydrusSortType) this.sortSpinner.getSelectedItem());
            v0_1.put("file_sort_type", String.valueOf(v4_3.getValue()));
            if (!v4_3.isAsc()) {
                v4_5 = "false";
            } else {
                v4_5 = "true";
            }
            v0_1.put("file_sort_asc", v4_5);
        }
        return v0_1;
    }

    private void hideKeyboard()
    {
        if (this.getActivity() != null) {
            android.widget.AutoCompleteTextView v0_3 = this.autoCompleteTextView;
            if ((v0_3 != null) && (v0_3.hasFocus())) {
                this.autoCompleteTextView.dismissDropDown();
                this.autoCompleteTextView.clearFocus();
                this.autoCompleteTextView.clearListSelection();
                this.stopAutocompleteHandler();
            }
        }
        return;
    }

    private Runnable runnable(String p2)
    {
        return new com.bisimplex.firebooru.view.SearchDialog$10(this, p2);
    }

    private boolean serverIsOnList(com.bisimplex.firebooru.danbooru.ServerItem p4)
    {
        java.util.Iterator v0_1 = this.selectedServers.iterator();
        while (v0_1.hasNext()) {
            if (((com.bisimplex.firebooru.danbooru.ServerItem) v0_1.next()).getServerId() == p4.getServerId()) {
                return 1;
            }
        }
        return 0;
    }

    private int serverListCount()
    {
        int v0_0 = this.selectedServers;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0.size();
        }
    }

    private void setImeVisibility(boolean p3)
    {
        android.os.IBinder v0_0 = this.autoCompleteTextView;
        if (v0_0 != null) {
            if (p3 == null) {
                v0_0.removeCallbacks(this.mShowImeRunnable);
                android.view.inputmethod.InputMethodManager v3_2 = ((android.view.inputmethod.InputMethodManager) this.autoCompleteTextView.getContext().getSystemService("input_method"));
                if (v3_2 != null) {
                    v3_2.hideSoftInputFromWindow(this.autoCompleteTextView.getWindowToken(), 0);
                }
            } else {
                v0_0.post(this.mShowImeRunnable);
                return;
            }
        }
        return;
    }

    private void stopAutocompleteHandler()
    {
        this.autocompleteHandler.removeCallbacksAndMessages(0);
        return;
    }

    private void updateSort()
    {
        if ((this.serverListCount() != 0) || (((com.bisimplex.firebooru.danbooru.ServerItem) this.serverSpinner.getSelectedItem()).getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus)) {
            this.sortSpinner.setVisibility(4);
            return;
        } else {
            android.widget.Spinner v0_2 = com.bisimplex.firebooru.model.HydrusSortType.all();
            int v1_2 = v0_2.indexOf(com.bisimplex.firebooru.model.HydrusSortType.ImportTime);
            if (v1_2 < 0) {
                v1_2 = 0;
            }
            android.widget.ArrayAdapter v3_1 = new android.widget.ArrayAdapter(this.getActivity(), 2131558651, 2131362196, v0_2);
            v3_1.setDropDownViewResource(2131558642);
            this.sortSpinner.setAdapter(v3_1);
            this.sortSpinner.setSelection(v1_2, 0);
            this.sortSpinner.setVisibility(0);
            return;
        }
    }

    protected int getDefaultServerID()
    {
        return com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer().getServerId();
    }

    protected java.util.List getSelectedServers()
    {
        java.util.List v0_3 = ((com.bisimplex.firebooru.danbooru.ServerItem) this.serverSpinner.getSelectedItem());
        java.util.ArrayList v1_1 = new java.util.ArrayList();
        if (!this.selectedServers.isEmpty()) {
            v1_1.addAll(this.selectedServers);
            return v1_1;
        } else {
            v1_1.add(v0_3);
            return v1_1;
        }
    }

    protected java.util.List getServers(com.bisimplex.firebooru.danbooru.ServerItemType p2)
    {
        return com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServersByType(p2);
    }

    public void onAttach(android.content.Context p2)
    {
        super.onAttach(p2);
        if ((this.listener == null) && ((p2 instanceof com.bisimplex.firebooru.activity.MainActivity))) {
            this.listener = ((com.bisimplex.firebooru.activity.MainActivity) p2).getSearchDialogListener();
        }
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        return p2.inflate(2131558629, p3, 0);
    }

    public void onResume()
    {
        super.onResume();
        android.widget.AutoCompleteTextView v0 = this.autoCompleteTextView;
        if (v0 != null) {
            v0.requestFocus();
        }
        return;
    }

    public void onViewCreated(android.view.View p10, android.os.Bundle p11)
    {
        boolean v5_0;
        super.onViewCreated(p10, p11);
        this.autoCompleteTextView = ((android.widget.AutoCompleteTextView) p10.findViewById(2131361898));
        this.numberEditText = ((android.widget.EditText) p10.findViewById(2131362377));
        this.serverChipGroup = ((com.google.android.material.chip.ChipGroup) p10.findViewById(2131362518));
        this.serverSpinner = ((android.widget.Spinner) p10.findViewById(2131362520));
        this.blacklistedCheckBox = ((android.widget.CheckBox) p10.findViewById(2131361911));
        this.sortSpinner = ((android.widget.Spinner) p10.findViewById(2131362548));
        Integer v0_35 = new com.bisimplex.firebooru.dataadapter.TagClientAdapter(this.getActivity());
        this.tagAdapter = v0_35;
        this.autoCompleteTextView.setAdapter(v0_35);
        Integer v0_36 = com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone;
        int v3_1 = 0;
        int v4_0 = -1;
        if (p11 != null) {
            v5_0 = 0;
        } else {
            com.bisimplex.firebooru.danbooru.UserConfiguration v11_3 = this.getArguments();
            if (v11_3 == null) {
                v5_0 = 0;
            } else {
                Integer v0_41 = ((com.bisimplex.firebooru.network.SourceQuery) new com.google.gson.Gson().fromJson(v11_3.getString("QUERY_JSON", ""), com.bisimplex.firebooru.network.SourceQuery));
                this.sourceQuery = v0_41;
                if (v0_41 == null) {
                    this.sourceQuery = new com.bisimplex.firebooru.network.SourceQuery();
                }
                v0_36 = com.bisimplex.firebooru.danbooru.ServerItemType.fromInteger(v11_3.getInt("SERVER_FILTER_TYPE_ID", com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone.getValue()));
                v4_0 = v11_3.getInt("SERVER_ID", -1);
                if (v11_3.getBoolean("HIDE_PAGE_SELECTOR", 0)) {
                    this.numberEditText.setVisibility(8);
                    boolean v5_9 = this.blacklistedCheckBox;
                    if (v5_9) {
                        v5_9.setVisibility(8);
                    }
                }
                v5_0 = v11_3.getBoolean("MULTI_SERVER_SELECTOR", 0);
                if (v5_0) {
                    v3_1 = v11_3.getIntegerArrayList("MULTI_SERVER_SELECTED_IDS");
                    if (v3_1 == 0) {
                        v3_1 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getMultiSearchSelectedServerIds();
                    }
                }
            }
            this.autoCompleteTextView.setText(this.sourceQuery.getText());
            com.bisimplex.firebooru.danbooru.UserConfiguration v11_1 = this.blacklistedCheckBox;
            if (v11_1 != null) {
                v11_1.setChecked(this.sourceQuery.isIncludeBlacklisted());
            }
        }
        if (v4_0 < 0) {
            v4_0 = this.getDefaultServerID();
        }
        com.bisimplex.firebooru.danbooru.UserConfiguration v11_2 = this.getServers(v0_36);
        Integer v0_3 = new android.widget.ArrayAdapter(this.getActivity(), 2131558650, 2131362196, v11_2);
        int v6_4 = (v11_2.size() - 1);
        int v7_1 = 0;
        while (v7_1 < v11_2.size()) {
            if (((com.bisimplex.firebooru.danbooru.ServerItem) v11_2.get(v7_1)).getServerId() != v4_0) {
                v7_1++;
            } else {
                v6_4 = v7_1;
                break;
            }
        }
        v0_3.setDropDownViewResource(2131558642);
        this.serverSpinner.setAdapter(v0_3);
        if (v6_4 > 0) {
            this.serverSpinner.setSelection(v6_4, 0);
        }
        if (this.sortSpinner != null) {
            this.updateSort();
            this.serverSpinner.setOnItemSelectedListener(new com.bisimplex.firebooru.view.SearchDialog$1(this));
        }
        this.autoCompleteTextView.setOnKeyListener(new com.bisimplex.firebooru.view.SearchDialog$2(this));
        this.autoCompleteTextView.addTextChangedListener(new com.bisimplex.firebooru.view.SearchDialog$3(this));
        this.autoCompleteTextView.setOnItemClickListener(new com.bisimplex.firebooru.view.SearchDialog$4(this));
        this.autoCompleteTextView.setOnFocusChangeListener(new com.bisimplex.firebooru.view.SearchDialog$5(this));
        ((android.widget.Button) p10.findViewById(2131362497)).setOnClickListener(new com.bisimplex.firebooru.view.SearchDialog$6(this));
        Integer v0_17 = this.numberEditText;
        if (v0_17 != null) {
            v0_17.setOnKeyListener(new com.bisimplex.firebooru.view.SearchDialog$7(this));
        }
        com.google.android.material.chip.ChipGroup v10_2 = ((android.widget.Button) p10.findViewById(2131361869));
        if (!v5_0) {
            if (v10_2 != null) {
                v10_2.setVisibility(8);
            }
            com.google.android.material.chip.ChipGroup v10_3 = this.serverChipGroup;
            if (v10_3 != null) {
                v10_3.setVisibility(8);
            }
        } else {
            v10_2.setVisibility(0);
            this.serverChipGroup.setVisibility(0);
            v10_2.setOnClickListener(new com.bisimplex.firebooru.view.SearchDialog$8(this));
            if (v3_1 != 0) {
                com.google.android.material.chip.ChipGroup v10_4 = v3_1.iterator();
                while (v10_4.hasNext()) {
                    Integer v0_26 = ((Integer) v10_4.next());
                    java.util.Iterator v1_0 = v11_2.iterator();
                    while (v1_0.hasNext()) {
                        com.bisimplex.firebooru.danbooru.ServerItem v2_2 = ((com.bisimplex.firebooru.danbooru.ServerItem) v1_0.next());
                        if (v2_2.getServerId() == v0_26.intValue()) {
                            this.addServerToList(v2_2);
                        }
                    }
                }
            }
        }
        return;
    }

    protected void search()
    {
        if (this.listener != null) {
            com.bisimplex.firebooru.danbooru.UserConfiguration v0_4 = new com.bisimplex.firebooru.network.SourceQuery(this.autoCompleteTextView.getText().toString());
            com.bisimplex.firebooru.danbooru.UserConfiguration v1_16 = this.blacklistedCheckBox;
            if (v1_16 != null) {
                v0_4.setIncludeBlacklisted(v1_16.isChecked());
            }
            try {
                v0_4.setInitialPage(((long) Integer.parseInt(this.numberEditText.getText().toString())));
            } catch (com.bisimplex.firebooru.danbooru.UserConfiguration v1_6) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v1_6);
            }
            com.bisimplex.firebooru.danbooru.UserConfiguration v1_7 = this.getSelectedServers();
            if (v1_7.size() != 1) {
                this.listener.onSearchSources(v0_4, v1_7);
                com.bisimplex.firebooru.danbooru.UserConfiguration v0_3 = new java.util.ArrayList();
                com.bisimplex.firebooru.danbooru.UserConfiguration v1_9 = v1_7.iterator();
                while (v1_9.hasNext()) {
                    Integer v2_5 = ((com.bisimplex.firebooru.danbooru.ServerItem) v1_9.next());
                    if (v2_5.getServerId() >= 0) {
                        v0_3.add(Integer.valueOf(v2_5.getServerId()));
                    }
                }
                com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setMultiSearchSelectedServerIds(v0_3);
            } else {
                Integer v2_9 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer();
                com.bisimplex.firebooru.danbooru.UserConfiguration v1_13 = ((com.bisimplex.firebooru.danbooru.ServerItem) v1_7.get(0));
                int v3_3 = this.getAdditionalParameters(v1_13);
                if (v3_3.size() > 0) {
                    v0_4.getExtraParams().putAll(v3_3);
                }
                if ((v2_9 == null) || (v2_9.getServerId() == v1_13.getServerId())) {
                    this.listener.onSearch(v0_4, 0);
                } else {
                    this.listener.onSearch(v0_4, v1_13);
                }
                com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setMultiSearchSelectedServerIds(0);
            }
        }
        this.dismiss();
        return;
    }

    public void searchTags(String p3)
    {
        if (this.sourceTag == null) {
            this.sourceTag = ((com.bisimplex.firebooru.network.SourceTag) com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Tag));
        }
        if (!android.text.TextUtils.isEmpty(p3)) {
            com.bisimplex.firebooru.network.SourceTag v3_1 = p3.split(" ");
            if (v3_1.length != 0) {
                com.bisimplex.firebooru.network.SourceTag v3_2 = v3_1[(v3_1.length - 1)];
                if (v3_2.length() >= this.autoCompleteTextView.getThreshold()) {
                    this.sourceTag.setQuery(new com.bisimplex.firebooru.network.SourceQuery(v3_2));
                    this.sourceTag.loadAnotherPage(new com.bisimplex.firebooru.view.SearchDialog$11(this));
                }
            }
        }
        return;
    }

    public void setListener(com.bisimplex.firebooru.view.SearchDialog$OnSearchDialogListener p1)
    {
        this.listener = p1;
        return;
    }
}
