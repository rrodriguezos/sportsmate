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

<form:form action="${requestURI}" modelAttribute="eventForm">

	<form:hidden path="id" />
	
	
	<b><spring:message code="event.creationMoment" />: </b> 
	<fmt:formatDate value="${creationMoment}" pattern="dd/MM/yyyy"/>	
	<br/>
	<br/>
	<b><spring:message code="event.title" />: </b> 
		<form:input path="title" readonly="true"/>
	<br/>
	<br/>
	<b><spring:message code="event.startMoment" />: </b> 
		<form:input path="startMoment" readonly="true" format="{0,date,dd/MM/yyyy HH:mm}"/>&nbsp;	

	<b><spring:message code="event.finishMoment" />: </b> 
		<form:input path="finishMoment" readonly="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
	<br/>
	<br/>	
	<b><spring:message code="event.sport" />: </b> 
		<form:input path="sport" readonly="true"/>&nbsp;	
	
	<b><spring:message code="event.numberMaxParticipant" />: </b> 
		<form:input path="numberMaxParticipant" readonly="true"/>
	<br/>
	<br/>	
	<b><spring:message code="event.place" />: </b> 
		<form:input path="place" readonly="true"/>
	<br/>
	<br/>
	<b><spring:message code="event.description" />: </b> 
		<form:textarea path="description" readonly="true"/>
	<br/>

	<h1><spring:message code="event.participants"/></h1>
	<display:table name="users" id="row" pagesize="5" class="displaytag">
	
		<spring:message code="event.user.name" var="nameHeader"/>
		<display:column property="name" title="${nameHeader}" />
			
		<spring:message code="event.user.surname" var="surnameHeader"/>
		<display:column property="surname" title="${surnameHeader}" />
			
		<spring:message code="event.user.email" var="emailHeader"/>
		<display:column property="email" title="${nameHeader}" />
			
		<spring:message code="event.user.phone" var="phoneHeader"/>
		<display:column property="phone" title="${phoneHeader}" />
			   
	</display:table>

	<security:authorize access="hasRole('CUSTOMER')">
		<acme:cancel code="event.back" url="event/customer/list.do" />
		<jstl:if test="${eventForm.customer.id == customer.id}">
			<input type="button" name="edit" value="<spring:message code="event.edit"/>" 
	   		   	onclick="javascript: window.location.replace('event/customer/edit.do?eventId= ${eventForm.id}')" /> 
	   		
			<jstl:if test="${eventForm.id != 0 && users.size()==0}"> 			
				<input type="submit" name="deleteEC" value="<spring:message code="event.delete" />"
			   		onclick="return confirm('<spring:message code="event.confirm.delete" />')" />
			</jstl:if>
		</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
		<acme:cancel code="event.back" url="event/user/list.do" />
		<jstl:if test="${eventForm.owner.id == user.id}">
			<input type="button" name="edit" value="<spring:message code="event.edit"/>" 
	   		   	onclick="javascript: window.location.replace('event/user/edit.do?eventId= ${eventForm.id}')" /> 
	   		
			<jstl:if test="${eventForm.id != 0 && users.size()==1}"> 			
				<input type="submit" name="deleteEU" value="<spring:message code="event.delete" />"
			       	onclick="return confirm('<spring:message code="event.confirm.delete" />')" />
			</jstl:if> 
		</jstl:if>
	</security:authorize>

</form:form>


