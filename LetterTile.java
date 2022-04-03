public class LetterTile {
    private char letter;
    private boolean isOpen;

    public LetterTile(char letter){
        this.letter = letter;
        isOpen = true;
    }

    public LetterTile(char letter, boolean isOpen){
        this.letter = letter;
        this.isOpen = isOpen;
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

    public String toString(){
        return "" + letter;
    }
}
