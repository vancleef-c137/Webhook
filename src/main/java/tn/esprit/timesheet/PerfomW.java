package tn.esprit.timesheet;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerfomW {
	private static final Logger l = LogManager.getLogger(ContratServiceTest.class);
	
	@Around("execution( * tn.esprit.timesheet.service.*.*(..))")
	public void perfomLog(ProceedingJoinPoint pjp) throws Throwable{
		long start = System.currentTimeMillis();
		pjp.proceed();
		long elapsed = System.currentTimeMillis() - start;
		l.info("execution time: "+elapsed+" ms");
	}
}
