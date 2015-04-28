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
					<spring:message code="tournament.title" var="titleHeader" />
					<jstl:out value="${row.title}"></jstl:out>
				</div>

				<div class="well well-sm col-xs-12 spm-no-margin-bottom">
					<div class="col-xs-12 col-md-4">
						<div>
							<b><spring:message code="tournament.startMoment" />: </b>
							<spring:message code="tournament.startMoment"
								var="startMomentHeader" />
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
							<spring:message code="tournament.advertised"
								var="advertisedHeader" />
							<jstl:if test="${row.advertised==true}">
								<spring:message code="tournament.yes" />
							</jstl:if>
							<jstl:if test="${row.advertised==false}">
								<spring:message code="tournament.no" />
							</jstl:if>
						</div>

						<div>
							<b><spring:message code="tournament.prize" />: </b>
							<spring:message code="tournament.prize" var="prizeHeader" />
							<jstl:out value="${row.prize}" />
						</div>
					</div>
				</div>
			</div>

			<div class="col-xs-12 spm-spacing-list"></div>

		</display:column>



	</display:table>
</div>

<acme:cancel code="tournament.back" url="welcome/index.do" />
