package net.weg.eng.springapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSession;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;

public class SpringSessionEmulator {

	private RequestAttributes attributes;

	private HttpSession session;

	// TODO: REMOVER MOCK, CRIAR UMA SESSION DA MANEIRA CORRETA
	public void emulate() {
		setSession(new MockHttpSession());// StandardSessionFacade (session);
		RequestContextListener listener = new RequestContextListener();
		ServletContext context = new MockServletContext();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(getSession());
		ServletRequestEvent event = new ServletRequestEvent(context, request);
		setAttributes(RequestContextHolder.getRequestAttributes());
		listener.requestInitialized(event);
	}

	public void emulateRequestDestroyed() {
		RequestContextListener listener = new RequestContextListener();
		ServletContext context = new MockServletContext();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(getSession());
		ServletRequestEvent event = new ServletRequestEvent(context, request);
		listener.requestDestroyed(event);
	}

	public void stopEmulation() {
		emulateRequestDestroyed();
		getSession().invalidate();

	}

	public RequestAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(RequestAttributes attributes) {
		this.attributes = attributes;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
}
