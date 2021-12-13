package com.bkap.manager.base;

import java.util.List;

public interface IManager<O, T> {

    List<O> getAll();
    O findById(T id);
    boolean add(O entity);
    boolean updateAll(T id, O entity);
    boolean remove(T id);

}
