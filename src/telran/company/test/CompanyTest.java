package telran.company.test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.dto.SalaryIntervalDistribution;
import telran.company.service.CompanyService;
import telran.company.service.CompanyServiceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyTest {
    private static final String FILE_PATH = "file.org";
    private static final long ID1 = 123;
    private static final long ID2 = 124;
    private static final long ID3 = 125;
    private static final long ID4 = 126;
    private static final long ID5 = 127;
    private static final long ID6 = 1000;
    private static final int SALARY1 = 5000;
    private static final int SALARY2 = 6000;
    private static final int SALARY3 = 7000;
    private static final int SALARY4 = 8000;
    private static final int SALARY5 = 9000;

    private static final String DEPARTMENT1 = "Dep1";
    private static final String DEPARTMENT2 = "Dep2";
    private static final String DEPARTMENT3 = "Dep3";
    private static final String DEPARTMENT6 = "Dep1000";

    private static final LocalDate DATE1 = LocalDate.of(1970,10,23);
    private static final LocalDate DATE2 = LocalDate.of(1975,1,1);
    private static final LocalDate DATE3 = LocalDate.of(1980,5,3);
    private static final LocalDate DATE4 = LocalDate.of(1990,10,11);
    private static final LocalDate DATE5 = LocalDate.of(2000,9,11);

    Employee empl1 = new Employee(ID1, "name1", SALARY1,DEPARTMENT1, DATE1);
    Employee empl2 = new Employee(ID2, "name2", SALARY2,DEPARTMENT1, DATE2);
    Employee empl3 = new Employee(ID3, "name3", SALARY3,DEPARTMENT2, DATE3);
    Employee empl4 = new Employee(ID4, "name4", SALARY4,DEPARTMENT2, DATE4);
    Employee empl5 = new Employee(ID5, "name4", SALARY5,DEPARTMENT3, DATE5);
    Employee[] employees = {empl1,empl2,empl3,empl4,empl5};
    CompanyService company = null;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        company = new CompanyServiceImpl();
        for (Employee empl: employees){
            company.hireEmployee(empl);
        }
    }

    @org.junit.jupiter.api.Test
    void hireEmployeeNormal() {
        Employee newEmployee = new Employee(ID6,"name6",SALARY1,DEPARTMENT1,DATE1);
        assertEquals(newEmployee, company.hireEmployee(newEmployee));
    }

    @org.junit.jupiter.api.Test
    void hireEmployeeException() {
        assertThrowsExactly(IllegalStateException.class, ()->company.hireEmployee(empl1));
//        Employee newEmployee = empl1;
//        boolean flException = false;
//        try{
//            company.hireEmployee(newEmployee);
//        }catch (IllegalStateException e){
//            flException = true;
//        }
//        assertTrue(flException);
    }

    @Test
    void fireEmployeeNormal() {
        assertEquals(empl1, company.fireEmployee(ID1));
        assertEquals(empl1,company.hireEmployee(empl1));
    }

    @Test
    void fireEmployeeException() {
        boolean flException = false;
        try{
            company.fireEmployee(ID6);
        }catch (IllegalStateException e){
            flException = true;
        }
        assertTrue(flException);
    }

    @Test
    void getEmployee() {
        assertEquals(empl1, company.getEmployee(ID1));
        assertNull(company.getEmployee(ID6));
    }

    @org.junit.jupiter.api.Test
    void getEmployeesByDepartment() {
        Employee[] employeesDep1 = {empl1,empl2};
        Employee[] employeesDep2 = {empl3, empl4};
        List<Employee> list1 = company.getEmployeesByDepartment(DEPARTMENT1);
        List<Employee> list2 = company.getEmployeesByDepartment(DEPARTMENT2);
        assertTrue(company.getEmployeesByDepartment(DEPARTMENT6).isEmpty());
        runListTest(employeesDep1,list1);
        runListTest(employeesDep2,list2);
    }

    @org.junit.jupiter.api.Test
    void getAllEmployees() {
        runListTest(employees,company.getAllEmployees());
    }

    @org.junit.jupiter.api.Test
    void getEmployeesBySalary() {
        runListTest(employees,company.getEmployeesBySalary(0,Integer.MAX_VALUE));
        runListTest(new Employee[0], company.getEmployeesBySalary(100000,100001));
        runListTest(new Employee[]{empl1,empl2}, company.getEmployeesBySalary(SALARY1,SALARY3));
    }

    @org.junit.jupiter.api.Test
    void getEmployeeByAge() {
        runListTest(employees,company.getEmployeeByAge(0,100));
        runListTest(new Employee[0], company.getEmployeeByAge(90,100));
        runListTest(new Employee[]{empl2,empl3}, company.getEmployeeByAge(getAge(DATE3),getAge(DATE1)));
    }

    @org.junit.jupiter.api.Test
    void salaryDistributionByDepartments() {
        DepartmentAvgSalary[] expectedDistribution = {
                new DepartmentAvgSalary(DEPARTMENT1, (SALARY1 + SALARY2) / 2),
                new DepartmentAvgSalary(DEPARTMENT2, (SALARY3 + SALARY4) / 2),
                new DepartmentAvgSalary(DEPARTMENT3,SALARY5)
        };
        List<DepartmentAvgSalary> listDistribution = company.salaryDistributionByDepartments();
        DepartmentAvgSalary[] actuallyDistribution = listDistribution.toArray(new DepartmentAvgSalary[]{});
        Arrays.sort(actuallyDistribution);
        assertArrayEquals(expectedDistribution,actuallyDistribution);
    }

    @org.junit.jupiter.api.Test
    void getSalaryDistribution() {
        int interval = 1500;
        List<SalaryIntervalDistribution> distribution = company.getSalaryDistribution(1500);
        SalaryIntervalDistribution[] expectedDistribution = {
                new SalaryIntervalDistribution(4500,4500+interval,1),
                new SalaryIntervalDistribution(6000,6000+interval,2),
                new SalaryIntervalDistribution(7500, 7500+interval,1),
                new SalaryIntervalDistribution(9000, 9000+interval,1)
        };
        assertArrayEquals(expectedDistribution,distribution.toArray(new SalaryIntervalDistribution[0]));
    }

    @org.junit.jupiter.api.Test
    void updateDepartment() {
        assertEquals(empl2, company.updateDepartment(ID2,DEPARTMENT2));
        runListTest(new Employee[]{empl1},company.getEmployeesByDepartment(DEPARTMENT1));
        runListTest(new Employee[]{empl2,empl3,empl4}, company.getEmployeesByDepartment(DEPARTMENT2));
        assertThrowsExactly(IllegalStateException.class, ()-> company.updateDepartment(ID6,DEPARTMENT1));
    }

    @org.junit.jupiter.api.Test
    void updateSalary() {
        assertEquals(empl2,company.updateSalary(ID2,SALARY3));
        runListTest(new Employee[]{empl1}, company.getEmployeesBySalary(SALARY1,SALARY3));
        runListTest(new Employee[]{empl2,empl3,empl4},company.getEmployeesBySalary(SALARY3,SALARY5));
        assertThrowsExactly(IllegalStateException.class, ()-> company.updateSalary(ID6,SALARY1));
    }

    @org.junit.jupiter.api.Test
    @Order(1)
    void save() {
        company.save(FILE_PATH);
    }

    @org.junit.jupiter.api.Test
    @Order(2)
    void restore() {
        CompanyService companySaved = new CompanyServiceImpl();
        companySaved.restore(FILE_PATH);
        runListTest(employees,companySaved.getAllEmployees());
    }

    private int getAge(LocalDate birthdate){
        return (int) ChronoUnit.YEARS.between(birthdate,LocalDate.now());
    }

    private void runListTest(Employee[] expected, List<Employee> list){
        Employee[] actual = list.toArray(new Employee[]{});
        Arrays.sort(actual);
        assertArrayEquals(expected,actual);
    }
}
