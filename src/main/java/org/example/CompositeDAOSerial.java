package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class CompositeDAOSerial extends DAO<CompositePersonnel> {


  @Override
  public void create(CompositePersonnel obj) {
    AffichageNode affichage = new AffichageNode(obj, true);
    try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("compositeDAO")))) {
      while (affichage.hasNext()) {
        out.writeObject(affichage.next());
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public CompositePersonnel find(String id) {
    CompositePersonnel cp = null;
    ArrayList<CompositePersonnel> listresu = new ArrayList<>();
    try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("compositeDAO")))) {
      listresu = (ArrayList<CompositePersonnel>) input.readObject();
      for (CompositePersonnel readcp : listresu) {
        if (readcp.equals(listresu)) {
          cp.enfantComposite.addAll((Collection<? extends Composite>) readcp);
        }
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return cp;
  }

  @Override
  public void delete(String fileName) {
    try {
      File file = new File(fileName);
      if (!file.delete()) {
        System.err.println("cannot Delete");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void update(CompositePersonnel obj) {

  }
}

