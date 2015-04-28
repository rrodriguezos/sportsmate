<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<br>

<form:form action="customer/edit.do" modelAttribute="customerForm" class="form-horizontal">

	<form:hidden path="id"/>
	
	<jstl:if test="${customerForm.id != 0 }">
		<h1><spring:message code="customer.registerEdit" /></h1>
	</jstl:if>
	<jstl:if test="${customerForm.id == 0 }">
		<h1><spring:message code="customer.register" /></h1>
	</jstl:if>

	<fieldset>
		<legend>
			<spring:message code="customer.useraccount" />
		</legend>
		<acme:textbox code="customer.username" path="username" />
		<acme:password code="customer.password" path="password" />
		<acme:password code="customer.password2" path="password2" />
	</fieldset>
	
	<br>
	
	<fieldset>
		<legend>
			<spring:message code="customer.user" />
		</legend>
		<acme:textbox code="customer.name" path="name" />
		<acme:textbox code="customer.surname" path="surname" />
		<acme:textbox code="customer.email" path="email" />
		<acme:textbox code="customer.phone" path="phone" />
		</fieldset>
		
	<br>
	
		<fieldset>
		<legend>
			<spring:message code="customer.center" />
		</legend>
		
	
		<acme:textbox code="customer.cif" path="cif" />
		<acme:textbox code="customer.street" path="street" />
		<acme:textbox code="customer.zip" path="zip" />
		<acme:textbox code="customer.provinceCenter" path="provinceCenter" />
		<acme:textbox code="customer.city" path="city" />
		<acme:textbox code="customer.nameCenter" path="nameCenter" />
		<acme:textbox code="customer.phoneCenter" path="phoneCenter" />
		<acme:textbox code="customer.emailCenter" path="emailCenter" />
		<acme:textbox code="customer.web" path="web" />
	
	</fieldset>
	
<!-- IGNORE THE WARNING, IT'S BEACUSE THE <FORM> -->	
<div class="col-xs-12">

	<br>	
	<b><spring:message code="customer.terms" /></b>
	<br>
		
	<form:checkbox path="terms" /><a href="conditions/laws.do" target="_blank"><spring:message code="customer.conditions"/></a> 

	<br><br>
	
	<acme:submit name="save" code="customer.save" />
	<acme:cancel url="welcome/index.do" code="customer.cancel" />
	
</form:form>
</div>