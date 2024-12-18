package LessonCollections.WordProcessor;

import java.util.*;

public class WordProcessor {
    private final String[] words;

    public WordProcessor(String[] words) {
        this.words = words;
    }
    public Set<String> getUniqueWords() {
        return new HashSet<>(Arrays.asList(words));
    }
    public Map<String, Integer> getWordCounts() {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            if (!wordCount.containsKey(word)) {
                wordCount.put(word, 1);
            } else {
                int count = wordCount.get(word);
                wordCount.put(word, count + 1);
            }
        }
        return wordCount;
    }
}
