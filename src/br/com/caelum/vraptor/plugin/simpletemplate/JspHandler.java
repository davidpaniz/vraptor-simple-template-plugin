package br.com.caelum.vraptor.plugin.simpletemplate;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

/**
 * It use the servletContext to verify if the layout jsp exists.
 * 
 * @author David Paniz
 * @author Erich Egert
 */

@Component
@ApplicationScoped
public class JspHandler {

	private final ServletContext servletContext;

	private Map<String, Boolean> cache = new HashMap<String, Boolean>();

	private JspHandler(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public boolean exists(String layoutFileName) {
		Boolean cachedValue = cache.get(layoutFileName);
		if (cachedValue == null) {
			cachedValue = new File(servletContext.getRealPath(layoutFileName))
					.exists();

			cache.put(layoutFileName, cachedValue);
		}

		return cachedValue;
	}

}
