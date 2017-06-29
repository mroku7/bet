package main.controller;



import main.data.JDBC;
import main.model.Bet;
import main.model.Match;

public class CalculateScore {

    public static Integer playerOneFixture(Integer playerID, Match match){
        Bet bet;
        JDBC db = new JDBC();
        bet = db.getMatchBet(match.getFixtureNr(), match.getUID(), playerID);

        try{
            if(match.getHomeScore()==bet.getHomeScore() && match.getAwayScore()==bet.getAwayScore()) {

                return 3;
            }
            if(match.getHomeScore()>match.getAwayScore() && bet.getHomeScore()> bet.getAwayScore()) {

                return 1;
            }
            if(match.getHomeScore()<match.getAwayScore() && bet.getHomeScore()< bet.getAwayScore()) {

                return 1;
            }
            if(match.getHomeScore()==match.getAwayScore() && bet.getHomeScore()==bet.getAwayScore()) {

                return 1;
            }
        }catch (Exception e){
            return 0;
        }
        return 0;
    }
}



