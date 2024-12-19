package edu.upc.dsa.services;

import edu.upc.dsa.models.Badge;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/badges", description = "Service to manage user badges")
@Path("/badges")
public class BadgesService {

    @GET
    @Path("/{userID}")
    @ApiOperation(value = "Get badges for a user", notes = "Returns a list of badges for a given user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Badge.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBadges(@PathParam("userID") String userID) {
        // Dummy implementation
        System.out.println("Searching badges for user: " + userID);

        List<Badge> badges = new ArrayList<>();
        badges.add(new Badge("Badge 1", "'https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png"));
        return Response.ok(badges).build();
    }
}
