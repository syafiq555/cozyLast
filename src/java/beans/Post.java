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
public class Post {
    private String postDetails, postUsername;
    private int threadId, postId;
    
    public Post(){
        
    }
    
    public Post(String postDetails, int threadId, String postUsername){
        this.threadId = threadId;
        this.postDetails = postDetails;
        this.postUsername = postUsername;
    }
    
    public Post(int postId, String postDetails, int threadId, String postUsername){
        this.threadId = threadId;
        this.postId = postId;
        this.postDetails = postDetails;
        this.postUsername = postUsername;
    }
    
    public String getPostUsername(){
        return postUsername;
    }
    
    public void setPostUsername(String postUsername){
        this.postUsername = postUsername;
    }
    
    public void setPostId(int postId){
        this.postId = postId;
    }
    
    public int getPostId(){
        return postId;
    }
    
    public void setThreadId(int threadId){
        this.threadId = threadId;
    }
    
    public int getThreadId(){
        return threadId;
    }
    
    public void setPostDetails(String postDetails){
        this.postDetails = postDetails;
    }
    
    public String getPostDetails(){
        return postDetails;
    }
}
