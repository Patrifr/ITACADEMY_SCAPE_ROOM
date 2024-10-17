package enums;

public enum Category {

    SENSORY(1, "sensory"),
    ALPHABETICAL(2, "alphabetical"),
    NUMERICAL(3, "numerical"),
    COMBINED(4, "combined");

    private final int VALUE;
    private String name;

    private Category(int VALUE, String levelName) {
        this.VALUE = VALUE;
        this.name = levelName;
    }

    public int getVALUE() {
        return VALUE;
    }

    public String getName() {
        return name;
    }

    public static Category findByValue(int value) {
        for (Category category : values()) {
            if (category.getVALUE() == value) {
                return category;
            }
        }
        return null;
    }

}
