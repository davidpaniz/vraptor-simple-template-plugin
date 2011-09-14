package br.com.caelum.vraptor.plugin.simpletemplate.interceptor;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.plugin.simpletemplate.annotation.Layout;
import br.com.caelum.vraptor.plugin.simpletemplate.components.LayoutHandler;
import br.com.caelum.vraptor.resource.ResourceMethod;


/**
 * Interceptor that verifies if the Controller or package is annotated with @Layout.
 * 
 * @author David Paniz
 * @author Erich Egert
 */

@Intercepts
public class TemplateInterceptor implements Interceptor {

	private final LayoutHandler handler;

	public TemplateInterceptor(LayoutHandler handler) {
		this.handler = handler;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		Class<?> type = method.getResource().getType();
		return type.getAnnotation(Layout.class) != null
				|| type.getPackage().getAnnotation(Layout.class) != null;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {

		Class<?> controllerType = method.getResource().getType();
		Layout layout = controllerType.getAnnotation(Layout.class);
		if (layout == null) {
			layout = controllerType.getPackage().getAnnotation(Layout.class);
		}

		String layoutName = layout.value();

		handler.setLayoutName(layoutName);
		stack.next(method, resourceInstance);
	}

}
