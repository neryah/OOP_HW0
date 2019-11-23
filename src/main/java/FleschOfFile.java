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
        File fileToFlesch = new File(fileName);

        if(!fileToFlesch.exists()) {
            System.out.println("Error. File doesnt exist.");
            return;
        }

        ArrayList<Character> delimiters = new ArrayList<Character>(Arrays.asList(' ', '.', ',', '\'', ':', '(', ')', '*', '&', '^',
                '%', '$', '#', '@', '!', '_', '+', '-', '=', '[', ']', '{', '}', '?', ';'));

        ArrayList<Character> vowels = new ArrayList<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'y'));

        ArrayList<Character> endSentence = new ArrayList<Character>(Arrays.asList('.', ',', ';', '?', '!'));

        int wordCount = 0, syllablesCount = 0, sentencesCount = 0;

        Reader fileReader = new FileReader(fileToFlesch);

        int data = fileReader.read();
        int i=0;
        boolean wordWithNoVowel = true, lastWasE = false;
        for (char curData = (char)data, lastData = '0' ; data != -1 ;
             lastData = curData, data = fileReader.read(), curData = (char)data){
            //System.out.println(i++);
            //System.out.println(curData);// + " cur");
            //System.out.println(lastData + " last");
            if(delimiters.contains(curData) && !delimiters.contains(lastData)){
                if(lastData == 'e' && lastWasE){
                    syllablesCount--;
                }
                if(wordWithNoVowel){
                    syllablesCount++;
                }
                wordWithNoVowel = true;
                wordCount++;
            }
            if(vowels.contains(curData) && !vowels.contains(lastData)){
                lastWasE = (curData == 'e');
                wordWithNoVowel = false;
                syllablesCount++;
            }
            if(endSentence.contains(curData) && !endSentence.contains(lastData)){
                sentencesCount++;
            }
        }
        System.out.println("syllables: " + syllablesCount);
        System.out.println("words: " + wordCount);
        System.out.println("sentences: " + sentencesCount);
        System.out.println(206.835 - 84.6*syllablesCount/wordCount - 1.015*wordCount/sentencesCount);
    }

    //private int fleschScore = 0;

    //String[] alphabet = new String[]{"A", "B", "C"};
}