package terlan.company.test;

import terlan.company.dto.Employee;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    Employee empl1 = new Employee(ID1, "name1", SALARY1,DEPARTAMENT1, DATE1);
    Employee empl2 = new Employee(ID2, "name2", SALARY2,DEPARTAMENT1, DATE2);

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void hireEmployee() {
    }

    @org.junit.jupiter.api.Test
    void fireEmployee() {
    }

    @org.junit.jupiter.api.Test
    void getEmployee() {
    }

    @org.junit.jupiter.api.Test
    void getAllEmployees() {
    }

    @org.junit.jupiter.api.Test
    void getEmployeesBySalary() {
    }

    @org.junit.jupiter.api.Test
    void getEmployeeByAge() {
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
}