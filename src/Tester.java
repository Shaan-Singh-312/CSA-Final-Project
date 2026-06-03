import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Tester{
    public static void main() throws FileNotFoundException{
        save(load(new File("DataFiles/morphemes.txt")),new File("DataFiles/morphemes.txt"));
    }
    public static VocabFile load(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        VocabFile voacb = new VocabFile();
        while(s.hasNextLine()){
            String[] data = s.nextLine().split("[|]");
            voacb.add(data[0], data[1]);
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
