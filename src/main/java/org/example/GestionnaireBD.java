package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionnaireBD {
  public Connection conn;
  public Statement stmt;

  static final String DB_URL = "jdbc:derby:./DB/5_2;create=true";
  static final String JBDB_DRIVE = "org.apache.derby.jdbc.ClientDriver";

  public GestionnaireBD() {
    conn = null;
    stmt = null;
  }

  public void connect() {
    try {
      Class.forName(JBDB_DRIVE).newInstance();
      this.conn = DriverManager.getConnection(DB_URL);
    } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  public void disconnect() {
    try {
      if (stmt != null) {
        stmt.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (SQLException se) {
      se.printStackTrace();
    }
  }
}
