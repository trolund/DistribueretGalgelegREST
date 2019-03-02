package SaveScoreboardFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import com.google.gson.Gson;

public class SaveAndLoadScoreboard {

    private static String fileURL = "Scoreboard.txt";

    public void saveEntriy(SaveContainer saveContainer) throws IOException {
        System.out.println("Write to file!");
        Gson gson = new Gson();
        String json = gson.toJson(saveContainer);

        File file = new File(fileURL);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter pr = new PrintWriter(br);
        pr.println(json);
        pr.close();
        br.close();
        fr.close();
    }

    public ArrayList<SaveContainer> loadList() throws IOException {
        ArrayList<SaveContainer> list = new ArrayList<>();
        Gson gson = new Gson();
        try(BufferedReader br = new BufferedReader(new FileReader(fileURL))) {
            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                SaveContainer saveContainer = gson.fromJson(line, SaveContainer.class);
                list.add(saveContainer);
            }
        }
    return list;
    }
}
