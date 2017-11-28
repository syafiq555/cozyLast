package beans;

public class Forum {
    private int forumId;
    private String forumName, forumDetails, username;
    
    public Forum(){
        
    }
    
    public Forum(int forumId, String forumName, String forumDetails, String username){
        this.forumId = forumId;
        this.forumName = forumName;
        this.forumDetails = forumDetails;
        this.username = username;
    }
    
    public void setForumId(int forumId){
        this.forumId = forumId;
    }
    
    public void setForumName(String forumName){
        this.forumName = forumName;
    }
    
    public void setForumDetails(String forumDetails){
        this.forumDetails = forumDetails;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public int getForumId(){
        return forumId;
    }
    
    public String getForumName(){
        return forumName;
    }
    
    public String getForumDetails(){
        return forumDetails;
    }
    
    public String getUsername(){
        return username;
    }
}
