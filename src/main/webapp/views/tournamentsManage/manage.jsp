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
	
	<display:column title="tets">
		
		<%-- equipos sin jugar aún --%>
		<jstl:set var="AllPlays" value="1"></jstl:set>
		<jstl:set var="AllTeamPlays" value="1"></jstl:set>
		<jstl:set var="numTeams" value="${row.teams.size() }"></jstl:set>
		
		<%-- primero comprobamos si aún quedan partidos sin jugar --%>
		
		<jstl:forEach items="${row.matches }" var="a">
			
			<jstl:if test="${!a.played }">
					<%-- hay partidos aún sin jugar --%>
					partidos sin jugar
					<jstl:set var="AllPlays" value= "0"></jstl:set>
			</jstl:if>
			
		</jstl:forEach>
		
		<%-- y comprobamos si quedan equipos sin jugar aún --%>
		
		<jstl:forEach items="${row.teams }" var="a">
						
						<%-- estos son los teams del tournament, que deben de tener una lista de ganados y perdidos debemos ver que incluyen algun 
						match, en caso de que no, este no ha jugado aún --%>
						
				<jstl:forEach items="${a.winners }" var="b">
						
						<jstl:forEach items="${a.defeats }" var="c">
									
								<jstl:forEach items="${row.matchs }" var="d">
								
								
									<jstl:if test="${!(b.id == d.id || c.id == d.id) }">
										
									<jstl:set var="AllTeamPlays" value= "0"></jstl:set>
										equipos sin jugar aún
									
								</jstl:if>
								
								</jstl:forEach>
												
						
						</jstl:forEach>
						
								
						
				</jstl:forEach>
						
						
					
				
		</jstl:forEach>
		
		<%-- entonces comprobamos si se cumplen las condiciones para poder generar otra ronda o que declarar al vencedor --%>
		
		<jstl:if test="${AllPlays == '1' && AllTeamPlays == '1' }">
		
				existe un vencedor
		</jstl:if>
		
		<jstl:if test="${AllPlays == '1' && AllTeamPlays == '0' }">
		
				CREAR RONDAS NUEVAS
		</jstl:if>
		
		
		<jstl:forEach items="${row.matches }" var="a">
			
			
			
			
			<jstl:if test="${AllPlays == '1' }"> 
				<%-- no hay partidos sin jugar, declaramos al vencedor del tournament --%>
				vencedor
			
			</jstl:if>
			
			<%--comprobamos si hay sin jugar para pasar a la siguiente validación que es ver si todos los equipos han jugado --%>
			
			${AllPlays }
			<jstl:if test="${AllPlays == '0' }">
				
				<%-- esta todos jugados, vemos si hay equipos sin jugar aún para generar la siguientes rondas o declarar el vencedor --%>
				
				<jstl:forEach items="${row.teams }" var="a">
						
						<%-- estos son los teams del tournament, que deben de tener una lista de ganados y perdidos debemos ver que incluyen algun 
						match, en caso de que no, este no ha jugado aún --%>
						
						<jstl:forEach items="${a.winners }" var="b">
						
								<jstl:forEach items="${a.defeats }" var="c">
									
									<jstl:if test="${b.id == a.id || c.id == a.id }">
										
										<jstl:set var="AllTeamPlays" value= "false"></jstl:set>
										pollas
									
									</jstl:if>
												
						
								</jstl:forEach>
						
								
						
						</jstl:forEach>
						
						
					
				
				</jstl:forEach>
				
			
			
			</jstl:if>
			
			
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