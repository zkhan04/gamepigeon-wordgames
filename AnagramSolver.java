//read user input *complete
//import a dictionary *complete
//signify whether or not a letter is open or closed *complete
//determine if a string of letters has potential to form a word
//determine if a string of letters IS a word
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AnagramSolver{
    private static ArrayList<String> dictionary = new ArrayList<>();
    private static ArrayList<Character> openLetters = new ArrayList<>();
    private static ArrayList<Character> closedLetters = new ArrayList<>();

    public void userInput(){
        Scanner anagrams = new Scanner(System.in);
        String letters = anagrams.next();
        for(int i = 0; i < letters.length(); i++){
            openLetters.add(letters.charAt(i));
        }
    }

    public void fillDictionary(){
        try{
            File dict = new File("newdictionary.txt");
            Scanner filldict = new Scanner(dict);
            while(filldict.hasNextLine()){
                dictionary.add(filldict.nextLine());
            }
        } catch (FileNotFoundException error){
            System.out.println("There was a FileNotFoundException.");
        }
    }

    public boolean hasPotential(String chain, ArrayList<String> dict){
        String k = dict.get(dict.size()/2);
        if (dict.size() == 1){
            if(chain == k){
                return true;
            } else {
                return false;
            }
        } else if(chain.compareTo(k) < 0){
            dict = new ArrayList<String>(dict.subList(0, dict.size()/2 - 1));
            hasPotential(chain, dict);
        } else if(chain.compareTo(k) > 0){
            dict = new ArrayList<String>(dict.subList(dict.size()/2 + 1, dict.size()));
            hasPotential(chain, dict);
        }
        return false;
    }
    public static void main(String[] args){
        AnagramSolver x = new AnagramSolver();
        x.fillDictionary();
        System.out.println(x.hasPotential("za", dictionary));

    }
}
