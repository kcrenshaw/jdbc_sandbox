package net.altapath.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactsDbSetup {
  
  private static String CONTACTS_TABLE = 
    "CREATE TABLE IF NOT EXISTS contacts (" +
    "contactId    INT PRIMARY KEY," +
    "first_name   VARCHAR(50)," +
    "last_name    VARCHAR(50)," +
    "email        VARCHAR(50)," +
    "phone        VARCHAR(50)" +
    ")";

  public boolean CreateContactsDb() {
    boolean result = false;
    Connection conn = null;
    try{
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "");
      PreparedStatement stmt = conn.prepareStatement(CONTACTS_TABLE);
      stmt.execute();
    } catch (SQLException sqlEx){
      sqlEx.printStackTrace();
      return false;
    } finally {
      try{
        if(conn != null){
          conn.close();
        }
      } catch (SQLException sqlEx){
        sqlEx.printStackTrace();
      }      
    }
    return result;
  }
}
