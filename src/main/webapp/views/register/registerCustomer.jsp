<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="register/customer/register.do" modelAttribute="customer">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.authorities" />
	
	
	<fieldset>
	
		<legend>
			<spring:message code="register.useraccount" />
		</legend>	

			
			<form:label path="userAccount.username">
			<spring:message code="register.username" />
			</form:label>
			<form:input path="userAccount.username" />
			<form:errors class="error" path="userAccount.username" />
			<br />
		
			<form:label path="userAccount.password">
				<spring:message code="register.password" />
			</form:label>
			<form:password path="userAccount.password" />
			<form:errors class="error" path="userAccount.password" />
			<br />
			
			<form:label path="userAccount.password2">
				<spring:message code="register.password2" />
			</form:label>
			<form:password path="userAccount.password2" />
			<form:errors class="error" path="userAccount.password2" />
			<br />
		
	</fieldset>
	<hr />
	
	<fieldset>
	
		<legend>
			<spring:message code="register.customer" />
		</legend>
		
		<form:label path="name">
				<spring:message code="register.name" />
			</form:label>
			<form:input path="name" />
			<form:errors class="error" path="name" />
			<br />
		
			<form:label path="surname">
				<spring:message code="register.surname" />
			</form:label>
			<form:input path="surname" />
			<form:errors class="error" path="surname" />
			<br />
		
			<form:label path="email">
				<spring:message code="register.email" />
			</form:label>
			<form:input path="email" />
			<form:errors class="error" path="email" />
			<br />
			
			<form:label path="phone">
				<spring:message code="register.phone" />
			</form:label>
			<form:input path="phone" />
			<form:errors class="error" path="phone" />
			<br />
				
	</fieldset>
	
	<fieldset>
	
		<legend>
			<spring:message code="register.center" />
		</legend>
		<form:label path="cif">
				<spring:message code="register.cif" />
			</form:label>
			<form:input path="cif" />
			<form:errors class="error" path="cif" />
			<br />
			
			<form:label path="street">
				<spring:message code="register.street" />
			</form:label>
			<form:input path="street" />
			<form:errors class="error" path="street" />
			<br />
			
			<form:label path="zip">
				<spring:message code="register.zip" />
			</form:label>
			<form:input path="zip" />
			<form:errors class="error" path="zip" />
			<br />
			
			<form:label path="provinceCenter">
				<spring:message code="register.provinceCenter" />
			</form:label>
			<form:input path="provinceCenter" />
			<form:errors class="error" path="provinceCenter" />
			<br />
			
			<form:label path="city">
				<spring:message code="register.city" />
			</form:label>
			<form:input path="city" />
			<form:errors class="error" path="city" />
			<br />
			
			<form:label path="nameCenter">
				<spring:message code="register.nameCenter" />
			</form:label>
			<form:input path="nameCenter" />
			<form:errors class="error" path="nameCenter" />
			<br />
			
			<form:label path="phoneCenter">
				<spring:message code="register.phoneCenter" />
			</form:label>
			<form:input path="phoneCenter" />
			<form:errors class="error" path="phoneCenter" />
			<br />
			
			<form:label path="emailCenter">
				<spring:message code="register.emailCenter" />
			</form:label>
			<form:input path="phone" />
			<form:errors class="emailCenter" path="emailCenter" />
			<br />
			
			<form:label path="web">
				<spring:message code="register.web" />
			</form:label>
			<form:input path="web" />
			<form:errors class="error" path="web" />
			<br />

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