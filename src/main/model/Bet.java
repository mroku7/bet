package main.model;


public class Bet {

    private Integer playerID;
    private Integer uID;
    private Integer fixtureNr;
    private Integer homeScore;
    private Integer awayScore;



    public Bet (Integer playerID,Integer uID, Integer fixtureNr, Integer homeScore, Integer awayScore){

        this.playerID = playerID;
        this.uID = uID;
        this.fixtureNr = fixtureNr;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public Integer getHomeScore(){
        return homeScore;
    }

    public Integer getAwayScore(){
        return awayScore;
    }
}
