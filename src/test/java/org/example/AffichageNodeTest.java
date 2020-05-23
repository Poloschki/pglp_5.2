package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class AffichageNodeTest {

  @Test
  public void next() {
    CompositePersonnel cp = new CompositePersonnel();
    CompositePersonnel cp1 = new CompositePersonnel();
    Personnel p1 = new Personnel.Builder("A","Anne","CEO").build();
    Personnel p2 = new Personnel.Builder("B","Bob","ADJ").build();
    Personnel p3 = new Personnel.Builder("C","Charlie","Stage").build();

    cp.add(p1);
    cp.add(p2);
    cp1.add(cp);
    cp1.add(p3);

    AffichageNode<Composite> afficahge = new AffichageNode<>(cp1,false);
    while(afficahge.hasNext()) afficahge.next().print();
  }
}