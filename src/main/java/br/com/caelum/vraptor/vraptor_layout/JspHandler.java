package br.com.caelum.vraptor.vraptor_layout;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;

/**
 * It use the servletContext to verify if the layout jsp exists.
 * 
 * @author David Paniz
 * @author Erich Egert
 */

@ApplicationScoped
public class JspHandler {
	
	@Inject
	private ServletContext servletContext;

	private Map<String, Boolean> cache = new HashMap<String, Boolean>();
		
	public JspHandler(){
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
