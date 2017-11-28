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
public class forumComment {
    private int commentId, forumId;
    public forumComment(){
        
    }
    
    public void setCommentId(int commentId){
        this.commentId = commentId;
    }
    
    public void setForumId(int forumId){
        this.forumId = forumId;
    }
    
    public int getForumId(){
        return forumId;
    }
    
    public int getCommentId(){
        return commentId;
    }
}
