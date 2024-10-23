package DAO.interfaces;

import java.util.List;

public interface DAO<T> {

    //he posat tots els mètodes per defecte sense return i sense paràmetres
    //quan els editem hem de tenir en compte que hem d'usar genèrics

    //mètodes bàsics CRUD; a afegir els que calguin
    void add(T t);
    List<T> showData();
    void update();
    void remove();


}
