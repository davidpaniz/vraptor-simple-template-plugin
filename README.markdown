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
The plugin doesn't change any default behavior of VRaptor. You can add it to a legacy code and 
start to use templates without break any working view.
To configure the controller all you need to do is annotate the controller with @Layout

<code>    
    @Layout
    @Resource
    public class PersonController {
      //your actions here
    }
</code>

Writing your layout:
--------
The plugin you search your layout file: WEB-INF/jsp/layouts/{CONTROLLER_NAME}.jsp. In the examlpe below the
plugin would search for the file WEB-INF/jsp/layouts/person.jsp. If the file doesn't exist the plugin will search 
for a WEB-INF/jsp/layouts/application.jsp.
Inside your layout file you don't hava to configure nothing. All you need is add a <jsp:include page="${renderBody}" /> 
where you want the local view will be displayed.
Example:

<code>
    <body>
      Always before the local view
	    <jsp:include page="${renderBody}" />
	    Always after the local view
    </body>
</code>

Customizing the layout file name
--------
As you see above, the plugin will search for a file with the name of the controller or the application if
the specific file doesn't exist, but if you want, you may change the layout name using the value property
in the annotation:
<code>    
    @Layout("custom_layout")
    @Resource
    public class PersonController {
      //your actions here
    }
</code>
Now, the file searched will be WEB-INF/jsp/layouts/custom_layout.jsp


More configuration
--------
Because the plugin doesn't change the the default workingflow of the VRaptor you would have to annotate ALL
controller with @Layout. To simplify you configuration you may create a package-info.java and annotate the
package with @Layout and then, every controller in this package will be affected.
<code>    
	@Layout
	package app.controllers;

	import br.com.caelum.vraptor.plugin.simpletemplate.Layout;
</code>
If both, package AND class have the annotation, the class you override the configuration.

Full example
--------
You can see a project example here: http://github.com/davidpaniz/simple-template-plugin-example