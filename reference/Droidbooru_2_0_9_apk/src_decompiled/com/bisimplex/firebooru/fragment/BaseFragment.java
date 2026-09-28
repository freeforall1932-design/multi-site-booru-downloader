package com.bisimplex.firebooru.fragment;
public class BaseFragment extends androidx.fragment.app.Fragment implements com.bisimplex.firebooru.fragment.IBaseFragment {
    public static final int REQUEST_WRITE_PERMISSION_KEY = 8;
    private com.google.android.material.bottomappbar.BottomAppBar bottomAppBar;
    private android.os.Handler progressHandler;
    private com.google.android.material.appbar.MaterialToolbar topAppBar;

    public static synthetic void $r8$lambda$x0Jrc0UciQl2MI6rmq2P0VVHr7Y(com.bisimplex.firebooru.fragment.BaseFragment p0, android.widget.EditText p1, com.bisimplex.firebooru.fragment.BaseFragment$AddGroupListener p2, android.content.DialogInterface p3, int p4)
    {
        p0.lambda$askAddGroup$0(p1, p2, p3, p4);
        return;
    }

    static bridge synthetic com.google.android.material.bottomappbar.BottomAppBar -$$Nest$fgetbottomAppBar(com.bisimplex.firebooru.fragment.BaseFragment p0)
    {
        return p0.bottomAppBar;
    }

    static bridge synthetic com.google.android.material.appbar.MaterialToolbar -$$Nest$fgettopAppBar(com.bisimplex.firebooru.fragment.BaseFragment p0)
    {
        return p0.topAppBar;
    }

    public BaseFragment()
    {
        return;
    }

    public static com.mikepenz.iconics.IconicsDrawable iconWithColor(android.content.Context p1, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon p2, int p3)
    {
        com.mikepenz.iconics.IconicsDrawable v0_1 = new com.mikepenz.iconics.IconicsDrawable(p1, p2);
        v0_1.setColorList(android.content.res.ColorStateList.valueOf(androidx.core.content.ContextCompat.getColor(p1, p3)));
        return com.mikepenz.iconics.utils.IconicsDrawableExtensionsKt.actionBar(v0_1);
    }

    private synthetic void lambda$askAddGroup$0(android.widget.EditText p1, com.bisimplex.firebooru.fragment.BaseFragment$AddGroupListener p2, android.content.DialogInterface p3, int p4)
    {
        this.addGroup(p1.getText().toString(), p2);
        return;
    }

    public void HideLoading()
    {
        if (!this.isDetached()) {
            this.progressHandler.post(new com.bisimplex.firebooru.fragment.BaseFragment$2(this));
            return;
        } else {
            return;
        }
    }

    public void ShowLoading()
    {
        if (!this.isDetached()) {
            com.bisimplex.firebooru.activity.MenuBaseActivity v0_2 = ((com.bisimplex.firebooru.activity.MenuBaseActivity) this.getActivity());
            if (v0_2 != null) {
                v0_2.ShowLoading();
            }
        }
        return;
    }

    public void addGroup(String p3, com.bisimplex.firebooru.fragment.BaseFragment$AddGroupListener p4)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            com.bisimplex.firebooru.model.SourceSpecs v0_2 = new com.bisimplex.firebooru.model.SourceSpecs();
            v0_2.setType(4);
            v0_2.setQuery(new com.bisimplex.firebooru.network.SourceQuery(p3));
            v0_2.setKey(java.util.UUID.randomUUID().toString());
            v0_2.setChilds(new java.util.ArrayList(1));
            if (p4 != null) {
                p4.groupCreatd(v0_2);
            }
        }
        return;
    }

    public void askAddGroup(com.bisimplex.firebooru.fragment.BaseFragment$AddGroupListener p7)
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_3 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext());
        android.view.View v1_0 = this.requireActivity().getLayoutInflater().inflate(2131558476, 0);
        android.widget.EditText v2_2 = ((android.widget.EditText) v1_0.findViewById(2131362442));
        ((com.google.android.material.textfield.TextInputLayout) v1_0.findViewById(2131362443)).setHint(this.getString(2131886598));
        v0_3.setTitle(2131886135).setNegativeButton(2131886205, 0).setPositiveButton(2131886133, new com.bisimplex.firebooru.fragment.BaseFragment$$ExternalSyntheticLambda0(this, v2_2, p7)).setView(v1_0).show();
        return;
    }

    protected void askAddGroup(com.bisimplex.firebooru.model.SourceSpecs p2, com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener p3)
    {
        this.askAddGroup(new com.bisimplex.firebooru.fragment.BaseFragment$8(this, p2, p3));
        return;
    }

    protected void askToValidateClient()
    {
        new com.bisimplex.firebooru.view.ValidateClientDialog().show(this.getParentFragmentManager(), "ValidateClientDialog");
        return;
    }

    protected void changeStatusBar(boolean p2)
    {
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isStatusBarVisible()) {
            if (!p2) {
                this.hideSystemUI();
            } else {
                this.showSystemUI();
                return;
            }
        }
        return;
    }

    protected void checkNotificationPermission()
    {
        androidx.fragment.app.FragmentActivity v0 = this.requireActivity();
        if ((android.os.Build$VERSION.SDK_INT >= 33) && (androidx.core.content.ContextCompat.checkSelfPermission(v0, "android.permission.POST_NOTIFICATIONS") != 0)) {
            String[] v2_0 = new String[1];
            v2_0[0] = "android.permission.POST_NOTIFICATIONS";
            androidx.core.app.ActivityCompat.requestPermissions(v0, v2_0, 68);
        }
        return;
    }

    protected void configureBar(androidx.appcompat.widget.Toolbar p5)
    {
        if (p5 != null) {
            p5.setVisibility(0);
            p5.setNavigationIcon(this.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_bars, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
            boolean v1_0 = p5.getMenu();
            this.setIconToMenuItem(v1_0, 2131362519, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_server);
            this.setIconToMenuItem(v1_0, 2131362498, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_search);
            this.setIconToMenuItem(v1_0, 2131362525, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_share);
            this.setIconToMenuItem(v1_0, 2131362167, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_file_import);
            this.setIconToMenuItem(v1_0, 2131362007, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_grip_horizontal);
            this.setIconToMenuItem(v1_0, 2131362010, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_download);
            this.setIconToMenuItem(v1_0, 2131362193, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_chevron_circle_down);
            this.setIconToMenuItem(v1_0, 2131362421, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_thumbtack);
            this.setIconToMenuItem(v1_0, 2131362157, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_history);
            this.setIconToMenuItem(v1_0, 2131362100, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_file_csv);
            p5.setNavigationOnClickListener(new com.bisimplex.firebooru.fragment.BaseFragment$7(this));
            boolean v1_5 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().hideNavBars();
            if (!(p5 instanceof com.google.android.material.bottomappbar.BottomAppBar)) {
                if ((p5 instanceof com.google.android.material.appbar.MaterialToolbar)) {
                    com.google.android.material.appbar.AppBarLayout$LayoutParams v5_3 = ((com.google.android.material.appbar.AppBarLayout$LayoutParams) ((com.google.android.material.appbar.MaterialToolbar) p5).getLayoutParams());
                    if (!v1_5) {
                        v5_3.setScrollFlags(0);
                    } else {
                        v5_3.setScrollFlags(21);
                        return;
                    }
                }
            } else {
                ((com.google.android.material.bottomappbar.BottomAppBar) p5).setHideOnScroll(v1_5);
                return;
            }
        }
        return;
    }

    protected void configureInsets(androidx.core.graphics.Insets p8, androidx.core.graphics.Insets p9)
    {
        android.view.View v0 = this.getInsetContentView();
        if (v0 != null) {
            int v1_4 = new android.util.TypedValue();
            this.requireContext().getTheme().resolveAttribute(16843499, v1_4, 1);
            int v1_1 = this.getResources().getDimensionPixelSize(v1_4.resourceId);
            int v2_2 = this.getResources().getDimensionPixelSize(2131166088);
            int v3_2 = this.getResources().getDimensionPixelSize(2131166091);
            if (!(v0 instanceof androidx.core.view.ScrollingView)) {
                v0.setPadding((p8.left + p9.left), ((Math.max(p8.top, p9.top) + v1_1) + v3_2), (p8.right + p9.right), ((p8.bottom + v2_2) + p9.bottom));
            } else {
                v0.setPadding((p8.left + p9.left), ((Math.max(p8.top, p9.top) + v1_1) + v3_2), (p8.right + p9.right), ((p8.bottom + v2_2) + p9.bottom));
                return;
            }
        }
        return;
    }

    protected void fixFloatButtonLayout(com.google.android.material.floatingactionbutton.FloatingActionButton p4)
    {
        androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams v0_1 = ((androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams) p4.getLayoutParams());
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
            v0_1.setAnchorId(-1);
            v0_1.setMargins(((int) this.getResources().getDimension(2131165370)), ((int) this.getResources().getDimension(2131165370)), ((int) this.getResources().getDimension(2131165370)), ((int) this.getResources().getDimension(2131165370)));
            v0_1.gravity = 81;
            p4.setLayoutParams(v0_1);
        }
        if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().hideNavBars()) {
            v0_1.setBehavior(0);
            p4.setLayoutParams(v0_1);
        }
        return;
    }

    protected void fixFloatViewLayout(android.view.View p4)
    {
        androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams v0_1 = ((androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams) p4.getLayoutParams());
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
            v0_1.setAnchorId(-1);
            v0_1.setMargins(((int) this.getResources().getDimension(2131165370)), ((int) this.getResources().getDimension(2131165370)), ((int) this.getResources().getDimension(2131165370)), ((int) this.getResources().getDimension(2131165370)));
            v0_1.gravity = 81;
            p4.setLayoutParams(v0_1);
        }
        if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().hideNavBars()) {
            v0_1.setBehavior(0);
            p4.setLayoutParams(v0_1);
        }
        return;
    }

    public com.bisimplex.firebooru.fragment.IBaseFragment getFramentCompanion()
    {
        return new com.bisimplex.firebooru.fragment.TagHistoryFragment();
    }

    protected android.view.View getInsetContentView()
    {
        return this.getView();
    }

    public boolean getShouldResetStack()
    {
        return 1;
    }

    protected android.view.View getSnackBarAnchorView()
    {
        if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
            return this.bottomAppBar;
        } else {
            return 0;
        }
    }

    public androidx.appcompat.app.ActionBar getSupportActionBar()
    {
        int v0_1 = ((androidx.appcompat.app.AppCompatActivity) this.getActivity());
        if (v0_1 == 0) {
            return 0;
        } else {
            return v0_1.getSupportActionBar();
        }
    }

    protected androidx.appcompat.widget.Toolbar getVisibleBar()
    {
        if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
            return this.bottomAppBar;
        } else {
            return this.topAppBar;
        }
    }

    public String getiOsFragmentName()
    {
        return this.getClass().getName();
    }

    public void goBackInStack()
    {
        if (!this.isDetached()) {
            this.progressHandler.post(new com.bisimplex.firebooru.fragment.BaseFragment$3(this));
            return;
        } else {
            return;
        }
    }

    public void hideKeyboard()
    {
        android.os.IBinder v0_0 = this.getActivity();
        if (v0_0 != null) {
            android.view.inputmethod.InputMethodManager v1_2 = ((android.view.inputmethod.InputMethodManager) v0_0.getSystemService("input_method"));
            int v2_1 = v0_0.getCurrentFocus();
            if (v2_1 == 0) {
                v2_1 = new android.view.View(v0_0);
            }
            v1_2.hideSoftInputFromWindow(v2_1.getWindowToken(), 0);
            return;
        } else {
            return;
        }
    }

    protected void hideSystemUI()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.setStatusBarVisible(0);
            return;
        } else {
            return;
        }
    }

    public com.mikepenz.iconics.IconicsDrawable iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon p2, int p3)
    {
        return com.bisimplex.firebooru.fragment.BaseFragment.iconWithColor(this.getContext(), p2, p3);
    }

    protected void initializeNavigationBar()
    {
        com.google.android.material.appbar.MaterialToolbar v0_0 = this.getView();
        if (v0_0 != null) {
            this.bottomAppBar = ((com.google.android.material.bottomappbar.BottomAppBar) v0_0.findViewById(2131362725));
            this.topAppBar = ((com.google.android.material.appbar.MaterialToolbar) v0_0.findViewById(2131362656));
            if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
                com.google.android.material.appbar.MaterialToolbar v0_5 = this.bottomAppBar;
                if (v0_5 == null) {
                    com.google.android.material.appbar.MaterialToolbar v0_6 = this.topAppBar;
                    if (v0_6 != null) {
                        this.configureBar(v0_6);
                    }
                } else {
                    this.configureBar(v0_5);
                    return;
                }
            } else {
                com.google.android.material.appbar.MaterialToolbar v0_7 = this.topAppBar;
                if (v0_7 == null) {
                    com.google.android.material.appbar.MaterialToolbar v0_8 = this.bottomAppBar;
                    if (v0_8 != null) {
                        this.configureBar(v0_8);
                        return;
                    }
                } else {
                    this.configureBar(v0_7);
                    return;
                }
            }
        }
        return;
    }

    public boolean isIncludedPermission(String p3)
    {
        android.content.Context v0_0 = this.getActivity();
        if (v0_0 != null) {
            if (v0_0.getApplicationContext().checkCallingOrSelfPermission(p3) != 0) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public boolean isKindleFire()
    {
        return android.os.Build.MANUFACTURER.contains("Amazon");
    }

    public void launchBrowser(String p3)
    {
        if (!this.isDetached()) {
            com.bisimplex.firebooru.activity.MainActivity v0_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
            if (v0_2 != null) {
                v0_2.launchBrowser(p3, 0);
                return;
            }
        }
        return;
    }

    public void menuOpened()
    {
        return;
    }

    public String messageForFailure(com.bisimplex.firebooru.data.FailureType p3, int p4)
    {
        switch (com.bisimplex.firebooru.fragment.BaseFragment$9.$SwitchMap$com$bisimplex$firebooru$data$FailureType[p3.ordinal()]) {
            case 1:
                return this.getString(2131886727);
            case 2:
                return this.getString(2131886219);
            case 3:
                return this.getString(2131886276);
            case 4:
                return this.getString(2131887225);
            case 5:
                return this.getString(2131886600);
            case 6:
                return this.getString(2131886993);
            case 7:
                return this.getString(2131886729);
            case 8:
                return this.getString(2131887220);
            case 9:
                return this.getString(2131887141);
            case 10:
                return this.getString(2131887149);
            default:
                if (p4 == p3.getValue()) {
                    return this.getString(2131886492, new Object[] {Integer.valueOf(p3.getValue())}));
                } else {
                    if (p4 == 0) {
                        return this.getString(2131886492, new Object[] {Integer.valueOf(p3.getValue())}));
                    } else {
                        return this.getString(2131886492, new Object[] {Integer.valueOf(p4)}));
                    }
                }
        }
    }

    public boolean onBackPressed()
    {
        return 0;
    }

    public void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        this.progressHandler = new android.os.Handler(this.getActivity().getApplicationContext().getMainLooper());
        this.setHasOptionsMenu(1);
        return;
    }

    public void onCreateOptionsMenu(android.view.Menu p2, android.view.MenuInflater p3)
    {
        p3.inflate(2131689483, p2);
        p2.findItem(2131362157).setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.BaseFragment$6(this)).setIcon(this.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_history, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
        return;
    }

    public boolean onKeyDown(int p1, android.view.KeyEvent p2)
    {
        return 0;
    }

    public boolean onKeyUp(int p3, android.view.KeyEvent p4)
    {
        if (!(this.requireActivity().getCurrentFocus() instanceof android.widget.EditText)) {
            if (((p4.getKeyCode() != 67) && (p4.getKeyCode() != 109)) || (p4.isCanceled())) {
                return 0;
            } else {
                this.goBackInStack();
                return 1;
            }
        } else {
            return 0;
        }
    }

    public void onResume()
    {
        super.onResume();
        this.showActionbarIfHidden();
        return;
    }

    public void onViewCreated(android.view.View p2, android.os.Bundle p3)
    {
        super.onViewCreated(p2, p3);
        this.initializeNavigationBar();
        if (android.os.Build$VERSION.SDK_INT >= 27) {
            androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(p2, new com.bisimplex.firebooru.fragment.BaseFragment$1(this));
        }
        return;
    }

    public void openDrawer()
    {
        if (!this.isDetached()) {
            com.bisimplex.firebooru.activity.MainActivity v0_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
            if (v0_2 != null) {
                v0_2.openDrawer();
                return;
            }
        }
        return;
    }

    public void openSecondaryDrawer()
    {
        return;
    }

    public java.util.List provideKeyboardShortcuts()
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        java.util.ArrayList v1_1 = new java.util.ArrayList();
        v1_1.add(new android.view.KeyboardShortcutInfo(this.getString(2131886164), 67, 0));
        v0_1.add(new android.view.KeyboardShortcutGroup(this.getString(2131886976), v1_1));
        return v0_1;
    }

    protected boolean savePermissionGranted()
    {
        if ((android.os.Build$VERSION.SDK_INT < 30) && (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isUsingStorageAcccessFramework())) {
            com.bisimplex.firebooru.activity.MainActivity v0_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
            if (v0_2 != null) {
                if (androidx.core.content.ContextCompat.checkSelfPermission(v0_2, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                    com.google.android.material.snackbar.Snackbar.make(this.getView(), 2131887186, 0).show();
                    String[] v2_1 = new String[1];
                    v2_1[0] = "android.permission.WRITE_EXTERNAL_STORAGE";
                    androidx.core.app.ActivityCompat.requestPermissions(v0_2, v2_1, 8);
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    protected void setIconToMenuItem(android.view.Menu p1, int p2, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon p3)
    {
        android.view.MenuItem v1_1 = p1.findItem(p2);
        if (v1_1 != null) {
            v1_1.setIcon(this.iconWithColor(p3, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
            return;
        } else {
            return;
        }
    }

    public void setTitle(int p2)
    {
        if (!this.isDetached()) {
            androidx.fragment.app.FragmentActivity v0_1 = this.getActivity();
            if (v0_1 != null) {
                v0_1.setTitle(p2);
            }
        }
        return;
    }

    public void setTitle(String p2)
    {
        if (!this.isDetached()) {
            androidx.fragment.app.FragmentActivity v0_1 = this.getActivity();
            if (v0_1 != null) {
                v0_1.setTitle(p2);
            }
        }
        return;
    }

    public void shareUrl(String p2)
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.shareURL(p2);
            return;
        } else {
            return;
        }
    }

    public void showActionbarIfHidden()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_0 = this.getSupportActionBar();
        if ((v0_0 != null) && (!v0_0.isShowing())) {
            v0_0.show();
            this.showSystemUI();
        }
        com.bisimplex.firebooru.activity.MainActivity v0_2 = this.getActivity();
        if ((v0_2 instanceof com.bisimplex.firebooru.activity.MainActivity)) {
            com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) v0_2);
            v0_1.setScrimVisible(1);
            v0_1.setStatusBarVisible(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isStatusBarVisible());
        }
        return;
    }

    public void showMessage(int p2, com.bisimplex.firebooru.activity.MessageType p3)
    {
        if ((!this.isDetached()) && (this.getActivity() != null)) {
            this.showMessage(this.getString(p2), p3);
            return;
        } else {
            return;
        }
    }

    public void showMessage(com.bisimplex.firebooru.data.FailureType p3, int p4)
    {
        if (p3 != com.bisimplex.firebooru.data.FailureType.Forbidden) {
            this.showMessage(this.messageForFailure(p3, p4), com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        } else {
            this.progressHandler.post(new com.bisimplex.firebooru.fragment.BaseFragment$4(this, p3, p4));
            return;
        }
    }

    public void showMessage(String p2, com.bisimplex.firebooru.activity.MessageType p3)
    {
        if ((!this.isDetached()) && (((com.bisimplex.firebooru.activity.MenuBaseActivity) this.getActivity()) != null)) {
            this.progressHandler.post(new com.bisimplex.firebooru.fragment.BaseFragment$5(this, p3, p2));
            return;
        } else {
            return;
        }
    }

    protected void showMultiSearch(com.bisimplex.firebooru.network.SourceQuery p9, java.util.List p10)
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if ((v0_1 != null) && (!p10.isEmpty())) {
            if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isCombineMultiSearch()) {
                com.bisimplex.firebooru.fragment.MultiSearchFragment v1_2 = new com.bisimplex.firebooru.fragment.MultiSearchFragment();
                android.os.Bundle v3_1 = new java.util.ArrayList();
                String v4_0 = p10.iterator();
                while (v4_0.hasNext()) {
                    int v5_2 = ((com.bisimplex.firebooru.danbooru.ServerItem) v4_0.next());
                    com.bisimplex.firebooru.model.SourceSpecs v6_1 = new com.bisimplex.firebooru.model.SourceSpecs();
                    v6_1.setKey(java.util.UUID.randomUUID().toString());
                    v6_1.setServer(v5_2);
                    v6_1.setUrl(v5_2.getUrl());
                    v6_1.setQuery(p9);
                    v6_1.setType(3);
                    v3_1.add(v6_1);
                }
                String v9_2 = com.bisimplex.firebooru.network.HttpClient.getGson().toJson(v3_1);
                android.os.Bundle v3_3 = new android.os.Bundle(1);
                v3_3.putString("SPECS", v9_2);
                v1_2.setArguments(v3_3);
                v0_1.switchContent(v1_2);
            } else {
                com.bisimplex.firebooru.fragment.MultiSearchFragment v1_5 = new com.bisimplex.firebooru.fragment.MixedSearchFragment();
                android.os.Bundle v3_5 = new android.os.Bundle(1);
                String v4_4 = ((com.bisimplex.firebooru.network.SourceMultiPost) com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.MultiPost));
                v4_4.prepare(p9, p10);
                v3_5.putString("SOURCE_KEY", v4_4.getKey());
                v1_5.setArguments(v3_5);
                v0_1.switchContent(v1_5);
            }
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().setSelectedServer(((com.bisimplex.firebooru.danbooru.ServerItem) p10.get((p10.size() - 1))).getServerId());
        }
        return;
    }

    protected void showSearchHistory()
    {
        java.util.List v0_1 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadHistory();
        com.bisimplex.firebooru.activity.MainActivity v1_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v1_1 != null) {
            v1_1.showSecondaryMenu(com.bisimplex.firebooru.custom.SecondaryMenuType.History, v0_1);
        }
        return;
    }

    protected void showSystemUI()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.setStatusBarVisible(1);
            return;
        } else {
            return;
        }
    }

    public void switchFragment(androidx.fragment.app.Fragment p2)
    {
        if ((!this.isDetached()) && (this.getActivity() != null)) {
            ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity()).switchContent(p2);
            return;
        } else {
            return;
        }
    }
}
