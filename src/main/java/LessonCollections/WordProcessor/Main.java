package LessonCollections.WordProcessor;

import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        String[] words = {"красный", "зеленый", "оранжевый", "белый",
                "черный", "синий", "коричневый", "голубой", "желтый",
                "зеленый", "серый", "белый", "молочный",
                "синий", "розовый", "красный", "черный", "зеленый"};

        WordProcessor processor = new WordProcessor(words);

        Set<String> uniqueWords = processor.getUniqueWords();
        System.out.println("Уникальные слова:");
        for (String word : uniqueWords) {
            System.out.println(word);
        }

        Map<String, Integer> wordCount = processor.getWordCounts();
        System.out.println("\nКоличество повторений каждого слова:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }
}
