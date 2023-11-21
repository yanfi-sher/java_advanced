package telran.view.test;
import telran.view.*;

public class NumbersDatesAppl {
    public static void main(String[] args) {
        InputOutput io = new StandartInputOutput();
        Menu menu = new Menu("Numbers-Dates-Operations",
                new Item[]{NumbersMenu.getMenu(),DatesMenu.getMenu(),Item.exit()});

        menu.perform(io);
    }
}
