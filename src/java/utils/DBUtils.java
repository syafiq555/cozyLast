package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
 
import beans.User;
import beans.Medication;
import beans.Thread;
import beans.Post;
import beans.ThreadPost;
 
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
        String sql = "Insert into reminder(medicationType, medicationName, username, time, date_start , date_end) values (?,?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, medication.getMedicationType());
        pstm.setString(2, medication.getMedicationName());
        pstm.setString(3, medication.getUsername());
        pstm.setString(4, medication.getTime());
        pstm.setDate(5,new java.sql.Date(medication.getDate_start().getTime()));
        pstm.setDate(6,new java.sql.Date(medication.getDate_end().getTime()));
 
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
            Date date_start = new Date(rs.getDate("date_start").getTime());
            Date date_end = new Date(rs.getDate("date_end").getTime());
            Date date = new Date();
            if(date.after(date_start) && date.before(date_end) || date.equals(date)){
                Medication medication = new Medication(medicationId, medicationType, medicationName, username, time, date_start, date_end);
                list.add(medication);
            }
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
            Date date_start = new Date(rs.getDate("date_start").getTime());
            Date date_end = new Date(rs.getDate("date_end").getTime());
            Medication medication = new Medication(medicationId, medicationType, medicationName, username, time, date_start, date_end);
            return medication;
        }
        return null;
    }
    
    public static Post findPost(Connection conn, int threadId) throws SQLException {
        String sql = "Select * from post where threadId=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, threadId);
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            int postId = rs.getInt("postId");
            String postDetails = rs.getString("postDetails");
            String postUsername = rs.getString("postUsername");
            Post post = new Post(postId, postDetails, threadId, postUsername);
            return post;
        }
        return null;
    }
 
    public static void updateMedication(Connection conn, Medication medication) throws SQLException {
        String sql = "Update reminder set medicationType=?, medicationName=?, username=?, time=?, date_start=? , date_end=? where medicationId=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, medication.getMedicationType());
        pstm.setString(2, medication.getMedicationName());
        pstm.setString(3, medication.getUsername());
        pstm.setString(4, medication.getTime());
        pstm.setDate(5, new java.sql.Date(medication.getDate_start().getTime()));
        pstm.setDate(6, new java.sql.Date(medication.getDate_end().getTime()));
        pstm.setInt(7, medication.getMedicationId());
        pstm.executeUpdate();
    }
    
    public static void deleteReminder(Connection conn, int medicationId) throws SQLException {
        String sql = "Delete From reminder where medicationId= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, medicationId);
 
        pstm.executeUpdate();
    }
    
    public static List<Thread> queryThread(Connection conn) throws SQLException {
        String sql = "Select * from thread order by threadId desc;";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        ResultSet rs = pstm.executeQuery();
        List<Thread> list = new ArrayList<>();
        while (rs.next()) {
            int threadId = rs.getInt("threadId");
            String threadName = rs.getString("threadName");
            String threadDetails = rs.getString("threadDetails");
            String username = rs.getString("username");
                Thread thread = new Thread(threadId, threadName, threadDetails, username);
                list.add(thread);
            
        }
        return list;
    }
    
    public static List<ThreadPost> queryThreadPost(Connection conn, int threadId) throws SQLException {
        String sql = "SELECT * FROM post join thread on post.threadId = thread.threadId WHERE post.threadId=?;";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, threadId);
        ResultSet rs = pstm.executeQuery();
        List<ThreadPost> list = new ArrayList<>();
        while (rs.next()) {
            String threadName = rs.getString("threadName");
            String threadDetails = rs.getString("threadDetails");
            String username = rs.getString("username");
            String postDetails = rs.getString("postDetails");
            String postUsername = rs.getString("postUsername");
            int postId = rs.getInt("postId");
                ThreadPost threadPost = new ThreadPost(postId, postDetails, threadId, threadName, threadDetails, username, postUsername);
                list.add(threadPost);
            
        }
        return list;
    }
    
    public static List<Post> queryPost(Connection conn, int threadId) throws SQLException {
        String sql = "Select * from post where threadId=?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, threadId);
        ResultSet rs = pstm.executeQuery();
        List<Post> list = new ArrayList<>();
        while (rs.next()) {
            String postDetails = rs.getString("postDetails");
            int postId = rs.getInt("postId");
            String postUsername = rs.getString("postUsername");
                Post post = new Post(postId, postDetails, threadId, postUsername);
                list.add(post);
            
        }
        return list;
    }
    
    public static void createThread(Connection conn, Thread thread) throws SQLException {
        String sql = "Insert into Thread(threadName, threadDetails, username) values (?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, thread.getThreadName());
        pstm.setString(2, thread.getThreadDetails());
        pstm.setString(3, thread.getUsername());
 
        pstm.executeUpdate();
    }
    
    public static void addPost(Connection conn, Post post) throws SQLException {
        String sql = "Insert into post(postDetails, threadId, postUsername) values (?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, post.getPostDetails());
        pstm.setInt(2, post.getThreadId());
        pstm.setString(3, post.getPostUsername());
 
        pstm.executeUpdate();
    }
    
    public static List<Thread> searchThread(Connection conn, String string) throws SQLException {
        String sql = "Select * from thread where threadName like ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, "%" + string + "%");
        
        ResultSet rs = pstm.executeQuery();
        List<Thread> list = new ArrayList<>();
        while (rs.next()) {
            int threadId = rs.getInt("threadId");
            String threadName = rs.getString("threadName");
            String threadDetails = rs.getString("threadDetails");
            String username = rs.getString("username");
            Thread thread = new Thread(threadId, threadName, threadDetails, username);
            list.add(thread);
            
        }
        return list;
    }
}