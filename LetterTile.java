public class LetterTile {
    private char letter;
    private boolean isOpen;
    private int points;
    private int wordMultiplier;

    private void findValue(char letter){
        if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'l' || letter == 'n' || letter == 'o' || letter =='r' || letter == 's' || letter == 't' || letter == 'u'){
            points = 1;
        } else if (letter == 'd' || letter == 'g'){
            points = 2;
        } else if (letter == 'b' || letter == 'c' || letter == 'm' || letter == 'p'){
            points = 3;
        } else if (letter == 'f' || letter == 'h' || letter == 'v' || letter == 'w' || letter == 'y'){
            points = 4;
        } else if (letter == 'k'){
            points = 5;
        } else if (letter == 'j' || letter == 'x'){
            points = 8;
        } else if (letter == 'q' || letter == 'z'){
            points = 10;
        }
    }

    public LetterTile(char letter){
        this.letter = letter;
        isOpen = true;
        wordMultiplier = 1;
        findValue(letter);
    }

    public LetterTile(char letter, boolean isOpen){
        this.letter = letter;
        this.isOpen = isOpen;
        wordMultiplier = 1;
        findValue(letter);
    }

    public LetterTile(char letter, int mult){
        this.letter = letter;
        findValue(letter);
        wordMultiplier = 1;
        if (mult == 1){
            points *= 2;
        } else if (mult == 2){
            points *= 3;
        } else if (mult == 3){
            wordMultiplier = 2;
        } else if (mult == 4){
            wordMultiplier = 3;
        }
        isOpen = true;
    }



    public char getLetter(){
        return this.letter;
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    public void setOpen(boolean open){
        this.isOpen = open;
    }

    public int getPoints(){
        return this.points;
    }

    public int getWordMultiplier(){
        return this.wordMultiplier;
    }

    public String toString(){
        return "" + letter;
    }
}
