
package DTO;

import entities.Employee;

public class EmployeeDTO {
    
    private long id;
    private String name;
    private String address;
    
    public EmployeeDTO(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
    }
}
