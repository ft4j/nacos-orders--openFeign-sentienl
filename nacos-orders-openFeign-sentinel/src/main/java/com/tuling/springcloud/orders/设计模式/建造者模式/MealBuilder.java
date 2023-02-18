package com.tuling.springcloud.orders.设计模式.建造者模式;

public abstract class MealBuilder {
    Meal t = new Meal();

    public abstract void buildFood(String food);
    public abstract void buildDrink(String drink);
    public Meal get套餐(){
        return t;
    }
}
