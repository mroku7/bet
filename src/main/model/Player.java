package main.model;


public class Player {

    private Integer playerID;
    private String name;
    private String email;

    public Player(Integer playerID, String name, String email){
        this.playerID = playerID;
        this.name = name;
        this.email = email;
    }

    public Integer getID(){
        return playerID;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }

}
