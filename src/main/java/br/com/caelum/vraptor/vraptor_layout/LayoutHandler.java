package br.com.caelum.vraptor.vraptor_layout;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class LayoutHandler {
	private String layoutName;
	
	public LayoutHandler() {
		
	}

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
