package dev.gabriel.liferay.drools.application;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dev.gabriel.liferay.drools.model.Account;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author gabriel
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/drools",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Drools.Rest",
			"auth.verifier.guest.allowed=true",
			"liferay.access.control.disable=true"
	},
	service = Application.class
)
public class DroolsAppApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/getRequiredDocuments")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getRequiredDocuments(Account account) {

		return Response.ok().entity(account.getNecessaryDocuments()).build();

	}

}