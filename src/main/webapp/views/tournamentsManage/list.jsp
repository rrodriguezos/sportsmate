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
<spring:message code="tournament.state" var="state"></spring:message>
<spring:message code="tournament.admin" var="admin"></spring:message>
<spring:message code="tournament.play" var="play"></spring:message>
<spring:message code="tournament.noPlay" var="noPlay"></spring:message>
<spring:message code="tournament.finished" var="finished"></spring:message>


<div class="table table-responsive">

<display:table name="tournaments" id="row"

requestURI="/tournament/user/rounds/list.do"
pagesize="5" class="">
	<display:column>
		<div class="col-xs-12 spm-search-row">
			<div class="spm-search-glyphicon col-sm-1 hidden-xs">
				<span class="glyphicon glyphicon-screenshot"></span>
			</div>
			
			<div class="col-xs-12 col-sm-4 spm-invoices-fix-top-margin">
				<b><spring:message code="tournament.title" />: </b>
				<spring:message code="tournament.title" var="title"></spring:message>
				<jstl:out value="${row.title}"></jstl:out>
			</div>
			
			<div class="col-xs-12 col-sm-4 spm-invoices-fix-top-margin">
				<b><spring:message code="tournament.state" />: </b>
				<jstl:if test="${row.matches.size()==0 }">
					<jstl:out value="${noPlay }"></jstl:out>
				</jstl:if>
				
				<jstl:if test="${row.matches.size()!=0 && row.winner == null}">
					<jstl:out value="${play }"></jstl:out>
				</jstl:if>
				
				<jstl:if test="${row.winner != null}">
					<jstl:out value="${finished }"></jstl:out>
				</jstl:if>
			</div>
			
			<div class="col-xs-12 col-sm-3 spm-invoices-fix-top-margin">
				<jstl:if test="${row.startMoment<actualDate}">
					<a href="tournament/user/rounds/manageTournament.do?id=${row.id }">
						<jstl:out value="${admin }" ></jstl:out>
					</a>
				</jstl:if>	
			</div>
		
		</div>	
	</display:column>
</display:table>
</div>