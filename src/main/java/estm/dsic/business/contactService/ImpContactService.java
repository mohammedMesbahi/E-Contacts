package estm.dsic.business.contactService;

import estm.dsic.beans.Contact;
import estm.dsic.beans.User;
import estm.dsic.controllers.contactsController.ImpContactController;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.TransactionalException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLIntegrityConstraintViolationException;

@Path("/contact")
public class ImpContactService implements ContactService {
    @Inject
    ImpContactController contactController;

    @Inject
    HttpServletRequest request;

    @Override
    public Response get(Long t) {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(Contact contact) {
        try {
            /* bring the user from the session */
            User authenticatedUser = (User) request.getSession().getAttribute("authUser");
            if (authenticatedUser == null) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("You are not authorized to perform this action").build();
            }
            contact.setUser(authenticatedUser);
            Contact newContact = contactController.create(contact);
            newContact.setUser(null);
            return Response.status(Response.Status.CREATED).entity(newContact).build();
        }catch (TransactionalException e) {
            Throwable cause = e.getCause();
            while (cause != null && !(cause instanceof SQLIntegrityConstraintViolationException)) {
                cause = cause.getCause();
            }

            if (cause != null) {
                // It's an integrity constraint violation
                return Response.status(Response.Status.CONFLICT).entity(cause.getMessage()).build();
            } else {
                // It's another type of persistence exception
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }
        } catch (Exception e) {
            // Handle other non-database related exceptions
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Override
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Contact contact) {
        try {
            /* bring the user from the session */
            User authenticatedUser = (User) request.getSession().getAttribute("authUser");
            if (authenticatedUser == null) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("You are not authorized to perform this action").build();
            }
            Contact updatedContact = contactController.update(contact);
            if (updatedContact == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Contact not found").build();
            }
            updatedContact.setUser(null);
            return Response.status(Response.Status.OK).entity(updatedContact).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("name") String name) {
        try {
            /* bring the user from the session */
            User authenticatedUser = (User) request.getSession().getAttribute("authUser");
            if (authenticatedUser == null) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("You are not authorized to perform this action").build();
            }
            Contact deletedContact = contactController.delete(name);
            if (deletedContact == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Contact not found").build();
            }
            return Response.status(Response.Status.NO_CONTENT).entity(deletedContact).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response get() {
        try {
            /* bring the user from the session */
            User authenticatedUser = (User) request.getSession().getAttribute("authUser");
            if (authenticatedUser == null) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("You are not authorized to perform this action").build();
            }
            return Response.ok(contactController.get(authenticatedUser.getId())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
