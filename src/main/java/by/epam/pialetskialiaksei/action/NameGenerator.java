package by.epam.pialetskialiaksei.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NameGenerator {
    private Random random = new Random();
    private List<String> names = new ArrayList<>();
    private static final NameGenerator INSTANCE = new NameGenerator();
    private String mas[] = {
            "Tomas", "Kelly", "Roma", "Dima", "Alexey", "Yana", "Olga", "Kirill", "Misha", "Vika", "Egor"
    };

    private NameGenerator(){}

    public static NameGenerator getInstance(){
        return INSTANCE;
    }

    public String getName(){
        return mas[random.nextInt(mas.length-1)];
    }

}
