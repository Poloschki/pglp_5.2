package org.example;

import java.sql.*;

public class PersonnelDAOBD extends DAOBD<Personnel> {
  @Override
  public void create(Personnel obj) {
    gestionBD.connect();
    try {
      PreparedStatement preparedStatement = gestionBD.conn.prepareStatement(
          "INSERT INTO PERSONNEL (nom,prenom,fonction,naissance) VALUES (?,?,?,?)");

      preparedStatement.setString(1,obj.nom);
      preparedStatement.setString(2,obj.prenom);
      preparedStatement.setString(3,obj.fonction);
      preparedStatement.setDate(4, Date.valueOf(String.valueOf(obj.naissance)));
      int resultat = preparedStatement.executeUpdate();
      assert resultat == 1;

      for(String telephone : obj.telephone){
        PreparedStatement ps  = gestionBD.conn.prepareStatement(
            "INSERT INTO TELEPHONE (nom, telephone) VALUES (?,?)");
        ps.setString(1,obj.nom);
        ps.setString(2,telephone);
        resultat = ps.executeUpdate();
        assert resultat == 1;
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    gestionBD.disconnect();
  }

  @Override
  public Personnel find(String id) {
    Personnel p = null;
    gestionBD.connect();
    try {
      PreparedStatement preparedStatement = gestionBD.conn.prepareStatement(
          "SELECT * FROM PERSONNEL WHERE nom = ?");
      preparedStatement.setString(1,id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        if(resultSet.getString("nom").equals(id)) {
          p = new Personnel.Builder(resultSet.getString("nom"),resultSet.getString("prenom"),
              resultSet.getString("fonction")).naissance(resultSet.getTimestamp("naissance").toLocalDateTime())
              .build();
        }
      }
      preparedStatement = gestionBD.conn.prepareStatement(
          "SELECT * FROM TELEPHONE WHERE nom = ?");
      preparedStatement.setString(1,id);
      while (resultSet.next()) {
        assert p != null;
        p.addTelephone(resultSet.getString("telephone"));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    gestionBD.disconnect();
    return p;
  }

  @Override
  public void delete(Personnel perso) {
    gestionBD.connect();
    try {
      PreparedStatement preparedStatement = gestionBD.conn.prepareStatement(
          "DELETE FROM PERSONNEL WHERE nom = ? ");
      preparedStatement.setString(1,perso.getnom());
      int result = preparedStatement.executeUpdate();
      assert result == 1;

      preparedStatement = gestionBD.conn.prepareStatement(
          "DELETE FROM TELEPHONE WHERE nom = ?");
      preparedStatement.setString(1,perso.getnom());
      result = preparedStatement.executeUpdate();
      assert result == 1;

      preparedStatement.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    gestionBD.disconnect();
  }

  @Override
  public void update(Personnel obj) {
    gestionBD.connect();
    try {
      PreparedStatement preparedStatement = gestionBD.conn.prepareStatement(
          "UPDATE PERSONNEL set prenom = ?, fonction = ?, naissance = ?, WHERE nom = ?");
      preparedStatement.setString(1,obj.prenom);
      preparedStatement.setString(2,obj.fonction);
      preparedStatement.setTimestamp(3, Timestamp.valueOf(obj.naissance));
      int result = preparedStatement.executeUpdate();
      assert result == 1;

      preparedStatement = gestionBD.conn.prepareStatement(
          "UPDATE TELEPHONE set telephone = ? WHERE nom = ?");
      preparedStatement.setString(1,obj.nom);

      result = preparedStatement.executeUpdate();
      assert result == 1;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    gestionBD.disconnect();
  }

  @Override
  public void createTable() {
    gestionBD.connect();
    try {
      PreparedStatement preparedStatement = gestionBD.conn.prepareStatement(
          "CREATE TABLE IF NOT EXISTS PERSONNEL("
              + "nom varchar(50) not null"
              + "prenom varchar(50) not null"
              + "fonction varchar(150) not null"
              + "naissance date "
              + "PRIMARY KEY (nom));");
      int set = preparedStatement.executeUpdate();
      assert set == 1;

      preparedStatement = gestionBD.conn.prepareStatement(
          "CREATE TABLE IF NOT EXISTS TELEPHONE("
              + "nom varchar(50) not null"
              + "telephone varchar(12) not null"
              + "PRIMARY KEY (nom,telephone));");
      set = preparedStatement.executeUpdate();
      assert set ==1;

      preparedStatement.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    gestionBD.disconnect();
  }
}
