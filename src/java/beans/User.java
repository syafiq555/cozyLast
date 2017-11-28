/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author User
 */
public class User {
    private String email, username, gender, password;
    private int birthYear, rankNumber;
    
    public User(){
        this.rankNumber = 0;
    }
    
    public User(String email, String password, String username, String gender, int birthYear){
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.birthYear = birthYear;
        this.rankNumber = 0;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    public String getGender(){
        return gender;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setBirthYear(int birthYear){
        this.birthYear = birthYear;
    }
    
    public int getBirthYear(){
        return birthYear;
    }
    
    public void setRankNumber(int rankNumber){
        this.rankNumber = rankNumber;
    }
    
    public int getRankNumber(){
        return rankNumber;
    }
}
