package com.airhacks.boundary;

import com.airhacks.control.FooManager;
import com.airhacks.control.FooStorage;
import com.airhacks.entity.Item;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author pablobastidasv
 */
@Path("foo")
@Produces("application/json")
@Consumes("application/json")
public class FooResource {
    
    @Inject
    private FooManager fooManager;
    
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
    @Path("{name}")
    public Response find(String id){
        final String saludo = fooManager.saludar(id);

        final JsonObject retorno = Json.createObjectBuilder()
                .add("saludo", saludo)
                .build();

        return Response
                .ok(retorno)
                .build();
    }
}
