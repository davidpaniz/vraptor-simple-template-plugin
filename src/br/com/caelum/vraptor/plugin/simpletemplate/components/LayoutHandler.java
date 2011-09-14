package br.com.caelum.vraptor.plugin.simpletemplate.components;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class LayoutHandler {
	private String layoutName;

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public boolean isUsingLayout() {
		return layoutName != null;
	}
}
