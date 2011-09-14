/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.caelum.vraptor.plugin.simpletemplate.components;

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
