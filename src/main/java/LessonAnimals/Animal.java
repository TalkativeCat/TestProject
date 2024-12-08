package LessonAnimals;

public class Animal {
    private String name;
    public static int animalCount;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public String getName() {
        return name;
    }

    public void run(int meters){}
    public void swim(int meters){}
}
