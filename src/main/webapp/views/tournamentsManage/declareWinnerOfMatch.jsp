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

<spring:message code="tournament.title" var="title"></spring:message>
<spring:message code="tournament.matches" var="matches"></spring:message>
<spring:message code="tournament.admin" var="admin"></spring:message>
<spring:message code="tournament.noMatches" var="noMatches"></spring:message>
<spring:message code="tournament.createMatches" var="createMatches"></spring:message>
<spring:message code="tournament.noTeams" var="noTeams"></spring:message>
<spring:message code="tournament.setWinner" var="setWinner"></spring:message>
<spring:message code="tournament.Winner" var="Winner"></spring:message>
<spring:message code="tournament.nameOfTeam" var="nombre"></spring:message>


<div class="table-responsive">
<display:table name="teams" id="row"

requestURI="customer/ownItemList.do"
pagesize="5" class="table table-bordered table-hover" >


	<display:column title="${nombre }">
	
	<jstl:out value="${row.name }"></jstl:out>
	
	</display:column>


	<display:column title="${setWinner }">
	
		<a href="tournament/user/rounds/declareWinnerOfMatchId.do?idTeam=${row.id }&idMatch=${match.id}"><jstl:out value="${setWinner }"></jstl:out></a>
	
	</display:column>


</display:table>
</div>