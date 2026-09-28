package com.bisimplex.firebooru.model;
public class Post extends com.activeandroid.Model {
    public java.util.Date dateAdded;
    public String file_url;
    public int has_children;
    public int has_comments;
    public int has_notes;
    public int height;
    public boolean isHistory;
    public int jpeg_height;
    public String jpeg_url;
    public int jpeg_width;
    public String md5;
    public String parent_id;
    public String postid;
    public String posturl;
    public int preview_height;
    public String preview_url;
    public int preview_width;
    public String rating;
    public int sample_height;
    public String sample_url;
    public int sample_width;
    public int score;
    public String source;
    public String tag_string_artist;
    public String tag_string_character;
    public String tag_string_copyright;
    public String tag_string_general;
    public String tags;
    public int width;

    public Post()
    {
        this.dateAdded = new java.util.Date();
        return;
    }
}
