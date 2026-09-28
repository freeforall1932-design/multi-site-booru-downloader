package com.bisimplex.firebooru.network;
public class SourceQuery {
    public static final String EXTRA_TAGS = "extra_tags";
    public static final String SERVER_TYPE = "serve_type";
    public static final String SERVER_URL = "SERVER_URL";
    private boolean disableAutoLoad;
    private java.util.Map extraParams;
    private boolean includeBlacklisted;
    private long initialPage;
    private String text;
    private String title;

    public SourceQuery()
    {
        this.text = "";
        this.setTitle("");
        this.setExtraParams(new java.util.HashMap(0));
        return;
    }

    public SourceQuery(com.bisimplex.firebooru.network.SourceQuery p2)
    {
        this.text = p2.text;
        this.setTitle(p2.getTitle());
        this.setDisableAutoLoad(p2.isDisableAutoLoad());
        java.util.HashMap v2_1 = p2.getExtraParams();
        if ((v2_1 == null) || (v2_1.isEmpty())) {
            this.setExtraParams(new java.util.HashMap(0));
            return;
        } else {
            this.setExtraParams(new java.util.HashMap(v2_1));
            return;
        }
    }

    public SourceQuery(String p1)
    {
        this.setText(p1);
        return;
    }

    public SourceQuery(String p1, String p2)
    {
        this.text = p1;
        this.title = p2;
        return;
    }

    public java.util.Map getExtraParams()
    {
        return this.extraParams;
    }

    public String getExtraTags()
    {
        return ((String) this.extraParams.get("extra_tags"));
    }

    public long getInitialPage()
    {
        return this.initialPage;
    }

    public String getText()
    {
        return this.text;
    }

    public String getTitle()
    {
        if (!android.text.TextUtils.isEmpty(this.title)) {
            return this.title;
        } else {
            return this.text;
        }
    }

    public boolean isDisableAutoLoad()
    {
        return this.disableAutoLoad;
    }

    public boolean isEqualTo(com.bisimplex.firebooru.network.SourceQuery p6)
    {
        java.util.Iterator v0_6;
        int v1_0 = "";
        if (!android.text.TextUtils.isEmpty(this.text)) {
            v0_6 = this.text;
        } else {
            v0_6 = "";
        }
        if (!android.text.TextUtils.isEmpty(p6.getText())) {
            v1_0 = p6.getText();
        }
        if (!android.text.TextUtils.equals(v0_6, v1_0)) {
            return 0;
        } else {
            java.util.Iterator v0_4 = this.extraParams.keySet();
            int v6_1 = p6.getExtraParams();
            java.util.Iterator v0_5 = v0_4.iterator();
            while (v0_5.hasNext()) {
                boolean v2_3 = ((String) v0_5.next());
                if (v6_1.containsKey(v2_3)) {
                    if (!android.text.TextUtils.equals(((String) v6_1.get(v2_3)), ((String) this.extraParams.get(v2_3)))) {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
            return 1;
        }
    }

    public boolean isIncludeBlacklisted()
    {
        return this.includeBlacklisted;
    }

    public com.bisimplex.firebooru.network.SourceQuery minus(String p6, String p7)
    {
        com.bisimplex.firebooru.network.SourceQuery v0_1 = new com.bisimplex.firebooru.network.SourceQuery(this);
        if (p7 == null) {
            p7 = " ";
        }
        if (!android.text.TextUtils.isEmpty(p6)) {
            if (!android.text.TextUtils.isEmpty(v0_1.text)) {
                String v1_0 = v0_1.text;
                if (com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase(v1_0, p6)) {
                    v1_0 = v1_0.replace(p6, "").replace(String.format("-%s", new Object[] {p6})), "");
                }
                v0_1.setText(String.format("%s%s-%s", new Object[] {v1_0, p7, p6})));
            } else {
                v0_1.setText(String.format("-%s", new Object[] {p6})));
            }
            v0_1.setTitle(v0_1.getText());
            return v0_1;
        } else {
            return v0_1;
        }
    }

    public com.bisimplex.firebooru.network.SourceQuery plus(String p6, String p7)
    {
        com.bisimplex.firebooru.network.SourceQuery v0_1 = new com.bisimplex.firebooru.network.SourceQuery(this);
        if (p7 == null) {
            p7 = " ";
        }
        if (!android.text.TextUtils.isEmpty(p6)) {
            if (!android.text.TextUtils.isEmpty(v0_1.text)) {
                String v1_0 = v0_1.text;
                if (com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase(v1_0, p6)) {
                    v1_0 = v1_0.replace(p6, "").replace(String.format("-%s", new Object[] {p6})), "");
                }
                v0_1.setText(String.format("%s%s%s", new Object[] {v1_0, p7, p6})));
            } else {
                v0_1.setText(p6);
            }
            v0_1.setTitle(v0_1.getText());
            return v0_1;
        } else {
            return v0_1;
        }
    }

    public void setDisableAutoLoad(boolean p1)
    {
        this.disableAutoLoad = p1;
        return;
    }

    public void setExtraParams(java.util.Map p1)
    {
        this.extraParams = p1;
        return;
    }

    public void setExtraTags(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            this.extraParams.put("extra_tags", p3);
            return;
        } else {
            this.extraParams.remove(p3);
            return;
        }
    }

    public void setIncludeBlacklisted(boolean p1)
    {
        this.includeBlacklisted = p1;
        return;
    }

    public void setInitialPage(long p1)
    {
        this.initialPage = p1;
        return;
    }

    public void setText(String p1)
    {
        this.text = p1;
        return;
    }

    public void setTitle(String p1)
    {
        this.title = p1;
        return;
    }
}
