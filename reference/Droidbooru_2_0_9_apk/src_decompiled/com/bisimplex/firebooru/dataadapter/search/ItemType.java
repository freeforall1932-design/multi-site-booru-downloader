package com.bisimplex.firebooru.dataadapter.search;
public final enum class ItemType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.dataadapter.search.ItemType[] $VALUES;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType Button;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType Caption;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType CheckBox;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType DoubleButton;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType Label;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType None;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType NumberField;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType Separator;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType SingleSelectorField;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType Subtitle;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType TagAutocomplete;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType TextField;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ItemType Title;
    private final int value;

    private static synthetic com.bisimplex.firebooru.dataadapter.search.ItemType[] $values()
    {
        com.bisimplex.firebooru.dataadapter.search.ItemType v2 = com.bisimplex.firebooru.dataadapter.search.ItemType.NumberField;
        com.bisimplex.firebooru.dataadapter.search.ItemType v4 = com.bisimplex.firebooru.dataadapter.search.ItemType.Button;
        com.bisimplex.firebooru.dataadapter.search.ItemType v6 = com.bisimplex.firebooru.dataadapter.search.ItemType.TagAutocomplete;
        com.bisimplex.firebooru.dataadapter.search.ItemType v8 = com.bisimplex.firebooru.dataadapter.search.ItemType.DoubleButton;
        com.bisimplex.firebooru.dataadapter.search.ItemType v10 = com.bisimplex.firebooru.dataadapter.search.ItemType.Separator;
        return new com.bisimplex.firebooru.dataadapter.search.ItemType[] {com.bisimplex.firebooru.dataadapter.search.ItemType.None, com.bisimplex.firebooru.dataadapter.search.ItemType.Label});
    }

    static ItemType()
    {
        com.bisimplex.firebooru.dataadapter.search.ItemType.None = new com.bisimplex.firebooru.dataadapter.search.ItemType("None", 0, 0);
        com.bisimplex.firebooru.dataadapter.search.ItemType.TextField = new com.bisimplex.firebooru.dataadapter.search.ItemType("TextField", 1, 1);
        com.bisimplex.firebooru.dataadapter.search.ItemType.NumberField = new com.bisimplex.firebooru.dataadapter.search.ItemType("NumberField", 2, 2);
        com.bisimplex.firebooru.dataadapter.search.ItemType.SingleSelectorField = new com.bisimplex.firebooru.dataadapter.search.ItemType("SingleSelectorField", 3, 3);
        com.bisimplex.firebooru.dataadapter.search.ItemType.Button = new com.bisimplex.firebooru.dataadapter.search.ItemType("Button", 4, 4);
        com.bisimplex.firebooru.dataadapter.search.ItemType.CheckBox = new com.bisimplex.firebooru.dataadapter.search.ItemType("CheckBox", 5, 5);
        com.bisimplex.firebooru.dataadapter.search.ItemType.TagAutocomplete = new com.bisimplex.firebooru.dataadapter.search.ItemType("TagAutocomplete", 6, 6);
        com.bisimplex.firebooru.dataadapter.search.ItemType.Subtitle = new com.bisimplex.firebooru.dataadapter.search.ItemType("Subtitle", 7, 7);
        com.bisimplex.firebooru.dataadapter.search.ItemType.DoubleButton = new com.bisimplex.firebooru.dataadapter.search.ItemType("DoubleButton", 8, 8);
        com.bisimplex.firebooru.dataadapter.search.ItemType.Title = new com.bisimplex.firebooru.dataadapter.search.ItemType("Title", 9, 9);
        com.bisimplex.firebooru.dataadapter.search.ItemType.Separator = new com.bisimplex.firebooru.dataadapter.search.ItemType("Separator", 10, 10);
        com.bisimplex.firebooru.dataadapter.search.ItemType.Caption = new com.bisimplex.firebooru.dataadapter.search.ItemType("Caption", 11, 11);
        com.bisimplex.firebooru.dataadapter.search.ItemType.Label = new com.bisimplex.firebooru.dataadapter.search.ItemType("Label", 12, 12);
        com.bisimplex.firebooru.dataadapter.search.ItemType.$VALUES = com.bisimplex.firebooru.dataadapter.search.ItemType.$values();
        return;
    }

    private ItemType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.dataadapter.search.ItemType fromInteger(int p0)
    {
        switch (p0) {
            case 1:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.TextField;
            case 2:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.NumberField;
            case 3:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.SingleSelectorField;
            case 4:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.Button;
            case 5:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.CheckBox;
            case 6:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.TagAutocomplete;
            case 7:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.Subtitle;
            case 8:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.DoubleButton;
            case 9:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.Title;
            case 10:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.Separator;
            case 11:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.Caption;
            case 12:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.Label;
            default:
                return com.bisimplex.firebooru.dataadapter.search.ItemType.None;
        }
    }

    public static com.bisimplex.firebooru.dataadapter.search.ItemType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.dataadapter.search.ItemType) Enum.valueOf(com.bisimplex.firebooru.dataadapter.search.ItemType, p1));
    }

    public static com.bisimplex.firebooru.dataadapter.search.ItemType[] values()
    {
        return ((com.bisimplex.firebooru.dataadapter.search.ItemType[]) com.bisimplex.firebooru.dataadapter.search.ItemType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
