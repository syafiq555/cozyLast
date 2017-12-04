package beans;
import java.util.Date;

public class Medication {
    private int medicationType, medicationId;
    private String medicationName, username, time;
    private Date date_start, date_end;
    
    public Medication(){
        
    }
    
    public Medication(int medicationType, String medicationName, String username, String time, Date date_start, Date date_end){
        this.medicationType = medicationType;
        this.medicationName = medicationName;
        this.username = username;
        this.time = time;
        this.date_start = date_start;
        this.date_end = date_end;
    }
    
    public Medication(int medicationId, int medicationType, String medicationName, String username, String time, Date date_start, Date date_end){
        this.medicationType = medicationType;
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.username = username;
        this.time = time;
        this.date_start = date_start;
        this.date_end = date_end;
    }
    
    public void setMedicationType(int medicationType){
        this.medicationType = medicationType;
    }
    
    public void setMedicationName(String medicationName){
        this.medicationName = medicationName;
    }
    
    public void setMedicationId(int medicationId){
        this.medicationId = medicationId;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setDate_start(Date date_start){
        this.date_start = date_start;
    }
    
    public void setDate_end(Date date_end){
        this.date_end = date_end;
    }
    
    public Date getDate_start(){
        return date_start;
    }
    
    public Date getDate_end(){
        return date_end;
    }
    
    public int getMedicationType(){
        return medicationType;
    }
    
    public String getMedicationName(){
        return medicationName;
    }
    
    public int getMedicationId(){
        return medicationId;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setTime(String time){
        this.time = time;
    }
    
    public String getTime(){
        return time;
    }
}
