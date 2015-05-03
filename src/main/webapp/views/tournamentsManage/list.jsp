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




<spring:message code="tournament.title" var="title"></spring:message>
<spring:message code="tournament.matches" var="matches"></spring:message>
<spring:message code="tournament.admin" var="admin"></spring:message>





<div class="table table-responsive">

<display:table name="tournaments" id="row"

requestURI="/tournament/user/rounds/list.do"
pagesize="5" class="table table-bordered table-hover" >
	
	<display:column title="${title }">
		
		<jstl:out value="${row.title }"></jstl:out>
	
	
	
	</display:column>
	
	<display:column title="${matches }">
		
		<jstl:forEach items="${row.matches }" var="a">
			
			<jstl:out value="${ a.title} "></jstl:out>
		
		</jstl:forEach>
	
	
	
	</display:column>
	
	<display:column title="${manage }">
		
		<a href="tournament/user/rounds/manageTournament.do?id=${row.id }"><jstl:out value="${admin }" ></jstl:out></a>
	
	
	
	</display:column>



</display:table>
</div>