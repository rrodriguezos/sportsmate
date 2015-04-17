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

<form:form action="${requestURI}" modelAttribute="tournamentForm">

	<b><spring:message code="tournament.title" />: </b>
	<jstl:out value="${tournament.title}" />
	<br />


	<b><spring:message code="tournament.creationMoment" />: </b>
	<fmt:formatDate value="${tournamentMoment}" pattern="dd/MM/yyyy" />
	<br />
	<b><spring:message code="tournament.advertised" />: </b>
	<jstl:out value="${tournament.advertised}" />
	<br />

	<b><spring:message code="tournament.prize" />: </b>
	<jstl:out value="${tournament.prize}" />
	<br />

	<b><spring:message code="tournament.startMoment" />: </b>
	<fmt:formatDate value="${tournament.startMoment}"
		pattern="dd/MM/yyyy HH:mm" />
	<br />

	<b><spring:message code="tournament.finishMoment" />: </b>
	<fmt:formatDate value="${tournament.finishMoment}"
		pattern="dd/MM/yyyy HH:mm" />
	<br />

	<b><spring:message code="tournament.numberOfTeams" />: </b>
	<jstl:out value="${tournament.numberOfTeams}" />
	<br />

	<b><spring:message code="tournament.sport" />: </b>
	<jstl:out value="${tournament.sport}" />
	<br />

	<b><spring:message code="tournament.place" />: </b>
	<jstl:out value="${tournament.place}" />
	<br />

	<b><spring:message code="tournament.description" />: </b>
	<jstl:out value="${tournament.description}" />
	<br />

	<h1>
		<spring:message code="tournament.teams" />
	</h1>
	<display:table name="teams" id="row" pagesize="5" class="displaytag">

		<spring:message code="tournament.team.name" var="nameHeader" />
		<display:column property="name" title="${nameHeader}" />

		<spring:message code="tournament.team.maxNumber" var="maxNumberHeader" />
		<display:column property="maxNumber" title="${maxNumberHeader}" />

	</display:table>

	<h1>
		<spring:message code="tournament.matches" />
	</h1>
	<display:table name="matches" id="row" pagesize="5" class="displaytag">

		<spring:message code="tournament.match.title" var="titleHeader" />
		<display:column property="title" title="${titleHeader}" />

		<spring:message code="tournament.match.description"
			var="descriptionHeader" />
		<display:column property="description" title="${descriptionHeader}" />

		<spring:message code="tournament.match.creationMoment"
			var="creationMomentHeader" />
		<display:column property="creationMoment"
			title="${creationMomentHeader}" />

		<spring:message code="tournament.match.startMoment"
			var="startMomentHeader" />
		<display:column property="startMoment" title="${startMomentHeader}" />

		<spring:message code="tournament.match.finishMoment"
			var="finishMomentHeader" />
		<display:column property="finishMoment" title="${finishMomentHeader}" />


	</display:table>

	<security:authorize access="hasRole('CUSTOMER')">
		<acme:cancel code="tournament.back" url="tournament/customer/list.do" />

		<input type="button" name="edit"
			value="<spring:message code="tournament.edit"/>"
			onclick="javascript: window.location.replace('tournament/customer/edit.do?tournamentId= ${tournamentId}')" />



	</security:authorize>

	<security:authorize access="hasRole('USER')">
		<acme:cancel code="tournament.back" url="tournament/user/list.do" />

		<input type="button" name="edit"
			value="<spring:message code="tournament.edit"/>"
			onclick="javascript: window.location.replace('tournament/user/edit.do?tournamentId= ${tournamentId}')" />


	</security:authorize>

</form:form>