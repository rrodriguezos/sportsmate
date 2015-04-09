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

<display:table name="teams" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="team.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<spring:message code="team.maxNumber" var="maxNumberHeader" />
	<display:column property="maxNumber" title="${maxNumberHeader}" />

	<spring:message code="team.display" var="displayHeader" />
	<display:column title="${displayHeader}">
		<security:authorize access="hasRole('USER')">
			<a href="team/user/display.do?teamId=${row.id}"> <spring:message
					code="team.display" />
			</a>
		</security:authorize>
	</display:column>

	<jstl:if test="${requestURI == 'team/user/listAllTeams.do' }">
		<spring:message code="team.joinTeam" var="joinTeamHeader" />
		<display:column title="${joinTeamHeader}">

			<security:authorize access="hasRole('USER')">
				<a href="team/user/joinTeam.do?teamId=${row.id}"> <spring:message
						code="team.joinTeam" />
				</a>
			</security:authorize>

		</display:column>
	</jstl:if>
	
	<jstl:if test="${requestURI == 'team/user/list.do' }">
		<spring:message code="team.disjoinTeam" var="disjoinTeamHeader" />
		<display:column title="${disjoinTeamHeader}">

			<security:authorize access="hasRole('USER')">
				<a href="team/user/disjoinTeam.do?teamId=${row.id}"> <spring:message
						code="team.disjoinTeam" />
				</a>
			</security:authorize>

		</display:column>
	</jstl:if>

</display:table>

<jstl:if test="${requestURI == 'team/user/list.do' }">
<security:authorize access="hasRole('USER')">
	<input type="button" name="create"
		value="<spring:message code="team.create"/>"
		onclick="javascript: window.location.replace('team/user/create.do');" />
</security:authorize>
</jstl:if>


<acme:cancel code="team.back" url="welcome/index.do" />