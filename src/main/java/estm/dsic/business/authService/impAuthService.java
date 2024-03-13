package estm.dsic.business.authService;

import estm.dsic.beans.User;
import estm.dsic.business.authService.interfaces.AuthService;
import estm.dsic.controllers.userController.ImpUserController;
import estm.dsic.dao.user.ImpUserDoa;
import jakarta.inject.Inject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

@Transactional
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
                HttpSession session = request.getSession();
                session.setAttribute("user", authUser);
                // Create a new cookie with the necessary information
                NewCookie cookie = new NewCookie("userID", authUser.getId() + "");
                // Add the cookie to the response
                authUser.setPassword(null);
                return Response.ok(authUser).cookie(cookie).entity(authUser).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
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
            return Response.ok(newUser).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
