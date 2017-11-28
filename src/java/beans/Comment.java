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
public class Comment {
    String commentId, commentDetails;
    
    public Comment(){
        
    }
    
    public Comment(String commentId, String commentDetails){
        this.commentId = commentId;
        this.commentDetails = commentDetails;
    }
    
    public void setCommentId(String commentId){
        this.commentId = commentId;
    }
    
    public String getCommentId(){
        return commentId;
    }
    
    public void setCommentDetails(String commentDetails){
        this.commentDetails = commentDetails;
    }
    
    public String getCommentDetails(){
        return commentDetails;
    }
}
