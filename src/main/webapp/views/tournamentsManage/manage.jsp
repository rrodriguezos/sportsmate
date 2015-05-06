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
<spring:message code="tournament.createMatches2" var="createMatches2"></spring:message>
<spring:message code="tournament.option" var="option"></spring:message>
<spring:message code="setWinnerOfTournament" var="setWinnerOfTournament"></spring:message>






<div class="table-responsive">
<display:table name="tournament" id="row"

requestURI="tournament/user/rounds/manageTournament.do"
pagesize="5" class="" >

<display:column>
<div class="col-xs-12 spm-search-row">	
	<div class="col-xs-12">
		<div>
			<!-- TITULO TORNEO -->
			<b><spring:message code="tournament.title" />: </b>
			<spring:message code="tournament.title" var="title"></spring:message>
			<jstl:out value="${row.title}"></jstl:out>
		</div>
		<div class="col-xs-12"><br></div>
		<div>
			<!-- TITULO MATCHES -->
			<b><spring:message code="tournament.matches" />: </b>
			<br><br>
			<jstl:if test="${row.matches.size() == 0}">
				<div class="col-xs-12 spm-match-row">
					<jstl:out value="${noMatches }"></jstl:out>
				</div>
				
				<jstl:if test="${row.teams.size() >= 2 }">
				<div class="col-xs-12 spm-match-row">
					<a href="tournament/user/rounds/createFirstRounds.do?id=${row.id }">
						<jstl:out value="${createMatches }"></jstl:out>
					</a>
				</div>
				</jstl:if>
					
				<jstl:if test="${row.teams.size() < 2 }">	
					<div class="col-xs-12 spm-match-row">
						<jstl:out value="${noTeams }"></jstl:out>
					</div>
				</jstl:if>		
			</jstl:if>
			
			<jstl:if test="${row.matches.size() != 0}">
				<jstl:forEach items="${row.matches }" var="a">
					<jstl:if test="${a.winner ==null }">
					<div class="col-xs-12 spm-match-row">
						<jstl:out value="${a.title }"></jstl:out>
							<a href="tournament/user/rounds/declareWinnerOfMatch.do?id=${a.id }">
								<button type="button" class="btn btn-sm btn-default pull-right col-xs-12 col-sm-3">
									<jstl:out value="${setWinner }"></jstl:out>
								</button>
							</a> 
						<br>
					</div>
					</jstl:if>
					
					<jstl:if test="${a.winner != null }">
						<div class="col-xs-12 spm-match-row">
							<jstl:out value="${a.title} : ${Winner } ---> ${a.winner.name }"></jstl:out>
						</div>
					</jstl:if>
				</jstl:forEach>
			</jstl:if>
		
			<jstl:if test="${row.matches.size() != 0}">
				<jstl:forEach items="${row.matches }" var="a">	
					<jstl:if test="${a.winner ==null }">
					<div class="col-xs-12 spm-match-row">
						<jstl:out value="${a.title }"></jstl:out>
							<a href="tournament/user/rounds/declareWinnerOfMatch.do?id=${a.id }">
								<button type="button" class="btn btn-sm btn-default pull-right col-xs-12 col-sm-3">
									<jstl:out value="${setWinner }"></jstl:out>
								</button>
							</a> 
						<br>
					</div>
					</jstl:if>
					<jstl:if test="${a.winner != null }">
					<div class="col-xs-12 spm-match-row">	
						<jstl:out value="${a.title} : ${Winner } ---> ${a.winner.name }"></jstl:out>
					</div>
					</jstl:if>
				</jstl:forEach>
			</jstl:if>		
		</div>
		
		
	</div>
	
	<div class="col-xs-12 hidden-sm hidden-md hidden-lg spm-xs-separator"></div>
	
	<!-- OPTIONS -->
	<div class="col-xs-12">
		<div>
			<!-- TITULO OPTIONS -->
			<b><spring:message code="tournament.option" />: </b>
			<br><br>
			<%-- equipos sin jugar aún --%>
			<jstl:set var="AllPlays" value="1"></jstl:set>
			<jstl:set var="AllTeamPlays" value="1"></jstl:set>
			<jstl:set var="numTeams" value="${row.teams.size() }"></jstl:set>
				
			<%-- primero comprobamos si aún quedan partidos sin jugar --%>
					
			<%-- entonces comprobamos si se cumplen las condiciones para poder generar otra ronda o que declarar al vencedor --%>
				
			<jstl:if test="${AllPlaysC  && AllTeamC && needRounds && row.winner == null }">
				<div class="col-xs-12 spm-match-row">	
					<a href="tournament/user/rounds/declareWinnerOfTournament.do?idTournament=${row.id }">
						<button type="button" class="btn btn-sm btn-default pull-right col-xs-12 col-sm-3">
							<jstl:out value="${setWinnerOfTournament }"></jstl:out>
						</button>
					</a>
				</div>
			</jstl:if>
			
			<jstl:if test="${AllPlaysC  && !AllTeamC }">	
				<div class="col-xs-12 spm-match-row">
					<a href="tournament/user/rounds/secondRounds.do?idTournament=${row.id }">
						<button type="button" class="btn btn-sm btn-default pull-right col-xs-12 col-sm-3">
							<jstl:out value="${createMatches2 }"></jstl:out>
						</button>
					</a>
				</div>
			</jstl:if>
			
			<jstl:if test="${AllPlaysC  && AllTeamC && !needRounds }">
				<div class="col-xs-12 spm-match-row">
					<a href="tournament/user/rounds/secondRounds.do?idTournament=${row.id }">
						<button type="button" class="btn btn-sm btn-default pull-right col-xs-12 col-sm-3">
							<jstl:out value="${createMatches2 }"></jstl:out>
						</button>
					</a>
				</div>
			</jstl:if>
		</div>
		
		<div>
			<jstl:if test="${row.winner != null }">
				<div class="col-xs-12 spm-match-row">
						<jstl:out value="${row.winner.name }"></jstl:out>
				</div>
			</jstl:if>
		</div>
	</div>

</div>
</display:column>
</display:table>
</div>
