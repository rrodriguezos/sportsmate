<%--
 * list.jsp
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

<br>
<div class='table-responsive'>
<display:table name="requestTeams" id="row" requestURI="${requestURI}"
	pagesize="5" class="table table-bordered table-hover">

	<spring:message code="requestTeam.user" var="userHeader" />
	<display:column property="user.email" title="${userHeader}" />

	<spring:message code="requestTeam.team" var="teamHeader" />
	<display:column property="team.name" title="${teamHeader}" />
	
	<spring:message code="requestTeam.requestDate" var="requestDateHeader" />
	<display:column property="requestDate" title="${requestDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="requestTeam.acceptRequest" var="acceptRequestHeader" />
	<display:column title="${acceptRequestHeader}">
		<security:authorize access="hasRole('USER')">
			<a href="requestTeam/user/acceptRequest.do?requestTeamId=${row.id}"> <spring:message
					code="requestTeam.acceptRequest" />
			</a>
		</security:authorize>
	</display:column>

</display:table>
</div>

<acme:cancel code="requestTeam.back" url="welcome/index.do" />