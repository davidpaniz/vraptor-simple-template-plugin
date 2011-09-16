VRaptor 3 Simple Template Plugin
=========

This is a plugin to add layout around your views, simple like Ruby on Rails do, using ONLY JSP without freemarker, velocity, etc.

Configuring the plugin in your project
========================
First of all configure the VRaptor classpath package scan adding the simple-template-plugin to
your web.xml as follows:


    <context-param>
      <param-name>br.com.caelum.vraptor.packages</param-name>
      <param-value>br.com.caelum.vraptor.plugin.simpletemplate</param-value>
    </context-param>

Configuring your Controller
--------
The plugin doesn't change any default beheavor of VRaptor. You can add it to a legacy code and 
start to use templates without break any working view.
To configure the controller all you need to do is annotate the controller with @Layout

<code>    
    @Layout
    @Resource
    public class PersonController {
      //your actions here
    }
</code>

Writting your layout:
--------
The plugin you search your layout file: WEB-INF/jsp/layouts/{CONTROLLER_NAME}.jsp. In the examlpe below the
plugin would search for the file WEB-INF/jsp/layouts/person.jsp. If the file doesn't exist the plugin will search 
for a WEB-INF/jsp/layouts/application.jsp.
Inside your layout file you don't hava to configure nothing. All you need is add a <jsp:include page="${renderBody}" /> 
where you want the local view will be displayed.
Example:
<code>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Simple Template Plugin</title>
    </head>
    <body>
	    Always before the local view
    	
	    <jsp:include page="${renderBody}" />
    	
	    Always after the local view
    </body>
    </html>
</code>
