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
<display:table name="teams" id="row" requestURI="${requestURI}"
	pagesize="5" class="table table-bordered table-hover">
	

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

	<jstl:if test="${showSend == true }">
		<spring:message code="team.sendRequest" var="sendRequestHeader" />
		<display:column title="${sendRequestHeader}">
				<security:authorize access="hasRole('USER')">
					<a href="requestTeam/user/sendRequest.do?teamId=${row.id}"> <spring:message
							code="team.sendRequest" />
					</a>
				</security:authorize>
		</display:column>
	</jstl:if>

	<jstl:if test="${showDisjoin == true }">
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

<jstl:if test="${showDisjoin == true }">
<security:authorize access="hasRole('USER')">
		<input type="button" class="btn btn-md btn-success" name="create"
			value="<spring:message code="team.create"/>"
			onclick="javascript: window.location.replace('team/user/create.do');" />
</security:authorize>
</jstl:if>
</div>

<br>

<acme:cancel code="team.back" url="welcome/index.do" />