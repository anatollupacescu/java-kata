package codewars;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HackerRankTest {

    private class FoodFactory {
        Food getFood(String food) {
            switch (food) {
                case "FastFood": return new FastFood(food);
                case "Fruit" : return new Fruit(food);
            }
            throw new IllegalArgumentException();
        }
    }
    private abstract class Food {
        final String food;
        Food(String food) {
            this.food = food;
        }
        abstract void serveFood();
    }

    private class Fruit extends Food {
        Fruit(String food) {
            super(food);
        }
        void serveFood() {
            System.out.println("I'm serving " + food);
        }
    }
    private class FastFood extends Food {
        FastFood(String food) {
            super(food);
        }
        public void serveFood() {
            System.out.println("I'm serving " + food);
        }
    }

    @Test
    public void test6() {
        FoodFactory myFoods = new FoodFactory();
        Food food1 = myFoods.getFood("FastFood");
        Food food2 = myFoods.getFood("Fruit");
        System.out.println("My name is: " + food1.getClass().getName());
        System.out.println("My name is: " + food2.getClass().getName());
        System.out.println("Our superclass is: " + food1.getClass().getSuperclass().getName());
        food1.serveFood();
        food2.serveFood();
    }

    @Test
    public void test() {
        Pattern p = Pattern.compile("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$");
        Matcher m = p.matcher("999.321.0.12");
        assertThat(m.matches(), is(true));
    }

}
