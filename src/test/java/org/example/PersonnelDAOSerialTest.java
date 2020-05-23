package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PersonnelDAOSerialTest {

  Personnel p;
  DAOSerial<Personnel> dao;

  @Before
  public void setUp() throws Exception {
    p = new Personnel.Builder("A","A","A").build();
    dao = (DAOSerial<Personnel>) DAOFactory.getPersonnelDAOSerial();
  }

  @Test
  public void create() {
    dao.create(p);
    File f = new File("personnelDAO");
    assertTrue(f.exists());
  }

  @Test
  public void find() {
    dao.create(p);
    Personnel newp= dao.find("personnelDAO");
  }

  @Test
  public void delete() {
    dao.delete("personnelDAO");
    File f = new File("personnelDAO");
    assertFalse(f.exists());
  }
}