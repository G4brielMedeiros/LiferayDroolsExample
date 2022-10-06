package dev.gabriel.liferay.drools.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.resource.StringResourceRetriever;
import com.liferay.portal.rules.engine.*;
import dev.gabriel.liferay.drools.model.Account;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
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
        return Collections.singleton(this);
    }

    @GET
    @Path("/getRequiredDocuments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRequiredDocuments(Account account) throws RulesEngineException {

        _loadRules("account", "rules/account_rules.drl");

        // This is how you fire the rules for a given entity.
        List<Fact<?>> facts = new ArrayList<>();
        facts.add(new Fact<>("account", account));
        _rulesEngine.execute("account", facts);

        return Response.ok().entity(account.getRequiredDocuments()).build();

    }

    private void _loadRules(String domain, String filePath) throws RulesEngineException {
        if (!_rulesEngine.containsRuleDomain(domain)) {
			System.out.println("Applying rules...");

            // This is how you apply rules from a String representation of a DRL file into the RulesEngine.
            String applicantRules = _loadRuleFromResource(filePath); // helper method that reads a file as a string
            RulesResourceRetriever rulesResourceRetriever =
                    new RulesResourceRetriever(
                            new StringResourceRetriever(applicantRules),
                            String.valueOf(RulesLanguage.DROOLS_RULE_LANGUAGE));

            _rulesEngine.update(domain, rulesResourceRetriever);
        }
    }

    private InputStream _getFileAsIOStream(final String fileName) {
        InputStream ioStream = getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    private String _getFileContentFromInputStream(InputStream is) {

        StringBuilder content = new StringBuilder();

        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            is.close();
        } catch (IOException ioException) {
            _log.error(ioException.getMessage());
            _log.error("Could not read InputStream");
        }

        return content.toString();
    }

    private String _loadRuleFromResource(String fileName) {
        return _getFileContentFromInputStream(_getFileAsIOStream(fileName));
    }

    @Reference
    RulesEngine _rulesEngine;

    private static final Log _log = LogFactoryUtil.getLog(DroolsAppApplication.class);

}