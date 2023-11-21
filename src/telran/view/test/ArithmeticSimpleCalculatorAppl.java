package telran.view.test;

import telran.view.*;
import static telran.view.Item.*;



public class ArithmeticSimpleCalculatorAppl {
    public static void main(String[] args) {
        InputOutput io = new StandartInputOutput();
        Item[] items = getItems();
        Menu menu = new Menu("Simple Calculator",items);
        menu.perform(io);
    }

    static Item[] getItems() {
        Item[] items = {
                of("Add numbers", io -> addNumbers(io)),
                of("Subtract numbers", io -> subtractNumbers(io)),
                of("Multiply numbers", io -> multiplyNumbers(io)),
                of("Divide numbers", io -> divideNumbers(io)),
                exit()
        };
        return items;
    }

    private static void divideNumbers(InputOutput io) {
        double[] numbers = getNumbers(io);
        io.writeLine(numbers[0]/numbers[1]);
    }

    private static double[] getNumbers(InputOutput io) {
        double number1 = io.readDouble("Enter first number", "Wrong number");
        double number2 = io.readDouble("Enter second number", "Wrong number");
        return new double[]{number1,number2};
    }

    private static void multiplyNumbers(InputOutput io) {
        double[] numbers = getNumbers(io);
        io.writeLine(numbers[0]*numbers[1]);
    }

    private static void subtractNumbers(InputOutput io) {
        double[] numbers = getNumbers(io);
        io.writeLine(numbers[0]-numbers[1]);
    }

    private static void addNumbers(InputOutput io) {
        double[] numbers = getNumbers(io);
        io.writeLine(numbers[0]+numbers[1]);
    }
}
