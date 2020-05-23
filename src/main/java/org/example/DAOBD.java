package org.example;

public abstract class DAOBD<T> extends DAO<T> {
  protected GestionnaireBD gestionBD = new GestionnaireBD();

  public abstract void createTable();

  public abstract void delete(T obj);

}
