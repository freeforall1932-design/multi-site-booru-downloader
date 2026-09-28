package com.bisimplex.firebooru.backup;
public class BackupTagHistory {
    public int itemId;
    public java.util.Date searchDate;
    public String searchText;
    public boolean starred;

    public BackupTagHistory()
    {
        this.searchDate = new java.util.Date();
        return;
    }

    public BackupTagHistory(com.bisimplex.firebooru.model.TagHistory p3)
    {
        this.searchDate = p3.searchDate;
        this.searchText = p3.search;
        int v1 = 1;
        if (p3.isFavoritedHistoryItem != 1) {
            v1 = 0;
        }
        this.starred = v1;
        this.itemId = 0;
        return;
    }

    public static com.bisimplex.firebooru.model.TagHistory fromBackupData(com.bisimplex.firebooru.backup.BackupTagHistory p2)
    {
        String v2_2;
        com.bisimplex.firebooru.model.TagHistory v0_1 = new com.bisimplex.firebooru.model.TagHistory();
        v0_1.isFavoritedHistoryItem = p2.starred;
        v0_1.search = p2.searchText;
        v0_1.searchDate = p2.searchDate;
        if (!android.text.TextUtils.isEmpty(p2.searchText)) {
            v2_2 = p2.searchText.toLowerCase(java.util.Locale.US);
        } else {
            v2_2 = "";
        }
        v0_1.lowercaseSearch = v2_2;
        return v0_1;
    }
}
