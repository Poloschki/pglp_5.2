package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompositePersonnelTest {

  @Test
  public void add() {
    CompositePersonnel cp = new CompositePersonnel();
    CompositePersonnel c1 = new CompositePersonnel();
    cp.add(c1);
    assertEquals(1,cp.enfantComposite.size());
    cp.add(cp);
    assertEquals(2,cp.enfantComposite.size());
    Personnel p1 = new Personnel.Builder("A","A","A").build();
    cp.add(p1);
    c1.add(p1);
    assertEquals(3,cp.enfantComposite.size());
    assertEquals(1,c1.enfantComposite.size());
  }

  @Test
  public void remove() {
    CompositePersonnel cp = new CompositePersonnel();
    CompositePersonnel c1 = new CompositePersonnel();
    Personnel p1 = new Personnel.Builder("A","A","A").build();
    Personnel p2 = new Personnel.Builder("A","A","A").build();

    cp.add(c1);
    cp.add(p1);
    cp.add(p2);
    assertEquals(3,cp.enfantComposite.size());
    cp.remove(c1);
    assertEquals(2,cp.enfantComposite.size());
    cp.remove(p1);
    assertEquals(1,cp.enfantComposite.size());
    cp.remove(p1);
    assertEquals(1,cp.enfantComposite.size());
    cp.remove(p2);
    cp.remove(cp);
    assertEquals(0,cp.enfantComposite.size());

  }
}