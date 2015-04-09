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




<spring:message code="sportCenter.name" var="name"/>
<spring:message code="sportCenter.seeCalendar" var="seeCalendar"/>
<spring:message code="sportCenter.city" var="city"/>
<spring:message code="sportCenter.street" var="street"/>
<spring:message code="sportCenter.events" var="events"/>
<spring:message code="sportCenter.tournaments" var="tournaments"/>
<spring:message code="sportCenter.description" var="description"/>







<spring:message code="sportCenter.startMoment" var="startMoment"/>
<spring:message code="sportCenter.place" var="place"/>
<spring:message code="sportCenter.sport" var="sport"/>
<spring:message code="sportCenter.maxParticipant" var="maxParticipant"/>
<spring:message code="sportCenter.participants" var="participants"/>
<spring:message code="sportCenter.join" var="join"/>
<spring:message code="sportCenter.joined" var="joined"/>

<spring:message code="sportCenter.full" var="full"/>








<h1> <jstl:out value="${events }"></jstl:out> </h1>

<display:table name="events" id="row"

requestURI="event/user/calendar/seeSportCenterCalendar.do"
pagesize="20" class="displaytag" defaultsort="1" defaultorder="ascending" >

<display:column title="${startMoment }"  format="{0,date,dd-MM-yyyy}"> 
	<jstl:out value="${row.startMoment}"/>
</display:column>

<display:column title="${place }">
	<jstl:out value="${row.place }"/>
</display:column>

<display:column title="${sport }">
	<jstl:out value="${ row.sport}"/>	
</display:column>

<display:column title="${maxParticipant }">

	<jstl:out value="${ row.numberMaxParticipant}"/>	
	

</display:column>


<display:column title="${description }">

	<jstl:out value="${ row.description}"/>	
	

</display:column>


<display:column title="${participants }">

	<jstl:out value="${ row.users.size()}"/>	
	

</display:column>


<display:column title="${join }">

	<jstl:if test="${row.users.size() == row.numberMaxParticipant }">
	
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
				<a href="event/user/calendar/joinEvent.do?id=${row.id }"><jstl:out value="${join }"></jstl:out></a>
			</jstl:if>
			
			<jstl:if test="${contains == true}">
				<jstl:out value="${joined }"></jstl:out>
			</jstl:if>
		</jstl:if>
	
	
	

</display:column>




</display:table>

<h1> <jstl:out value="${tournaments }"></jstl:out> </h1>


<display:table name="tournaments" id="row"

requestURI="event/user/calendar/seeSportCenterCalendar.do"
pagesize="5" class="displaytag" >



<display:column title="${startMoment }"  format="{0,date,dd-MM-yyyy}"> 
	<jstl:out value="${row.startMoment}"/>
</display:column>

<display:column title="${place }">
	<jstl:out value="${row.place }"/>
</display:column>

<display:column title="${sport }">
	<jstl:out value="${ row.sport}"/>	
</display:column>




<display:column title="${description }">

	<jstl:out value="${ row.description}"/>	
	

</display:column>






	
	
	
	
	
	
	






</display:table>
