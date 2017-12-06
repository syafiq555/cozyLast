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
public class ThreadPost {
    private String postDetails;
    private int threadId, postId;
    private String threadName, threadDetails, username, postUsername;
    
    public ThreadPost(){
        
    }
    
    public ThreadPost(int postId, String postDetails, int threadId, String threadName, String threadDetails, String username, String postUsername){
        this.threadId = threadId;
        this.postId = postId;
        this.postDetails = postDetails;
        this.threadName = threadName;
        this.threadDetails = threadDetails;
        this.username = username;
        this.postUsername = postUsername;
    }
    
    public String getPostUsername(){
        return postUsername;
    }
    
    public void setPostUsername(String postUsername){
        this.postUsername = postUsername;
    }
    
    public void setThreadId(int threadId){
        this.threadId = threadId;
    }
    
    public void setThreadName(String threadName){
        this.threadName = threadName;
    }
    
    public void setThreadDetails(String threadDetails){
        this.threadDetails = threadDetails;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public int getThreadId(){
        return threadId;
    }
    
    public String getThreadName(){
        return threadName;
    }
    
    public String getThreadDetails(){
        return threadDetails;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setPostId(int postId){
        this.postId = postId;
    }
    
    public int getPostId(){
        return postId;
    }
    
    public void setPostDetails(String postDetails){
        this.postDetails = postDetails;
    }
    
    public String getPostDetails(){
        return postDetails;
    }
}
