package app.redoge.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesUtil {
    public List<String> getLinesByFileName(String fileName) {
        var result = new ArrayList<String>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void writeFileByLinesAndFileName(List<String> lines, String fileName){
        var content = String.join("\n",lines);
        try{
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
