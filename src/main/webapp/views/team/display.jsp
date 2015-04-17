<%--
 * display.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="teamForm">

	<form:hidden path="id" />

	<b><spring:message code="team.name" />: </b>
	<form:input path="name" readonly="true" />
	<br />
	<br />

	<b><spring:message code="team.captain" />: </b>
	<form:input path="captain.email" readonly="true" />
	<br />
	<br />

	<b><spring:message code="team.maxNumber" />: </b>
	<form:input path="maxNumber" readonly="true" />
	<br />
	<br />

	<h1>
		<spring:message code="team.members" />
	</h1>
	
	<div class="table-responsive">
	<display:table name="users" id="row" pagesize="5" class="table table-bordered table-hover">

		<spring:message code="team.user.name" var="nameHeader" />
		<display:column property="name" title="${nameHeader}" />

		<spring:message code="team.user.surname" var="surnameHeader" />
		<display:column property="surname" title="${surnameHeader}" />

		<spring:message code="team.user.email" var="emailHeader" />
		<display:column property="email" title="${nameHeader}" />

		<spring:message code="team.user.phone" var="phoneHeader" />
		<display:column property="phone" title="${phoneHeader}" />

	</display:table>
	</div>
	
	<security:authorize access="hasRole('USER')">
		<acme:cancel code="team.back" url="team/user/list.do" />
		<jstl:if test="${principal.teamsCreated.contains(team)}">
			<input type="button" class="btn btn-md btn-success" name="edit"
				value="<spring:message code="team.edit"/>"
				onclick="javascript: window.location.replace('team/user/edit.do?teamId= ${teamForm.id}')" />

			<jstl:if test="${teamForm.id != 0 && users.size()==1}">
				<input type="submit" class="btn btn-md btn-danger" name="delete"
					value="<spring:message code="team.delete" />"
					onclick="return confirm('<spring:message code="team.confirm.delete" />')" />
			</jstl:if>
		</jstl:if>
	</security:authorize>

</form:form>


