package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositePersonnel implements Composite, Serializable {
  protected List<Composite> enfantComposite = new ArrayList<>();


  @Override
  public void print() {
    for (Composite composite : enfantComposite) {
      composite.print();
    }
  }

  public void add(Composite composite) {
    enfantComposite.add(composite);
  }

  public void remove(Composite composite) {
    enfantComposite.remove(composite);
  }

}
