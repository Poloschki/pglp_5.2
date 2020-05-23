package org.example;

public class DAOFactory {
  public static DAO<Personnel> getPersonnelDAOBD() {
    return new PersonnelDAOBD();
  }

  public static DAO<Personnel> getPersonnelDAOSerial() {
    return new PersonnelDAOSerial();
  }

  public static DAO<CompositePersonnel> getCompositeDAOSerial() {
    return new CompositeDAOSerial();
  }

  public static DAO<CompositePersonnel> getCompositeDAOBD() {
    return new CompositePersonnelDAODB();
  }
}
