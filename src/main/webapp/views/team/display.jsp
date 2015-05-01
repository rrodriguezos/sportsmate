<%--
 * display.jsp
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

<form:form action="${requestURI}" modelAttribute="teamForm">

	<form:hidden path="id" />
	
	<div class="row">
		<div class="col-xs-12">
			<div class="col-xs-12 col-sm-8 well well-sm spm-message-field-spacing">
				<b><spring:message code="team.name" />: </b>
				<form:input path="name" readonly="true" class="spm-message-moment" />
			</div>
			
			<div class="col-xs-12 col-sm-8 well well-sm spm-message-field-spacing">
				<b><spring:message code="team.captain" />: </b>
				<form:input path="captain.email" readonly="true" class="spm-message-moment" />
			</div>
			
			<div class="col-xs-12 col-sm-8 well well-sm spm-message-field-spacing">
				<b><spring:message code="team.maxNumber" />: </b>
				<form:input path="maxNumber" readonly="true" class="spm-message-moment spm-mini-input" />
			</div>
		</div>
	</div>	
	
	<h2>
		<spring:message code="team.members" />
	</h2>
	
	<div class="table-responsive">
	<display:table name="users" id="row" pagesize="5" class="table table-bordered table-hover">

		<spring:message code="team.user.name" var="nameHeader" />
		<display:column property="name" title="${nameHeader}" />

		<spring:message code="team.user.surname" var="surnameHeader" />
		<display:column property="surname" title="${surnameHeader}" />

		<spring:message code="team.user.email" var="emailHeader" />
		<display:column property="email" title="${nameHeader}" />

		<spring:message code="team.user.phone" var="phoneHeader" />
		<display:column property="phone" title="${phoneHeader}" />

	</display:table>
	</div>
	
	<br>
	
	<security:authorize access="hasRole('USER')">
		<acme:cancel code="team.back" url="welcome/index.do" />
		<jstl:if test="${principal.teamsCreated.contains(team)}">
			<input type="button" class="btn btn-md btn-success" name="edit"
				value="<spring:message code="team.edit"/>"
				onclick="javascript: window.location.replace('team/user/edit.do?teamId= ${teamForm.id}')" />

		</jstl:if>
	</security:authorize>

</form:form>


