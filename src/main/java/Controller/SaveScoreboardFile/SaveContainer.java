package Controller.SaveScoreboardFile;

import java.sql.Timestamp;
import java.util.ArrayList;

public class SaveContainer {

    private String userid;
    private String word;
    private Timestamp timeStamp;
    private ArrayList<String> usedLetters;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ArrayList<String> getUsedLetters() {
        return usedLetters;
    }

    public void setUsedLetters(ArrayList<String> usedLetters) {
        this.usedLetters = usedLetters;
    }

    @Override
    public String toString() {
        return "SaveContainer{" +
                "userid='" + userid + '\'' +
                ", word='" + word + '\'' +
                ", timeStamp=" + timeStamp +
                ", usedLetters=" + usedLetters +
                '}';
    }
}
