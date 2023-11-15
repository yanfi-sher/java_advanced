package terlan.company.service;

import terlan.company.dto.DepartmentAvgSalary;
import terlan.company.dto.Employee;
import terlan.company.dto.SalaryIntervalDistribution;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
        set.add(empl);
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
        return new ArrayList<>(employeesMap.values());
    }

    @Override
    public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
        Collection<Set<Employee>> col = employeesSalary.subMap(salaryFrom,salaryTo).values();
        ArrayList<Employee> res = new ArrayList<>();
        for (Set<Employee> set: col){
            res.addAll(set);
        }
        return res;
    }

    @Override
    public List<Employee> getEmployeeByAge(int ageFrom, int ageTo) {
        LocalDate dateFrom = getBirthDate(ageTo);
        LocalDate dateTo = getBirthDate(ageFrom);
        Collection<Set<Employee>> col = employeesAge.subMap(dateFrom,dateTo).values();
        ArrayList<Employee> res = new ArrayList<>();
        for (Set<Employee> set: col){
            res.addAll(set);
        }
        return res;
    }

    private LocalDate getBirthDate(int age) {
        return LocalDate.now().minusYears(age);
    }

    @Override
    public List<DepartmentAvgSalary> salaryDistributionByDepartments() {
        Map<String, Double> map = employeesMap.values().stream()
            .collect(Collectors.groupingBy(Employee::department
                        ,Collectors.averagingInt(Employee::salary)));

        return map.entrySet().stream()
                .map(e->new DepartmentAvgSalary(e.getKey(),e.getValue().intValue())).toList();
    }

    @Override
    public List<SalaryIntervalDistribution> getSalaryDistribution(int interval) {
        Map<Integer, Long> map = employeesMap.values().stream()
                .collect(Collectors.groupingBy(e->e.salary()/interval, Collectors.counting()));
        return map.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(e->new SalaryIntervalDistribution
                        (e.getKey()*interval,(e.getKey()+1)*interval, e.getValue())).toList();
    }

    @Override
    public Employee updateDepartment(long id, String newDepartment) {
        Employee empl = fireEmployee(id);
        Employee newEmployee = new Employee(id,empl.name(),empl.salary(),newDepartment,empl.birthDate());
        return hireEmployee(newEmployee);
    }

    @Override
    public Employee updateSalary(long id, int newSalary) {
        Employee empl = fireEmployee(id);
        Employee newEmployee = new Employee(id,empl.name(),newSalary,
                empl.department(),empl.birthDate());
        return hireEmployee(newEmployee);
    }

    @Override
    public void save(String filePath) {
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath))){
            output.writeObject(getAllEmployees());
        }catch (Exception e){
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void restore(String filePath) {
        List<Employee> employees = null;
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))){
            employees = (List<Employee>) input.readObject();
            employees.forEach(this::hireEmployee);
        }catch (FileNotFoundException e){
            System.out.println(filePath + "File with data doesn't exist");
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
