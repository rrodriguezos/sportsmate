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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<br>
<div class='table-responsive'>
<display:table name="folders" id="row" requestURI="folder/actor/list.do" 
			   pagesize="5" class="table table-bordered table-hover">

	<spring:message code="folder.name" var="name"/>
	<display:column property="name" title="${name}"  />	
	
	<spring:message code="folder.messages" var="messagesHeader"/>
	<display:column title="${messagesHeader}">
		<a href="message/actor/list.do?folderId=${row.id}">
			<spring:message code="folder.messages" />
		</a>
	</display:column>
			   
</display:table>
</div>

<acme:cancel code="folder.back" url="welcome/index.do" />&nbsp;

<input type="button" name="create" class="btn btn-md btn-success" value="<spring:message code="message.create"/>" 
	   	   onclick="javascript: window.location.replace('message/actor/create.do');" />

