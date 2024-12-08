package LessonParkAndAttractions;


public class Main {
    public static void main(String[] args) {
        Park park = new Park("Парк Сказка");
        park.setAttractionList(park.attractionsList());

        System.out.println("Список аттракционов в парке " + park.getName() + ":\n");
        for(Park.Attraction attraction : park.getAttractionList()) {
            System.out.println("Название: " + attraction.getAttractionName());
            System.out.println("Время открытия: " + attraction.getTimeClose());
            System.out.println("Время закрытия: " + attraction.getTimeOpen());
            System.out.println("Стоимость: " + attraction.getCost() + " руб.\n");
        }

    }
}