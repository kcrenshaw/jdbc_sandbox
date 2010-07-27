package net.altapath.jdbc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ContactDao {
  
  private static String CREATE_CONTACT = "INSERT INTO contacts(contact_id, first_name, last_name, email, phone) VALUES(?,?,?,?,?)";
  private static String UPDATE_CONTACT = "UPDATE contacts SET contact_id = ?, first_name = ?, last_name = ?, email = ?, phone = ?";
  private static String DELETE_CONTACT = "DELETE FROM contacts WHERE contact_id = ?";
  private static String FIND_CONTACT_BY_ID = "SELECT contact_id, first_name, last_name, email, phone FROM contacts WHERE contact_id = ?";
  private static String FIND_CONTACT_BY_NAME = "SELECT contact_id, first_name, last_name, email, phone FROM contacts WHERE first_name = ? AND last_name = ?";

  public ContactDao() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
  public boolean createContact(Contact contact){
    if(contact == null){
      return false;
    }
    Connection conn = null;
    try{
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "");
      PreparedStatement stmt = conn.prepareStatement(CREATE_CONTACT);
      stmt.setInt(1, contact.getContactId());
      stmt.setString(2, contact.getFirstName());
      stmt.setString(3, contact.getLastName());
      stmt.setString(4, contact.getEmailAddress());
      stmt.setString(5, contact.getPhoneNumber());
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
    return true;
  }
  
  public boolean updateContact(Contact contact){
    if(contact == null){
      return false;
    }
    Connection conn = null;
    try{
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "");
      PreparedStatement stmt = conn.prepareStatement(UPDATE_CONTACT);
      stmt.setInt(1, contact.getContactId());
      stmt.setString(2, contact.getFirstName());
      stmt.setString(3, contact.getLastName());
      stmt.setString(4, contact.getEmailAddress());
      stmt.setString(5, contact.getPhoneNumber());
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
    return true;
  }
  
  public boolean deleteContact(Contact contact){
    if(contact == null){
      return false;
    }
    Connection conn = null;
    try{
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "");
      PreparedStatement stmt = conn.prepareStatement(DELETE_CONTACT);
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
    return true;
  }
  
  public Contact findContact(int contactId){
    Connection conn = null;
    Contact contact = new Contact();
    try{
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "");
      PreparedStatement stmt = conn.prepareStatement(FIND_CONTACT_BY_ID);
      ResultSet rs = stmt.executeQuery();
      while(rs.next()){
        contact.setContactId(rs.getInt(0));
        contact.setFirstName(rs.getString(1));
        contact.setLastName(rs.getString(2));
        contact.setEmailAddress(rs.getString(3));
        contact.setPhoneNumber(rs.getString(4));
      }
      
    } catch (SQLException sqlEx){
      sqlEx.printStackTrace();
    } finally {
      try{
        if(conn != null){
          conn.close();
        }
      } catch (SQLException sqlEx){
        sqlEx.printStackTrace();
      }      
    }
    return contact;
  }

}
