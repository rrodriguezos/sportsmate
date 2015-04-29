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
		pagesize="5" class="">
		
		<display:column>
		
		<div class="spm-team-list-wrapper">
			<div class="spm-team-list bg-success col-xs-12 col-sm-3">
				<spring:message code="team.name" var="nameHeader" />
				<jstl:out value="${row.name}"></jstl:out>
			</div>
			
			<!-- <b><spring:message code="team.name" />: </b> -->
			
			<div class="spm-team-list col-xs-12 col-sm-5">
				<b><spring:message code="team.maxNumber" />: </b>
				<spring:message code="team.maxNumber" var="maxNumberHeader" />
				<jstl:out value="${row.maxNumber}"></jstl:out>					
			</div>

			<!-- DETAILS BUTTON -->
			<div class="spm-team-list-btn col-xs-12 col-sm-2 pull-right">
				<security:authorize access="hasRole('USER')">
					<a href="team/user/display.do?teamId=${row.id}">
						<button type="button" class="btn btn-sm btn-default col-xs-12">
							<spring:message	code="team.display" />
						</button>
					</a>
				</security:authorize>
			</div>
						
			<!-- JOIN BUTTON -->
			
				<jstl:if test="${showSend == true }">
					<spring:message code="team.sendRequest" var="sendRequestHeader" />
					<security:authorize access="hasRole('USER')">	
						<jstl:if test="${!teamsRequested.contains(row)}">
							<div class="spm-team-list-btn col-xs-12 col-sm-2 pull-right">
							<a href="requestTeam/user/sendRequest.do?teamId=${row.id}">
								<button type="button" class="btn btn-sm btn-success col-xs-12 ">
									<spring:message	code="team.sendRequest" />
								</button>
							</a>
							</div>
						</jstl:if>
					</security:authorize>
				</jstl:if>
			
			<!-- DISJOIN BUTTON -->
			
			<jstl:if test="${showDisjoin == true }">
				<spring:message code="team.disjoinTeam" var="disjoinTeamHeader" />
				<security:authorize access="hasRole('USER')">
					<div class="spm-team-list-btn col-xs-12 col-sm-2 pull-right">
					<a href="team/user/disjoinTeam.do?teamId=${row.id}">
						<button type="button" class="btn btn-sm btn-warning col-xs-12">
							<spring:message	code="team.disjoinTeam" />
						</button>
					</a>
					</div>
				</security:authorize>
			</jstl:if>
		</div>
		
		<spring:message code="team.display" var="displayHeader" />
		</display:column>
		
		
	</display:table>
</div>
	<br>	
			<!-- CREATE A TEAM -->
			<jstl:if test="${showDisjoin == true }">
				<br>
				<security:authorize access="hasRole('USER')">
					<input type="button" class="btn btn-md btn-success" name="create"
						value="<spring:message code="team.create"/>"
						onclick="javascript: window.location.replace('team/user/create.do');" />
				</security:authorize>
			</jstl:if>

			

<acme:cancel code="team.back" url="welcome/index.do" />
