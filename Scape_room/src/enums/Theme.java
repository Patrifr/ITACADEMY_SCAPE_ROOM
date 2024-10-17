package enums;

public enum Theme {

    ADVENTURE(1, "adventure"),
    MYSTERY(2, "mystery"),
    HUMOUR(3, "humour"),
    HISTORY(4, "history"),
    SCIENCE(5, "science");

    private final int VALUE;
    private String name;

    private Theme(int VALUE, String name) {
        this.VALUE = VALUE;
        this.name = name;
    }

    public int getVALUE() {
        return VALUE;
    }

    public String getName() {
        return name;
    }

    public static Theme findByValue(int value) {
        for (Theme theme : values()) {
            if (theme.getVALUE() == value) {
                return theme;
            }
        }
        return null;
    }
}
