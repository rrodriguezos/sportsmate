<%--
 * edit.jsp
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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<br>

<form:form action="${requestURI}" modelAttribute="eventForm">

	<form:hidden path="id" />

	<acme:textbox code="event.title" path="title" />
	<br />

	<acme:textbox code="event.startMoment" path="startMoment" />
	<div class="col-xs-12">
		<spring:message code="event.start" />
	</div>

	<acme:textbox code="event.finishMoment" path="finishMoment" />
	<div class="col-xs-12">
		<spring:message code="event.finish" />
	</div>
	
	<div class="col-xs-12"><br></div>

	<acme:textarea code="event.description" path="description" />
	<br />

	<acme:textbox code="event.numberMaxParticipant"
		path="numberMaxParticipant" />
	<br />
	
	<div class="col-xs-12">
		<div class="row">
		<div class="col-xs-12 col-sm-3 form-group">
		<form:label class="control-label" path="sport">
			<spring:message code="event.sport" />
		</form:label>
		
		<form:select class="form-control" path="sport">
			<form:options items="${sports}" />
		</form:select>
		<form:errors class="alert alert-danger spm-form-error" path="sport" />
		</div>

		<security:authorize access="hasRole('USER')">
			<div class="col-xs-12 col-sm-3 form-group">
				<form:label class="control-label" path="place">
					<spring:message code="event.place" />
				</form:label>
				
				<form:select class="form-control" path="place">
					<form:options items="${places}" />
				</form:select>
				<form:errors class="alert alert-danger spm-form-error" path="place" />
			</div>
			
		</security:authorize>
		</div>
	</div>
	
	<security:authorize access="hasRole('USER')">
		<acme:textbox code="event.otherSportCenter" path="otherSportCenter" />
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">
		<acme:textbox code="event.place" path="place" readonly="true" />
	</security:authorize>
	<br />
	

	<!-- Buttons -->
	
	<div class="col-xs-12">
	<br>
	<security:authorize access="hasRole('CUSTOMER')">

		<acme:submit code="event.save" name="saveEC" />
		
		<jstl:if test="${eventForm.id != 0 && users.size()==0}">
			<input type="submit" name="deleteEC"
				value="<spring:message code="event.delete" />"
				onclick="return confirm('<spring:message code="event.confirm.delete" />')" />
		</jstl:if>

		<acme:cancel code="event.cancel" url="event/customer/list.do" />
	</security:authorize>

	<security:authorize access="hasRole('USER')">
		<acme:submit code="event.save" name="saveEU" />
		
		<jstl:if test="${eventForm.id != 0 && users.size()==1}">
			<input type="submit" class="btn btn-md btn-danger" name="deleteEU"
				value="<spring:message code="event.delete" />"
				onclick="return confirm('<spring:message code="event.confirm.delete" />')" />
		</jstl:if>

		<acme:cancel code="event.cancel" url="event/user/list.do" />
	</security:authorize>
	</div>
</form:form>

