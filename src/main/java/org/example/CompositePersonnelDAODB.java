package org.example;

import sun.applet.AppletResourceLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompositePersonnelDAODB extends DAOBD<CompositePersonnel>{

  @Override
  public void create(CompositePersonnel obj) {
    gestionBD.connect();
    try {
      PreparedStatement preparedStatement = gestionBD.conn.prepareStatement(
          "INSERT INTO COMPOSITE (nom,nomcomposite) VALUES (?,?)");
      preparedStatement.setString(1,obj.getnom());
      preparedStatement.setString(2,obj.enfantComposite.get(obj.enfantComposite.size()-1).getnom());
      int result = preparedStatement.executeUpdate();
      assert result == 1;

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    gestionBD.disconnect();
  }

  @Override
  public CompositePersonnel find(String id) {
    gestionBD.connect();
    CompositePersonnel cp = null;
    try {
      PreparedStatement preparedStatement = gestionBD.conn.prepareStatement(
          "SELECT * FROM COMPOSITE WHERE nom = ?"
      );
      preparedStatement.setString(1,id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        cp = new CompositePersonnel(resultSet.getString("nom"));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    gestionBD.disconnect();
    return cp;
  }

  @Override
  public void delete(CompositePersonnel obj) {
    gestionBD.connect();
    try {
      PreparedStatement preparedStatement = gestionBD.conn.prepareStatement(
          "DELETE FROM COMPOSITE where nom = ? "
      );
      preparedStatement.setString(1, obj.getnom());
      int result = preparedStatement.executeUpdate();
      assert result == 1;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    gestionBD.disconnect();
  }

  @Override
  public void update(CompositePersonnel obj) {

  }

  @Override
  public void createTable() {
    gestionBD.connect();
    try {
      PreparedStatement preparedStatement = gestionBD.conn.prepareStatement(
          "CREATE TABLE IF NOT EXISTS COMPOSITE("
              + "nom varchar(100) NOT NULL,"
              + "id int(10) AUTO_INCREMENT,"
              + "nomcomposite varchar(100) NOT NULL,"
              + "PRIMARY KEY (id)); ");
      int set = preparedStatement.executeUpdate();
      assert set == 1;
      preparedStatement.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    gestionBD.disconnect();
  }
}
