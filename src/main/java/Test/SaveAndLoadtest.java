package Test;

import Controller.SaveScoreboardFile.SaveAndLoadScoreboard;
import Controller.SaveScoreboardFile.SaveContainer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class SaveAndLoadtest {

    public static void main(String[] args) {
        SaveAndLoadScoreboard saveAndLoadScoreboard = new SaveAndLoadScoreboard();
        for (int i = 0; i < 10; i++) {

            SaveContainer saveContainer = new SaveContainer();
            saveContainer.setUserid("s00000");
            saveContainer.setWord("Test");
            saveContainer.setTimeStamp(new Timestamp(System.currentTimeMillis()));
            saveContainer.setUsedLetters(new ArrayList<>());

            try {
                saveAndLoadScoreboard.saveEntriy(saveContainer);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
            try {
                ArrayList<SaveContainer> list = saveAndLoadScoreboard.loadList();
                System.out.println(list);
            } catch (IOException e) {
                e.printStackTrace();
            }



}


}
