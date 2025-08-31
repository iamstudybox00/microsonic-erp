<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!-- CDN jquery, bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="../css/style.css">

<nav class="navbar">
	<div>
		&nbsp;&nbsp;<a class="navbar-brand" href="/"><img alt="logo" src="../images/micro_sonic_logo.png" style="height: 60px;"></a>	
	</div>
	<div align="right">
		<c:choose>
		    <c:when test="${empty principal}">
		        <button class="basic-button" onclick="location.href='/myLogin.do'">Sign in</button>
		    </c:when>
		    <c:otherwise>
		       	<button class="cancel-button" onclick="location.href='/myLogout.do'">Sign Out</button>
		    </c:otherwise>
	    </c:choose>
	    &nbsp;&nbsp;
	</div>
</nav>