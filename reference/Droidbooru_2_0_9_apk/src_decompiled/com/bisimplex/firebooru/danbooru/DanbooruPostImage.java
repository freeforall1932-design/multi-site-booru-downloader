package com.bisimplex.firebooru.danbooru;
public class DanbooruPostImage {
    private com.bisimplex.firebooru.danbooru.DanbooruPostContentType contentType;
    private int errorLoadCount;
    private String ext;
    private int height;
    private String url;
    private int width;

    public DanbooruPostImage()
    {
        return;
    }

    public DanbooruPostImage(String p1, int p2, int p3)
    {
        this.setUrl(p1);
        this.width = p2;
        this.height = p3;
        return;
    }

    public int getColorForExtension()
    {
        int v0_0 = this.getExtension();
        if ((!v0_0.equalsIgnoreCase("jpg")) && (!v0_0.equalsIgnoreCase("jpeg"))) {
            if (!v0_0.equalsIgnoreCase("png")) {
                if (!v0_0.equalsIgnoreCase("gif")) {
                    return -1;
                } else {
                    return -65536;
                }
            } else {
                return -16776961;
            }
        } else {
            return -16711936;
        }
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPostContentType getContentType()
    {
        return this.contentType;
    }

    public int getErrorLoadCount()
    {
        return this.errorLoadCount;
    }

    public String getExtension()
    {
        return this.ext;
    }

    public int getHeight()
    {
        return this.height;
    }

    public String getUrl()
    {
        return this.url;
    }

    public int getWidth()
    {
        return this.width;
    }

    public boolean isAnimated()
    {
        if ((!this.isWebM()) && ((!this.isGif()) && (!this.isMP4()))) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isGif()
    {
        if (this.contentType != com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Gif) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isMP4()
    {
        if (this.contentType != com.bisimplex.firebooru.danbooru.DanbooruPostContentType.MP4) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isVideo()
    {
        if ((!this.isWebM()) && (!this.isMP4())) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isWebM()
    {
        if (this.contentType != com.bisimplex.firebooru.danbooru.DanbooruPostContentType.WebM) {
            return 0;
        } else {
            return 1;
        }
    }

    public String renderResolution()
    {
        String v0_0 = this.width;
        if ((v0_0 != null) || (this.height != 0)) {
            return String.format("%dpx X %dpx", new Object[] {Integer.valueOf(v0_0), Integer.valueOf(this.height)}));
        } else {
            return "Unknown size";
        }
    }

    public void setErrorLoadCount(int p1)
    {
        this.errorLoadCount = p1;
        return;
    }

    public void setExt(String p2)
    {
        if ((!android.text.TextUtils.isEmpty(p2)) && (p2.startsWith("."))) {
            p2 = p2.substring(1);
        }
        this.ext = p2;
        this.contentType = com.bisimplex.firebooru.danbooru.DanbooruPostContentType.fromExtension(p2);
        return;
    }

    public void setHeight(int p1)
    {
        this.height = p1;
        return;
    }

    public void setUrl(String p5)
    {
        String v0_0 = "";
        if (p5 != null) {
            this.url = p5;
            try {
                int v1_2 = android.net.Uri.parse(p5);
                String v2_1 = v1_2.getQueryParameter("ext");
            } catch (Exception) {
                if (!android.text.TextUtils.isEmpty(v0_0)) {
                    p5 = v0_0;
                }
                String v0_2 = p5.lastIndexOf(".");
                if (v0_2 >= null) {
                    String v5_1 = p5.substring((v0_2 + 1));
                    this.ext = v5_1;
                    String v5_2 = v5_1.lastIndexOf(63);
                    if (v5_2 >= null) {
                        this.ext = this.ext.substring(0, v5_2);
                    }
                } else {
                    this.ext = "???";
                }
            }
            if (!android.text.TextUtils.isEmpty(v2_1)) {
                v0_0 = v2_1;
            } else {
                v0_0 = v1_2.getLastPathSegment();
            }
        } else {
            this.url = "";
            this.ext = "";
        }
        this.contentType = com.bisimplex.firebooru.danbooru.DanbooruPostContentType.fromExtension(this.ext);
        return;
    }

    public void setUrlExtension(String p4)
    {
        if ((!android.text.TextUtils.isEmpty(this.url)) && (!android.text.TextUtils.isEmpty(p4))) {
            String v0_1 = this.url.lastIndexOf(".");
            if (v0_1 >= null) {
                this.url = String.format("%s.%s", new Object[] {this.url.substring(0, v0_1), p4}));
                this.ext = p4;
                this.contentType = com.bisimplex.firebooru.danbooru.DanbooruPostContentType.fromExtension(p4);
            }
        }
        return;
    }

    public void setWidth(int p1)
    {
        this.width = p1;
        return;
    }

    public boolean supportsExif()
    {
        return this.getExtension().equalsIgnoreCase("jpg");
    }
}
