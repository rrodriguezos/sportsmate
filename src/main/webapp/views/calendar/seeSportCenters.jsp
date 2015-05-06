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




<spring:message code="sportCenter.seeCalendar" var="seeCalendar"/>


<spring:message code="sportCenter.CalendarOfEvents" var="calendarOfEvents"/>



<div class="table-responsive">
<display:table name="centers" id="row" requestURI="event/user/calendar/seeSportCenters.do" pagesize="5" class="">

 
	<display:column>
		<div class="col-xs-12 spm-search-row">
			
			<div class="spm-search-glyphicon col-sm-1 hidden-xs hidden-sm">
				<span class="glyphicon glyphicon-calendar"></span>
			</div>		
		
			<div class="col-xs-12 col-sm-4 col-md-3">
				<div>
					<b><spring:message code="sportCenter.name" />: </b>
					<spring:message code="sportCenter.name" var="name"/>
					<jstl:out value="${row.nameCenter}"></jstl:out>
				</div>
				
				<div>
					<b><spring:message code="sportCenter.city" />: </b>
					<spring:message code="sportCenter.city" var="city"/>
					<jstl:out value="${row.city}"></jstl:out>	
				</div>
			</div>
			
			<div class="col-xs-12 col-sm-3">
				<div>
					<b><spring:message code="sportCenter.street" />: </b>
					<spring:message code="sportCenter.street" var="street"/>
					<jstl:out value="${row.street}"></jstl:out>		
				</div>
				<br>
			</div>
			
			<div class="col-xs-12 col-sm-2 pull-right spm-button-nopaddingleft">
				<a href="event/user/calendar/seePerfilOfCustomer.do?id=${row.id }">
					<button type="button" class="btn btn-md btn-default col-xs-12">
						<spring:message code="sportCenter.perfil" var="perfil"/>
						<jstl:out value="${perfil}"></jstl:out>
					</button>
				</a>
			</div>
			
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg spm-xs-separator"></div>
			
			<div class="col-xs-12 col-sm-3 pull-right">
				<a href="event/user/calendar/seeSportCenterCalendar.do?id=${row.id }">
					<button type="button" class="btn btn-md btn-default col-xs-12">
						<spring:message code="sportCenter.CalendarOfEvents" var="seeCalendar"/>
						<jstl:out value="${calendarOfEvents}"></jstl:out>
					</button>
				</a>
			</div>	
		</div>		
	</display:column>
</display:table>
</div>