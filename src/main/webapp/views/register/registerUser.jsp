<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="register/user/register.do" modelAttribute="user">


		
		

	
	
		<legend>
			<spring:message code="register.useraccount" />
		</legend>
		<acme:textbox code="register.username" path="username" />
		<acme:password code="register.password" path="password" />
		<acme:password code="register.password2" path="password2" />
		
	
	<hr />
	

		<legend>
			<spring:message code="register.user" />
		</legend>
		<acme:textbox code="register.name" path="name" />
		<acme:textbox code="register.surname" path="surname" />
		<acme:textbox code="register.email" path="email" />
		<acme:textbox code="register.phone" path="phone" />
		
	


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