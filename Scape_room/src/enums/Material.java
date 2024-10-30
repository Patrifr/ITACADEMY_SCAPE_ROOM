package enums;

public enum Material {

    WOOD(1, "wood"),
    GLASS(2, "glass"),
    PLASTIC(3, "plastic"),
    METAL(4, "metal");

    private final int VALUE;
    private String name;

    private Material(int VALUE, String name) {
        this.VALUE = VALUE;
        this.name = name;
    }

    public int getVALUE() {
        return VALUE;
    }

    public String getMaterialName() {
        return name;
    }

    public static Material findByValue(int value) {
        for (Material material : values()) {
            if (material.getVALUE() == value) {
                return material;
            }
        }
        return null;
    }

}
