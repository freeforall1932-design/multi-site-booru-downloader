package com.bisimplex.firebooru.dataadapter;
public class FileNameDataAdapter extends com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter {
    public static final String CURRENT_FORMAT = "CURRENT_FORMAT";
    public static final String EXAMPLE = "EXAMPLE";
    private java.util.List currentConfiguration;

    public FileNameDataAdapter(android.content.Context p1)
    {
        super(p1);
        super.currentConfiguration = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getFileNameConfiguration();
        super.fillForm();
        return;
    }

    private void fillForm()
    {
        this.data.clear();
        android.content.Context v0_1 = this.getContext();
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.LabelItem("CURRENT_FORMAT", v0_1.getString(2131886588), this.generateFileNameFormatting(this.currentConfiguration)));
        java.util.List v1_1 = com.bisimplex.firebooru.model.FileNamePartType.all().iterator();
        while (v1_1.hasNext()) {
            String v3_8;
            String v2_7 = ((com.bisimplex.firebooru.model.FileNamePartType) v1_1.next());
            String v3_6 = com.bisimplex.firebooru.dataadapter.FileNameDataAdapter$1.$SwitchMap$com$bisimplex$firebooru$model$FileNamePartType[v2_7.ordinal()];
            if (v3_6 == 1) {
                v3_8 = v0_1.getString(2131886610);
            } else {
                if (v3_6 == 2) {
                    v3_8 = v0_1.getString(2131886608);
                } else {
                    if (v3_6 == 3) {
                        v3_8 = v0_1.getString(2131886611);
                    } else {
                        if (v3_6 == 4) {
                            v3_8 = v0_1.getString(2131886609);
                        } else {
                            v3_8 = "";
                        }
                    }
                }
            }
            Boolean v5_6 = this.currentConfiguration.iterator();
            while (v5_6.hasNext()) {
                if (((com.bisimplex.firebooru.model.FileNamePartType) v5_6.next()) == v2_7) {
                    Boolean v5_7 = 1;
                }
                this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem(String.valueOf(v2_7.getValue()), v3_8, Boolean.valueOf(v5_7), 1));
            }
            v5_7 = 0;
        }
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CaptionItem("caption", v0_1.getString(2131886589)));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem("button", v0_1.getString(2131887099), com.bisimplex.firebooru.dataadapter.search.ActionType.Edit, v0_1.getString(2131887086), com.bisimplex.firebooru.dataadapter.search.ActionType.Reset, 1));
        this.bindValueChangeListener();
        return;
    }

    private String generateFileNameFormatting(java.util.List p6)
    {
        StringBuilder v0_1 = new StringBuilder();
        android.content.Context v1 = this.getContext();
        if (!p6.isEmpty()) {
            String v6_9 = p6.iterator();
            while (v6_9.hasNext()) {
                String v2_4 = com.bisimplex.firebooru.dataadapter.FileNameDataAdapter$1.$SwitchMap$com$bisimplex$firebooru$model$FileNamePartType[((com.bisimplex.firebooru.model.FileNamePartType) v6_9.next()).ordinal()];
                if (v2_4 == 1) {
                    v0_1.append(v1.getString(2131886610));
                    v0_1.append(" ");
                } else {
                    if (v2_4 == 2) {
                        v0_1.append(v1.getString(2131886608));
                        v0_1.append(" ");
                    } else {
                        if (v2_4 == 3) {
                            v0_1.append(v1.getString(2131886611));
                            v0_1.append(" ");
                        } else {
                            if (v2_4 == 4) {
                                v0_1.append(v1.getString(2131886609));
                                v0_1.append(" ");
                            }
                        }
                    }
                }
            }
            v0_1.trimToSize();
            v0_1.deleteCharAt((v0_1.length() - 1));
            v0_1.append(".");
            v0_1.append(v1.getString(2131886607));
            return v0_1.toString();
        } else {
            return v1.getString(2131886984);
        }
    }

    protected void notifyAction(int p6, com.bisimplex.firebooru.dataadapter.search.ActionType p7)
    {
        if (p7 != com.bisimplex.firebooru.dataadapter.search.ActionType.Reset) {
            if (p7 == com.bisimplex.firebooru.dataadapter.search.ActionType.Edit) {
                if (this.currentConfiguration.isEmpty()) {
                    this.currentConfiguration.add(com.bisimplex.firebooru.model.FileNamePartType.MD5);
                }
                com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setFileNameConfiguration(this.currentConfiguration);
            }
        } else {
            this.currentConfiguration.clear();
            this.currentConfiguration.add(com.bisimplex.firebooru.model.FileNamePartType.MD5);
            com.bisimplex.firebooru.danbooru.UserConfiguration v0_7 = String.valueOf(com.bisimplex.firebooru.model.FileNamePartType.MD5.getValue());
            java.util.List v1_3 = this.data.iterator();
            while (v1_3.hasNext()) {
                Boolean v2_2 = ((com.bisimplex.firebooru.dataadapter.search.Item) v1_3.next());
                if (!v2_2.getKey().equalsIgnoreCase("CURRENT_FORMAT")) {
                    if (v2_2.getType() == com.bisimplex.firebooru.dataadapter.search.ItemType.CheckBox) {
                        ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) v2_2).setValue(Boolean.valueOf(v2_2.getKey().equalsIgnoreCase(v0_7)));
                    }
                } else {
                    ((com.bisimplex.firebooru.dataadapter.search.LabelItem) v2_2).setValue(this.generateFileNameFormatting(this.currentConfiguration));
                }
            }
            this.notifyItemRangeChanged(0, this.data.size());
        }
        super.notifyAction(p6, p7);
        return;
    }

    protected void notifyValueChanged(com.bisimplex.firebooru.dataadapter.search.Item p3)
    {
        if (p3.getType() == com.bisimplex.firebooru.dataadapter.search.ItemType.CheckBox) {
            java.util.List v1_0 = com.bisimplex.firebooru.model.FileNamePartType.fromInteger(Integer.parseInt(((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p3).getKey()));
            if (!((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p3).getValue()).booleanValue()) {
                int v0_5 = this.currentConfiguration.indexOf(v1_0);
                if (v0_5 >= 0) {
                    this.currentConfiguration.remove(v0_5);
                }
            } else {
                if (!this.currentConfiguration.contains(v1_0)) {
                    this.currentConfiguration.add(v1_0);
                }
            }
            int v0_11 = ((com.bisimplex.firebooru.dataadapter.search.LabelItem) this.findEditableItemByKey("CURRENT_FORMAT"));
            v0_11.setValue(this.generateFileNameFormatting(this.currentConfiguration));
            this.notifyItemChanged(this.data.indexOf(v0_11));
        }
        super.notifyValueChanged(p3);
        return;
    }
}
