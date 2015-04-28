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

<div class="table-responsive">
	<display:table name="tournaments" id="row" requestURI="${requestURI}"
		pagesize="5" class="">

		<display:column>
		
		<div class="col-xs-12">
			<div class="alert alert-success spm-no-margin-bottom">
				<!--  <b><spring:message code="tournament.title" />: </b> -->
				<spring:message code="tournament.title" var="titleHeader" />
				<jstl:out value="${row.title}"></jstl:out>
			</div>
			
			<div class="well well-sm col-xs-12 spm-no-margin-bottom">
				<div class="col-xs-12 col-md-4">
					<div>
					<b><spring:message code="tournament.startMoment" />: </b>
					<spring:message code="tournament.startMoment" var="startMomentHeader" />
					<jstl:out value="${row.startMoment}"></jstl:out>
					</div>
					
					<div>
					<b><spring:message code="tournament.finishMoment" />: </b>
					<spring:message code="tournament.finishMoment"
						var="finishMomentHeader" />
					<jstl:out value="${row.finishMoment}"></jstl:out>
					</div>
				</div>
				
				<div class="col-xs-12 col-md-3">
					<div>
					<b><spring:message code="tournament.sport" />: </b>
					<spring:message code="tournament.sport" var="sportHeader" />
					<jstl:out value="${row.sport}" />
					</div>
					
					<div>
					<b><spring:message code="tournament.numberOfTeams" />: </b>
					<spring:message code="tournament.numberOfTeams"
						var="numberOfTeamsHeader" />
					<jstl:out value="${row.numberOfTeams}" />
					</div>
				</div>
				
				<div class="col-xs-12 col-md-3">
					<div>
					<b><spring:message code="tournament.advertised" />: </b>
					<spring:message code="tournament.advertised" var="advertisedHeader" />
					<jstl:if test="${row.advertised==true}">
					<spring:message code="tournament.yes"/>
					</jstl:if>
					<jstl:if test="${row.advertised==false}">
					<spring:message code="tournament.no"/>
					</jstl:if>
					</div>
					
					<div>
					<b><spring:message code="tournament.prize" />: </b>
					<spring:message code="tournament.prize" var="prizeHeader" />
					<jstl:out value="${row.prize}" />
					</div>
				</div>	
			</div>
			
			<div class="row">
			<div class="col-xs-12 spm-button-list">
				<spring:message code="tournament.display" var="displayHeader" />
					
					<security:authorize access="hasRole('USER')">
							<a href="tournament/user/display.do?tournamentId=${row.id}"> 
									<button type="button" class="btn btn-md btn-default col-xs-2">
										<spring:message	code="tournament.display" />
									</button>
							</a>
					</security:authorize>
					
					<security:authorize access="hasRole('CUSTOMER')">
						<a href="tournament/customer/display.do?tournamentId=${row.id}">
							<button type="button" class="btn btn-md btn-default col-xs-2">
								<spring:message code="tournament.display" />
							</button>
						</a>
					</security:authorize>
			</div>
			</div>
		</div>
		</display:column>
		

		
	</display:table>
</div>

<security:authorize access="hasRole('USER')">
	<input type="button" class="btn btn-md btn-success" name="create"
		value="<spring:message code="tournament.create"/>"
		onclick="javascript: window.location.replace('tournament/user/create.do');" />
</security:authorize>

<security:authorize access="hasRole('CUSTOMER')">
	<input type="button" class="btn btn-md btn-success" name="create"
		value="<spring:message code="tournament.create"/>"
		onclick="javascript: window.location.replace('tournament/customer/create.do');" />
</security:authorize>

<acme:cancel code="tournament.back" url="welcome/index.do" />

