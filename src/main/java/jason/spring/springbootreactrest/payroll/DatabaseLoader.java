package jason.spring.springbootreactrest.payroll;

import jason.spring.springbootreactrest.payroll.domain.Employee;
import jason.spring.springbootreactrest.payroll.domain.Manager;
import jason.spring.springbootreactrest.payroll.repository.EmployeeRepository;
import jason.spring.springbootreactrest.payroll.repository.ManagerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private static final String ROLE_MANAGER = "ROLE_MANAGER";

    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;

    public DatabaseLoader(EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Manager greg = this.managerRepository.save(new Manager("greg", "turnquist", ROLE_MANAGER));
        Manager oliver = this.managerRepository.save(new Manager("oliver", "gierke", ROLE_MANAGER));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("greg", "doesn't matter", AuthorityUtils.createAuthorityList(ROLE_MANAGER))
        );

        this.employeeRepository.save(new Employee("Frodo", "Baggins", "ring bearer", greg));
        this.employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar", greg));
        this.employeeRepository.save(new Employee("Gandalf", "the Grey", "wizard", greg));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("oliver", "doesn't matter", AuthorityUtils.createAuthorityList(ROLE_MANAGER))
        );

        this.employeeRepository.save(new Employee("Samwise", "Gamgee", "gardener", oliver));
        this.employeeRepository.save(new Employee("Merry", "Brandybuck", "pony rider", oliver));
        this.employeeRepository.save(new Employee("Peregrin", "Took", "pipe smoker", oliver));

        SecurityContextHolder.clearContext();
    }
}
