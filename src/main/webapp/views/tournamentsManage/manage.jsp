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
<spring:message code="tournament.noMatches" var="noMatches"></spring:message>
<spring:message code="tournament.createMatches" var="createMatches"></spring:message>
<spring:message code="tournament.noTeams" var="noTeams"></spring:message>
<spring:message code="tournament.setWinner" var="setWinner"></spring:message>
<spring:message code="tournament.Winner" var="Winner"></spring:message>





<display:table name="tournament" id="row"

requestURI="tournament/user/rounds/manageTournament.do"
pagesize="5" class="displaytag" >

	<display:column title="${title }">
	
		<jstl:out value="${row.title }"></jstl:out>
	</display:column>
	
	<display:column title="${matches }">
	
		
		<jstl:if test="${row.matches.size() == 0}">
		
			<jstl:out value="${noMatches }"></jstl:out>
			
			<jstl:if test="${row.teams.size() >= 2 }">
				
				
				<a href="tournament/user/rounds/createFirstRounds.do?id=${row.id }"><jstl:out value="${createMatches }"></jstl:out></a>
			
			</jstl:if>
			
			<jstl:if test="${row.teams.size() < 2 }">
				
				<jstl:out value="${noTeams }"></jstl:out>
			
			</jstl:if>
			
		</jstl:if>
		
		<jstl:if test="${row.matches.size() != 0}">
		
		
			<jstl:forEach items="${row.matches }" var="a">
				
				<jstl:if test="${a.winner ==null }">
				<jstl:out value="${a.title }"></jstl:out>  <a href="tournament/user/rounds/declareWinnerOfMatch.do?id=${a.id }"><jstl:out value="${setWinner }"></jstl:out></a> 
				<br>
				</jstl:if>
				<jstl:if test="${a.winner != null }">
					
					<jstl:out value="${a.title} : ${Winner } ---> ${a.winner.name }"></jstl:out>
					
				</jstl:if>
			</jstl:forEach>
		
			
			
		</jstl:if>
		
		
	

		
		
	</display:column>
	
	
	<display:column title="${setWinner}">
	
		<jstl:if test="${row.matches.size() != 0}">
			<jstl:set var="ganado" value="true"></jstl:set>
			<jstl:forEach items="${row.matches }" var="a">
				
				
				<jstl:if test="${a.winner == null }">
						<jstl:set var="ganado" value="false"></jstl:set>
				</jstl:if>
			
			
			</jstl:forEach>
			
			<jstl:if test="${!ganado }">
					
					<jstl:out value="no hay ninguno ganado"></jstl:out>		
				
				</jstl:if>
				
				<jstl:if test="${ganado }">
					
					<jstl:out value="Todos los maches estan ganados"></jstl:out>		
				
				</jstl:if>
			
		</jstl:if>
		
	
	
	</display:column>
	
	<display:column>
		
		<%-- equipos sin jugar aún --%>
		<jstl:set var="AllPlays" value="false"></jstl:set>
		<jstl:set var="numTeams" value="${row.teams.size() }"></jstl:set>
		<jstl:forEach items="${row.matches }" var="a">
				  
				<jstl:forEach items="${a.teams }" var="b">
					  
					
					
					<jstl:forEach items="${b.winners }" var="c">
						 
						<jstl:if test="${c.id == a.id }">
							
							test1
						</jstl:if>
						
					
					</jstl:forEach>
			
			
			</jstl:forEach>
				
				
				
				
			
			
			</jstl:forEach>
			
			
			
	
	
	
	</display:column>
	
	
	
	
</display:table>



<display:table name="tournament" id="row"

requestURI="customer/ownItemList.do"
pagesize="5" class="displaytag" >

	<display:column title="${matches }">
	
		
		<jstl:if test="${row.matches.size() == 0}">
		
			<jstl:out value="${noMatches }"></jstl:out>
			
			<jstl:if test="${row.teams.size() >= 2 }">
				
				
				<a href="tournament/user/rounds/createFirstRounds.do?id=${row.id }"><jstl:out value="${createMatches }"></jstl:out></a>
			
			</jstl:if>
			
			<jstl:if test="${row.teams.size() < 2 }">
				
				<jstl:out value="${noTeams }"></jstl:out>
			
			</jstl:if>
			
		</jstl:if>
		
		<jstl:if test="${row.matches.size() != 0}">
		
		
			<jstl:forEach items="${row.matches }" var="a">
				
				<jstl:if test="${a.winner ==null }">
				<jstl:out value="${a.title }"></jstl:out>  <a href="tournament/user/rounds/declareWinnerOfMatch.do?id=${a.id }"><jstl:out value="${setWinner }"></jstl:out></a> 
				<br>
				</jstl:if>
				<jstl:if test="${a.winner != null }">
					
					<jstl:out value="${a.title} : ${Winner } ---> ${a.winner.name }"></jstl:out>
					
				</jstl:if>
			</jstl:forEach>
		
			
			
		</jstl:if>
		
		
	

		
		
	</display:column>


</display:table>