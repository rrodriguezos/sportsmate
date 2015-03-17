<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="register/supplier/register.do" modelAttribute="supplier">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.authorities" />


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