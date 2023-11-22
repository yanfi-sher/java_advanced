package telran.company.controller;

import telran.company.dto.Employee;
import telran.company.service.CompanyService;
import telran.view.InputOutput;
import  telran.view.Item;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static telran.view.Item.*;
public class CompanyItems {
    private static final int MIN_SALARY = 5500;
    private static final int MAX_SALARY = 50000;
    private static final int MIN_AGE = 20;
    private static final int MAX_AGE = 70;
    static CompanyService company;
    static String fileName;
    static Set<String> departments = new HashSet<>(Arrays.asList("Development", "QA", "Audit",
            "Accounting", "Management", "Sales"));
    public static Item[] getItems(CompanyService companyService, String file) {
        company = companyService;
        fileName = file;
        Item[] items = {
            of("Hiring Employee", io-> hireEmployee(io)),
            of("Firing Employee", io-> fireEmployee(io)),
            of("Display data about Employee", io-> getEmployee(io)),
            of("Display data about Employee in Department", io->getEmployeesByDepartment(io)),
            of("Display data about all Employees", io->getAllEmployees(io)),
            of("Display data about Employees with salary values", io-> getEmployeesBySalary(io)),
            of("Display data about Employees having given age values", io->getEmployeesByAge(io)),
            of("Display salary distribution by department", io-> salaryDistributionByDepartments(io)),
            of("Display salary distribution by given interval", io-> salaryDistributionByInterval(io)),
            of("Updating employee's department", io-> updateDepartment(io)),
            of("Updating employee's salary", io-> updateSalary(io)),
            of("Exit and Save", io-> saveEmployees(io), true)
        };
        return items;
    }

    private static void saveEmployees(InputOutput io) {
        company.save(fileName);
    }

    private static void updateSalary(InputOutput io) {
        long id = io.readLong("Enter id", "Wrong ID");
        int salary = io.readInt("Enter salary value", "Wrong salary",
                MIN_SALARY, MAX_SALARY);
        company.updateSalary(id,salary);
        io.writeLine("Salary update");
    }

    private static void updateDepartment(InputOutput io) {
        long id = io.readLong("Enter id", "Wrong ID");
        String department = io.readOptions("Enter Department"+departments,
                "Wrong department", departments);
        company.updateDepartment(id, department);
        io.writeLine("Department updated");
    }

    private static void salaryDistributionByInterval(InputOutput io) {
        int interval = io.readInt("Enter interval value", "wrong interval", 500, 5000);
        displayList(io,company.getSalaryDistribution(interval));
    }

    private static void salaryDistributionByDepartments(InputOutput io) {
        displayList(io, company.salaryDistributionByDepartments());
    }

    private static void getEmployeesByAge(InputOutput io) {
        int fromAge = io.readInt("Enter age from", "Wrong age value",
                MIN_AGE, MAX_AGE-1);
        int toAge = io.readInt("Enter age to", "Wrong age value",
                fromAge+1, MAX_AGE);
        displayList(io, company.getEmployeeByAge(fromAge, toAge));
    }

    private static void getEmployeesBySalary(InputOutput io) {
        int salaryFrom = io.readInt("Enter age salary", "Wrong age salary",
                MIN_SALARY,MAX_SALARY-1);
        int salaryTo = io.readInt("Enter salary to", "Wrong salary value",
                salaryFrom+1, MAX_SALARY);
        displayList(io, company.getEmployeesBySalary(salaryFrom, salaryTo));
    }

    private static void getAllEmployees(InputOutput io) {
        displayList(io, company.getAllEmployees());
    }

    private static void getEmployeesByDepartment(InputOutput io) {
        String department = io.readOptions("Enter department", "Wrong department",
                departments);
        displayList(io, company.getEmployeesByDepartment(department));
    }

    private static void getEmployee(InputOutput io) {
        long id = io.readLong("Enter id", "Wrong ID");
        Employee employee = company.getEmployee(id);
        String res = employee == null ? "Employee not found": employee.toString();
        io.writeLine(employee);
    }

    private static void fireEmployee(InputOutput io) {
        long id = io.readLong("Enter id", "Wrong ID");
        Employee employee = company.getEmployee(id);
        company.fireEmployee(id);
        io.writeLine("Employee has been fire succesfully");
    }

    private static void hireEmployee(InputOutput io) {
        long id = io.readLong("Enter id", "Wrong ID");
        if (company.getEmployee(id)!=null) {
            throw new RuntimeException("Employee with entered id already exists");
        }
        String name = io.readPredicate("Enter name", "Wrong name: must be not less then 3 letters", str->checkName(str));
        int salary = io.readInt("Enter salary value", "Wrong salary",
                MIN_SALARY, MAX_SALARY);
        String department = io.readOptions("Enter department", "Wrong department",
                departments);
        LocalDate birthdate = io.readDate("Enter birthdate", "Wrong birthdate value", getDate(MAX_AGE),getDate(MIN_AGE));
        Employee empl = new Employee(id, name, salary, department, birthdate);
        company.hireEmployee(empl);
    }

    private static boolean checkName(String str) {
        boolean res = false;
        if (str.length()>=3){
            if (Character.isUpperCase(str.charAt(0))){
                if(str.substring(1).chars().allMatch(Character::isLowerCase)){
                    res = true;
                }
            }
        }
        return res;
    }

    private static LocalDate getDate(int age) {
        return LocalDate.now().minusYears(age);
    }

    private static <T> void displayList(InputOutput io, List<T> list){
        list.forEach(io::writeLine);
    }
}