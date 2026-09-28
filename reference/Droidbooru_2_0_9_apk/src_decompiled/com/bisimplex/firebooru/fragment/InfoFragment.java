package com.bisimplex.firebooru.fragment;
public class InfoFragment extends com.bisimplex.firebooru.fragment.ListBaseFragment {
    private com.bisimplex.firebooru.danbooru.DanbooruPost post;
    private int tagTitleIndex;

    static bridge synthetic com.bisimplex.firebooru.danbooru.DanbooruPost -$$Nest$fgetpost(com.bisimplex.firebooru.fragment.InfoFragment p0)
    {
        return p0.post;
    }

    public InfoFragment()
    {
        return;
    }

    private void blackList(int p1)
    {
        return;
    }

    public void copyInfo(int p2)
    {
        int v2_4 = ((com.bisimplex.firebooru.fragment.InfoFragment$InfoItem) ((com.bisimplex.firebooru.fragment.InfoFragment$InfoDataAdapter) this.getListAdapter()).getItem(p2));
        if (!v2_4.hasUrl) {
            this.copyToClipboard(v2_4.tag);
        } else {
            this.copyToClipboard(v2_4.url);
        }
        this.showMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
        return;
    }

    public void copyToClipboard(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            android.content.ClipboardManager v0_1 = this.getActivity();
            if (v0_1 != null) {
                ((android.content.ClipboardManager) v0_1.getSystemService("clipboard")).setPrimaryClip(android.content.ClipData.newPlainText("Copied Text", p3));
                return;
            }
        }
        return;
    }

    public String getiOsFragmentName()
    {
        return "InfoViewController";
    }

    public void minusSearchTag(int p1)
    {
        return;
    }

    public void onActivityCreated(android.os.Bundle p1)
    {
        super.onActivityCreated(p1);
        this.registerForContextMenu(this.getListView());
        return;
    }

    public boolean onContextItemSelected(android.view.MenuItem p4)
    {
        android.widget.AdapterView$AdapterContextMenuInfo v0_1 = ((android.widget.AdapterView$AdapterContextMenuInfo) p4.getMenuInfo());
        switch (p4.getItemId()) {
            case 2131361910:
                this.blackList(v0_1.position);
                return 1;
            case 2131361970:
                this.copyInfo(v0_1.position);
                return 1;
            case 2131362482:
                this.saveToTagHistory(v0_1.position);
                return 1;
            case 2131362498:
                this.seachTag(v0_1.position);
                return 1;
            case 2131362499:
                this.minusSearchTag(v0_1.position);
                return 1;
            case 2131362500:
                this.plusSearchTag(v0_1.position);
                return 1;
            case 2131362525:
                this.sendLink(v0_1.position);
                return 1;
            default:
                return super.onContextItemSelected(p4);
        }
    }

    public void onCreate(android.os.Bundle p1)
    {
        super.onCreate(p1);
        return;
    }

    public void onCreateContextMenu(android.view.ContextMenu p3, android.view.View p4, android.view.ContextMenu$ContextMenuInfo p5)
    {
        super.onCreateContextMenu(p3, p4, p5);
        String v4_4 = this.getActivity().getMenuInflater();
        int v5_6 = ((android.widget.AdapterView$AdapterContextMenuInfo) p5).position;
        com.bisimplex.firebooru.fragment.InfoFragment$InfoItem v0_1 = ((com.bisimplex.firebooru.fragment.InfoFragment$InfoItem) ((com.bisimplex.firebooru.fragment.InfoFragment$InfoDataAdapter) this.getListAdapter()).getItem(v5_6));
        if (v5_6 <= this.tagTitleIndex) {
            if (v0_1.hasUrl) {
                v4_4.inflate(2131689480, p3);
                p3.setHeaderTitle(v0_1.tag);
            }
            return;
        } else {
            p3.setHeaderTitle(v0_1.tag);
            v4_4.inflate(2131689479, p3);
            p3.setHeaderTitle(v0_1.tag);
            return;
        }
    }

    public android.view.View onCreateView(android.view.LayoutInflater p1, android.view.ViewGroup p2, android.os.Bundle p3)
    {
        return p1.inflate(2131558432, 0);
    }

    public void onListItemClick(android.widget.ListView p2, android.view.View p3, int p4, long p5)
    {
        int v2_13 = ((com.bisimplex.firebooru.fragment.InfoFragment$InfoItem) ((com.bisimplex.firebooru.fragment.InfoFragment$InfoDataAdapter) this.getListAdapter()).getItem(p4));
        if (v2_13 != 0) {
            com.bisimplex.firebooru.activity.MainActivity v3_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
            if (p4 != 1) {
                if (p4 != 4) {
                    if (!v2_13.hasUrl) {
                        if ((p4 > this.tagTitleIndex) && (v3_1 != null)) {
                            v3_1.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(v2_13.tag), 0);
                        }
                    } else {
                        if (v3_1 != null) {
                            v3_1.launchBrowser(v2_13.url, 0);
                            return;
                        }
                    }
                } else {
                    if ((!this.post.getSample().renderResolution().equalsIgnoreCase(this.post.getFile().renderResolution())) && (this.post.getVisibleVersion() != this.post.getFile())) {
                        this.post.setEnforceOriginalImage(1);
                        if (v3_1 != null) {
                            v3_1.reloadImage();
                            return;
                        }
                    }
                }
            } else {
                if ((!this.post.getSample().renderResolution().equalsIgnoreCase(this.post.getFile().renderResolution())) && (this.post.getVisibleVersion() != this.post.getSample())) {
                    this.post.setEnforceOriginalImage(0);
                    if (v3_1 != null) {
                        v3_1.reloadImage();
                        return;
                    }
                }
            }
        }
        return;
    }

    public java.util.List partTagList(String p6, int p7)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        if (p6 != null) {
            String[] v6_2 = p6.trim();
            if (!v6_2.isEmpty()) {
                String[] v6_1 = v6_2.split(" ");
                int v2 = 0;
                while (v2 < v6_1.length) {
                    v0_1.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, v6_1[v2], 0, p7));
                    v2++;
                }
            }
        }
        return v0_1;
    }

    public void plusSearchTag(int p1)
    {
        return;
    }

    public void saveToTagHistory(int p2)
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().addHistoryItem(((com.bisimplex.firebooru.fragment.InfoFragment$InfoItem) ((com.bisimplex.firebooru.fragment.InfoFragment$InfoDataAdapter) this.getListAdapter()).getItem(p2)).tag);
        this.showMessage(2131887103, com.bisimplex.firebooru.activity.MessageType.Success);
        return;
    }

    public void seachTag(int p3)
    {
        com.bisimplex.firebooru.danbooru.BooruProvider v3_4 = ((com.bisimplex.firebooru.fragment.InfoFragment$InfoItem) ((com.bisimplex.firebooru.fragment.InfoFragment$InfoDataAdapter) this.getListAdapter()).getItem(p3));
        com.bisimplex.firebooru.activity.MainActivity v0_3 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if ((v0_3 != null) && (v3_4 != null)) {
            v0_3.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(v3_4.tag), com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance());
        }
        return;
    }

    public void sendLink(int p2)
    {
        String v2_2 = ((com.bisimplex.firebooru.fragment.InfoFragment$InfoItem) ((com.bisimplex.firebooru.fragment.InfoFragment$InfoDataAdapter) this.getListAdapter()).getItem(p2));
        if (v2_2.hasUrl) {
            this.shareUrl(v2_2.url);
        }
        return;
    }

    public void setPost(com.bisimplex.firebooru.danbooru.DanbooruPost p9)
    {
        this.post = p9;
        com.bisimplex.firebooru.fragment.InfoFragment$InfoDataAdapter v9_2 = new com.bisimplex.firebooru.fragment.InfoFragment$InfoDataAdapter(this, this.getActivity());
        if (this.post != null) {
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, this.getString(2131886989), 1));
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, this.post.getSample().renderResolution(), this.post.getSample().getUrl()));
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, this.post.getSample().getExtension(), 0));
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, this.getString(2131887004), 1));
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, this.post.getFile().renderResolution(), this.post.getFile().getUrl()));
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, this.post.getFile().getExtension(), 0));
            java.util.List v0_38 = this.post.getSource();
            if ((v0_38 != null) && (!v0_38.isEmpty())) {
                v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, this.getString(2131887173), 1));
                v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, v0_38, v0_38));
            }
            if (!com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().getServerDescription().isDefault()) {
                java.util.List v0_44 = this.post.getPostUrl();
                if ((v0_44 != null) && (!v0_44.isEmpty())) {
                    v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, this.getString(2131887238), 1));
                    v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, v0_44, v0_44));
                }
            }
            int v4_12;
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, this.getString(2131887005), 1));
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, String.format(java.util.Locale.US, "Id: %s", new Object[] {this.post.getPostId()})), 0));
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, String.format(java.util.Locale.US, "Score: %d", new Object[] {Integer.valueOf(this.post.getScore())})), 0));
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, String.format(java.util.Locale.US, "Rating: %s", new Object[] {this.post.getRating()})), 0));
            String v5_3 = 2131887271;
            if (!this.post.getHas_notes()) {
                v4_12 = 2131886982;
            } else {
                v4_12 = 2131887271;
            }
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, String.format(java.util.Locale.US, "Has notes: %s", new Object[] {this.getString(v4_12)})), 0));
            if (!this.post.getHas_children()) {
                v5_3 = 2131886982;
            }
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, String.format(java.util.Locale.US, "Child posts: %s", new Object[] {this.getString(v5_3)})), 0));
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, String.format(java.util.Locale.US, "Parent id: %s", new Object[] {this.post.getParent_id()})), 0));
            v9_2.add(new com.bisimplex.firebooru.fragment.InfoFragment$InfoItem(this, this.getString(2131887201), 1));
            this.tagTitleIndex = (v9_2.getCount() - 1);
            java.util.List v0_22 = this.partTagList(this.post.getTag_copyright(), 3);
            v0_22.addAll(this.partTagList(this.post.getTag_character(), 4));
            v0_22.addAll(this.partTagList(this.post.getTag_artist(), 1));
            v0_22.addAll(this.partTagList(this.post.getTag_general(), 0));
            if (v0_22.size() != 0) {
                v9_2.addAll(v0_22);
            } else {
                v9_2.addAll(this.partTagList(this.post.getTags(), -1));
            }
        }
        this.setListAdapter(v9_2);
        return;
    }

    public void shareUrl(String p3)
    {
        if ((p3 != null) && (!p3.isEmpty())) {
            android.content.Intent v0_2 = new android.content.Intent();
            v0_2.setAction("android.intent.action.SEND");
            v0_2.putExtra("android.intent.extra.TEXT", p3);
            v0_2.setType("text/plain");
            this.startActivity(android.content.Intent.createChooser(v0_2, this.getResources().getText(2131887133)));
            return;
        } else {
            return;
        }
    }
}
