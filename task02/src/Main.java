package task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.Soundbank;
import javax.swing.Spring;

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

    public static void main(String[] args) throws IOException {
        
        // loading
        // load the folder (.../texts) -> listFiles() into ARRAY 
        // -> for each item check if it is directory or file -> if file, analyse /
        // if directory, new path name? until it reaches files

        // analysing
        // maybe ClientHandler? thread pool?
        // read file: FileReader -> BufferedReader -> String/Builder?
        // ** remove punctuation
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
                i--; // brings the index back if the current item is removed
            }
        }

// ------------- list = arraylist of path names

        List<String> textList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String text = "";

            File newFile = new File(list.get(i));
            FileReader fr = new FileReader(newFile);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line = br.readLine()) != null) {
                line = line.replaceAll("…", " ");
                line = line.replaceAll("[^a-zA-Z 0-9]", "");
                // "\\p{Punct}" it doesn't remove properly, leaving "?" when replacing within a string
                text = text + line.toLowerCase() + " \n";
            }

            br.close();
            fr.close();

            textList.add(text);
        }

// ------------- create HashMap and inner HashMap    

        HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();

        String[] temp;
        for (int i = 0; i < textList.size(); i++) {
            temp = textList.get(i).split("\\s+");

            for (int j = 0; j < temp.length - 1; j++) {
                if (map.containsKey(temp[j])) {
                    HashMap<String, Integer> innerMap = map.get(temp[j]);
                    if (innerMap.containsKey(temp[j+1])) {
                        innerMap.put(temp[j+1], innerMap.get(temp[j+1]) + 1);
                    } else {
                        innerMap.put(temp[j+1], 1);
                    }
                } else {
                    HashMap<String, Integer> innerMap = new HashMap<String, Integer>();
                    innerMap.put(temp[j+1], 1);
                    map.put(temp[j], innerMap);
                }
            }

            // adding the last word because paper says "print all the words"
            if (!map.containsKey(temp[temp.length - 1])) {
                map.put(temp[temp.length - 1], new HashMap<String, Integer>());
            }
        }

        for (String s : map.keySet()) {
            
            System.out.println(s);
            HashMap<String, Integer> innerMap = map.get(s);

            Double total = 0.0;
            for (String str : innerMap.keySet()) {
                total += innerMap.get(str);
            }
            
            for (String str : innerMap.keySet()) {
                Double value = innerMap.get(str) / total;
                if (value == 1.0) {
                    System.out.println("\t" + str + " " + 1);
                } else {
                    System.out.println("\t" + str + " " + value);
                }
                
            }
        }

    }
}