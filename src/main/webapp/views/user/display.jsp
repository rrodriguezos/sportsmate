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
			
	<div class="row">
		<div class='col-md-3 spm-profile-picture-div text-center'> 
			<jstl:if test="${userImagen==true}">
				<img src="images/default_profile.jpg" alt="Default profile" class="img-thumbnail .spm-profile-picture" />
			</jstl:if>
			
	
			<jstl:if test="${userImagen == false}">					
					<img style="position: relative; width: 250px; height: 250px;" src="upload/imageUser.do?userId=${user.id}"/>
			</jstl:if>
			
				<br><br>
				<div>
					
					<jstl:if test="${rating != 0.0}">
						<spring:message code="user.rating" />
						<jstl:out value="${rating}" />
					</jstl:if>
					

				</div>
		</div>
			
			<div class="col-md-1 hidden-sm hidden-xs spm-profile-margin"></div>
			
			<div class='col-md-5'> 
				<h3>
				<spring:message code="user.personalInformation" />
				</h3>
				<br>
					
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="user.name" />: </b> 
				<jstl:out value="${user.name}" />
				<br/>
				<span class="glyphicon glyphicon-font">&nbsp</span><b><spring:message code="user.surname" />: </b> 
				<jstl:out value="${user.surname}" />
				<br/>
				<span class="glyphicon glyphicon-envelope">&nbsp</span><b><spring:message code="user.email" />: </b> 
				<jstl:out value="${user.email}" />
				<br/>
				<span class="glyphicon glyphicon-earphone">&nbsp</span><b><spring:message code="user.phone" />: </b> 
				<jstl:out value="${user.phone}" />
				<br/>
			</div>

		
		</div>
			<br>			
			<div class="row">	
				<div class="col-xs-12 col-md-3">
					<a href="user/edit.do?userId=${user.id}">
						<button type="button" class="btn btn-lg btn-success col-xs-12 col-md-10 col-md-offset-1">
							<spring:message code="user.edit" />
						</button>
					</a>
				</div>
			</div>	   
		   
		   
		   
	
		   
