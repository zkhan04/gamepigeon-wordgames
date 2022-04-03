import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnagramsSolver {
    private ArrayList<LetterTile> letters = new ArrayList<>();
    private ArrayList<String> dictionary = new ArrayList<>();
    private ArrayList<String> wordList = new ArrayList<>();
    private ArrayList<String> triedChainList = new ArrayList<>();

    public AnagramsSolver(String l){
        for(int i = 0; i < l.length(); i++){
            letters.add(new LetterTile(l.charAt(i), true));
        }
    }

    public void fillDictionary(){
        try{
            File dict = new File("C:/Users/Zayd/IdeaProjects/AP Computer Science/Word Hunt Solver/newdict.txt/");
            Scanner filldict = new Scanner(dict);
            while(filldict.hasNextLine()){
                dictionary.add(filldict.nextLine());
            }
        } catch (FileNotFoundException f){ }
    }

    public boolean isWord(String str, ArrayList<String> dict) {
        String k = dict.get(dict.size() / 2).toLowerCase();
        if (str.compareTo(k) > 0 && dict.size() > 1) {
            dict = new ArrayList<>(dict.subList(dict.size()/2, dict.size()));
            return isWord(str, dict);
        }
        if (str.compareTo(k) < 0 && dict.size() > 1) {
            dict = new ArrayList<>(dict.subList(0, (dict.size()) / 2));
            return isWord(str, dict);
        } else if (str.equals(k)){
            return true;
        } else {
            return false;
        }
    }

    public boolean hasPotential(String str, ArrayList<String> dict){
        String k = dict.get(dict.size() / 2).toLowerCase();
        String l = k.substring(0, Math.min(str.length(), k.length()));
        if (str.compareTo(l) > 0 && dict.size() > 1) {
            dict = new ArrayList<>(dict.subList(dict.size()/2, dict.size()));
            return hasPotential(str, dict);
        }
        if (str.compareTo(l) < 0 && dict.size() > 1) {
            dict = new ArrayList<>(dict.subList(0, (dict.size()) / 2));
            return hasPotential(str, dict);
        } else if (str.equals(l)){
            return true;
        } else {
            return false;
        }
    }

    public void removeDuplicateWords(){
        for(int i = 0; i < wordList.size() - 1; i++){
            for(int k = i + 1; k < wordList.size(); k++){
                if(wordList.get(i).equalsIgnoreCase(wordList.get(k))){
                    wordList.remove(k);
                    k--;
                }
            }
        }
    }

    public void findWords(int x, String chain){
        String p = chain + letters.get(x).toString();
        if(isWord(p, dictionary)){
            wordList.add(chain + letters.get(x).toString());
        }
        letters.get(x).setOpen(false);
        for(int i = 0; i < letters.size(); i++){
            if(i != x && letters.get(i).isOpen()){
                String o = p + letters.get(i).toString();
                if(hasPotential(chain + letters.get(x).toString() + letters.get(i).toString(), dictionary)){
                    boolean hasBeenTried = false;
                    for(int j = 0; j < triedChainList.size(); j++){
                        if(o.equalsIgnoreCase(triedChainList.get(j))){
                            hasBeenTried = true;
                        }
                    }
                    if(!hasBeenTried) {
                        findWords(i, p);
                        triedChainList.add(o);
                    }
                }
            }
        }
        letters.get(x).setOpen(true);
    }

    public void findWords(){
        for(int i = 0; i < letters.size(); i++){
            findWords(i, "");
        }
    }

    public void printallWords(){
        int words = 0;
        for(int i = letters.size(); i > 3; i--){
            for(int k = 0; k < wordList.size(); k++){
                if(wordList.get(k).length() == i){
                    words++;
                    System.out.print(wordList.get(k));
                    for(int j = 0; j < letters.size() + 4 - wordList.get(k).length(); j++){
                        System.out.print(" ");
                    }
                    if(words % 4 == 0){
                        System.out.println("");
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner anagrams = new Scanner(System.in);
        String set = anagrams.next();
        AnagramsSolver k = new AnagramsSolver(set);
        k.fillDictionary();
        k.findWords();
        k.printallWords();
    }
}
