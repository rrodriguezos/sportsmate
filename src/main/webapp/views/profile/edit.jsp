<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<security:authorize access="hasRole('CUSTOMER')">

<form:form action="profile/customer/edit.do" modelAttribute="customerForm">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="events" />
	<form:hidden path="tournaments" />
	<form:hidden path="invoices" />
	
	
	<fieldset>
			<legend>
				<spring:message code="customer.useraccount" />
			</legend>
			<acme:textbox code="useraccount.name" path="username" />
			<acme:password code="useraccount.password" path="password" />
			<acme:password code="useraccount.password2" path="password2" />
		</fieldset>
		<fieldset>
			<legend>
				<spring:message code="customer.actor" />
			</legend>
		<acme:textbox code="actor.name" path="name" />
		<acme:textbox code="actor.surname" path="surname" />
		<acme:textbox code="actor.email" path="email" />
		<acme:textbox code="actor.phone" path="phone" />
		</fieldset>
		<fieldset>
			<legend>
				<spring:message code="customer.center" />
			</legend>
		<acme:textbox code="center.cif" path="cif" />
		<acme:textbox code="center.street" path="street" />
		<acme:textbox code="center.zip" path="zip" />
		<acme:textbox code="center.provinceCenter" path="provinceCenter" />
		<acme:textbox code="center.city" path="city" />
		<acme:textbox code="center.nameCenter" path="nameCenter" />
		<acme:textbox code="center.phoneCenter" path="phoneCenter" />
		<acme:textbox code="center.emailCenter" path="emailCenter" />
		<acme:textbox code="center.web" path="web" />
	
		</fieldset>

	
		<acme:submit name="save" code="customer.save" />&nbsp;
		
			<input type="submit" name="delete"
				value="<spring:message code="customer.delete"/>"
				onclick="return confirm('<spring:message code="customer.confirm.delete" />')" />&nbsp;
		
		<acme:cancel url="welcome/index.do" code="customer.cancel" />&nbsp;
</form:form>		
</security:authorize>

<security:authorize access="hasRole('USER')">

<form:form action="profile/user/edit.do" modelAttribute="userForm">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="events" />
	<form:hidden path="tournaments" />
	<form:hidden path="teams" />
	
	<fieldset>
		<legend>
			<spring:message code="user.useraccount" />
		</legend>
		<acme:textbox code="actor.name" path="name" />
		<acme:password code="actor.password" path="password" />
		<acme:password code="actor.password2" path="password2" />
	</fieldset>
	<hr />

		<legend>
			<spring:message code="register.user" />
		</legend>
		<acme:textbox code="actor.name" path="name" />
		<acme:textbox code="actor.surname" path="surname" />
		<acme:textbox code="actor.email" path="email" />
		<acme:textbox code="actor.phone" path="phone" />
	
			
		
		<acme:submit name="save" code="user.save" />&nbsp;
		
			<input type="submit" name="delete"
				value="<spring:message code="user.delete"/>"
				onclick="return confirm('<spring:message code="user.confirm.delete" />')" />&nbsp;
		
		<acme:cancel url="welcome/index.do" code="user.cancel" />&nbsp;
	
	

</form:form>


</security:authorize>
