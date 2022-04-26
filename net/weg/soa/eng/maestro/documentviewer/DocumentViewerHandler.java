package net.weg.soa.eng.maestro.documentviewer;

import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class DocumentViewerHandler implements SOAPHandler<SOAPMessageContext> {

	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	public void close(MessageContext arg0) {
		

	}

	public boolean handleFault(SOAPMessageContext arg0) {

		return false;
	}

	public boolean handleMessage(SOAPMessageContext arg0) {
		return true;

	}

}
