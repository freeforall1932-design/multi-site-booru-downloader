package com.bisimplex.firebooru.model;
public class BlacklistRule {
    public static final String RATING_KEY = "rating:";
    public static final String SITE_KEY = "site:";
    public static final String SITE_SEPARATOR = ",";
    public static final String USER_KEY = "user:";
    private long id;
    private String rating;
    private String rule;
    private String siteString;
    private String[] sites;
    private String tagString;
    private String[] tags;
    private String user;

    public BlacklistRule()
    {
        return;
    }

    public BlacklistRule(String p9, long p10)
    {
        this.rule = p9;
        this.id = p10;
        if (p9 != null) {
            String v9_9 = p9.trim();
            this.rule = v9_9;
            if (!android.text.TextUtils.isEmpty(v9_9)) {
                String v9_2 = this.rule.split(" ");
                boolean v11_1 = new StringBuilder();
                StringBuilder v0_1 = new StringBuilder();
                int v1 = v9_2.length;
                int v3 = 0;
                while (v3 < v1) {
                    String[] v4_0 = v9_2[v3];
                    if (!v4_0.trim().isEmpty()) {
                        if ((!v4_0.startsWith("user:")) || (v4_0.length() <= "user:".length())) {
                            if ((!v4_0.startsWith("rating:")) || (v4_0.length() <= "rating:".length())) {
                                if ((!v4_0.startsWith("site:")) || (v4_0.length() <= "site:".length())) {
                                    v11_1.append(v4_0);
                                    v0_1.append(v4_0);
                                    v11_1.append(" ");
                                    v0_1.append(" ");
                                } else {
                                    String[] v4_1 = v4_0.substring("site:".length());
                                    this.siteString = v4_1;
                                    if (!android.text.TextUtils.isEmpty(v4_1)) {
                                        this.sites = this.siteString.split(",");
                                    }
                                }
                            } else {
                                this.rating = v4_0.substring("rating:".length());
                                v0_1.append(v4_0);
                                v0_1.append(" ");
                            }
                        } else {
                            this.user = v4_0.substring("user:".length());
                            v0_1.append(v4_0);
                            v0_1.append(" ");
                        }
                    }
                    v3++;
                }
                String v9_4 = v11_1.toString().trim();
                if (!v9_4.isEmpty()) {
                    this.tags = v9_4.split(" ");
                } else {
                    String v9_6 = new String[0];
                    this.tags = v9_6;
                }
                this.tagString = v0_1.toString().trim();
            }
        }
        return;
    }

    public boolean checkRule(String p9, String p10, String p11)
    {
        if (!android.text.TextUtils.isEmpty(this.rule)) {
            int v0_1 = this.tags.length;
            if (!android.text.TextUtils.isEmpty(this.rating)) {
                v0_1++;
            }
            if (!android.text.TextUtils.isEmpty(this.user)) {
                v0_1++;
            }
            if ((v0_1 != 0) || (android.text.TextUtils.isEmpty(this.rule))) {
                if ((android.text.TextUtils.isEmpty(this.rating)) || ((android.text.TextUtils.isEmpty(p10)) || (!com.bisimplex.firebooru.network.Parser.containsIgnoreCase(p10.substring(0, 1), this.rating.substring(0, 1))))) {
                    int v10_3 = 0;
                } else {
                    v10_3 = 1;
                }
                if ((!android.text.TextUtils.isEmpty(this.user)) && ((!android.text.TextUtils.isEmpty(p11)) && (this.user.equalsIgnoreCase(p11)))) {
                    v10_3++;
                }
                boolean v11_2 = this.tags;
                int v4 = 0;
                while (v4 < v11_2.length) {
                    boolean v5_0 = v11_2[v4];
                    if (!android.text.TextUtils.isEmpty(v5_0)) {
                        if (!v5_0.startsWith("-")) {
                            if (!p9.contains(String.format(" %s ", new Object[] {v5_0})))) {
                                v4++;
                            }
                        } else {
                            if (p9.contains(String.format(" %s ", new Object[] {v5_0.substring(1)})))) {
                            }
                        }
                        v10_3++;
                    }
                }
                if (v10_3 != v0_1) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public long getId()
    {
        return this.id;
    }

    public String getRating()
    {
        return this.rating;
    }

    public String getRule()
    {
        return this.rule;
    }

    public String getSiteString()
    {
        return this.siteString;
    }

    public String[] getSites()
    {
        return this.sites;
    }

    public String getTagString()
    {
        return this.tagString;
    }

    public String[] getTags()
    {
        return this.tags;
    }

    public String getUser()
    {
        return this.user;
    }

    public boolean isEmpty()
    {
        return android.text.TextUtils.isEmpty(this.rule);
    }

    public void setRule(String p1)
    {
        this.rule = p1;
        return;
    }

    public void setSites(String[] p1)
    {
        this.sites = p1;
        return;
    }
}
