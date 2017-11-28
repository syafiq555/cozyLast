package beans;

public class Rank {
    String rankName;
    int rankNumber;
    
    public Rank(){
        
    }
    
    public Rank(String rankName, int rankNumber){
        this.rankName = rankName;
        this.rankNumber = rankNumber;
    }
    
    public void setRankName(String rankName){
        this.rankName = rankName;
    }
    
    public String getRankName(){
        return rankName;
    }
    
    public void setRankNumber(int rankNumber){
        this.rankNumber = rankNumber;
    }
    
    public int getRankNumber(){
        return rankNumber;
    }
}
