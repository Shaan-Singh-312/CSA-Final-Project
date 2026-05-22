import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class VocabFile  {
    private final ArrayList<String[]> file;


    public VocabFile(){
        file = new ArrayList<>();
    }

    public void add(String term, String def){
        file.add(new String[] {term, def});
    }

    public String findDefForTerm(String term){
        for(String[] arr : file){
            if (arr[0].equals(term)){
                return arr[1];
            }
        }
        return null;
    }

    public void randomize(){
        Collections.shuffle(file);
    }

    public int size(){
        return file.size();
    }

    public String getTerm(int i){
        return file.get(i)[0];
    }

    public String getDef(int i){
        return file.get(i)[1];
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            str += (Arrays.toString(file.get(i)) + ", ");
        }
        return str;
    }
}
