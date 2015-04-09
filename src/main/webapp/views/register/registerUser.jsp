<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="register/registerUser.do" modelAttribute="userForm">


	
	<fieldset>
		<legend>
			<spring:message code="register.useraccount" />
		</legend>
		<acme:textbox code="register.username" path="username" />
		<acme:password code="register.password" path="password" />
		<acme:password code="register.password2" path="password2" />
	</fieldset>
	<hr />

		<legend>
			<spring:message code="register.user" />
		</legend>
		<acme:textbox code="register.name" path="name" />
		<acme:textbox code="register.surname" path="surname" />
		<acme:textbox code="register.email" path="email" />
		<acme:textbox code="register.phone" path="phone" />
		
		<hr />	
	
	<br />
	<b><spring:message code="actor.terms" /></b>
	<br />	
	<form:checkbox path="terms" /><a href="conditions/laws.do" target="_blank"><spring:message code="actor.conditions"/></a> 
	<br />	
	
	<br />
	<acme:submit name="save" code="register.save" />
	<acme:cancel url="welcome/index.do" code="register.cancel" />

</form:form>