package org.example;

public class DAOFactory {
  public static DAO<Personnel> getPersonnelDAO() {
    return new PersonnelDAO();
  }

  public static DAO<CompositePersonnel> getCompositeDAO() {
    return new CompositeDAO();
  }
}
