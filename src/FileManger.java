import java.io.File;
import java.util.ArrayList;

public class FileManger {
    public static void save(VocabFile vocab, File f){
        //TODO: Implement This method

    }

    public static VocabFile load(File f){
        //TODO: Implement This method
        return null;
    }

    public static String[] FindFiles(){
        File f = new File("DataFiles");
        String[] names = f.list();
        ArrayList<String> namesList = new ArrayList<>();
        for(String str : names){
            namesList.add(str.substring(0,str.length() -4));
        }
        names = new String[namesList.size()];

        namesList.add("Make a new file");

        for (int i = 0; i < names.length; i++) {
            names[i] = namesList.get(i);
        }
        return names;
    }
}
