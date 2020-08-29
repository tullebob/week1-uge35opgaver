package facades;

import DTO.EmployeeDTO;
import com.google.gson.Gson;
import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EmployeeDTO getByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee e = em.find(Employee.class, id);

            return new EmployeeDTO(e);
        } finally {
            em.close();
        }

    }

    public EmployeeDTO getByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM Employee WHERE e.name = :name");
            query.setParameter("name", name);
            Employee employee = (Employee) query.getSingleResult();
            return new EmployeeDTO(employee);
        } finally {
            em.close();
        }

    }

    public List<EmployeeDTO> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        List<EmployeeDTO> employeeList = new ArrayList<>();
        try {
            TypedQuery<Employee> query = em.createQuery("Select e from Employee e", Employee.class);
            List<Employee> list = query.getResultList();
            for (Employee employee : list) {
                employeeList.add(new EmployeeDTO(employee));
            }
            return employeeList;
        } finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getHighestSalaries() {
        EntityManager em = emf.createEntityManager();
        List<EmployeeDTO> employeeList = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT e FROM Employee e ORDER BY e.salary DESC").setMaxResults(2);
            List<Employee> list = query.getResultList();
            for (Employee employee : list) {
                employeeList.add(new EmployeeDTO(employee));
                
            }
            return employeeList;

        } finally {
            em.close();
        }
    }

    public EmployeeDTO addEmployee(String name, String address, int salary) {
        Employee employee = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return new EmployeeDTO(employee);
        } finally {
            em.close();
        }
    }
}
