package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositePersonnel implements Composite, Serializable {
  protected List<Composite> enfantComposite;
  private final String nom;
  public CompositePersonnel(String nom){
    this.nom = nom;
    this.enfantComposite = new ArrayList<>();
  }


  @Override
  public void print() {
    for (Composite composite : enfantComposite) {
      composite.print();
    }
  }

  @Override
  public String getnom() {
    return this.nom;
  }

  public void add(Composite composite) {

    enfantComposite.add(composite);
  }

  public void remove(Composite composite) {

    enfantComposite.remove(composite);
  }

}
