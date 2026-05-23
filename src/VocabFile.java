import java.util.ArrayList;

import java.util.Collections;

/// Custom Data type to store data for quiz
public class VocabFile  {
    /// Implemented as an Arraylist of String arrays
    /// Uses Key -> value pairs
    private final ArrayList<String[]> file;

    /// Creates a new VocabFile object
    public VocabFile(){
        file = new ArrayList<>();
    }

    /// Adds a new entry to the file
    /// @param term Key of new item
    /// @param def value of new item
    public void add(String term, String def){
        file.add(new String[] {term, def});
    }

    /// Randomizes contents of vocab file
    public void randomize(){
        Collections.shuffle(file);
    }

    /// The number of elements in VocabFile
    /// @return Returns number of key->value pairs in VocabFile
    public int size(){
        return file.size();
    }

    /// Term at index of VocabFile
    /// @return  Key of item at index i
    /// @param i location of term in file
    public String getTerm(int i){
        return file.get(i)[0];
    }

    /// Definition at index of VocabFile
    /// @return Value of item at index i
    /// @param i location of value in file
    public String getDef(int i){
        return file.get(i)[1];
    }

    /// Displays VocabFile in a readable format of (Key,Value), (Key, Value), ...
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            str += ("(" + file.get(i)[0] + "," + file.get(i)[1] + "), ");
        }
        return str;
    }
}
