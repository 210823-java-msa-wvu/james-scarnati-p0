package dev.scarnati.repositories;

import dev.scarnati.model.Accounts;

import java.util.List;

public interface CrudInterface <T>{

    T getById(Integer id);
    List<T> getAll();
    Boolean add(T t);
    Boolean delete(Integer id);
    Boolean update(T t);
}
