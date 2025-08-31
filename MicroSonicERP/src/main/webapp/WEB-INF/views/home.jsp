<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Micro Sonic ERP</title>
		<style>
		button {
		  border: none;
		  color: black;
		  padding: 7.5px 16px;
		  text-align: center;
		  text-decoration: none;
		  display: inline-block;
		  font-size: 16px;
		  margin: 2px 1px;
		  cursor: pointer;
		  border-radius: 20px;
		}
		.cancel-button {
		  color: red;
		}
		.submit-button {
		  background-color: #C4D9FF;
		}
		.basic-button {
		  background-color: #C5BAFF;
		}
		.reset-button {
		  background-color: #fffcee;
		}
		</style>
	</head>
	<body>
		<%@ include file="topnav.jsp" %>
		<%@ include file="SideBar.jsp" %>
		<div class="main-content">
			<button class="cancel-button">cancel-button</button>
			<button class="submit-button">submit-button #C4D9FF</button>
			<button class="basic-button">basic-button #C5BAFF</button>
			<button class="reset-button">reset-button #fffcee</button>
		</div>
	</body>
</html>