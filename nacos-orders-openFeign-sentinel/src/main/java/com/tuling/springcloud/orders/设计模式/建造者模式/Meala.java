package com.tuling.springcloud.orders.设计模式.建造者模式;

public class Meala extends MealBuilder {

    @Override
    public void buildFood(String food) {
        t.setFood(food);
    }

    @Override
    public void buildDrink(String drink) {
        t.setDrink(drink);
    }
}
