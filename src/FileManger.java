import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/// Manages reading and writing to external files
public final class FileManger {

    /// Private constructor to prevent creation of FileManger objects
    private FileManger(){}

    /// writes to a file the data inside of vocab file
    /// If file already exists, it is deleted then recreated as a new empty file of the same name
    /// @param vocab VocabFile with data being written
    /// @param f File where data is being written to
    public static void save(VocabFile vocab, File f){
        try{
            if(!f.createNewFile()){
                f.delete();
                f.createNewFile();
            }
            FileWriter writer = new FileWriter(f);
            for (int i = 0; i < vocab.size(); i++){
                writer.write(vocab.getTerm(i) + "|" + vocab.getDef(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /// Reads data from file and saves it as a VocabFile object
    /// @param f File being read from
    /// @return A VocabFile object containing the contents of File f
    /// @throws FileNotFoundException If File f does not exist
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


    /// Searches for all files in the directory "DataFiles"
    /// @return A String array of the names of the files without the file extension, the second to last element is always the string "Add New ++" and the last element is always "Update Old"
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
        namesList.add("Update Old");
        names = new String[namesList.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = namesList.get(i);
        }
        return names;
    }
}
