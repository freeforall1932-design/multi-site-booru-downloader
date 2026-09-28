package com.bisimplex.firebooru.model;
public class TagHistory extends com.activeandroid.Model {
    public int isFavoritedHistoryItem;
    public String lowercaseSearch;
    public String search;
    public java.util.Date searchDate;

    public TagHistory()
    {
        this.searchDate = new java.util.Date();
        return;
    }
}
