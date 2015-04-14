<%--
 * edit.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="tournamentForm">

	<form:hidden path="id" />
	
	<acme:textbox code="tournament.title" path="title"/>
	<br/>
	
	<acme:textbox code="tournament.startMoment" path="startMoment"/><spring:message code="tournament.start"/><br/>
	<br/>
	
	<acme:textbox code="tournament.finishMoment" path="finishMoment"/><spring:message code="tournament.finish"/><br/>
	<br/>
	
	<acme:textarea code="tournament.description" path="description"/>
	<br/>
	
	<acme:textbox code="tournament.numberOfTeams" path="numberOfTeams"/>
	<br/>
	
	<form:label path="advertised">
		<spring:message code="tournament.advertised" />
	</form:label>
	
		<form:select path="advertised">
        <option value="">--</option>
        <option value="true">Yes</option>
        <option value="false">No</option>
    </form:select>

	<form:label path="sport"><spring:message code="tournament.sport"/></form:label>
		<form:select path="sport" >
			<form:options items="${sports}"  />
		</form:select>
		<form:errors cssClass="error" path="sport" />
	<br />
	<br />	
	
	<form:label path="teams"><spring:message code="tournament.team"/></form:label>
		<form:select path="teams" >
			<form:options items="${teams}"  />
		</form:select>
		<form:errors cssClass="error" path="teams" />
	<br />
	<br />
	
	<form:label path="matches"><spring:message code="tournament.match"/></form:label>
		<form:select path="matches" >
			<form:options items="${matches}"  />
		</form:select>
		<form:errors cssClass="error" path="matches" />
	<br />
	<br />
	<security:authorize access="hasRole('CUSTOMER')">
		<acme:textbox code="tournament.place" path="place" readonly="true"/>
	</security:authorize>
	
	<security:authorize access="hasRole('USER')">
		<form:label path="place"><spring:message code="tournament.place"/></form:label>
			<form:select path="place" >
				<form:options items="${places}"  />
			</form:select>
			<form:errors cssClass="error" path="place" />
		<br />
		<br />
	
		<acme:textbox code="tournament.otherSportCenter" path="otherSportCenter"/>
	</security:authorize>
		<br/>
	<security:authorize access="hasRole('CUSTOMER')">
		<acme:textbox code="tournament.place" path="place" readonly="true"/>
	</security:authorize>
	<br />
		
	
<security:authorize access="hasRole('CUSTOMER')">
		
		<acme:submit code="tournament.save" name="saveTC" />&nbsp;
		
		<jstl:if test="${tournamentForm.id != 0}"> 			
			<input type="submit" name="deleteTC" value="<spring:message code="tournament.delete" />"
			   	   onclick="return confirm('<spring:message code="tournament.confirm.delete" />')" />&nbsp;
		</jstl:if>
		
		<acme:cancel code="tournament.cancel" url="tournament/customer/list.do" />
	</security:authorize>	
	
	<security:authorize access="hasRole('USER')">
		<acme:submit code="tournament.save" name="saveTU" />&nbsp;
		
		<jstl:if test="${tournamentForm.id != 0}"> 			
				<input type="submit" name="deleteTU" value="<spring:message code="tournament.delete" />"
			       	onclick="return confirm('<spring:message code="tournament.confirm.delete" />')" />
		</jstl:if>
		
		<acme:cancel code="tournament.cancel" url="tournament/user/list.do" />
	</security:authorize>	
	
</form:form>


	

