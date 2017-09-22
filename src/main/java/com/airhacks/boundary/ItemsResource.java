package com.airhacks.boundary;

import com.airhacks.control.FooStorage;
import com.airhacks.entity.Item;
import java.util.Objects;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author pablobastidasv
 */
@Path("items")
@Produces("application/json")
@Consumes("application/json")
public class ItemsResource {
                
    @Inject
    private FooStorage fooStorage;
    
    @GET
    public JsonArray fooGet(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        
        fooStorage.list()
                .stream()
                .map(Item::getJson)
                .forEach(builder::add);
        
        return builder.build();
    }
    
    @POST
    public Response save(JsonObject body){
        Long id = fooStorage.save(body.getString("name"));
        
        return Response.status(Response.Status.CREATED)
                .entity(Json.createObjectBuilder().add("id", id).build())
                .build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id){
        Item item = fooStorage.get(id);
        
        if(Objects.isNull(item)){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response
                .ok(item.getJson())
                .build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
        fooStorage.remove(id);
        
        return Response.noContent().build();
    }
}
