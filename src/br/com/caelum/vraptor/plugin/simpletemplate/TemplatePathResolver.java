package br.com.caelum.vraptor.plugin.simpletemplate;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.DefaultPathResolver;

/**
 * The template path resolver uses the the default path resolver but wraps it
 * with a layout if the interceptor creates one.
 * 
 * @author David Paniz
 * @author Erich Egert
 */
@RequestScoped
@Component
public class TemplatePathResolver extends DefaultPathResolver {

	
	private final LayoutHandler handler;
	private HttpServletRequest request;
	private final JspHandler jspHandler;

	public TemplatePathResolver(FormatResolver resolver, LayoutHandler handler,
			HttpServletRequest request, JspHandler jspHandler) {
		super(resolver);
		this.handler = handler;
		this.request = request;
		this.jspHandler = jspHandler;
	}

	@Override
	public String pathFor(ResourceMethod method) {
		String originalPath = super.pathFor(method);
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
		return super.getPrefix() + "layouts/" + layoutName + "."
				+ super.getExtension();
	}

	private String extractLayoutName(ResourceMethod method) {
		String layoutName = handler.getLayoutName();
		if (layoutName.isEmpty()) {
			layoutName = extractControllerFromName(method.getResource()
					.getType().getSimpleName());
		}

		return layoutName;
	}
}
