
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;

/**
 * REST Web Service
 *
 * @author jimmy
 */
@Path("animals")
public class AnimalDemo {

    @Context
    private UriInfo context;
     private Gson GSON = new GsonBuilder().setPrettyPrinting().create();
     private List<String> animallist = new ArrayList();

    /**
     * Creates a new instance of AnimalDemo
     */
    public AnimalDemo() {
        if(animallist.isEmpty()){
            animallist.add("Dog");
            animallist.add("Cat");
            animallist.add("Mouse");
            animallist.add("Bird");
        }
    }

    /**
     * Retrieves representation of an instance of rest.AnimalDemo
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)

    public String getJson() {
        //TODO return proper representation object
        return "vuf";
    }
    
    @Path("animal_list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
     public String getJson2() {
        String jsonString = GSON.toJson(animallist);
        return jsonString;
    }
     
     @Path("animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3() {
        //TODO return proper representation object
        AnimalNoDB a1 = new AnimalNoDB ("Duck", "quack!!");
        String jsonString = GSON.toJson(a1);
        return jsonString;
       
    }

    /**
     * PUT method for updating or creating an instance of AnimalDemo
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
