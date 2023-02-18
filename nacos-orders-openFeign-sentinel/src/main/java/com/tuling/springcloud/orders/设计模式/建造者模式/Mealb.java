package com.tuling.springcloud.orders.设计模式.建造者模式;

public class Mealb extends MealBuilder {

    @Override
    public void buildFood(String food) {
        t.setDrink(food);
    }

    @Override
    public void buildDrink(String drink) {
        t.setDrink(drink);
    }
}
