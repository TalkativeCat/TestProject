package LessonCollections.Contacts;

public class Main {
    public static void main(String[] args) {
        TelephoneDirectory directory = new TelephoneDirectory();
        directory.add("Денис", "+79994445566", "+78887779900");
        directory.add("Коля", "+79994445500");
        directory.add("Денис", "+79994445567", "+78887779900");
        directory.get("енис");

    }
}
