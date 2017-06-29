package main.model;

public class Match {

    private Integer matchID;
    private Integer uID;
    private Integer fixtureNr;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;

    public Match(Integer matchID,Integer uID, Integer fixtureNr, String homeTeam, String awayTeam, Integer homeScore, Integer awayScore ){
        this.matchID = matchID;
        this.uID = uID;
        this.fixtureNr = fixtureNr;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getHomeTeam(){
        return homeTeam;
    }
    public String getAwayTeam(){
        return awayTeam;
    }
    public Integer getHomeScore(){
        return homeScore;
    }
    public Integer getAwayScore(){
        return awayScore;
    }
    public void setHomeScore(Integer score){
        this.homeScore = score;
    }
    public void setAwayScore(Integer score){
        this.awayScore = score;
    }
    public Integer getUID(){
        return uID;
    }
    public Integer getFixtureNr(){
        return fixtureNr;
    }

}
