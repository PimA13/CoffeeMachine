
public class CoffeeMachine {

    public static void main(String[] args) {
        Coffee coffeeMachine = new Coffee();

        while (coffeeMachine.getPower()) {
            coffeeMachine.selectAction();
        }
    }
}
