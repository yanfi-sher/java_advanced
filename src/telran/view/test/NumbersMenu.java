package telran.view.test;

import telran.view.*;
import telran.view.Item;


public class NumbersMenu {
    public static Item getMenu() {

        Menu menu = new Menu("Arithmetic operations", getItems());
        return menu;
    }

    private static Item[] getItems() {
        return ArithmeticSimpleCalculatorAppl.getItems();
    }

}

