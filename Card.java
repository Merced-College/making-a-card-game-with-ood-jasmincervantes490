//Group Member Names:
//Jasmin Cervantes
//Arnold Rocha
//Juan Padilla
//Angel Cortez
public class Card {
    private int val;
    private String suits; 
    private String ranks;
        
    public Card(int val, String suits, String ranks) {
     
        //constructors
        this.val = val;
        this.suits = suits;
        this.ranks = ranks;
    }
    
    // accesssors
    public int getValue(){
        return val;
    }
    public String getSuit(){
        return suits;
    }
    public String getRank(){
        return ranks;
    }
     
    // Mutators
    public void setVal(int val){
        this.val = val;
    }
    public void setSuits(String suits){
        this.suits = suits;
    }
    public void setRanks(String ranks){
        this.ranks = ranks;
    }
}      
        

