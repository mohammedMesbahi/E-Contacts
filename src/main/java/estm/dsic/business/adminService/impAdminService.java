package estm.dsic.business.adminService;

import estm.dsic.beans.User;
import estm.dsic.controllers.userController.ImpUserController;
import estm.dsic.dto.UserDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/admin")
public class impAdminService implements AdminService{
    @Inject
    ImpUserController userController;

    @Path("/verify/user/{id}")
    @PUT
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response verify(@PathParam("id") Long id) {
        try {
            User verifiedUser = userController.verify(id);
            if (verifiedUser == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
            }
            UserDTO userDTO = new UserDTO(verifiedUser);
            return Response.ok(userDTO).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
    @Path("/unverify/user/{id}")
    @PUT
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response unVerify(@PathParam("id") Long id) {
        try {
            User unVerifiedUser = userController.unVerify(id);
            if (unVerifiedUser == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
            }
            UserDTO userDTO = new UserDTO(unVerifiedUser);
            return Response.ok(userDTO).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }



    @Override
    @Path("/suspend/user/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response suspend(@PathParam("id") Long id) {
        try {
            User suspendedUser = userController.suspend(id);
            if (suspendedUser == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
            }
            UserDTO userDTO = new UserDTO(suspendedUser);
            return Response.ok(userDTO).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();

        }
    }

    @Override
    @Path("/unsuspend/user/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response unSuspend(@PathParam("id") Long id) {
        try {
            User unSuspendedUser = userController.unSuspen(id);
            if (unSuspendedUser == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
            }
            UserDTO userDTO = new UserDTO(unSuspendedUser);
            return Response.ok(userDTO).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    @Path("/user/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        try {
            User deletedUser = userController.delete(id);
            if (deletedUser == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
            }
            UserDTO userDTO = new UserDTO(deletedUser);
            return Response.ok(userDTO).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    @Path("/users")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        try {
            List<User> users = userController.getAll();
            return Response.ok().entity(users).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
