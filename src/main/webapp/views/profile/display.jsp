<%--
 * display.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<security:authorize access="hasRole('CUSTOMER')">
<form:form action="${requestURI}" modelAttribute="customerForm">
	<form:hidden path="id" />
	
			<br>
			
			<div class="row">
				<div class='col-md-3 spm-profile-picture-div text-center'> 
					<img src="images/default_profile.jpg" alt="Default profile" class="img-thumbnail .spm-profile-picture" />
					<br><br>
					<div>
						<jstl:if test="${customer.rating == null}">
							<spring:message code="actor.rating.Empty" />
						<jstl:out value=""></jstl:out>
						</jstl:if>
						
						<jstl:if test="${customer.rating != null}">
							<spring:message code="actor.rating" />
						<jstl:out value="${customer.rating}" />
						</jstl:if>
					</div>
				</div>
			
				<div class='col-md-3'> 
					<h3>
						<spring:message code="customer.center" />
					</h3>
					<br>
					<span class="glyphicon glyphicon-barcode">&nbsp</span><b><spring:message code="center.cif" />: </b> 
					<jstl:out value="${customer.cif}" />
					<br/>
					<span class="glyphicon glyphicon-home">&nbsp</span><b><spring:message code="center.street" />: </b> 
					<jstl:out value="${customer.street}" />
					<br/>
					<span class="glyphicon glyphicon-home">&nbsp</span><b><spring:message code="center.zip" />: </b> 
					<jstl:out value="${customer.zip}" />
					<br/>
					<span class="glyphicon glyphicon-home">&nbsp</span><b><spring:message code="center.provinceCenter" />: </b> 
					<jstl:out value="${customer.provinceCenter}" />
					<br/>
					<span class="glyphicon glyphicon-home">&nbsp</span><b><spring:message code="center.city" />: </b> 
					<jstl:out value="${customer.city}" />
					<br/>
					<span class="glyphicon glyphicon-king">&nbsp</span><b><spring:message code="center.nameCenter" />: </b> 
					<jstl:out value="${customer.nameCenter}" />
					<br/>
					<span class="glyphicon glyphicon-earphone">&nbsp</span><b><spring:message code="center.phoneCenter" />: </b> 
					<jstl:out value="${customer.phoneCenter}" />
					<br/>
					<span class="glyphicon glyphicon-envelope">&nbsp</span><b><spring:message code="center.emailCenter" />: </b> 
					<jstl:out value="${customer.emailCenter}" />
					<br/>
					<span class="glyphicon glyphicon-cloud">&nbsp</span><b><spring:message code="center.web" />: </b> 
					<jstl:out value="${customer.web}" />
					<br/>
				</div>
				
				<div class='col-md-3'>
					<h3>
						<spring:message code="personal.actor" />
					</h3>
					<br>
					<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="actor.name" />: </b> 
						<jstl:out value="${actor.name}" />
					<br/>
					<span class="glyphicon glyphicon-font">&nbsp</span><b><spring:message code="actor.surname" />: </b> 
						<jstl:out value="${actor.surname}" />
					<br/>
					<span class="glyphicon glyphicon-envelope">&nbsp</span><b><spring:message code="actor.email" />: </b> 
						<jstl:out value="${actor.email}" />
					<br/>
					<span class="glyphicon glyphicon-earphone">&nbsp</span><b><spring:message code="actor.phone" />: </b> 
						<jstl:out value="${actor.phone}" />
					<br/>
					
				</div>
				
				
				
				</div>
				
				<br><br>
			
			<div class="row">
			
				<div class="text-center">	
					<a href="profile/customer/edit.do?customerId=${profile.id}">
						<button type="button" class="btn btn-lg btn-success col-xs-12 col-md-6 col-md-offset-3">
							<spring:message code="customer.edit" />
						</button>
					</a>
				</div>
			</div>
			
</form:form>
</security:authorize>

<security:authorize access="hasRole('USER')">
<form:form action="${requestURI}" modelAttribute="userForm">
			
			<br>
			
			<div class="row">
			<div class='col-md-3 spm-profile-picture-div text-center'> 
					<img src="images/default_profile.jpg" alt="Default profile" class="img-thumbnail .spm-profile-picture" />
					<br><br>
					<div>
						<jstl:if test="${user.rating == null}">
							<spring:message code="actor.rating.Empty" />
						<jstl:out value=""></jstl:out>
					
						</jstl:if>
						<jstl:if test="${user.rating != null}">
							<spring:message code="actor.rating" />
						<jstl:out value="${user.rating}" />
						</jstl:if>
			
					</div>
			</div>
			
			<div class='col-md-3'> 
				<h3>
				<spring:message code="personal.actor" />
				</h3>
				<br>
					
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="actor.name" />: </b> 
				<jstl:out value="${actor.name}" />
				<br/>
				<span class="glyphicon glyphicon-font">&nbsp</span><b><spring:message code="actor.surname" />: </b> 
				<jstl:out value="${actor.surname}" />
				<br/>
				<span class="glyphicon glyphicon-envelope">&nbsp</span><b><spring:message code="actor.email" />: </b> 
				<jstl:out value="${actor.email}" />
				<br/>
				<span class="glyphicon glyphicon-earphone">&nbsp</span><b><spring:message code="actor.phone" />: </b> 
				<jstl:out value="${actor.phone}" />
				<br/>
			</div>

		
		</div>
				
				<br><br>
			
			<div class="row">
			
				<div class="text-center">	
					<a href="profile/user/edit.do?userId=${profile.id}">
						<button type="button" class="btn btn-lg btn-success col-xs-12 col-md-6 col-md-offset-3">
							<spring:message code="user.edit" />
						</button>
					</a>
				</div>
			</div>
		
		
</form:form>
</security:authorize>