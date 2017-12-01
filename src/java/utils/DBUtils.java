package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import beans.User;
import beans.Medication;
 
public class DBUtils {
 
    public static User findUser(Connection conn, //
            String username, String password) throws SQLException {
 
        String sql = "Select a.* from user a " //
                + " where a.username = ? and a.password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String gender = rs.getString("gender");
            String email = rs.getString("email");
            int birthYear = rs.getInt("birthYear");
            User user = new User(email, password, username, gender, birthYear);
            return user;
        }
        return null;
    }
 
    public static User findUser(Connection conn, String username) throws SQLException {
 
        String sql = "Select a.* from user a "//
                + " where a.username = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String gender = rs.getString("gender");
            String password = rs.getString("password");
            String email = rs.getString("email");
            int birthYear = rs.getInt("birthYear");
            User user = new User(email, password, username, gender, birthYear);
            return user;
        }
        return null;
    }
    
    public static void createUser(Connection conn, User user) throws SQLException {
        String sql = "Insert into User(email, username, birthYear, gender, password, rankNumber) values (?,?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, user.getEmail());
        pstm.setString(2, user.getUsername());
        pstm.setInt(3, user.getBirthYear());
        pstm.setString(4, user.getGender());
        pstm.setString(5, user.getPassword());
        pstm.setInt(6, user.getRankNumber());
 
        pstm.executeUpdate();
    }
    
    public static void createMedication(Connection conn, Medication medication) throws SQLException {
        String sql = "Insert into reminder(medicationType, medicationName, username, time) values (?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, medication.getMedicationType());
        pstm.setString(2, medication.getMedicationName());
        pstm.setString(3, medication.getUsername());
        pstm.setString(4, medication.getTime());
 
        pstm.executeUpdate();
    }
 
    public static List<Medication> queryMedication(Connection conn, String username) throws SQLException {
        String sql = "Select * from reminder where username = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        
        ResultSet rs = pstm.executeQuery();
        List<Medication> list = new ArrayList<>();
        while (rs.next()) {
            int medicationType = rs.getInt("medicationType");
            String medicationName = rs.getString("medicationName");
            int medicationId = rs.getInt("medicationId");
            String time = rs.getString("time");
            Medication medication = new Medication(medicationId, medicationType, medicationName, username, time);
            list.add(medication);
        }
        return list;
    }
 
    public static Medication findMedication(Connection conn, int medicationId) throws SQLException {
        String sql = "Select * from reminder a where medicationId=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, medicationId);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            String medicationName = rs.getString("medicationName");
            int medicationType = rs.getInt("medicationType");
            String username = rs.getString("username");
            String time = rs.getString("time");
            Medication medication = new Medication(medicationId, medicationType, medicationName, username, time);
            return medication;
        }
        return null;
    }
 /*
    public static void updateProduct(Connection conn, Product product) throws SQLException {
        String sql = "Update Product set Name =?, Price=? where Code=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, product.getName());
        pstm.setFloat(2, product.getPrice());
        pstm.setString(3, product.getCode());
        pstm.executeUpdate();
    }
 
    public static void insertProduct(Connection conn, Product product) throws SQLException {
        String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, product.getCode());
        pstm.setString(2, product.getName());
        pstm.setFloat(3, product.getPrice());
 
        pstm.executeUpdate();
    }
 
    public static void deleteProduct(Connection conn, String code) throws SQLException {
        String sql = "Delete From Product where Code= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, code);
 
        pstm.executeUpdate();
    }*/
 
}