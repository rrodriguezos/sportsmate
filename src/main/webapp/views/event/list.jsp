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
	<display:table name="events" id="row" requestURI="${requestURI}"
		pagesize="5" class="spm-events-table">
		
		<!-- Espacio  spm-spacing-list -->
		
		<display:column>
		<div class="col-xs-12 spm-event-header-spacing">
			<div class="alert alert-success spm-no-margin-bottom col-xs-12">
				<spring:message code="tournament.title" var="titleHeader" />
				<jstl:out value="${row.title}"></jstl:out>
			</div>
			
			<!-- <b><spring:message code="event.title" />: </b> -->
			
			<div class="well well-sm col-xs-12 spm-no-margin-bottom">
				<div class="col-xs-12 col-sm-9 col-md-10">
					<div>
						<b><spring:message code="event.startMoment" />: </b>
						<spring:message code="event.startMoment" var="startMomentHeader" />
						<jstl:out value="${row.startMoment}"></jstl:out>
					</div>
				
					<div>
						<b><spring:message code="event.finishMoment" />: </b>
						<spring:message code="event.finishMoment" var="finishMomentHeader" />
						<jstl:out value="${row.finishMoment}"></jstl:out>
					</div>
					
					<div>
						<b><spring:message code="event.numberMaxParticipant" />: </b>
						<spring:message code="event.numberMaxParticipant" var="numberMaxParticipantHeader" />
						<jstl:out value="${row.numberMaxParticipant}"></jstl:out>
					</div>
				</div>
				
				<div class="col-xs-12 col-sm-3 col-md-2">
					<security:authorize access="hasRole('USER')">
						<a href="event/user/display.do?eventId=${row.id}">
							<button type="button" class="btn btn-md btn-default col-xs-12 spm-event-detail-btn">
								<spring:message code="event.display" />
							</button>
						</a>
					</security:authorize>
				
					<security:authorize access="hasRole('CUSTOMER')">
						<a href="event/customer/display.do?eventId=${row.id}">
							<button type="button" class="btn btn-md btn-default col-xs-12 spm-event-detail-btn">
								<spring:message code="event.display" />
							</button>
						</a>
					</security:authorize>
				</div>
			</div>

			<spring:message code="event.display" var="displayHeader" />

		</div>
		
		</display:column>
		
		
		
		<jstl:if test="${showJoin == true}">
		
		<display:column title="${join }">
		
		<div class="col-xs-5 col-sm-3 spm-events-button">
			<jstl:if test="${row.users.size() == row.numberMaxParticipant }">
				<spring:message code="event.full" var="full"/>
				<jstl:out value="${full }"></jstl:out>
			</jstl:if>

			<jstl:set var="contains" value="false" />

			<jstl:if test="${userEvents.size() > 0 }">

				<jstl:forEach var="item" items="${userEvents}">

					<jstl:if test="${item.id eq row.id}">
						<jstl:set var="contains" value="true" />

					</jstl:if>
				</jstl:forEach>

			</jstl:if>

			<jstl:if test="${row.users.size() < row.numberMaxParticipant }">
				<jstl:if test="${contains == false}">
					<spring:message code="event.join" var="join" />
					<a href="event/user/joinEvent.do?eventId=${row.id }">
						<button class="btn btn-md btn-default col-xs-12">
							<jstl:out value="${join }"></jstl:out>
						</button>
					</a>
				</jstl:if>
			

			<jstl:if test="${contains == true}">
				
				<span class="col-xs-12 bg-success spm-event-joined text-center">
					<spring:message code="event.joined" var="joined" />
					<jstl:out value="${joined }"></jstl:out>
				</span>
			</jstl:if>
			</jstl:if>
		
		</div>
		</display:column>
		</jstl:if>

	

	</display:table>
	
</div>

<br>

<security:authorize access="hasRole('USER')">
	<input type="button" class="btn btn-md btn-success" name="create"
		value="<spring:message code="event.create"/>"
		onclick="javascript: window.location.replace('event/user/create.do');" />
</security:authorize>

<security:authorize access="hasRole('CUSTOMER')">
	<input type="button" class="btn btn-md btn-success" name="create"
		value="<spring:message code="event.create"/>"
		onclick="javascript: window.location.replace('event/customer/create.do');" />
</security:authorize>

<acme:cancel code="event.back" url="welcome/index.do" />

