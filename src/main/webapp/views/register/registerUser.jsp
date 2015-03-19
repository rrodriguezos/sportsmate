<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="register/supplier/register.do" modelAttribute="${user}Form">

	<fieldset>
	
		<legend>
			<spring:message code="register.useraccount" />
		</legend>
		<acme:textbox code="register.username" path="username" />
		<acme:password code="register.password" path="password" />
		<acme:password code="register.password2" path="password2" />
		
	</fieldset>
	<hr />
	
	<fieldset>
	
		<legend>
			<spring:message code="register.user" />
		</legend>
		<acme:textbox code="register.name" path="name" />
		<acme:textbox code="register.surname" path="surname" />
		<acme:textbox code="register.email" path="email" />
		<acme:textbox code="register.phone" path="phone" />
		
	</fieldset>


	<jstl:if test="${showError == true}">
		<div class="error">
			<spring:message code="register.failed" />
		</div>
	</jstl:if>

	<input type="submit" name="save"
		value="<spring:message code="register.save" />" />
		&nbsp;
	
	<input type="button" name="cancel"
		value="<spring:message code="register.cancel" />"
		onclick="javascript: relativeRedir('');" />
	<br />

</form:form>