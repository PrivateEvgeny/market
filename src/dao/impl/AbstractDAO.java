package dao.impl;

import entity.ProductGroup;
import entity.Transaction;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public class AbstractDAO<T> {

    private T type;

    public T create(T entity) {
        if (type.getClass().getName().equals("Tranzaction")) {
            Transaction entity1 = (Transaction) entity;
            /*insert to transaction DB*/
        } else if (type.getClass().getName().equals("ProductGroup")) {
            ProductGroup entity1 = (ProductGroup) entity;
            /*insert to transaction DB*/
        }
        return null;
    }

    protected T update(T entity) {
        return null;
    }

    boolean delete(Integer id) {
        return true;
    }


}
