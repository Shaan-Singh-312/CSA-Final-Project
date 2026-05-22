import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManger {
    public static void save(VocabFile vocab, File f){
        //TODO: Implement This method
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

    public static String[] findFiles(){
        File f = new File("DataFiles");
        String[] names = f.list();
        ArrayList<String> namesList = new ArrayList<>();
        if (names != null) {
            for (String str : names) {
                namesList.add(str.substring(0, str.length() - 4));
            }
        }
        namesList.add("Add New ++");
        names = new String[namesList.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = namesList.get(i);
        }
        return names;
    }
}
