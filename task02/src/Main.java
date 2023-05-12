package task02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void getFile(File file, List<String> list) {
        File current = file;
        File[] currentFiles;
    
        list.add(current.toString());
        // System.out.println(current);
    
        if (current.isDirectory()) {
            currentFiles = current.listFiles();
    
            for (File f : currentFiles) {
                getFile(f, list);
            }
        }
    }

    public static void main(String[] args) {
        
        // loading
        // load the folder (.../texts) -> listFiles() into ARRAY 
        // -> for each item check if it is directory or file -> if file, analyse /
        // if directory, new path name? until it reaches files

        // analysing
        // maybe ClientHandler? thread pool?
        // read file: FileReader -> BufferedReader -> String/Builder?
        // parse String/Builder: for/while loop -> (i) (i+1)
        // store data: HashMap<String, HashMap<String, int>> 
        // -> keyword (i) and list of next words (i+1)

        // printing
        // for each key, print (innerKey : innerKeySet()) + (innerValue / innerSize())
        // key
        // "\t" <innerKey> <innerValue / innerSize>

        String dirPath = args[0];

        File startDir = new File(dirPath);
        if (!startDir.exists()) {
            System.out.println("No directory found");
            System.exit(0);
        } else if (!startDir.isDirectory()) {
            System.out.println("Not a directory");
            System.exit(0);
        } else {
            System.out.println("Directory loaded");
        }

        System.out.println("---------------");

        List<String> list = new ArrayList<>();

        getFile(startDir, list);

        for (int i = 0; i < list.size(); i++) {
            if(!list.get(i).endsWith(".txt")) {
                list.remove(i);
                i--;
            }
        }

// ------------- list = arraylist of path names








    }
}