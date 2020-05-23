package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class CompositeDAOSerialTest {

  CompositePersonnel cp;
  DAO<CompositePersonnel> dao;

  @Before
  public void init() {
    cp = new CompositePersonnel("nom");
    dao = DAOFactory.getCompositeDAOSerial();
  }


  @Test
  public void create() {
    dao.create(cp);
    File f = new File("compositeDAO");
    assertTrue(f.exists());
  }

  @Test
  public void delete() {
    dao.create(cp);
    File f = new File("compositeDAO");
    assertTrue(f.exists());
  }

}