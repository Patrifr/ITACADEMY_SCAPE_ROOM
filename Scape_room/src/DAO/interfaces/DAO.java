package DAO.interfaces;

public interface DAO<T> {

    //he posat tots els mètodes per defecte sense return i sense paràmetres
    //quan els editem hem de tenir en compte que hem d'usar genèrics

    //mètodes bàsics CRUD; a afegir els que calguin
    void add();
    void showData();
    void update();
    void remove();


}
