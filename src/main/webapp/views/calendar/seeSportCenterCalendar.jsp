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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<spring:message code="sportCenter.name" var="name"/>
<spring:message code="sportCenter.seeCalendar" var="seeCalendar"/>
<spring:message code="sportCenter.city" var="city"/>
<spring:message code="sportCenter.street" var="street"/>
<spring:message code="sportCenter.events" var="events"/>
<spring:message code="sportCenter.tournaments" var="tournaments"/>


<spring:message code="sportCenter.joined" var="joined"/>

<spring:message code="sportCenter.full" var="full"/>


<h2> <jstl:out value="${events }"></jstl:out> </h2>

<div class="table-responsive">
<display:table name="events" id="row"
requestURI="event/user/calendar/seeSportCenterCalendar.do"
pagesize="20" class="" defaultsort="1" defaultorder="ascending" >

<display:column>
	<div class="col-xs-12 spm-search-row">
	
		<div class="spm-3-row-glyphicon col-sm-1 hidden-xs hidden-sm">
				<span class="glyphicon glyphicon-calendar"></span>
		</div>
		
		<div class="col-xs-12 col-sm-4 col-md-3">
			<div>
				<b><spring:message code="sportCenter.startMoment" />: </b>
				<spring:message code="sportCenter.startMoment" var="startMoment"/>
				<fmt:formatDate value="${row.startMoment}" pattern="dd/MM/yyyy HH:mm" />
			</div>
			
			<div>
				<b><spring:message code="sportCenter.maxParticipant" />: </b>
				<spring:message code="sportCenter.maxParticipant" var="maxParticipant"/>
				<jstl:out value="${row.numberMaxParticipant}"/>
			</div>
			
			<div>
				<b><spring:message code="sportCenter.participants" />: </b>
				<spring:message code="sportCenter.participants" var="participants"/>
				<jstl:out value="${row.users.size()}"/>
			</div>
		</div>

		<div class="col-xs-12 col-sm-4 col-md-3">
			<div>
				<b><spring:message code="sportCenter.place" />: </b>
				<spring:message code="sportCenter.place" var="place"/>
				<jstl:out value="${row.place}"></jstl:out>
			</div>
			
			<div>
				<b><spring:message code="sportCenter.sport" />: </b>
				<spring:message code="sportCenter.sport" var="sport"/>
				<jstl:out value="${row.sport}"></jstl:out>	
			</div>
			
			<div>
				<b><spring:message code="sportCenter.description" />: </b>
				<spring:message code="sportCenter.description" var="description"/>
				<jstl:out value="${row.description}"></jstl:out>
			</div>
		
		</div>
		
		<div class="col-xs-12 hidden-sm hidden-md hidden-lg spm-xs-separator"></div>
		
		<div class="col-xs-12 col-sm-2 pull-right spm-button-nopaddingleft">
			<!-- Details Button -->
			<a href="event/user/calendar/display.do?eventId=${row.id }">
				<button type="button" class="btn btn-md btn-default col-xs-12">
					<spring:message code="sportCenter.details" var="Details"/>
					<jstl:out value="${Details}"></jstl:out>
				</button>
			</a>
		</div>

		<div class="col-xs-12 hidden-sm hidden-md hidden-lg spm-xs-separator"></div>
		
		<div class="col-xs-12 col-sm-2 pull-right">
				<!-- FULL EVENT -->
				<jstl:if test="${row.users.size() == row.numberMaxParticipant }">
					<span class="col-xs-12 bg-danger spm-event-full text-center">
						<jstl:out value="${full }"></jstl:out>
					</span>
				</jstl:if>
				
				<jstl:set var="contains" value="false" />
				    
				<jstl:if test="${userEvents.size() > 0 }">	
					<jstl:forEach var="item" items="${userEvents}">
						  <jstl:if test="${item.id eq row.id}">
						  <jstl:set var="contains" value="true" />
						  </jstl:if>
					</jstl:forEach>
				</jstl:if>
				
				<!-- JOIN BUTTON -->
				<jstl:if test="${row.users.size() < row.numberMaxParticipant }">
					<jstl:if test="${contains == false}">
						<a href="event/user/calendar/joinEvent.do?id=${row.id }">
							<button type="button" class="btn btn-md btn-success col-xs-12">
								<spring:message code="sportCenter.join" var="join"/>
								<jstl:out value="${join }"></jstl:out>
							</button>
						</a>
					</jstl:if>
					
					<!-- JOINED EVENT -->
					<jstl:if test="${contains == true}">
						<span class="col-xs-12 bg-success spm-event-joined text-center">
							<jstl:out value="${joined }"></jstl:out>
						</span>
					</jstl:if>
				</jstl:if>			
		</div>
			
	</div>
</display:column>



</display:table>
</div>


<h2> <jstl:out value="${tournaments }"></jstl:out> </h2>


<div class="table-responsive">
<display:table name="tournaments" id="row"

requestURI="event/user/calendar/seeSportCenterCalendar.do"
pagesize="5" class="">
	
	<display:column>
		<div class="col-xs-12 spm-search-row">
		
			<div class="spm-search-glyphicon col-sm-1 hidden-xs hidden-sm">
					<span class="glyphicon glyphicon-calendar"></span>
			</div>
							
			<div class="col-xs-12 col-sm-5 col-md-3">
				<div>
					<b><spring:message code="sportCenter.startMoment" />: </b>
					<spring:message code="sportCenter.startMoment" var="startMoment"/>
					<fmt:formatDate value="${row.startMoment}" pattern="dd/MM/yyyy HH:mm" />
				</div>
				<div>
					<b><spring:message code="sportCenter.place" />: </b>
					<spring:message code="sportCenter.place" var="place"/>
					<jstl:out value="${row.place}"></jstl:out>
				</div>
			</div>
			
			<div class="col-xs-12 col-sm-4 col-md-3">
				<div>
					<b><spring:message code="sportCenter.sport" />: </b>
					<spring:message code="sportCenter.sport" var="sport"/>
					<jstl:out value="${row.sport}"></jstl:out>
				</div>
				
				<div>
					<b><spring:message code="sportCenter.description" />: </b>
					<spring:message code="sportCenter.description" var="description"/>
					<jstl:out value="${row.description}"></jstl:out>
				</div>
			</div>
			
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg spm-xs-separator"></div>
			
			<div class="col-xs-12 col-sm-2 pull-right spm-button-nopaddingleft">
				<a href="event/user/calendar/displayTournament.do?tournamentId=${row.id }">
					<button type="button" class="btn btn-md btn-default col-xs-12">
						<spring:message code="sportCenter.details" var="Details"/>
						<jstl:out value="${Details}"></jstl:out>
					</button>
				</a>
			</div>
		</div>	
	</display:column>
</display:table>
</div>