import java.util.Scanner;

public class Coffee {
    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int disposableCups = 9;
    private int money = 550;
    private boolean power = true;
    private TypeOfCoffee coffee;
    private final Scanner scanner = new Scanner(System.in);

    public Coffee() {

    }

    public void selectAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String act = scanner.next();

        switch(act) {
            case ("exit"):
                power = false;
                break;

            case ("buy"):
                selectionCoffee();
                break;

            case ("fill"):
                restocking();
                break;

            case ("take"):
                getMoney();
                break;

            case ("remaining"):
                showRemainder(water, milk, coffeeBeans, disposableCups, money);
                break;
        }
        System.out.println("");
    }

/* ---------------------------------------- */

    private void selectionCoffee() {
        System.out.println("");
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        String input = scanner.next();
        if (input.equals("exit")) {
            setPower(false);
        } else {
            try {
                selectionTypeOfCoffee(Integer.parseInt(input));
                calcRemainder(this.coffee);
            } catch (Exception e) {}
        }

    }

    private void selectionTypeOfCoffee(int choise) {
        if (choise == 1) {
            this.coffee = TypeOfCoffee.ESSPRESSO;
        } else if (choise == 2) {
            this.coffee = TypeOfCoffee.LATTE;
        } else if (choise == 3) {
            this.coffee = TypeOfCoffee.CAPPUCCINO;
        }
    }

    private void calcRemainder(TypeOfCoffee type) {
        boolean enoughComponents = checkingWater(this.water - type.getWater())
              && checkingMilk(this.milk - type.getMilk())
              && checkingCoffeeBeans(this.coffeeBeans - type.getCoffeeBeans())
              && checkingDisposableCups(this.disposableCups - type.getDisposableCups());
        makingCoffee(enoughComponents, type);
    }

    private void showRemainder(int remainderWater, int remainderMilk, int remainderCoffeeBeans, int remainderDisposableCups, int remainderMoney) {
        System.out.println("");
        System.out.println("The coffee machine has:");
        System.out.println(remainderWater + " of water");
        System.out.println(remainderMilk + " of milk");
        System.out.println(remainderCoffeeBeans + " of coffee beans");
        System.out.println(remainderDisposableCups + " of disposable cups");
        System.out.println(remainderMoney + " of money");
    }

    private void setPower(boolean power) {
        this.power = power;
    }

    public boolean getPower() {
        return this.power;
    }

    private boolean checkingWater(int requiredWater) {
        if (requiredWater < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkingMilk(int requiredMilk) {
        if (requiredMilk < 0) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkingCoffeeBeans(int requiredCoffeeBeans) {
        if (requiredCoffeeBeans < 0) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        } else {
            return true;
        }

    }

    private boolean checkingDisposableCups(int requiredDisposableCups) {
        if (requiredDisposableCups < 0) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        } else {
            return true;
        }
    }

    private void makingCoffee(boolean permit, TypeOfCoffee type) {
        if (permit) {
            this.water = this.water - type.getWater();
            this.milk = this.milk - type.getMilk();
            this.coffeeBeans = this.coffeeBeans - type.getCoffeeBeans();
            this.disposableCups = this.disposableCups - type.getDisposableCups();
            this.money = this.money + type.getCost();
            printMassage();
        }
    }

    private void restocking() {
        System.out.println("");
        System.out.println("Write how many ml of water do you want to add:");
        this.water = this.water + scanner.nextInt();

        System.out.println("Write how many ml of milk do you want to add:");
        this.milk = this.milk + scanner.nextInt();

        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.coffeeBeans = this.coffeeBeans + scanner.nextInt();

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        this.disposableCups = this.disposableCups + scanner.nextInt();
    }

    private void getMoney() {
        System.out.println("");
        System.out.println("I gave you $" + this.money);
        this.money = 0;
    }

    private void printMassage() {
        System.out.println("I have enough resources, making you a coffee!");
    }


}


enum TypeOfCoffee {
    ESSPRESSO(250, 0, 16, 1, 4),
    LATTE(350, 75, 20, 1, 7),
    CAPPUCCINO(200, 100, 12, 1, 6);

    private int quantityWater;
    private int quantityMilk;
    private int quantityCoffeeBeans;
    private int quantityDisposableCups;
    private int quantityCost;

    TypeOfCoffee (int water, int milk, int coffeeBeans, int disposableCups, int cost) {
        this.quantityWater = water;
        this.quantityMilk = milk;
        this.quantityCoffeeBeans = coffeeBeans;
        this.quantityDisposableCups = disposableCups;
        this.quantityCost = cost;
    }


    public int getWater() {
        return quantityWater;
    }
    public int getMilk() {
        return quantityMilk;
    }
    public int getCoffeeBeans() {
        return quantityCoffeeBeans;
    }
    public int getDisposableCups() {
        return quantityDisposableCups;
    }
    public int getCost() {
        return quantityCost;
    }

}