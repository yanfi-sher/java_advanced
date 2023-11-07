package terlan.company.test;

import org.junit.jupiter.api.Test;
import terlan.company.dto.Employee;
import terlan.company.service.CompanyService;
import terlan.company.service.CompanyServiceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
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

    private static final String DEPARTMENT1 = "QA";
    private static final String DEPARTMENT2 = "Development";
    private static final String DEPARTMENT3 = "Management";
    private static final String DEPARTMENT6 = "Audit";

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
        Employee newEmployee = empl1;
        //FIXME
        boolean flException = false;
        try{
            company.hireEmployee(newEmployee);
        }catch (IllegalStateException e){
            flException = true;
        }
        assertTrue(flException);
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
        Employee[] actualDep1 = list1.toArray(new Employee[]{});
        Employee[] actualDep2 = list2.toArray(new Employee[]{});
        Arrays.sort(actualDep1);
        Arrays.sort(actualDep2);
        assertTrue(company.getEmployeesByDepartment(DEPARTMENT6).isEmpty());
        assertArrayEquals(employeesDep1,actualDep1);
        assertArrayEquals(employeesDep2,actualDep2);
    }

    @org.junit.jupiter.api.Test
    void getAllEmployees() {
        //home
    }

    @org.junit.jupiter.api.Test
    void getEmployeesBySalary() {
        //home
    }

    @org.junit.jupiter.api.Test
    void getEmployeeByAge() {
        //now
    }

    @org.junit.jupiter.api.Test
    void salaryDistributionByDepartments() {
    }

    @org.junit.jupiter.api.Test
    void getSalaryDistribution() {
    }

    @org.junit.jupiter.api.Test
    void updateDepartment() {
    }

    @org.junit.jupiter.api.Test
    void updateSalary() {
    }

    @org.junit.jupiter.api.Test
    void save() {
    }

    @org.junit.jupiter.api.Test
    void restore() {
    }

    private int getAge(LocalDate birthdate){
        int result = (int) ChronoUnit.YEARS.between(birthdate,LocalDate.now());8
        return result;
    }
}