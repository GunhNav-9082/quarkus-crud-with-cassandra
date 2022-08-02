package hung.nguyen.itc.controller;

import hung.nguyen.itc.dto.UserDto;
import hung.nguyen.itc.entity.User;
import hung.nguyen.itc.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService service;

    @GET
    public Response listUser(){
        List<User> users = service.userList();
        return Response.ok(users).build();
    }

    @POST
    public Response saveUser(UserDto userDto){
        User user = service.saveUser(userDto);
        return Response.ok(user).status(200).build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id , UserDto userDto){
        service.updateUser(id,userDto);
        return Response.ok(204).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id){
        service.removeUser(id);
        return Response.ok(205).build();
    }
}
