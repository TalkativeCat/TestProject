package LessonCollections.Contacts;

import java.util.*;

public class TelephoneDirectory {
    /**
     * Карта для хранения контактов. Ключи - имена, значения - наборы уникальных номеров телефонов, реализованных с помощью Set.
     */
    Map<String, Set<String>> directory = new HashMap<>();

    /**
     * Метод для добавления нового контакта или обновления существующих номеров телефона.
     *
     * @param family   Имя контакта.
     * @param numbers Массив номеров телефонов для добавления.
     */
    public void add(String family, String... numbers) {
        // Если контакта с данным именем еще нет, создаем новую запись
        if (!directory.containsKey(family)) {
            // Создаем HashSet и кладем в него массив строк с номерами, преобразованный в List
            // с помощью метода Arrays.asList()
            Set<String> uniqueNumbers = new HashSet<>(Arrays.asList(numbers));
            // Кладем в карточку с найденной фамилией, HashSet с переданными в numbers номерами телефонов
            directory.put(family, uniqueNumbers);
            System.out.println("Контакт " + family + " успешно добавлен");
        }
        // Если контакт уже есть, обновляем его номера
        else {
            // Создаем временный Set c существующими номерами найденного контакта (existingNumbers)
            Set<String> existingNumbers = directory.get(family);
            // Добавляем к Set существующих в карточке контакта номеров (existingNumbers) новые номера,
            // переданные в numbers, преобразовав их в List с помощью метода Arrays.asList().
            existingNumbers.addAll(Arrays.asList(numbers));
            // Поскольку Set предполагает хранение только уникальных значений, добавляются только те номера,
            // которых раньше не было в карточке контакта и сохраняем обновленный Set номеров в существующий контакт
            directory.put(family, existingNumbers);
        }
    }
    /**
     * Метод для поиска контактов по частичному совпадению имени.
     *
     * @param partialFamily Подстрока для поиска в именах контактов.
     */
    public void get(String partialFamily) {
        System.out.println("\nНайденные контакты:");
        for (Map.Entry<String, Set<String>> entry : directory.entrySet()) {
            // Проверяем наличие подстроки в фамилиях
            if (entry.getKey().toLowerCase().contains(partialFamily.toLowerCase())) {
                // Формируем строку с номерами телефонов через запятую
                String phoneNumbers = String.join(", ", entry.getValue());
                System.out.println(entry.getKey() + ": " + phoneNumbers);
            }
        }
    }

}
