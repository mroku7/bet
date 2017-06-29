package main.data;

import main.Main;
import main.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JDBC {

    public Connection connection(){
        Connection c =null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }

    public void insertPlayer(String name, String email) {
        Connection c = connection();
        Statement stmt;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO PLAYER (NAME,EMAIL,SCORE) " +
                    "VALUES ('" + name + "', '" + email + "', null);";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
        }
    }

    public void insertFixture(List<String> home, List<String> away, Integer nr, Integer seasonID) {
        Connection c = connection();
        Statement stmt = null;
        try {
            c.setAutoCommit(false);
            for (int i = 0; i < 8; i++) {
                stmt = c.createStatement();
                String sql = "INSERT INTO MATCH (MatchID,SeasonID,FixtureNr,HomeTeam,AwayTeam,HomeScore,AwayScore) " +
                        "VALUES ('"+(i+1)+"',"+seasonID+"," + nr + ",'" + home.get(i) + "','" + away.get(i) + "',null,null);";
                stmt.executeUpdate(sql);
            }
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
        }

    }

    public List<String> getPlayersName() {
        Connection c = connection();
        Statement stmt;
        List<String> playerList = new ArrayList<>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NAME FROM PLAYER;");
            while (rs.next()) {
                String name = rs.getString("name");
                playerList.add(name);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
        }
        return playerList;
    }

    public List<Match> getMatchScore(Integer nr) {
        Connection c = connection();
        Statement stmt;
        List<Match> matchList = new ArrayList<Match>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MATCH WHERE FixtureNr=" + nr + " AND SeasonID="+ Main.seasonID+";");
            while (rs.next()) {
                Integer matchID = rs.getInt("MatchID");
                Integer uID = rs.getInt("UID");
                Integer fixtureNr = nr;
                String homeTeam = rs.getString("HomeTeam");
                String awayTeam = rs.getString("AwayTeam");
                Integer homeScore = rs.getInt("HomeScore");
                if (rs.wasNull())
                    homeScore = null;
                Integer awayScore = rs.getInt("AwayScore");
                if (rs.wasNull())
                    awayScore = null;
                Match match = new Match(matchID, uID, fixtureNr, homeTeam, awayTeam, homeScore, awayScore);
                matchList.add(match);

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
        }
        return matchList;
    }

    public List<Integer> getFixtureNumbers(Integer sID) {
        Connection c = connection();
        Statement stmt;
        List<Integer> fixtureNrList = new ArrayList<>();
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT FixtureNr FROM MATCH WHERE SeasonID="+sID+";");
            while (rs.next()) {
                Integer nr = rs.getInt("FixtureNr");
                fixtureNrList.add(nr);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
        }
        return fixtureNrList;
    }

    public void updateMatchScore(Match match) {
        Connection c = connection();
        Statement stmt ;
        try {
            c.setAutoCommit(true);
            stmt = c.createStatement();
            stmt.executeUpdate("UPDATE MATCH SET HomeScore=" + match.getHomeScore() + ", AwayScore=" + match.getAwayScore() + " WHERE UID=" + match.getUID() + ";");
            stmt.close();
            c.close();
        } catch (Exception e) {
        }
    }

    public Player getPlayer(String name) {
        Connection c = connection();
        Statement stmt;
        Player player = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER WHERE Name='" + name + "';");
            while (rs.next()) {
                Integer playerID = rs.getInt("PlayerID");
                String namedb = rs.getString("Name");
                String email = rs.getString("Email");
                player = new Player(playerID, namedb, email);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
        }
        return player;
    }

    public void insertBet(Match match, Integer i, Integer playerID, Integer sID) {
        Connection c = connection();
        Statement stmt;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO BET (MatchID,SeasonID,PlayerID,UID,FixtureNr,HomeScore,AwayScore) " +
                    "VALUES ("+i+","+sID+", " + playerID + ","+match.getUID()+"," + match.getFixtureNr() + "," + match.getHomeScore() + "," + match.getAwayScore() + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
        }
    }

    public boolean betIsPlaced(Integer playerID, Integer fixtureID) {
        Connection c = connection();
        Statement stmt;
        boolean flag = true;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PlayerID, FixtureNr FROM BET WHERE PlayerID=" + playerID + " AND FixtureNr=" + fixtureID + ";");
            if (!rs.isBeforeFirst()) {
                flag = false;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
        }
        return flag;
    }

    public List<Player> getAllPlayers() {
        Connection c = connection();
        Statement stmt;
        List<Player> playerList = new ArrayList<>();
        Player player;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER;");
            while (rs.next()) {
                Integer playerID = rs.getInt("PlayerID");
                String namedb = rs.getString("Name");
                String email = rs.getString("Email");
                player = new Player(playerID, namedb, email);
                playerList.add(player);

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
        }
        return playerList;
    }

    public void inserScore(Integer playerID, Integer FixtureNr, Integer sID) {
        Connection c = connection();
        Statement stmt;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO SCORE (PlayerID,SeasonID,FixtureNr,Score) " +
                    "VALUES ('" + playerID + "',"+sID+",'" + FixtureNr + "','0');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
        }
    }

    public void updateScore(Integer score, Integer fixtureNr, Integer playerID, Integer sID) {
        Connection c = connection();
        Statement stmt;
        try {
            c.setAutoCommit(true);
            stmt = c.createStatement();
            stmt.executeUpdate("UPDATE SCORE SET Score=" + score + " WHERE PlayerID=" + playerID + " AND FixtureNr=" + fixtureNr + " AND SeasonID="+sID+" ;");
            stmt.close();
            c.close();
        } catch (Exception e) {
        }
    }


    public Bet getMatchBet(Integer fixNr,Integer uID , Integer playerID) {
        Connection c = connection();
        Statement stmt;
        Bet bet = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PlayerID, UID, HomeScore, AwayScore FROM BET WHERE FixtureNr=" + fixNr +  " AND PlayerID='"+playerID+"' AND UID="+uID+";");

            while (rs.next()) {
                System.out.println("unique sadsadsadsasdaaaaaaaaaaaaaaaaaaaaa ");
                Integer pID = rs.getInt("PlayerID");
                Integer uniID = rs.getInt("UID");
                Integer fixtureNr = fixNr;
                Integer homeScore = rs.getInt("HomeScore");
                if (rs.wasNull())
                    return null;

                Integer awayScore = rs.getInt("AwayScore");
                if (rs.wasNull())
                    awayScore = null;
                bet = new Bet(pID, uniID, fixtureNr, homeScore, awayScore);
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
        }
        return bet;
    }

    public List<PlayerTable> getOneFixtureScore(Integer fixtureNr){
        Connection c = connection();
        Statement stmt;
        List<PlayerTable> playerList = new ArrayList<PlayerTable>();
        PlayerTable player = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PLAYER.Name, SCORE.FixtureNr, SCORE.Score FROM SCORE " +
                    "INNER JOIN PLAYER ON PLAYER.PlayerID = SCORE.PlayerID  WHERE FixtureNr="+fixtureNr+";");
            while (rs.next()) {
                String name = rs.getString("Name");
                Integer score = rs.getInt("Score");
                player = new PlayerTable(name, score);
                playerList.add(player);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return playerList;
    }

    public List<String> getTeams (Integer sID){
        List<String> teams = new ArrayList<>();
        Connection c = connection();
        Statement stmt;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SEASON WHERE SeasonID="+sID+";");
            while (rs.next()) {
                teams.add(rs.getString("Team1"));
                teams.add(rs.getString("Team2"));
                teams.add(rs.getString("Team3"));
                teams.add(rs.getString("Team4"));
                teams.add(rs.getString("Team5"));
                teams.add(rs.getString("Team6"));
                teams.add(rs.getString("Team7"));
                teams.add(rs.getString("Team8"));
                teams.add(rs.getString("Team9"));
                teams.add(rs.getString("Team10"));
                teams.add(rs.getString("Team11"));
                teams.add(rs.getString("Team12"));
                teams.add(rs.getString("Team13"));
                teams.add(rs.getString("Team14"));
                teams.add(rs.getString("Team15"));
                teams.add(rs.getString("Team16"));
            }
            stmt.close();
            c.commit();
            c.close();

        }catch (Exception e){
        }
        return teams;
    }

    public List<List<Integer>> getSeasonYear(){
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Connection c = connection();
        Statement stmt;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Year1, Year2 FROM SEASON ");
            while (rs.next()) {
                List season = new ArrayList();
                season.add(rs.getInt("Year1"));
                season.add(rs.getInt("Year2"));
                list.add(season);
            }
            stmt.close();
            c.commit();
            c.close();

        }catch (Exception e){
        }
        return list;

    }

    public void insertSeason(List<String> teams, int year1, int year2){
        Connection c = connection();
        Statement stmt;
        try {
                c.setAutoCommit(false);
                stmt = c.createStatement();
                StringBuilder sqlb = new StringBuilder();
                sqlb.append("INSERT INTO SEASON (Year1,Year2,Team1,Team2,Team3,Team4,Team5,Team6,Team7,Team8,Team9,Team10" +
                               ",Team11,Team12,Team13,Team14,Team15,Team16) VALUES("+year1+","+year2+",");
                for(int i=0 ; i<16; i++){
                    sqlb.append("'"+teams.get(i)+"'");
                    if(i!=15)
                        sqlb.append(",");
                }
                sqlb.append(");");
                String sql = sqlb.toString();
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
                c.close();

        }catch (Exception e){
        }
    }

    public int getSeasonID(int year1, int year2){
        Connection c = connection();
        Statement stmt;
        int id = 0;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SeasonID FROM SEASON WHERE Year1="+year1+" AND Year2="+year2+";");
            while (rs.next()) {
                id = rs.getInt("SeasonID");
            }
            stmt.close();
            c.commit();
            c.close();

        }catch (Exception e){
        }
        return id;

    }
    public Integer getLastSeason(){
        Connection c = connection();
        Statement stmt;
        Integer id =0;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SeasonID FROM LASTSEASON ");
            while (rs.next()) {
                id= rs.getInt("SeasonID");
                if(rs.wasNull())
                    id=null;
            }
            stmt.close();
            c.commit();
            c.close();

        }catch (Exception e){
        }
        return id;
    }

    public void lastSeason(Integer id){
        Connection c = connection();
        Statement stmt;
        try {
            c.setAutoCommit(true);
            stmt = c.createStatement();
            stmt.executeUpdate("UPDATE LASTSEASON SET SeasonID=" +id+";");

            stmt.close();
            c.close();
        } catch (Exception e) {
        }
    }

    public List<Season> getAllSeasons(){
        List<Season> seasonList = new ArrayList<>();
        Connection c = connection();
        Statement stmt;
        Integer id =0,
                year1,
                year2;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SeasonID, Year1, Year2 FROM SEASON  ");
            while (rs.next()) {
                id= rs.getInt("SeasonID");
                year1 = rs.getInt("Year1");
                year2 = rs.getInt("Year2");
                Season season = new Season(id, year1, year2);
                seasonList.add(season);
            }
            stmt.close();
            c.commit();
            c.close();

        }catch (Exception e){
        }
        return seasonList;
    }

    public List<Integer> getSeasonYearByID(Integer id ) {
        List<Integer> list = new ArrayList<>();
        Connection c = connection();
        Statement stmt;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Year1, Year2 FROM SEASON WHERE SeasonID="+id+" ;");
            while (rs.next()) {
                list.add(rs.getInt("Year1"));
                list.add(rs.getInt("Year2"));
            }
            stmt.close();
            c.commit();
            c.close();

        } catch (Exception e) {
        }
        return list;

    }

    public void deletePlayer(int id){
        Connection c = connection();
        try {
            PreparedStatement ps = c.prepareStatement("DELETE FROM BET WHERE PlayerID = "+id+";");
            ps.executeUpdate();
            ps = c.prepareStatement("DELETE FROM PLAYER WHERE PlayerID = "+id+";");
            ps.executeUpdate();
            ps = c.prepareStatement("DELETE FROM SCORE WHERE PlayerID = "+id+";");
            ps.executeUpdate();
            c.close();
        } catch (Exception e) {
        }
    }

    public void updatePlayer(String name, String email, Integer id){
        Connection c = connection();
        Statement stmt;
        try {
            c.setAutoCommit(true);
            stmt = c.createStatement();
            stmt.executeUpdate("UPDATE PLAYER SET Name='" +name+"', Email='"+email+"' WHERE PlayerID ="+id+";");

            stmt.close();
            c.close();
        } catch (Exception e) {
        }
    }
}





