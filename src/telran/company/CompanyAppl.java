package telran.company;

import telran.company.service.*;
import telran.view.*;
import telran.company.controller.CompanyItems;

public class CompanyAppl {
    private static final String FILE_NAME = "employees.data";
    public static void main(String[] args) {
        CompanyService company = new CompanyServiceImpl();
        company.restore(FILE_NAME);
        Item[] items = CompanyItems.getItems(company, FILE_NAME);
        InputOutput io = new StandartInputOutput();
        Menu menu = new Menu("Company Application",items);
        menu.perform(io);

    }
}
