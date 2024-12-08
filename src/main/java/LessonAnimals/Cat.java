package LessonAnimals;

public class Cat extends Animal {
    private final int runLimit = 200;
    public static int catCount;
    public static int catFood;
    private boolean satiety = false;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    public void setSatiety(boolean satiety) {
        this.satiety = satiety;
    }


    @Override
    public void run(int meters){
        if (meters > runLimit){
            System.out.println("Котики не могут бегать больше " + runLimit + " метров :(");
        }
        else {
            System.out.println("Котик " + getName() + " пробежал " + meters + " метров");
        }
    }

    @Override
    public void swim(int meters){
            System.out.println("Котики не умеют плавать :(");
    }
    public void eat(int count){
        if (count > catFood){
            setSatiety(false);
            System.out.println("Кот " + getName() + " не может покушать. В миске не хватает " + (count-catFood) + " еды. " + getSatiety());
        }
        else {
            catFood -= count;
            setSatiety(true);
            System.out.println("Кот " + getName() + " скушал " + count + " еды! " + getSatiety() + " В миске еды осталось: " + catFood + ". " );
        }

    }
    public String getSatiety() {
        if (satiety) {
            return getName() + " сыт.";
        }
        else return getName() + " голоден.";
    }

    public static void addCatFood(int count) {
        catFood += count;
        System.out.println("\nВ миску добавлено " + count + " еды");
    }

}
