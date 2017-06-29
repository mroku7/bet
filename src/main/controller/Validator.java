package main.controller;

import main.Main;
import main.data.JDBC;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean isValidEmail(String enteredEmail){
        String EMAIL_REGIX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(EMAIL_REGIX);
        Matcher matcher = pattern.matcher(enteredEmail);
        return ((!enteredEmail.isEmpty()) && (enteredEmail!=null) && (matcher.matches()));
    }

    public boolean uniqueName(String enteredName){
        List<String> playerList;
        String name = new String(enteredName);
        name = name.toUpperCase();
        JDBC db = new JDBC();
        playerList = db.getPlayersName();
        playerList.replaceAll(String::toUpperCase);
        for (String n: playerList) {
            if(name.equals(n)|| name.equals(""))
                return false;
        }
        return true;
    }
    public boolean allFieldsEntered(List<String> entered){

        try {
            for (String test :
                    entered) {
                if(test.isEmpty())
                    return false;
            }
        } catch (Exception e) {
           return  false;
        }
        return true;
    }

    public boolean uniqueSeason(int[] season){
        JDBC db = new JDBC();
        List<List<Integer>> fullList;
        fullList = db.getSeasonYear();

        for (List<Integer> year :
                fullList) {
            if(year.get(0).equals(season[0]) && year.get(1).equals(season[1]))
                return false;
        }
        return true;
    }

    public boolean allFieldsUnique(List<String> entered){
        List<String> ent = new ArrayList<>(entered);
        ent.replaceAll(String::toUpperCase);
        Set test = new HashSet(ent);
        if(test.size() < entered.size())
            return false;

        return true;
    }
    public boolean uniqueFixtureInSeason(int fix){
        JDBC db = new JDBC();
        List<Integer> list;
        list = db.getFixtureNumbers(Main.seasonID);

        for (Integer fixNr :
                list) {
            if(fixNr.equals(fix))
                return false;
        }
        return true;
    }

}
