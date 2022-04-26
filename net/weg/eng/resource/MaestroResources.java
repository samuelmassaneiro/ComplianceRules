package net.weg.eng.resource;

import net.weg.maestro.plm.rfc.SapRFCConnector;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/resources/utils")
@Controller
public class MaestroResources {

	@GET
	@Path("/rfc/active")
	@Produces("text/plain")
	public String getRfcActive(@QueryParam("active") Boolean toActivate) {
		if (toActivate != null) {
			SapRFCConnector.getInstance().setActive(toActivate);
			if (toActivate && !SapRFCConnector.getInstance().isActive()) {
				return "Error on activating SAP RFC. The communication can not be established";
			}
		}
		return String.valueOf(SapRFCConnector.getInstance().isActive());
	}

}
