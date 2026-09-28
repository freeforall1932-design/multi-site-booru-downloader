package com.bisimplex.firebooru.skin;
 class SkinManager$1 extends android.graphics.drawable.ShapeDrawable$ShaderFactory {
    final synthetic com.bisimplex.firebooru.skin.SkinManager this$0;

    SkinManager$1(com.bisimplex.firebooru.skin.SkinManager p1)
    {
        this.this$0 = p1;
        return;
    }

    public android.graphics.Shader resize(int p9, int p10)
    {
        float v1 = ((float) (p9 / 2));
        float[] v6 = new float[3];
        v6 = {0, 1050253722, 1065353216};
        return new android.graphics.LinearGradient(v1, 0, v1, ((float) p10), new int[] {-65536, -1, 0}), v6, android.graphics.Shader$TileMode.REPEAT);
    }
}
