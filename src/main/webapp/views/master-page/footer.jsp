<%--
 * footer.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date" />



<div class="col-sm-4 col-sm-offset-1">
	<div class="spm-footer-gly text-left">
		<span class="glyphicon glyphicon-envelope"></span>
		<span class="lead">&nbsp <spring:message code="master.page.contact" /></span>
		
		<ul class="list-unstyled text-left">
			<li>contacto@sportsmate.com</li>
		</ul>
	</div>
</div>

<div class="col-sm-4">
	<div class="spm-footer-gly text-left">
		<span class="glyphicon glyphicon-floppy-disk"></span>
		<span class="lead">&nbsp<spring:message code="master.page.terms" /></span>
		
		<ul class="list-unstyled text-left">
			<li>
				<a href="conditions/laws.do">
					<spring:message code="master.page.laws" />
				</a>
			</li>
			<li>
				<a href="conditions/cookies.do">
					<spring:message code="master.page.cookies" />
				</a>
			</li>
		</ul>
	</div>
</div>

<div class="col-sm-3">
	<div class="spm-footer-gly text-left">
		<span class="glyphicon glyphicon-info-sign"></span>
		<span class="lead">&nbsp <spring:message code="master.page.aboutus" /></span>
		
		<ul class="list-unstyled text-left">
			<li><a href="http://github.com/ISPPG5/sportsmate" target="blank">Github</a></li>
		</ul>
	</div>
</div>

<br>
<div class="col-xs-12 spm-copyright">
	<div class="spm-footer-gly text-center">
		Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> SportsMate Co., Inc.
	</div>
</div>
