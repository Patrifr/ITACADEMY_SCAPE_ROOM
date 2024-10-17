package enums;

public enum Level {

    BEGINNER(1, "beginner"),
    INTERMEDIATE(2, "intermediate"),
    EXPERT(3, "expert");

    private final int VALUE;
    private String levelName;

    private Level(int VALUE, String levelName) {
        this.VALUE = VALUE;
        this.levelName = levelName;
    }

    public int getVALUE() {
        return VALUE;
    }

    public String getLevelName() {
        return levelName;
    }

    public static Level findByValue(int value) {
        for (Level level : values()) {
            if (level.getVALUE() == value) {
                return level;
            }
        }
        return null;
    }
}
