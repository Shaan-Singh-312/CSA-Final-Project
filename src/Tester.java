import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Tester{
    public static void main() throws FileNotFoundException{
        save(load(new File("DataFiles/Pokemon.csv")),new File("DataFiles/Pokemon.txt"));
    }
    public static VocabFile load(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        VocabFile voacb = new VocabFile();
        while(s.hasNextLine()){
            String[] data = s.nextLine().split(",");
            if(data[4].equals("\" \"")){
                voacb.add(data[1].substring(1,data[1].length() -1), data[3].substring(1,data[3].length() -1));
            }else{
                voacb.add(data[1].substring(1,data[1].length() -1), data[3].substring(1,data[3].length() -1) + "/" + data[4].substring(1,data[4].length() -1));
            }
        }
        s.close();
        return voacb;
    }

    public static void save(VocabFile vocab, File f){
        try{
            if(!f.createNewFile()){
                f.delete();
                f.createNewFile();
            }
            FileWriter writer = new FileWriter(f);
            for (int i = 0; i < vocab.size(); i++){
                writer.write(vocab.getTerm(i) + "\u0000" + vocab.getDef(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}