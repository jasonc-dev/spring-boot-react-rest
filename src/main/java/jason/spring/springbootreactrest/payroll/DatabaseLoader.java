package jason.spring.springbootreactrest.payroll;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public DatabaseLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.employeeRepository.save(new Employee("Jason", "Lowe", "Developer"));
        this.employeeRepository.save(new Employee("John", "Doe", "Tester"));
        this.employeeRepository.save(new Employee("Mike", "Dean", "PM"));
    }
}
