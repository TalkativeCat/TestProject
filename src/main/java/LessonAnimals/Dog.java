package LessonAnimals;

public class Dog extends Animal {
    private final int runLimit = 200;
    private final int swimLimit = 10;
    public static int dogCount;

    public Dog(String name) {
        super(name);
        dogCount++;
    }


    @Override
    public void run(int meters){
        if (meters > runLimit){
            System.out.println("Собаки не могут бегать больше " + runLimit + " метров :(");
        }
        else {
            System.out.println("Пёс " + getName() + " пробежал " + meters + " метров");
        }
    }
    @Override
    public void swim(int meters){
        if (meters > swimLimit){
            System.out.println("Собаки не могут плавать больше " + swimLimit + " метров :(");
        }
        else {
            System.out.println("Пёс " + getName() + " проплыл " + meters + " метров");
        }
    }
}
