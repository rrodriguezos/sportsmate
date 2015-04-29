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
			<img src="images/default_profile.jpg" alt="Default profile" class="img-thumbnail .spm-profile-picture" />
				<br><br>
				<div>
					
						<spring:message code="customer.rating" />
						<jstl:out value="${rating}" />
					

				</div>
		</div>
			
			<div class="col-md-1 hidden-sm hidden-xs spm-profile-margin"></div>
			
			<div class='col-md-5'> 
				<h3>
				<spring:message code="customer.personalInformation" />
				</h3>
				<br>
					
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="customer.name" />: </b> 
				<jstl:out value="${customer.name}" />
				<br/>
				<span class="glyphicon glyphicon-font">&nbsp</span><b><spring:message code="customer.surname" />: </b> 
				<jstl:out value="${customer.surname}" />
				<br/>
				<span class="glyphicon glyphicon-envelope">&nbsp</span><b><spring:message code="customer.email" />: </b> 
				<jstl:out value="${customer.email}" />
				<br/>
				<span class="glyphicon glyphicon-earphone">&nbsp</span><b><spring:message code="customer.phone" />: </b> 
				<jstl:out value="${customer.phone}" />
				<br/>
				
				<h3>
				<spring:message code="customer.center" />
				</h3>
				<br>
				
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="customer.cif" />: </b> 
				<jstl:out value="${customer.cif}" />
				<br/>
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="customer.street" />: </b> 
				<jstl:out value="${customer.street}" />
				<br/>
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="customer.zip" />: </b> 
				<jstl:out value="${customer.zip}" />
				<br/>
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="customer.provinceCenter" />: </b> 
				<jstl:out value="${customer.provinceCenter}" />
				<br/>
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="customer.city" />: </b> 
				<jstl:out value="${customer.city}" />
				<br/>
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="customer.nameCenter" />: </b> 
				<jstl:out value="${customer.nameCenter}" />
				<br/>
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="customer.phoneCenter" />: </b> 
				<jstl:out value="${customer.phoneCenter}" />
				<br/>
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="customer.emailCenter" />: </b> 
				<jstl:out value="${customer.emailCenter}" />
				<br/>
				<span class="glyphicon glyphicon-user">&nbsp</span><b><spring:message code="customer.web" />: </b> 
				<jstl:out value="${customer.web}" />
				<br/>
				
				
			</div>

		
		
		   
		   
		   
	