package com.bisimplex.firebooru.model;
public class DownloadEntry {
    public static final int STATUS_DOWNLOADED = 2;
    public static final int STATUS_DOWNLOADING = 1;
    public static final int STATUS_DUPLICATED = 4;
    public static final int STATUS_FAILED = 3;
    public static final int STATUS_WAITING;
    private boolean avoid_duplicate;
    private java.util.Date date_added;
    private java.util.Date download_date;
    private int error_code;
    private String error_message;
    private boolean exclude_animated;
    private String extension;
    private String file_name;
    private String file_url;
    private long id;
    private String md5;
    private String post_id;
    private String post_url;
    private String preview_url;
    private String query;
    private String rating;
    private String sample_url;
    private String source;
    private int status;
    private String tag_artist;
    private String tag_character;
    private String tag_copyright;
    private String tag_general;
    private String tags;
    private String target_folder;

    public DownloadEntry()
    {
        return;
    }

    public java.util.Date getDate_added()
    {
        return this.date_added;
    }

    public java.util.Date getDownload_date()
    {
        return this.download_date;
    }

    public int getError_code()
    {
        return this.error_code;
    }

    public String getError_message()
    {
        return this.error_message;
    }

    public String getExtension()
    {
        return this.extension;
    }

    public String getFile_name()
    {
        return this.file_name;
    }

    public String getFile_url()
    {
        return this.file_url;
    }

    public long getId()
    {
        return this.id;
    }

    public String getMd5()
    {
        return this.md5;
    }

    public String getPost_id()
    {
        return this.post_id;
    }

    public String getPost_url()
    {
        return this.post_url;
    }

    public String getPreview_url()
    {
        return this.preview_url;
    }

    public String getQuery()
    {
        return this.query;
    }

    public String getRating()
    {
        return this.rating;
    }

    public String getSample_url()
    {
        return this.sample_url;
    }

    public String getSource()
    {
        return this.source;
    }

    public int getStatus()
    {
        return this.status;
    }

    public String getTag_artist()
    {
        return this.tag_artist;
    }

    public String getTag_character()
    {
        return this.tag_character;
    }

    public String getTag_copyright()
    {
        return this.tag_copyright;
    }

    public String getTag_general()
    {
        return this.tag_general;
    }

    public String getTags()
    {
        return this.tags;
    }

    public String getTarget_folder()
    {
        return this.target_folder;
    }

    public boolean isAvoid_duplicate()
    {
        return this.avoid_duplicate;
    }

    public boolean isDownloaded()
    {
        if (this.getStatus() != 2) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isDownloading()
    {
        if (this.getStatus() != 1) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isExclude_animated()
    {
        return this.exclude_animated;
    }

    public boolean isFailed()
    {
        if (this.getStatus() != 3) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isWaiting()
    {
        if (this.getStatus() != 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public void setAvoid_duplicate(boolean p1)
    {
        this.avoid_duplicate = p1;
        return;
    }

    public void setDate_added(java.util.Date p1)
    {
        this.date_added = p1;
        return;
    }

    public void setDownload_date(java.util.Date p1)
    {
        this.download_date = p1;
        return;
    }

    public void setError_code(int p1)
    {
        this.error_code = p1;
        return;
    }

    public void setError_message(String p1)
    {
        this.error_message = p1;
        return;
    }

    public void setExclude_animated(boolean p1)
    {
        this.exclude_animated = p1;
        return;
    }

    public void setExtension(String p1)
    {
        this.extension = p1;
        return;
    }

    public void setFile_name(String p1)
    {
        this.file_name = p1;
        return;
    }

    public void setFile_url(String p1)
    {
        this.file_url = p1;
        return;
    }

    public void setId(long p1)
    {
        this.id = p1;
        return;
    }

    public void setMd5(String p1)
    {
        this.md5 = p1;
        return;
    }

    public void setPost_id(String p1)
    {
        this.post_id = p1;
        return;
    }

    public void setPost_url(String p1)
    {
        this.post_url = p1;
        return;
    }

    public void setPreview_url(String p1)
    {
        this.preview_url = p1;
        return;
    }

    public void setQuery(String p1)
    {
        this.query = p1;
        return;
    }

    public void setRating(String p1)
    {
        this.rating = p1;
        return;
    }

    public void setSample_url(String p1)
    {
        this.sample_url = p1;
        return;
    }

    public void setSource(String p1)
    {
        this.source = p1;
        return;
    }

    public void setStatus(int p1)
    {
        this.status = p1;
        return;
    }

    public void setTag_artist(String p1)
    {
        this.tag_artist = p1;
        return;
    }

    public void setTag_character(String p1)
    {
        this.tag_character = p1;
        return;
    }

    public void setTag_copyright(String p1)
    {
        this.tag_copyright = p1;
        return;
    }

    public void setTag_general(String p1)
    {
        this.tag_general = p1;
        return;
    }

    public void setTags(String p1)
    {
        this.tags = p1;
        return;
    }

    public void setTarget_folder(String p1)
    {
        this.target_folder = p1;
        return;
    }
}
