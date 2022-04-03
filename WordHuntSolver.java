import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordHuntSolver {
    private static LetterTile[][] board = new LetterTile[6][6];
    private static List<String> wordList = new ArrayList<>();
    private static ArrayList<String> dictionary = new ArrayList<>();

    public void fillDictionary(){
        try{
            File dict = new File("C:/Users/Zayd/IdeaProjects/AP Computer Science/Word Hunt Solver/newdict.txt/");
            Scanner filldict = new Scanner(dict);
            while(filldict.hasNextLine()){
                dictionary.add(filldict.nextLine());
            }
        } catch (FileNotFoundException f){ }
    }

    public LetterTile[][] getBoard() {
        return board;
    }

    public List<String> getWordList(){
        return this.wordList;
    }
    public WordHuntSolver(String letters){
        for(int i = 0; i < 6; i++){
            for(int k = 0; k < 6; k++){
                this.board[i][k] = new LetterTile('~', false);
            }
        }
        for(int i = 0; i < 4; i++){
            for(int k = 0; k < 4; k++){
                this.board[i + 1][k + 1] = new LetterTile(letters.charAt(4 * i + k));
            }
        }
        fillDictionary();
    }

    public void printBoard(){
        for(int i = 0; i < board.length; i++){
            for(int k = 0; k < board[0].length; k++){
                System.out.print(board[i][k] + " ");
            }
            System.out.println("");
        }
    }

    public void findWords(int x, int y, String chain){
        String p = chain + board[x][y].toString();
        if(isWord(chain + board[x][y].toString(), dictionary) && p.length() > 3){
            wordList.add(p);
        }
        board[x][y].setOpen(false);
        for(int i = -1; i < 2; i++){
            for(int k = -1; k < 2; k++){
                if(!(i == 0 && k == 0)){
                    if(board[x + i][k + y].isOpen()){
                        if(hasPotential(p + board[x + i][k + y].toString(), dictionary)){
                            findWords(x + i, k + y, p);
                        }
                    }
                }
            }
        }
        board[x][y].setOpen(true);
    }

    public void findAllWords(){
        for(int i = 1; i < 5; i++){
            for(int k = 1; k < 5; k++){
                findWords(i, k, "");
            }
        }
        printallWords();
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

    public void printallWords(){
        removeDuplicateWords();
        for(int i = 16; i > 3; i--){
            for(int k = 0; k < wordList.size(); k++){
                if(wordList.get(k).length() == i){
                    System.out.println(wordList.get(k));
                }
            }
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

    public static void main(String[] args){
        Scanner wordHunt = new Scanner(System.in);
        WordHuntSolver cracked = new WordHuntSolver(wordHunt.next());
        cracked.printBoard();
        cracked.findAllWords();
    }
}
