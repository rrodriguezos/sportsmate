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

<form:form action="${requestURI}" modelAttribute="tournamentForm">

<div class="col-xs-12 col-md-9">
	<div class="input-group input-group-md">
		<form:label class="input-group-addon" path="teams">
			<spring:message code="tournament.teams" />
		</form:label>
		<form:select class="form-control" path="teams">
			<form:options items="${teams}" itemLabel="name" />
		</form:select>
		<form:errors class="alert alert-danger spm-form-error"
			path="teams" />
		<br />
	</div>
	<br>
</div>

<div class="col-xs-12">
		<br>
		<acme:submit code="tournament.join" name="join" />
		<acme:cancel code="tournament.cancel" url="tournament/listAll.do" />
	</div>
</form:form>

