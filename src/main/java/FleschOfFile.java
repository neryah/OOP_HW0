import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class FleschOfFile{
    public static void main(String args[]) throws IOException {

        if (args.length != 1){
            System.out.println("Error. Incorrect number of arguments.");
            return;
        }

        String fileName = args[0];
        //System.out.println(args[0]);
        File tempFile = new File(fileName);

        if(!tempFile.exists()) {
            System.out.println("Error. File doesnt exist.");
            return;
        }

        ArrayList<Character> delimiters = new ArrayList<Character>(Arrays.asList('\'', ':', '(', ')', '*', '&', '^',
                '%', '$', '#', '@', '!', '_', '+', '-', '=', '[', ']', '{', '}', '?', ';'));

        ArrayList<Character> vowels = new ArrayList<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'y'));

        ArrayList<Character> endSentence = new ArrayList<Character>(Arrays.asList('.', ',', ';', '?', '!'));

        int wordCount = 0, syllablesCount = 0, sentencesCount = 0;

        Reader fileReader = new FileReader(fileName);

        for (int data = fileReader.read(), lastData = -1; data != -1 ;
             lastData = data, data = fileReader.read()){

            if(vowels.contains(data) && !vowels.contains(lastData)){
                syllablesCount++;
            }
            if(delimiters.contains(data) && !delimiters.contains(lastData)){
                wordCount++;
            }
            if(endSentence.contains(data) && !endSentence.contains(lastData)){
                sentencesCount++;
            }
        }
        System.out.println(206.835 - 84.6*syllablesCount/wordCount - 1.015*wordCount/sentencesCount);
    }

    //private int fleschScore = 0;

    //String[] alphabet = new String[]{"A", "B", "C"};
}