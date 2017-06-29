package main.model;


public class PlayerTable {

    private Integer score;
    private String name;

    public PlayerTable(String name, Integer score){
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return name;
    }

    public Integer getScore (){
        return score;
    }
}
