package org.example;

import java.sql.Connection;

public abstract class DAO<T> {


  public abstract void create(T obj);

  public abstract T find(String id);

  public abstract void update(T obj);

}

