package iss.persistent;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeTest {
    public static void main (String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("JPA01");
        EntityManager em = emf.createEntityManager();
        EmployeeService service = new EmployeeService(em);

        // Create and persist an employee
        em.getTransaction().begin();
        Employee emp = service.createEmployee(159, "Emily Tan", 55000);
        em.getTransaction().commit();
        System.out.println("Persisteed " + emp);

        // Find all employees
        List<Employee> emps = service.findAllEmployees();
        for (Employee e : emps) {
            System.out.println("Found employee: " + e);
        }

        // Update an employee
        em.getTransaction().begin();
        Employee updatedEmp = service.raiseEmployeeSalary(158, 3000);
        em.getTransaction().commit();
        System.out.println("Updated " + updatedEmp);

        // Find an employee
        Employee e = service.findEmployee(158);
        System.out.println("Found employee: " + e);
    }
}

