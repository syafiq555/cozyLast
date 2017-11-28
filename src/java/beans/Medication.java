package beans;

public class Medication {
    private int medicationType, medicationId;
    private String medicationName, username, time;
    
    public Medication(){
        
    }
    
    public Medication(int medicationType, String medicationName, String username, String time){
        this.medicationType = medicationType;
        this.medicationName = medicationName;
        this.username = username;
        this.time = time;
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
