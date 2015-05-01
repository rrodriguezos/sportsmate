<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
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


<!-- <p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> -->

<br>

<!-------------->
<!-- Carousel -->
<!-------------->

<div class="col-xs-12 col-lg-10 col-lg-offset-1">
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="4000">
	  
	  <!-- Indicators -->
	  
	  <ol class="carousel-indicators">
	    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
	    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
	  </ol>
	
	  <!-- Wrapper for slides -->
	  
	  <div class="carousel-inner" role="listbox">
	    <div class="item active">
	      <img src="images/carousel_01.jpg" alt="Carousel_image_01">
	      <div class="carousel-caption">
	      <div class="spm-carousel-text-bck">
	        <span class="spm-carousel-text">Centro Deportivo Antonio Álvarez</span>
	        <p>Ronda de Triana, 5 41010 Sevilla</p>
	      </div>
	      </div>
	    </div>
	    
	    <div class="item">
	      <img src="images/carousel_02.jpg" alt="Carousel_image_02">
	      <div class="carousel-caption">
	      <div class="spm-carousel-text-bck">
	        <span class="spm-carousel-text">Centro Deportivo Antonio Álvarez</span>
	        <p>Ronda de Triana, 5 41010 Sevilla</p>
	      </div>
	      </div>
	    </div>
	    
	    <div class="item">
	      <img src="images/carousel_03.jpg" alt="Carousel_image_03">
	      <div class="carousel-caption">
	      <div class="spm-carousel-text-bck">
	        <span class="spm-carousel-text">Centro Deportivo Antonio Álvarez</span>
	        <p>Ronda de Triana, 5 41010 Sevilla</p>
	      </div>
	      </div>
	    </div>
	    <!-- ...  -->
	  </div>
	
	  <!-- Controls -->
	  
	  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	
	    <span class="sr-only">Previous</span>
	  </a>
	  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	</div>
</div>

<!-- Carousel's end -->


<!-- Label -->

<div class="col-xs-12 text-center spm-advertise">
	<span class="glyphicon glyphicon-bookmark" aria-hidden="true">&nbsp</span>
	<span class=""><spring:message code="welcome.adverisewithus" /></span>
</div>



<!-- Groups -->

<div class="row">
	<div class="col-xs-12 text-center">
		<div class="col-xs-12 col-md-4 welcome-group">
			<div class="welcome-group-inner">
				<div class="welcome-group-header">
					<span class="glyphicon glyphicon-user"></span>
					<span class="spm-glyphicon-welcome-text"><spring:message code="welcome.users" /></span>
				</div>
				<div class="text-center">
					<p><spring:message code="welcome.users.caption" /></p>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12 col-md-4 welcome-group">
			<div class="welcome-group-inner">
				<div class="welcome-group-header">
					<span class="glyphicon glyphicon-home"></span>
					<span class="spm-glyphicon-welcome-text"><spring:message code="welcome.customers" /></span>
				</div>
				<div class="text-center">
					<p><spring:message code="welcome.customers.caption" /></p>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-md-4 welcome-group">
			<div class="welcome-group-inner">
				<div class="welcome-group-header">
					<span class="glyphicon glyphicon-flag"></span>
					<span class="spm-glyphicon-welcome-text"><spring:message code="welcome.events" /></span>
				</div>
				<div class="text-center">
					<p><spring:message code="welcome.events.caption" /></p>
				</div>
			</div>
		</div>
		
	</div>
</div>
