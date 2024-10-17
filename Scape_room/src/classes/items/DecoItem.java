package classes.items;

import enums.Material;

public class DecoItem extends Item {

    private Material material;

    public DecoItem(String name, double price, int quantity, Material material) {
        super(name, price, quantity);
        //this.addIva();
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }

    //cal??
    /*private double addIva() {
        final double IVA = 0.21;
        super.setPrice((super.getPrice() * IVA) + super.getPrice());
        return super.getPrice();
    }*/

    //què fem amb aquest method??
    @Override
    public void commonMethod() {

    }

    //cal?? falten els atributs de super

    @Override
    public String toString() {
        return "DecorationItem{" +
                "material=" + material +
                '}';
    }
}
