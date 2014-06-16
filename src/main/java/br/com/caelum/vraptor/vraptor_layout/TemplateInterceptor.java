package br.com.caelum.vraptor.vraptor_layout;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;


/**
 * Interceptor that verifies if the Controller or package is annotated with @Layout.
 * 
 * @author David Paniz
 * @author Erich Egert
 */


@Intercepts
@RequestScoped
public class TemplateInterceptor {

	@Inject
	private LayoutHandler handler;
	
	@Inject
    private HttpServletRequest request;
	
	private ControllerMethod method;
	
	
	@Accepts
	public boolean accepts(ControllerMethod method) {
		this.method = method;
		Class<?> type = method.getController().getType();
		return type.getAnnotation(Layout.class) != null
				|| type.getPackage().getAnnotation(Layout.class) != null;
	}
	
	 
   
	@AroundCall
	public void intercept(SimpleInterceptorStack stack) {
    	
		Class<?> controllerType = method.getController().getType();
		Layout layout = controllerType.getAnnotation(Layout.class);
		if (layout == null) {
			layout = controllerType.getPackage().getAnnotation(Layout.class);
		}

		String layoutName = layout.value();
		
		handler.setLayoutName(layoutName);
		stack.next();
	}

}
