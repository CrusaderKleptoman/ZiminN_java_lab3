package lab3;

public class TaskText {
    private String[] text;

    public TaskText()
    {
        this.text = new String[1]; this.text[0] = "";
    }

    public TaskText(TaskText taskText)
    {
        this.text = taskText.getText();
    }

    public TaskText(String[] text)
    {
        this.text = new String[text.length + 1];
        for (int i = 0; i < text.length; i++) {
            this.text[i] = text[i];
        }
    }

    public TaskText(String text)
    {
        int spaceCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ')
            {
                spaceCount++;
            }
        }

        this.text = new String[spaceCount + 1];

        int textArrayCount = 0;
        int firstSpace = 0;
        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == ' ' && firstSpace < i)
            {
                this.text[textArrayCount] = text.substring(firstSpace, i);
                textArrayCount++;
            }

            if (textArrayCount == spaceCount)
            {
                this.text[textArrayCount] = text.substring(i + 1, text.length());
                break;
            }

            if (text.charAt(i) == ' ')
            {
                firstSpace = i + 1;
            }
        }
    }

    public String[] getText()
    {
        String[] sendText = new String[this.text.length];
        for (int i = 0; i < this.text.length; i++) {
            sendText[i] = this.text[i];
        }
        return sendText;
    }

    public int getNumOfWord() {

        int numbOfBWord = 1;
        int numbOfSWord = 1;
        int symblInBWord = this.text[0].length();
        int symblInSWord = this.text[0].length();
        int count = 0;

        for (int i = 0; i < this.text.length; i++) {
            if (symblInSWord > this.text[i].length()) {
                symblInSWord = this.text[i].length();
                numbOfSWord = i + 1;
            }
            if (symblInBWord < this.text[i].length()) {
                symblInBWord = this.text[i].length();
                numbOfBWord = i + 1;
            }
        }

        if (numbOfBWord > numbOfSWord) {
            count = this.text.length - numbOfSWord - (this.text.length - numbOfBWord) - 1;
        } else if (numbOfBWord < numbOfSWord) {
            count = this.text.length - numbOfBWord - (this.text.length - numbOfSWord) - 1;
        }

        return count;
    }
}
