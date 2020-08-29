package rest;

import DTO.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class EmployeeResource {
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDTO> getAll() {
        return facade.getAllEmployees();
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeDTO getEmployeeById(@PathParam("id") long id) {
        return facade.getByID(id);
    }
    
    @Path("/highestpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDTO> getHighestPaid() {
        return facade.getHighestSalaries();
    }
    
    @Path("/name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeDTO getEmployeeByName(@PathParam("name")String name) {
        return facade.getByName(name);
    }
}

