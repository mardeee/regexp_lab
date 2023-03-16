import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MoodAnalyzer {
    private int negativeCount = 0;
    private int positiveCount = 0;

    public static void main(String[] args) throws IOException {
        MoodAnalyzer moodAnalyzer = new MoodAnalyzer();
        moodAnalyzer.analyzeText("");  //в пустых ковычках указать путь к файлу с текстом
        int mood = moodAnalyzer.getMood();
        System.out.println("Mood: " + mood);
        System.out.println("Negative word count: " + moodAnalyzer.getNegativeCount());
        System.out.println("Positive word count: " + moodAnalyzer.getPositiveCount());
    }

    public void analyzeText(String filePath) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for(String word : words) {
                    if(isNegativeWord(word)) {
                        negativeCount++;
                    } else if(isPositiveWord(word)) {
                        positiveCount++;
                    }
                }
            }
        }
    }

    private boolean isNegativeWord(String word) {

        String[] negativeWords = {"hate", "kill", "depress", "sad", "lonely", "angry"};
        for(String negativeWord : negativeWords) {
            if(negativeWord.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPositiveWord(String word) {

        String[] positiveWords = {"love", "happy", "excited", "blessed", "hopeful", "grateful"};
        for(String positiveWord : positiveWords) {
            if(positiveWord.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    public int getNegativeCount() {
        return negativeCount;
    }

    public int getPositiveCount() {
        return positiveCount;
    }

    public int getMood() {
        return negativeCount - positiveCount;
    }
}
