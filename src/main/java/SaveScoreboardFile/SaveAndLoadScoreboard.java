package SaveScoreboardFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveAndLoadScoreboard {

    private static String fileURL = "Scoreboard.txt";

    public void save(ArrayList<SaveContainer> list) throws FileNotFoundException {
        System.out.println("Write to file!");
        String tmp = list.toString();
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileURL));
        for (SaveContainer item : list){
            pw.println(item.toString());
         }
        pw.close();
    }


    /*
    public ArrayList<SaveContainer> load() throws FileNotFoundException {
        FileInputStream fileIn = new FileInputStream(fileURL);
        Scanner scan = new Scanner(fileIn);
        String loadedClubs = scan.next();
        for (Club club : clubs)
            pw.println(club.getName());
        pw.close();
    }
*/

}
