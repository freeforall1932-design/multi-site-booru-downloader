package com.bisimplex.firebooru.backup;
public class LoadLiveDBTask {
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;
    private ref.WeakReference mListener;
    private final android.net.Uri uri;
    private boolean working;

    public static synthetic void $r8$lambda$V1qTYKbm5o8OvAfFho6Oqci1Uyk(com.bisimplex.firebooru.backup.LoadLiveDBTask p0, com.bisimplex.firebooru.backup.LiveDB p1)
    {
        p0.lambda$notifyFinish$0(p1);
        return;
    }

    public static synthetic void $r8$lambda$jelnwXZOCjNFx1UQsqFpL9HRJ-4(com.bisimplex.firebooru.backup.LoadLiveDBTask p0)
    {
        p0.doWork();
        return;
    }

    public LoadLiveDBTask(ref.WeakReference p2, android.net.Uri p3)
    {
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        this.mListener = p2;
        this.uri = p3;
        return;
    }

    private void doWork()
    {
        if (this.uri != null) {
            com.bisimplex.firebooru.backup.LiveDB v1_1 = 1;
            this.working = 1;
            String v2_3 = new com.google.gson.GsonBuilder().setDateFormat("yyyy-MM-dd\'T\'HH:mm:ssZ").registerTypeAdapter(com.bisimplex.firebooru.backup.BackupServerItemType, new com.bisimplex.firebooru.backup.LoadLiveDBTask$2(this)).registerTypeAdapter(com.bisimplex.firebooru.danbooru.DanbooruPostContentType, new com.bisimplex.firebooru.backup.LoadLiveDBTask$1(this)).excludeFieldsWithModifiers(new int[] {1176})).create();
            try {
                String v4_4 = System.nanoTime();
                StringBuilder v6_1 = com.bisimplex.firebooru.DroidBooruApplication.getAppContext().getContentResolver();
                long v7_1 = v6_1.getType(this.uri);
            } catch (String v0_1) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_1);
                this.notifyFinish(0);
                this.working = 0;
                return;
            } catch (String v0_3) {
                this.working = 0;
                throw v0_3;
            }
            if (android.text.TextUtils.isEmpty(v7_1)) {
                v1_1 = 0;
            } else {
                if (!v7_1.endsWith("csv")) {
                    if (!v7_1.endsWith("comma-separated-values")) {
                    } else {
                    }
                }
            }
            StringBuilder v6_2 = v6_1.openInputStream(this.uri);
            if (v6_2 != null) {
                com.bisimplex.firebooru.backup.LiveDB v1_5;
                if (v1_1 == null) {
                    v1_5 = ((com.bisimplex.firebooru.backup.LiveDB) v2_3.fromJson(new com.google.gson.stream.JsonReader(new java.io.InputStreamReader(v6_2, java.nio.charset.StandardCharsets.UTF_8)), com.bisimplex.firebooru.backup.LiveDB));
                } else {
                    v1_5 = this.loadFromCSV(v6_2, v2_3);
                }
                android.util.Log.e("Measure", new StringBuilder("Loading Took: ").append(((System.nanoTime() - v4_4) / 1000000)).append("mS\n").toString());
                this.notifyFinish(v1_5);
                this.working = 0;
                return;
            } else {
                this.working = 0;
                return;
            }
        } else {
            return;
        }
    }

    private synthetic void lambda$notifyFinish$0(com.bisimplex.firebooru.backup.LiveDB p2)
    {
        com.bisimplex.firebooru.backup.LoadLiveDBTask$LoadLiveDBTaskListener v0_0 = this.mListener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.backup.LoadLiveDBTask$LoadLiveDBTaskListener) this.mListener.get()).loadDone(p2);
        }
        return;
    }

    private com.bisimplex.firebooru.backup.LiveDB loadFromCSV(java.io.InputStream p18, com.google.gson.Gson p19)
    {
        Throwable v0_1 = new java.text.SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ssZ", java.util.Locale.US);
        java.util.List v3_33 = 1;
        de.siegmar.fastcsv.reader.CsvReader v2_5 = de.siegmar.fastcsv.reader.CsvReader.builder().skipEmptyLines(1).commentCharacter(35).commentStrategy(de.siegmar.fastcsv.reader.CommentStrategy.SKIP).extraFieldStrategy(de.siegmar.fastcsv.reader.FieldMismatchStrategy.IGNORE).missingFieldStrategy(de.siegmar.fastcsv.reader.FieldMismatchStrategy.IGNORE).ofCsvRecord(p18);
        try {
            com.bisimplex.firebooru.backup.LiveDB v4_6 = new com.bisimplex.firebooru.backup.LiveDB();
            new java.util.HashMap();
            java.util.ArrayDeque v5_2 = new java.util.ArrayDeque();
            de.siegmar.fastcsv.reader.CloseableIterator v6 = v2_5.iterator();
        } catch (Throwable v0_2) {
            java.util.List v3_34 = v0_2;
            if (v2_5 != null) {
                try {
                    v2_5.close();
                } catch (Throwable v0_3) {
                    v3_34.addSuppressed(v0_3);
                }
            }
            throw v3_34;
        }
        while (v6.hasNext()) {
            java.util.List v7_24 = ((de.siegmar.fastcsv.reader.CsvRecord) v6.next()).getFields();
            if (!v7_24.isEmpty()) {
                com.bisimplex.firebooru.backup.LiveDB v16_1;
                switch (com.bisimplex.firebooru.backup.LoadLiveDBTask$3.$SwitchMap$com$bisimplex$firebooru$backup$BackupCSVRowType[com.bisimplex.firebooru.backup.BackupCSVRowType.fromValueString(((String) v7_24.get(0))).ordinal()]) {
                    case 2:
                        if (v7_24.size() >= 14) {
                            java.util.ArrayList v9_19 = new com.bisimplex.firebooru.backup.BackupServerItem();
                            v16_1 = v4_6;
                            v9_19.setServerId(((long) this.optInt(((String) v7_24.get(v3_33)))));
                            v9_19.setUrl(((String) v7_24.get(2)));
                            v9_19.setServerName(((String) v7_24.get(3)));
                            v9_19.setUseNativeAutocomplete(this.optBoolean(((String) v7_24.get(4))));
                            v9_19.setRatingFilterEnabled(this.optBoolean(((String) v7_24.get(5))));
                            v9_19.setSelected(this.optBoolean(((String) v7_24.get(6))));
                            v9_19.setDefault(this.optBoolean(((String) v7_24.get(7))));
                            v9_19.setType(com.bisimplex.firebooru.backup.BackupServerItemType.fromInteger(this.optInt(((String) v7_24.get(8)))));
                            v9_19.setUserName(((String) v7_24.get(9)));
                            v9_19.setPassword(((String) v7_24.get(10)));
                            v9_19.setUrl(((String) v7_24.get(11)));
                            v9_19.setApikey(((String) v7_24.get(12)));
                            v9_19.setApiKey(((String) v7_24.get(13)));
                            v16_1.getServers().add(v9_19);
                        } else {
                        }
                        break;
                    case 3:
                        String v8_76 = new com.bisimplex.firebooru.backup.BackupTagHistory();
                        if (v7_24.size() >= 5) {
                            v8_76.itemId = this.optInt(((String) v7_24.get(v3_33)));
                            v8_76.searchText = ((String) v7_24.get(2));
                            v8_76.searchDate = this.optDate(((String) v7_24.get(3)), v0_1);
                            v8_76.starred = this.optBoolean(((String) v7_24.get(4)));
                            v4_6.getSearchHistory().add(v8_76);
                            v16_1 = v4_6;
                        } else {
                        }
                        break;
                    case 4:
                        if (v7_24.size() >= 29) {
                            java.util.ArrayList v9_7 = new com.bisimplex.firebooru.model.Favorite();
                            v9_7.postid = ((String) v7_24.get(v3_33));
                            v9_7.posturl = ((String) v7_24.get(2));
                            v9_7.sample_width = this.optInt(((String) v7_24.get(3)));
                            v9_7.sample_height = this.optInt(((String) v7_24.get(4)));
                            v9_7.sample_url = ((String) v7_24.get(5));
                            v9_7.preview_width = this.optInt(((String) v7_24.get(6)));
                            v9_7.preview_height = this.optInt(((String) v7_24.get(7)));
                            v9_7.preview_url = ((String) v7_24.get(8));
                            v9_7.width = this.optInt(((String) v7_24.get(9)));
                            v9_7.height = this.optInt(((String) v7_24.get(10)));
                            v9_7.file_url = ((String) v7_24.get(11));
                            v9_7.jpeg_width = this.optInt(((String) v7_24.get(12)));
                            v9_7.jpeg_height = this.optInt(((String) v7_24.get(13)));
                            v9_7.jpeg_url = ((String) v7_24.get(14));
                            v9_7.tags = ((String) v7_24.get(15));
                            v9_7.tag_string_general = ((String) v7_24.get(16));
                            v9_7.tag_string_artist = ((String) v7_24.get(17));
                            v9_7.tag_string_character = ((String) v7_24.get(18));
                            v9_7.tag_string_copyright = ((String) v7_24.get(19));
                            v9_7.md5 = ((String) v7_24.get(20));
                            v9_7.source = ((String) v7_24.get(21));
                            v9_7.parent_id = ((String) v7_24.get(22));
                            v9_7.score = this.optInt(((String) v7_24.get(23)));
                            v9_7.rating = ((String) v7_24.get(24));
                            v9_7.has_notes = this.optInt(((String) v7_24.get(25)));
                            v9_7.has_comments = this.optInt(((String) v7_24.get(26)));
                            v9_7.has_children = this.optInt(((String) v7_24.get(27)));
                            v9_7.dateAdded = this.optDate(((String) v7_24.get(28)), v0_1);
                            v4_6.getRawFavorites().add(v9_7);
                        } else {
                        }
                        break;
                    case 5:
                        if (v7_24.size() >= 3) {
                            String v8_2 = new com.bisimplex.firebooru.model.BannedTag();
                            v8_2.tagText = ((String) v7_24.get(v3_33));
                            v8_2.bannedTagid = this.optInt(((String) v7_24.get(2)));
                            v4_6.getBannedTags().add(v8_2);
                        } else {
                        }
                        break;
                    case 6:
                        if (v7_24.size() >= 6) {
                            String v8_95 = new com.bisimplex.firebooru.model.SourceSpecs();
                            java.util.ArrayList v9_26 = ((String) v7_24.get(v3_33));
                            v8_95.setKey(((String) v7_24.get(2)));
                            v8_95.setUrl(((String) v7_24.get(3)));
                            v8_95.setType(this.optInt(((String) v7_24.get(4))));
                            java.util.List v7_1 = ((String) v7_24.get(5));
                            if (android.text.TextUtils.isEmpty(v7_1)) {
                            } else {
                                v8_95.setQuery(((com.bisimplex.firebooru.network.SourceQuery) p19.fromJson(v7_1, com.bisimplex.firebooru.network.SourceQuery)));
                            }
                            java.util.List v7_6;
                            java.util.List v7_5 = ((com.bisimplex.firebooru.model.SourceSpecs) v5_2.peekLast());
                            if ((android.text.TextUtils.isEmpty(v9_26)) || (v7_5 == null)) {
                                if ((!android.text.TextUtils.isEmpty(v9_26)) || (v7_5 == null)) {
                                    v7_6 = 0;
                                } else {
                                    v5_2.pollLast();
                                }
                            } else {
                                while ((v7_5 != null) && (!v7_5.getKey().contentEquals(v9_26))) {
                                    v5_2.pollLast();
                                    v7_5 = ((com.bisimplex.firebooru.model.SourceSpecs) v5_2.peekLast());
                                }
                                if (v7_5 == null) {
                                } else {
                                    java.util.ArrayList v9_1 = v7_5.getChilds();
                                    if (v9_1 == null) {
                                        v9_1 = new java.util.ArrayList();
                                        v7_5.setChilds(v9_1);
                                    }
                                    v9_1.add(v8_95);
                                    v7_6 = v3_33;
                                }
                            }
                            v5_2.addLast(v8_95);
                            if (v7_6 != null) {
                            } else {
                                v4_6.getHomePins().add(v8_95);
                            }
                        } else {
                        }
                        break;
                    case 7:
                        if (v7_24.size() >= 5) {
                            v4_6.setBackupVersion(((String) v7_24.get(v3_33)));
                            v4_6.setCreatorName(((String) v7_24.get(2)));
                            v4_6.setCreatorVersion(((String) v7_24.get(3)));
                            v4_6.setBackupTime(this.optDate(((String) v7_24.get(4)), v0_1));
                        } else {
                        }
                        break;
                    default:
                }
                v4_6 = v16_1;
                v3_33 = 1;
            } else {
            }
        }
        com.bisimplex.firebooru.backup.LiveDB v16_0 = v4_6;
        if (v2_5 != null) {
            v2_5.close();
        }
        return v16_0;
    }

    private void notifyFinish(com.bisimplex.firebooru.backup.LiveDB p3)
    {
        this.enqueueHandler.post(new com.bisimplex.firebooru.backup.LoadLiveDBTask$$ExternalSyntheticLambda1(this, p3));
        return;
    }

    private boolean optBoolean(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            return Boolean.parseBoolean(p2);
        } else {
            return 0;
        }
    }

    private java.util.Date optDate(String p2, java.text.DateFormat p3)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            return p3.parse(p2);
        } else {
            return 0;
        }
    }

    private int optInt(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            return Integer.parseInt(p2);
        } else {
            return 0;
        }
    }

    private java.net.URL optURL(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            return new java.net.URL(p2);
        } else {
            return 0;
        }
    }

    public boolean isWorking()
    {
        return this.working;
    }

    public void setListener(ref.WeakReference p1)
    {
        this.mListener = p1;
        return;
    }

    public void start()
    {
        this.executor.execute(new com.bisimplex.firebooru.backup.LoadLiveDBTask$$ExternalSyntheticLambda0(this));
        return;
    }
}
