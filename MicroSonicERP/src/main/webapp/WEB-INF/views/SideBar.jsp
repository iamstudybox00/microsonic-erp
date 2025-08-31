<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>

<html>
<head>
	<link href="/css/SideBarStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="sidebar">
		<nav class="flex-column">
			<div class="py-2">
				<a class="collapsed sideElement p-2" data-bs-toggle="collapse"
					href="#collapseInventory" role="button" aria-expanded="false"
					aria-controls="collapseInventory"> 재고관리 </a>
			</div>
			<div class="collapse" id="collapseInventory">
				<nav class="sb-sidenav-menu-nested">
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/allItemsView">품목관리</a>
					</div>
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/inventory/index.html">재고관리</a>
					</div>
				</nav>
			</div>

			<div class="py-2">
				<a class="collapsed sideElement p-2" data-bs-toggle="collapse"
					href="#collapseBusiness" role="button" aria-expanded="false"
					aria-controls="collapseBusiness"> 영업관리 </a>
			</div>
			<div class="collapse" id="collapseBusiness">
				<nav class="sb-sidenav-menu-nested">
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/quotationLists">견적서</a>
					</div>
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/POLists">수주서</a>
					</div>
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/">거래처관리</a>
					</div>
				</nav>
			</div>

			<div class="py-2">
				<a class="collapsed sideElement p-2" data-bs-toggle="collapse"
					href="#collapseAccounting" role="button" aria-expanded="false"
					aria-controls="collapseAccounting"> 회계관리 </a>
			</div>
			<div class="collapse" id="collapseAccounting">
				<nav class="sb-sidenav-menu-nested">
					<div class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="http://localhost:5173/transactionList/1">전표</a>
					</div>
				</nav>
			</div>

			<div class="py-2">
				<a class="collapsed sideElement p-2" data-bs-toggle="collapse"
					href="#collapseFinance" role="button" aria-expanded="false"
					aria-controls="collapseFinance"> 금융관리 </a>
			</div>
			<div class="collapse" id="collapseFinance">
				<nav class="sb-sidenav-menu-nested">
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/">자산</a>
					</div>
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/">입출금내역</a>
					</div>
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/">이체</a>
					</div>
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/">미수금</a>
					</div>
				</nav>
			</div>

			<div class="py-2">
				<a class="collapsed sideElement p-2" data-bs-toggle="collapse"
					href="#collapseHumanResource" role="button" aria-expanded="false"
					aria-controls="collapseHumanResource"> 인사관리 </a>
			</div>
			<div class="collapse" id="collapseHumanResource">
				<nav class="sb-sidenav-menu-nested">
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/">조직도</a>
					</div>
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/">급여 기준 정보</a>
					</div>
					<div
						class="sideLinkDetail p-2 ${fn:contains(currentPath, '/링크를 입력하세요') ? 'activatedLink' : ''}">
						<a href="/test">복리후생 등록</a>
					</div>
				</nav>
			</div>
		</nav>
	</div>
</body>
</html>

