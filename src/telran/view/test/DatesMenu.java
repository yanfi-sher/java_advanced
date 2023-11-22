package telran.view.test;
import static telran.view.Item.*;
import telran.view.*;

import java.time.LocalDate;

import static telran.view.Item.*;


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
        LocalDate date1 = io.readDate("Enter the first date (yyyy-MM-dd): ", "ERROR");
        LocalDate date2 = io.readDate("Enter the second date (yyyy-MM-dd): ", "ERROR");
        long daysBetween = Math.abs(date1.until(date2).getDays());
        io.writeLine("Days between dates: " + daysBetween);
    }

    private static void dateBefore(InputOutput io) {
        LocalDate currentDate = io.readDate("Enter the current date (yyyy-MM-dd): ", "ERROR");
        int daysBefore = io.readInt("Enter the number of days before: ", "ERROR");
        LocalDate dateBefore = currentDate.minusDays(daysBefore);
        io.writeLine("Date before given days: " + dateBefore);
    }

    private static void dateAfter(InputOutput io) {
        LocalDate currentDate = io.readDate("Enter the current date (yyyy-MM-dd): ", "ERROR");
        int daysAfter = io.readInt("Enter the number of days after: ", "ERROR");
        LocalDate dateAfter = currentDate.plusDays(daysAfter);
        io.writeLine("Date after given days: " + dateAfter);
    }
}