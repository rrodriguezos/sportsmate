<%--
 * edit.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="eventForm">

	<form:hidden path="id" />
	
	<acme:textbox code="event.title" path="title"/>
	<br/>
	
	<acme:textbox code="event.startMoment" path="startMoment"/><spring:message code="event.start"/><br/>
	<br/>
	
	<acme:textbox code="event.finishMoment" path="finishMoment"/><spring:message code="event.finish"/><br/>
	<br/>
	
	<acme:textarea code="event.description" path="description"/>
	<br/>
	
	<acme:textbox code="event.numberMaxParticipant" path="numberMaxParticipant"/>
	<br/>

	<form:label path="sport"><spring:message code="event.sport"/></form:label>
		<form:select path="sport" >
			<form:options items="${sports}"  />
		</form:select>
		<form:errors cssClass="error" path="sport" />
	<br />
	<br />	
	
	<security:authorize access="hasRole('USER')">
		<form:label path="place"><spring:message code="event.place"/></form:label>
			<form:select path="place" >
				<form:options items="${places}"  />
			</form:select>
			<form:errors cssClass="error" path="place" />
		<br />
		<br />	
		<acme:textbox code="event.otherSportCenter" path="otherSportCenter"/>		
	</security:authorize>
			
	
	<security:authorize access="hasRole('CUSTOMER')">
		<acme:textbox code="event.place" path="place" readonly="true"/>
	</security:authorize>
	<br />
		
	
	<security:authorize access="hasRole('CUSTOMER')">
		<acme:submit code="event.save" name="saveEC" />&nbsp;
		
		<acme:cancel code="event.cancel" url="event/customer/list.do" />
	</security:authorize>	
	
	<security:authorize access="hasRole('USER')">
		<acme:submit code="event.save" name="saveEU" />&nbsp;
		<acme:cancel code="event.cancel" url="event/user/list.do" />
	</security:authorize>
	
</form:form>


	

