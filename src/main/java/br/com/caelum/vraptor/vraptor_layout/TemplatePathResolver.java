package br.com.caelum.vraptor.vraptor_layout;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.view.DefaultPathResolver;

/**
 * The template path resolver uses the the default path resolver but wraps it
 * with a layout if the interceptor creates one.
 * 
 * @author David Paniz
 * @author Erich Egert
 */

@Specializes
@RequestScoped
public class TemplatePathResolver extends DefaultPathResolver {

	
	@Inject
	private LayoutHandler handler;
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private JspHandler jspHandler;
	
	@Inject
	private FormatResolver resolver;
	
	public TemplatePathResolver() {
	
	}

	@Override
	public String pathFor(ControllerMethod method) {
		DefaultPathResolver defaultPathResolver = new DefaultPathResolver(resolver);
		String originalPath = defaultPathResolver.pathFor(method);	
		
		if (!handler.isUsingLayout()) {
			return originalPath;
		}

		request.setAttribute("renderBody", originalPath);

		String layoutName = extractLayoutName(method);

		String layoutFileName = getLayoutFileName(layoutName);

		if (!jspHandler.exists(layoutFileName)) {
			layoutFileName = getLayoutFileName("application");
		}
	
		return layoutFileName;
	}

	private String getLayoutFileName(String layoutName) {
		return super.getPrefix() + "layouts/" + layoutName + "." + super.getExtension();
	}

	private String extractLayoutName(ControllerMethod method) {
		String layoutName = handler.getLayoutName();
		if (layoutName.isEmpty()) { //se o nome não for especificado ele usa o nome do controller
			layoutName = extractControllerFromName(method.getController()
					.getType().getSimpleName());
		}

		return layoutName;
	}
}
