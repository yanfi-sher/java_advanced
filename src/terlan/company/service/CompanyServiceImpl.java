package terlan.company.service;

import terlan.company.dto.DepartmentAvgSalary;
import terlan.company.dto.Employee;
import terlan.company.dto.SalaryIntervalDistribution;

import java.time.LocalDate;
import java.util.*;

public class CompanyServiceImpl implements CompanyService{
    HashMap<Long,Employee> employeesMap = new HashMap<>();
    HashMap<String, Set<Employee>> employeesDepartment = new HashMap<>();
    TreeMap<Integer, Set<Employee>> employeesSalary = new TreeMap<>();
    TreeMap<LocalDate, Set<Employee>> employeesAge = new TreeMap<>();

    @Override
    /**
     * adds new Employee into a company
     * In the case an employee with the given ID already exists,
     * the exception IllegalStateException must be thrown
     * returns reference to the being added Employee object
     */
    public Employee hireEmployee(Employee empl) {
        long id = empl.id();
        if (employeesMap.containsKey(id)) {
            throw new IllegalStateException("Employee already exists "+ id);
        }
        employeesMap.put(id,empl);
        addEmployeeSalary(empl);
        addEmployeeAge(empl);
        addEmployeeDepartment(empl);
        return empl;
    }

    private void addEmployeeDepartment(Employee empl) {
        String department = empl.department();
        //Set<Employee> set = employeesDepartment.computeIfAbsent(department,k -> new HashSet<Employee>());
        //set.add(empl);
        Set<Employee> set = employeesDepartment.get(department);
        if (set == null){
            set = new HashSet<>();
            employeesDepartment.put(department,set);
        }
        set.add(empl);
    }

    private void addEmployeeAge(Employee empl) {
        LocalDate birthDate = empl.birthDate();
        Set<Employee> set = employeesAge.computeIfAbsent(birthDate,k->new HashSet<Employee>());
    }

    private void addEmployeeSalary(Employee empl) {
        int salary = empl.salary();
        //Set<Employee> set = employeesSalary.computeIfAbsent(salary,k -> new HashSet<Employee>());
        //set.add(empl);
        employeesSalary.computeIfAbsent(empl.salary(), k-> new HashSet<>()).add(empl);
    }

    @Override
    /**
     * removes employeee object from company according to a given ID
     * In the case an employee with the given ID doesn't exist
     * the method must throw IllegalStateException
     */
    public Employee fireEmployee(long id) {
        Employee empl = employeesMap.remove(id);
        if (empl == null){
            throw new IllegalStateException("Employee not found " + id);
        }
        removeEmployeesDepartment(empl);
        removeEmployeesSalary(empl);
        removeEmployeesAge(empl);
        return empl;
    }

    private void removeEmployeesAge(Employee empl) {
        LocalDate birthDate = empl.birthDate();
        Set<Employee> set = employeesAge.get(birthDate);
        set.remove(empl); //removing reference to being employee from the set of employees with the given birthdate
        if (set.isEmpty()){
            employeesAge.remove(birthDate);
        }
    }

    private void removeEmployeesSalary(Employee empl) {
        int salary = empl.salary();
        Set<Employee> set = employeesSalary.get(salary);
        set.remove(empl);
        if (set.isEmpty()){
            employeesSalary.remove(salary);
        }
    }

    private void removeEmployeesDepartment(Employee empl) {
        String department = empl.department();
        Set<Employee> set = employeesDepartment.get(department);
        set.remove(empl);
        if (set.isEmpty()){
            employeesDepartment.remove(department);
        }
    }

    @Override
    /**
     * return reference to Employee object with a given ID value
     * in the case employee with the ID doesn't exist
     * the method return null
     */
    public Employee getEmployee(long id) {
        return employeesMap.get(id);
    }

    @Override
    /**
     * returnlist of employee objects working in a given department
     * in case none employees in the department, the method return empty list
     */
    public List<Employee> getEmployeesByDepartment(String department) {
        Set<Employee> setEmployeesDep = employeesDepartment.getOrDefault(department, new HashSet<>());
        return new ArrayList<>(setEmployeesDep);
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
    public Employee updateSalary(long id, int newSalary) {
        return null;
    }

    @Override
    public void save(String filePath) {

    }

    @Override
    public void restore(String filePath) {

    }
}
