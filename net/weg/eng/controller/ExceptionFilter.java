package net.weg.eng.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.weg.eng.MaestroException;
import net.weg.eng.bean.rule.MessageError;
import net.weg.iceberg.util.Log;

public class ExceptionFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (MessageError e) {
			Log.getLogger(e.getClass()).info(e.getMessage());
		} catch (MaestroException e) {
			throw new MaestroException(e);
		} 
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
}
 