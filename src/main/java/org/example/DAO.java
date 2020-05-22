package org.example;

import java.sql.Connection;

public abstract class DAO<T> {
  protected GestionnaireBD gestionBD = new GestionnaireBD();

  public abstract void create(T obj);

  public abstract T find(String id);

  public abstract void delete(String fileName);

  public abstract void update(T obj);

}
