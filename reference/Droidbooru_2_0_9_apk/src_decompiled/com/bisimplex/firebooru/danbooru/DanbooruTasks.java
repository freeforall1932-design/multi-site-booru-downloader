package com.bisimplex.firebooru.danbooru;
public class DanbooruTasks {
    private static com.bisimplex.firebooru.danbooru.DanbooruTasks sharedInstance;
    java.util.ArrayList tasks;

    static DanbooruTasks()
    {
        return;
    }

    protected DanbooruTasks()
    {
        this.tasks = new java.util.ArrayList();
        return;
    }

    public static com.bisimplex.firebooru.danbooru.DanbooruTasks getInstance()
    {
        if (com.bisimplex.firebooru.danbooru.DanbooruTasks.sharedInstance == null) {
            com.bisimplex.firebooru.danbooru.DanbooruTasks.sharedInstance = new com.bisimplex.firebooru.danbooru.DanbooruTasks();
        }
        return com.bisimplex.firebooru.danbooru.DanbooruTasks.sharedInstance;
    }

    public void addTask(com.bisimplex.firebooru.custom.DanbooruImageLoadTask p5, android.view.View p6)
    {
        if (p5 != null) {
            String v0_4 = new java.util.ArrayList();
            String v1_3 = 0;
            while (v1_3 < this.tasks.size()) {
                String v2_3 = ((com.bisimplex.firebooru.custom.DanbooruImageLoadTask) this.tasks.get(v1_3));
                if (!v2_3.isCancelled()) {
                    if (v2_3.getContainer() == p6) {
                        v0_4.add(String.valueOf(v1_3));
                    }
                } else {
                    v0_4.add(String.valueOf(v1_3));
                }
                v1_3++;
            }
            java.util.ArrayList v6_2 = v0_4.iterator();
            while (v6_2.hasNext()) {
                String v0_7 = ((com.bisimplex.firebooru.custom.DanbooruImageLoadTask) this.tasks.remove(Integer.parseInt(((String) v6_2.next()))));
                if (!v0_7.isCancelled()) {
                    v0_7.cancel(1);
                }
                android.util.Log.i("task", String.format("remove task at  pos:%s", new Object[] {v0_7.getContainer().getTag()})));
            }
            this.tasks.add(p5);
            return;
        } else {
            return;
        }
    }
}
