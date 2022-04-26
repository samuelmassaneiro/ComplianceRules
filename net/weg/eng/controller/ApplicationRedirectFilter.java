package net.weg.eng.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@WebFilter("/beta/*")
@Service
public class ApplicationRedirectFilter implements Filter {

	private static final String URL_POST = "#urlPost";
	private static final String SERVER_ERROR_MESSAGE = "Remote server of data: #urlPost is down! Remove /beta from URL!";
	private static final String TEXT_HTML_CHARSET_UTF_8 = "text/html;charset=UTF-8";
	private static final String GET = "GET";
	private static final String HTML = "html";
	private static final String DOT = ".";
	private static final String BAR = "/";
	private static final String PUBLIC = "";
	private static final String MAESTRO_BETA = "/maestro/beta";
	private static final int _1 = 1;
	private static final int _200 = 200;
	//private static final String SERVER = "http://brjgs816.weg.net:3000";
	private static final String QA_SERVER = "http://localhost:3000";

	@Override
	public void destroy() { }

	@Override
	@SuppressWarnings("PMD")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String urlRequest = httpServletRequest.getRequestURI();
		urlRequest = urlRequest.replace(MAESTRO_BETA, PUBLIC);
		String urlPost = "";
		urlPost = QA_SERVER + urlRequest;

		try {
			
			String urlSplit[] = urlPost.split(BAR);
			String dataType = urlSplit[urlSplit.length - _1];
			if(dataType.lastIndexOf(DOT) >= 0) {
				dataType = dataType.substring(dataType.indexOf(DOT) + _1);
			} else {
				dataType= HTML;
			}
			
			if(dataType.equals(HTML)) {
				URL url = new URL(urlPost);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod(GET);
				if(connection.getResponseCode() == _200) {
				   InputStream is = connection.getInputStream();
					httpServletResponse.setContentType(TEXT_HTML_CHARSET_UTF_8);
					sendResponseData(httpServletResponse, is);
				} else {
					httpServletResponse.getWriter().print(SERVER_ERROR_MESSAGE.replace(URL_POST, urlPost));
				}
			} else {
				httpServletResponse.sendRedirect(urlPost);
			}
			
		} catch (ConnectException ex) {
			httpServletResponse.getWriter().print(SERVER_ERROR_MESSAGE.replace(URL_POST, urlPost));
		}
		
	}

	private void sendResponseData(HttpServletResponse httpServletResponse, InputStream is) throws IOException {
		String responseRemote = IOUtils.toString(is);
		httpServletResponse.getWriter().print(responseRemote);
	}

	@Override
	public void init(FilterConfig fc) throws ServletException { }

	public void unauthorizedAction(ServletResponse response) throws IOException {
		((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnauthorizedIcebergException");
	}
	
}
