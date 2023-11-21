package telran.view.test;
import static telran.view.Item.*;
import telran.view.*;

import static telran.view.Item.exit;


public class DatesMenu {
    public static Item getMenu(){
        Menu menu = new Menu("Dates Operations", getItems());
        return menu;
    }

    private static Item[] getItems() {
        Item[] items = {
                of("Date after given days", io-> dateAfter(io) ),
                of("Date before given days", io-> dateBefore(io) ),
                of("Number days between two dates", io-> daysBetweenDates(io) ),
                exit()
        };
        return items;
    }

    private static void daysBetweenDates(InputOutput io) {

    }

    private static void dateBefore(InputOutput io) {

    }

    private static void dateAfter(InputOutput io) {

    }
}
