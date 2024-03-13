package estm.dsic.business.authService;

import estm.dsic.beans.User;
import estm.dsic.business.authService.interfaces.AuthService;
import estm.dsic.controllers.userController.ImpUserController;
import estm.dsic.dto.UserDTO;
import jakarta.inject.Inject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jdk.jpackage.internal.Log;

import java.io.Console;

@Path("/auth")
public class impAuthService implements AuthService {
    @Inject
    ImpUserController userController;
    @Inject
    HttpServletRequest request;

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        try {
            User authUser = userController.find(user.getLogin(), user.getPassword());
            if (authUser != null) {
                if (authUser.isSuspended()) {
                    return Response.status(Response.Status.UNAUTHORIZED.getStatusCode(), "the account is suspend")
                            .entity("the account is suspend").build();
                } else if (!authUser.isVerified()) {
                    return Response.status(Response.Status.UNAUTHORIZED.getStatusCode(), "the account is not verified")
                            .entity("the account is not verified").build();
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("authUser", authUser);
                    // Create a new cookie with the necessary information
                    NewCookie cookie = new NewCookie("userID", authUser.getId() +
                            "", "/", "", "JWT", 3600, false, false);
                    // Add the cookie to the response
                    UserDTO userDTO = new UserDTO(authUser);
                    return Response.ok(authUser).cookie(cookie).entity(userDTO).build();
                }
            } else {
                return Response.status(Response.Status.UNAUTHORIZED.getStatusCode(), "login or password is incorrect")
                        .entity("login or password is incorrect").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Path("/signup")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response signup(User user) {

        try {
            User newUser = userController.create(user);
            if (newUser == null) {
                return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Error while creating user")
                        .entity("Error while creating user").build();
            }
            return Response.ok(new UserDTO(newUser)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Path("/logout")
    @POST
    @Override
    public Response logout() {
        try {
            request.getSession().invalidate();
            /* destroy cookie*/
            NewCookie cookie = new NewCookie("userID", "", "/", "", "JWT",
                    0, false, false);
            return Response.ok()
                    .cookie(cookie)
                    .build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
