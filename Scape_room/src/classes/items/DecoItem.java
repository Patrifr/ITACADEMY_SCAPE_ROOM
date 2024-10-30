package classes.items;

import enums.Material;

public class DecoItem extends Item {

    private Material material;

    public DecoItem(){
    }

    public DecoItem(String name, double price, Material material) {
        super(name, price);
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "DecorationItem{" +
                "material=" + material +
                '}';
    }
}
