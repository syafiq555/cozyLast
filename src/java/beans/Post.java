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
    String postId, postDetails;
    
    public Post(){
        
    }
    
    public Post(String postId, String postDetails){
        this.postId = postId;
        this.postDetails = postDetails;
    }
    
    public void setPostId(String postId){
        this.postId = postId;
    }
    
    public String getPostId(){
        return postId;
    }
    
    public void setPostDetails(String postDetails){
        this.postDetails = postDetails;
    }
    
    public String getPostDetails(){
        return postDetails;
    }
}
