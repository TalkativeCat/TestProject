package LessonAnimals;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Котики бегают
        Cat barsik = new Cat("Барсик");
        barsik.run(30);
        barsik.run(250);

        // Котики плавают
        Cat tishka = new Cat("Тишка");
        tishka.swim(10);

        // Котики кушают из миски
        Cat lastik = new Cat("Ластик");
        Cat.addCatFood(100);
        lastik.eat(10);
        barsik.eat(10);
        tishka.eat(100);
        Cat.addCatFood(80);
        lastik.eat(100);

        // Все котики поочередно кушают из миски
        System.out.println("\nСейчас в миске " + Cat.catFood + " еды. Котики кушают поочередно из миски:");
        List<Cat> catsList = new ArrayList<>();
        catsList.add(barsik);
        catsList.add(tishka);
        catsList.add(lastik);
        for (Cat cat : catsList) {
            cat.eat(40);
        }


        // Собачки бегают
        Dog bobik = new Dog("Бобик");
        bobik.run(250);
        Dog grom = new Dog("Гром");
        grom.run(550);

        //Собачки плавают
        Dog matros = new Dog("Матрос");
        matros.swim(10);
        matros.swim(20);

        // Считаем животных
        System.out.println("\nОбщее количество котиков: " + Cat.catCount);
        System.out.println("\nОбщее количество собак: " + Dog.dogCount);
        System.out.println("\nОбщее количество животных: " + Animal.animalCount);



    }
}
