package org.example;

import java.io.*;

public class PersonnelDAO extends DAO<Personnel> {

  @Override
  public void create(Personnel obj) {
    try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("personnelDAO")))) {
      out.writeObject(obj);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public Personnel find(String id) {
    Personnel personnel = null;
    try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(id)))) {
      personnel = (Personnel) input.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return personnel;
  }

  @Override
  public void delete(String filename) {
    try {
      File file = new File(filename);
      if (!file.delete()) {
        System.err.println("cannot Delete");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
