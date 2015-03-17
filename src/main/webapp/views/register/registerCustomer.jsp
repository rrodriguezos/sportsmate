<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="register/customer/register.do" modelAttribute="${customerForm}Form">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.authorities" />
	
	
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
			<spring:message code="register.customer" />
		</legend>
		<acme:textbox code="register.name" path="name" />
		<acme:textbox code="register.surname" path="surname" />
		<acme:textbox code="register.email" path="email" />
		<acme:textbox code="register.phone" path="phone" />
		
	</fieldset>
	
	<fieldset>
	
		<legend>
			<spring:message code="register.center" />
		</legend>
		<acme:textbox code="register.cif" path="cif" />
		<acme:textbox code="register.street" path="street" />
		<acme:textbox code="register.zip" path="zip" />
		<acme:textbox code="register.provinceCenter" path="provinceCenter" />
		<acme:textbox code="register.city" path="city" />
		<acme:textbox code="register.nameCenter" path="nameCenter" />
		<acme:textbox code="register.phoneCenter" path="phoneCenter" />
		<acme:textbox code="register.emailCenter" path="emailCenter" />
		<acme:textbox code="register.web" path="web" />
		
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