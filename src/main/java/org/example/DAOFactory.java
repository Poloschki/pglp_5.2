package org.example;

public class DAOFactory {
  public static DAO<Personnel> getPersonnelDAO() {
    return new PersonnelDAOBD();
  }

  //public static DAO<CompositePersonnel> getCompositeDAO() {
   // return new CompositeDAOSerial();
  //}
}
