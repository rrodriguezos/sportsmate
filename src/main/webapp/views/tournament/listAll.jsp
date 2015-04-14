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
<?xml version="1.0" encoding="UTF-8"?>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div class="table-responsive">
<display:table name="tournaments" id="row" requestURI="${requestURI}" 
			   pagesize="5" class="table table-bordered table-hover">
			   
			   
	<spring:message code="tournament.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="tournament.advertised" var="advertisedHeader"/>
	<display:column property="advertised" title="${advertisedHeader}" />	
	
	<spring:message code="tournament.startMoment" var="startMomentHeader"/>
	<display:column property="startMoment" title="${startMomentHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="tournament.finishMoment" var="finishMomentHeader"/>
	<display:column property="finishMoment" title="${finishMomentHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="tournament.numberOfTeams" var="numberOfTeamsHeader"/>
	<display:column property="numberOfTeams" title="${numberOfTeamsHeader}" />
	
	<spring:message code="tournament.prize" var="prizeHeader"/>
	<display:column property="prize" title="${prizeHeader}" />
	
	<spring:message code="tournament.sport" var="sportHeader"/>
	<display:column property="sport" title="${sportHeader}" />
	
		  	   
			   
			   
</display:table>
</div>

<acme:cancel code="tournament.back" url="welcome/index.do" />	   