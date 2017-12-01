package beans;

public class Thread {
    private int threadId;
    private String threadName, threadDetails, username;
    
    public Thread(){
        
    }
    
    public Thread(int threadId, String threadName, String threadDetails, String username){
        this.threadId = threadId;
        this.threadName = threadName;
        this.threadDetails = threadDetails;
        this.username = username;
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
}
