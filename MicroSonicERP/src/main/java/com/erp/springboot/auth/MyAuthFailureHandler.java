package com.erp.springboot.auth;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//자동으로 생성되는 빈 임을 명시하는 어노테이션 
@Configuration
public class MyAuthFailureHandler implements AuthenticationFailureHandler
{	//핸들러 제작을 위해 인터페이스를 구현한 후 클래스 정의한다.
	
	/*
		시큐리티 환경에서 로그인 시 인증오류가 발생되는 여러가지 케이스를 처리하기
		위해 메서드 오버라이딩 후 정의한다. 
	 */
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) 
					throws IOException, ServletException
	{
		String errorMsg = "";
		
		/*
			instanceof 연산자를 이용해서 전달된 예외객체의 종류를 파악한 후 적절한
			에러메세지를 지정한다. 단, 인증관련 메세지는 너무 자세히 기술하지 않는것이
			좋다. 
		 */
		if (exception instanceof BadCredentialsException)
		{
			//로그인 실패시 잠금기능 등이 필요하다면 정의한 후 호출한다.(필수사항아님) 
			loginFailureCnt(request.getParameter("my_id"));
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. "
					+ "다시 확인해 주세요.(1)";
		} else if(exception instanceof InternalAuthenticationServiceException)
		{
			loginFailureCnt(request.getParameter("my_id"));
			errorMsg = "아이디나 비밀번호가 맞지 않습니다. "
					+ "다시 확인해 주세요.(2)";
		} else if (exception instanceof DisabledException)
		{
			errorMsg = "계정이 비활성화되었습니다. 관리자에게 문의하세요(3)";
		} else if(exception instanceof CredentialsExpiredException)
		{
			errorMsg = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요(4)";
		}
			
		
		//request 영역에 에러메세지를 저장한다.
		request.setAttribute("errorMsg", errorMsg);
		//로그인 페이지로 포워드한다. 이때 파라미터 error를 전달한다. 
		request.getRequestDispatcher("/myLogin.do?error").forward(request, response);
	}
	
	//각 업무에 맞게 커스텀해서 사용할 수 있는 메서드 정의 
	public void loginFailureCnt(String username)
	{
		System.out.println("요청 아이디:"+username);
		/* 틀린 횟수 업데이트
		틀린 횟수 조회
		만약 3회 이상 실패했다면 계정 잠금처리 */
	}	
}