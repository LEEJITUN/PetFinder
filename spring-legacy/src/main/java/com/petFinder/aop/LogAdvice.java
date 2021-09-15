package com.petFinder.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/*

	AOP(Aspect ORiented Programming : ���� ����
	: �ֺ��� ����(���� ���)�� �и��ؼ� �����ϴ� ���
	�������� AOP����� Ÿ�� �޼ҵ� ȣ�� �������� �ֿ� ������ �ֺ��� ������ ���ս�����


	- Advice : �ֺ��� ����(������)�� ���� Ŭ����
	- Target : �ֿ� ������ ������ �ִ�, �ֺ��� ������ ����� ��� ������Ʈ 
	- Join Point : �ֺ��� ������ ����� �� �ִ� �޼ҵ� �ĺ���
	- Point Cut : ��������Ʈ(�ĺ�)�� �߿� ���� ���ս�ų �޼ҵ带 ����.
*/

@Aspect // �ֺ��� ������ ���� �����̽� Ŭ���� ǥ��
@Component // ������ ������ ���
public class LogAdvice {

	
	@Before("execution( * com.petFinder.service.MemberService..*(..) )")
	public void logBefore() {
		System.out.println("==================== logBefore() ====================");
	}
	
	// @Around ��� �� �Ű������� ProceedingJoinPoint Ÿ���� �����ϰ� 
	// ����Ÿ���� Object�� �Ǿ�� ��
	@Around("execution(* com.petFinder.service.MemberService.*(..) )")
	public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("==================== logBeTime() ====================");
		
		Object[] args = joinPoint.getArgs();
		List<Object> argsList = Arrays.asList(args);
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getName();
		
	    System.out.println("�޼ҵ�� : " + methodName + ", �Ű����� : " + argsList);
      
	    
		long beginTime = System.currentTimeMillis();
      
		Object result = joinPoint.proceed(); // ���� Ÿ�� �޼ҵ带 ȣ����(*)
		System.out.println("result : " + result);
  
		long endTime = System.currentTimeMillis();
  
		long diff = endTime - beginTime;
		System.out.println("�޼ҵ� ����ð� : " + diff + "ms");
		
		
		return result;
   }

	
}
