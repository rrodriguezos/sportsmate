<%--
 * display.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<b><spring:message code="event.title" />: </b> 
	<jstl:out value="${event.title}" />
<br/>

<b><spring:message code="event.startMoment" />: </b> 
	<fmt:formatDate value="${event.startMoment}" pattern="dd/MM/yyyy HH:mm"/>	
<br/>

<b><spring:message code="event.finishMoment" />: </b> 
	<fmt:formatDate value="${event.finishMoment}" pattern="dd/MM/yyyy HH:mm"/>	
<br/>

<b><spring:message code="event.numberMaxParticipant" />: </b> 
	<jstl:out value="${event.numberMaxParticipant}" />
<br/>

<b><spring:message code="event.sport" />: </b> 
	<jstl:out value="${event.sport}" />
<br/>

<b><spring:message code="event.place" />: </b> 
	<jstl:out value="${event.place}" />
<br/>

<b><spring:message code="event.description" />: </b> 
	<jstl:out value="${event.description}" />
<br/>
<br/>
<security:authorize access="hasRole('USER')">
	<acme:cancel code="event.back" url="event/user/list.do" />
	
	<input type="button" name="create" value="<spring:message code="event.edit"/>" 
	   		onclick="javascript: window.location.replace('event/user/edit.do?eventId= ${event.id}')" /> 
	   		
	<jstl:if test="${event.id != 0}"> 			
		<input type="submit" name="delete" value="<spring:message code="event.delete" />"
			   onclick="return confirm('<spring:message code="event.confirm.delete" />')" />
	</jstl:if> 
</security:authorize>

<security:authorize access="hasRole('CUSTOMER')">
	<acme:cancel code="event.back" url="event/customer/list.do" />
	
	<input type="button" name="create" value="<spring:message code="event.edit"/>" 
	   		onclick="javascript: window.location.replace('event/customer/edit.do?eventId= ${event.id}')" /> 
	   		
	<jstl:if test="${event.id != 0}"> 			
		<input type="submit" name="delete" value="<spring:message code="event.delete" />"
			   onclick="return confirm('<spring:message code="event.confirm.delete" />')" />
	</jstl:if> 
	
</security:authorize>

