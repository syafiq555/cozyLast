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
public class Friend {
    String friendUsername;
    boolean status;
    
    public Friend(){
        
    }
    
    public void setFriendUsername(String friendUsername){
        this.friendUsername = friendUsername;
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }
    
    public String getFriendUsername(){
        return friendUsername;
    }
    
    public boolean getStatus(){
        return status;
    }
}
