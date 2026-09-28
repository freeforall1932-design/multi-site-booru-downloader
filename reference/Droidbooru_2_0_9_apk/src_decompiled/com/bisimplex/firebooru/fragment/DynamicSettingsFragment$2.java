package com.bisimplex.firebooru.fragment;
 class DynamicSettingsFragment$2 implements com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener {
    final synthetic com.bisimplex.firebooru.fragment.DynamicSettingsFragment this$0;

    DynamicSettingsFragment$2(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    static synthetic void lambda$valueChanged$0(android.content.DialogInterface p0, int p1)
    {
        return;
    }

    public void triggerAction(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p2, com.bisimplex.firebooru.dataadapter.search.ActionType p3, int p4)
    {
        com.bisimplex.firebooru.fragment.DynamicSettingsFragment v2_1 = p2.getItem(p4);
        if (v2_1 != null) {
            if (!v2_1.getKey().equalsIgnoreCase("VALIDATE")) {
                if (!v2_1.getKey().equalsIgnoreCase("COOKIES_BUTTONS")) {
                    if (!v2_1.getKey().equalsIgnoreCase("SAVE_TO_BUTTONS")) {
                        if (!v2_1.getKey().equalsIgnoreCase("DELETE_FAVORITES")) {
                            if (!v2_1.getKey().equalsIgnoreCase("DELETE_SEARCH_HISTORY")) {
                                if (!v2_1.getKey().equalsIgnoreCase("DELETE_POST_HISTORY")) {
                                    if (!v2_1.getKey().equalsIgnoreCase("CLEAR_CACHE")) {
                                        if (!v2_1.getKey().equalsIgnoreCase("BACKUP_BUTTONS")) {
                                            if (!v2_1.getKey().equalsIgnoreCase("SCREEN_LOCK_BUTTON")) {
                                                if (!v2_1.getKey().equalsIgnoreCase("PROXY_BUTTON")) {
                                                    if (!v2_1.getKey().equalsIgnoreCase("EULA_BUTTON")) {
                                                        if (!v2_1.getKey().equalsIgnoreCase("APP_UPDATE_BUTTON")) {
                                                            if (!v2_1.getKey().equalsIgnoreCase("edit_file_name")) {
                                                                if (!v2_1.getKey().equalsIgnoreCase("APP_CHANGE_LOG_BUTTON")) {
                                                                    if (v2_1.getKey().equalsIgnoreCase("APP_EVENT_LOG_BUTTON")) {
                                                                        com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$msendEventLog(this.this$0);
                                                                    }
                                                                } else {
                                                                    com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mviewChangeLog(this.this$0);
                                                                    return;
                                                                }
                                                            } else {
                                                                com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$meditFileNameFormatting(this.this$0);
                                                                return;
                                                            }
                                                        } else {
                                                            com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mupdateApp(this.this$0);
                                                            return;
                                                        }
                                                    } else {
                                                        com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mviewEULA(this.this$0);
                                                        return;
                                                    }
                                                } else {
                                                    com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mconfigureProxy(this.this$0);
                                                    return;
                                                }
                                            } else {
                                                com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mconfigureScreenLock(this.this$0);
                                                return;
                                            }
                                        } else {
                                            if (p3 != com.bisimplex.firebooru.dataadapter.search.ActionType.Add) {
                                                com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mrestore(this.this$0);
                                                return;
                                            } else {
                                                com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mbackup(this.this$0);
                                                return;
                                            }
                                        }
                                    } else {
                                        com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mclearCache(this.this$0);
                                        return;
                                    }
                                } else {
                                    com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mdeletePostHistory(this.this$0);
                                    return;
                                }
                            } else {
                                com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mdeleteHistory(this.this$0);
                                return;
                            }
                        } else {
                            com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mdeleteFavorites(this.this$0);
                            return;
                        }
                    } else {
                        if (p3 != com.bisimplex.firebooru.dataadapter.search.ActionType.Reset) {
                            com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mselectStorageLocation(this.this$0);
                            return;
                        } else {
                            com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mresetStorageLocation(this.this$0);
                            return;
                        }
                    }
                } else {
                    if (p3 != com.bisimplex.firebooru.dataadapter.search.ActionType.Reset) {
                        com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mshowCookies(this.this$0);
                        return;
                    } else {
                        com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mclearCookies(this.this$0);
                        return;
                    }
                }
            } else {
                com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mvalidateClient(this.this$0);
                return;
            }
        }
        return;
    }

    public void valueChanged(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p2, int p3)
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v2_1 = p2.getItem(p3);
        if (v2_1 != null) {
            if (!v2_1.getKey().equalsIgnoreCase("THEME")) {
                if (!v2_1.getKey().equalsIgnoreCase("STATUS_BAR")) {
                    if (v2_1.getKey().equalsIgnoreCase("DNS_PROVIDER")) {
                        com.google.android.material.dialog.MaterialAlertDialogBuilder v2_5 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.this$0.requireActivity());
                        v2_5.setMessage(2131886428).setTitle(2131887087);
                        v2_5.setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$2$$ExternalSyntheticLambda0());
                        v2_5.show();
                    }
                } else {
                    com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mupdateStatusBarVisible(this.this$0, ((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) v2_1).getValue()).booleanValue());
                    return;
                }
            } else {
                com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mreloadTheme(this.this$0);
                return;
            }
        }
        return;
    }
}
