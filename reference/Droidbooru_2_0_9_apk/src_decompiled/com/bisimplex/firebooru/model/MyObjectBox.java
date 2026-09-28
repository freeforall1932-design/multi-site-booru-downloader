package com.bisimplex.firebooru.model;
public class MyObjectBox {

    public MyObjectBox()
    {
        return;
    }

    private static void buildEntityBooruTag(io.objectbox.ModelBuilder p10)
    {
        io.objectbox.ModelBuilder$EntityBuilder v10_1 = p10.entity("BooruTag");
        v10_1.id(4, 6547651068472312147).lastPropertyId(4, 7096144811214691104);
        v10_1.property("id", 6).id(1, 5981789669580178509).flags(1);
        v10_1.property("type", 5).id(2, 2273854665240869847);
        v10_1.property("hits", 6).id(3, 8463411414555494499);
        v10_1.property("name", 9).id(4, 7096144811214691104).flags(8).indexId(1, 7059664540763203496);
        v10_1.entityDone();
        return;
    }

    private static void buildEntityDownloadEntry(io.objectbox.ModelBuilder p11)
    {
        io.objectbox.ModelBuilder$EntityBuilder v11_1 = p11.entity("DownloadEntry");
        v11_1.id(2, 8742785878097735087).lastPropertyId(25, 7011537189198383227);
        v11_1.flags(1);
        v11_1.property("id", 6).id(1, 383403644708112776).flags(1);
        v11_1.property("preview_url", 9).id(2, 4120631051954091821);
        v11_1.property("sample_url", 9).id(3, 4583694807093874873);
        v11_1.property("file_url", 9).id(4, 2409271800763364743);
        v11_1.property("post_id", 9).id(5, 3415511658718236466);
        v11_1.property("tags", 9).id(6, 5636913477152253310);
        v11_1.property("post_url", 9).id(7, 435151609146773743);
        v11_1.property("rating", 9).id(8, 6554263231403663891);
        v11_1.property("md5", 9).id(9, 158543457656600801);
        v11_1.property("source", 9).id(10, 1359102152771279375);
        v11_1.property("tag_general", 9).id(11, 8544352496879025006);
        v11_1.property("tag_copyright", 9).id(12, 1089003463625565260);
        v11_1.property("tag_character", 9).id(13, 2753241769151703401);
        v11_1.property("tag_artist", 9).id(14, 4349151818018392999);
        v11_1.property("query", 9).id(15, 4096950500959413255);
        v11_1.property("date_added", 10).id(16, 4144555158121367409);
        v11_1.property("download_date", 10).id(24, 6602957303294511818);
        v11_1.property("file_name", 9).id(17, 699532891137669424);
        v11_1.property("status", 5).id(18, 474196071336312439);
        v11_1.property("avoid_duplicate", 1).id(19, 4904608795404792647);
        v11_1.property("exclude_animated", 1).id(20, 622558796987556604);
        v11_1.property("error_message", 9).id(21, 4652729997362661804);
        v11_1.property("error_code", 5).id(22, 6075219802448453073);
        v11_1.property("extension", 9).id(23, 8714237060113301058);
        v11_1.property("target_folder", 9).id(25, 7011537189198383227);
        v11_1.entityDone();
        return;
    }

    private static void buildEntityUpdateEntry(io.objectbox.ModelBuilder p8)
    {
        io.objectbox.ModelBuilder$EntityBuilder v8_1 = p8.entity("UpdateEntry");
        v8_1.id(3, 8830694813197142589).lastPropertyId(6, 3552688888068886614);
        v8_1.flags(1);
        v8_1.property("id", 6).id(1, 4445617588798070121).flags(1);
        v8_1.property("fav_id", 6).id(2, 1002146352703545712);
        v8_1.property("status", 5).id(3, 772194724415256667);
        v8_1.property("message", 9).id(4, 616871247360324040);
        v8_1.property("update_date", 10).id(5, 2625075416063335560);
        v8_1.property("added_date", 10).id(6, 3552688888068886614);
        v8_1.entityDone();
        return;
    }

    public static io.objectbox.BoxStoreBuilder builder()
    {
        io.objectbox.BoxStoreBuilder v0_1 = new io.objectbox.BoxStoreBuilder(com.bisimplex.firebooru.model.MyObjectBox.getModel());
        v0_1.entity(com.bisimplex.firebooru.model.BooruTag_.__INSTANCE);
        v0_1.entity(com.bisimplex.firebooru.model.DownloadEntry_.__INSTANCE);
        v0_1.entity(com.bisimplex.firebooru.model.UpdateEntry_.__INSTANCE);
        return v0_1;
    }

    private static byte[] getModel()
    {
        byte[] v0_1 = new io.objectbox.ModelBuilder();
        v0_1.lastEntityId(4, 6547651068472312147);
        v0_1.lastIndexId(1, 7059664540763203496);
        v0_1.lastRelationId(0, 0);
        com.bisimplex.firebooru.model.MyObjectBox.buildEntityBooruTag(v0_1);
        com.bisimplex.firebooru.model.MyObjectBox.buildEntityDownloadEntry(v0_1);
        com.bisimplex.firebooru.model.MyObjectBox.buildEntityUpdateEntry(v0_1);
        return v0_1.build();
    }
}
