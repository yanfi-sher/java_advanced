package terlan.company.service;

import terlan.company.dto.DepartmentAvgSalary;
import terlan.company.dto.Employee;
import terlan.company.dto.SalaryIntervalDistribution;

import java.util.List;

public class CompanyServiceImpl implements CompanyService{
    @Override
    /**
     * adds new Employee into a company
     * In the case an employee with the given ID already exists,
     * the exception IllegalStateException must be thrown
     * returns reference to the being added Employee object
     */
    public Employee hireEmployee(Employee empl) {

        return null;
    }

    @Override
    /**
     * removes employeee object from company according to a given ID
     * In the case an employee with the given ID doesn't exist
     * the method must throw IllegalStateException
     */
    public Employee fireEmployee(long id) {
        return null;
    }

    @Override
    /**
     * return reference to Employee object with a given ID value
     * in the case employee with the ID doesn't exist
     * the method return null
     */
    public Employee getEmployee(long id) {
        return null;
    }

    @Override
    /**
     * returnlist of employee objects working in a given department
     * in case none employees in the department, the method return empty list
     */
    public List<Employee> getEmployeesByDepartment(String department) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public List<Employee> getEmployeesBySalary(int salaryTo, int salary) {
        return null;
    }

    @Override
    public List<Employee> getEmployeeByAge(int ageFrom, int ageTo) {
        return null;
    }

    @Override
    public List<DepartmentAvgSalary> salaryDistributionByDepartments() {
        return null;
    }

    @Override
    public List<SalaryIntervalDistribution> getSalaryDistribution(int interval) {
        return null;
    }

    @Override
    public Employee updateDepartment(long id, String newDepartment) {
        return null;
    }

    @Override
    public Employee updateSalary(long id, String newSalary) {
        return null;
    }

    @Override
    public void save(String filePath) {

    }

    @Override
    public void restore(String filePath) {

    }
}
