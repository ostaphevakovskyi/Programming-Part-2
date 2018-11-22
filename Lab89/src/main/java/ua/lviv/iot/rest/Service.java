package ua.lviv.iot.rest;

import ua.lviv.iot.Clothes;
import ua.lviv.iot.Material;
import ua.lviv.iot.Size;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;

@Path("/clothes")
public class Service {
    private static Map<Integer, Clothes> clothesMap = new HashMap<>();

    @GET
    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Clothes getClothes(final @PathParam("id") Integer id) {
        clothesMap.put(222, new Clothes(222, 111, Size.LARGE, Material.COTTON));
        return clothesMap.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClothes(final Clothes clothes) {
        clothesMap.put(clothes.getId(), clothes);
        return Response.status(200).entity("Good").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response replaceClothes(final Clothes clothes) {
        clothesMap.replace(clothes.getId(), clothes);
        return Response.status(200).entity("Good").build();
    }

    @DELETE
    @Path("{id}/")
    public Response deleteClothes(final @PathParam("id") Integer id) {
        clothesMap.remove(id);
        return Response.status(200).entity("Good").build();
    }

}
