package com.hanbit.cock.api.aop;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hanbit.cock.api.emblem.dao.CockEmblemDAO;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.RestVO;

@Aspect 
@Component 
public class EmblemAspecet {

	@Autowired
	private CockEmblemDAO cockEmblemDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(EmblemAspecet.class);
	
	@Pointcut("@annotation(com.hanbit.cock.api.annotation.EmblemUpdate)")
	public void emblemServiceMethod() {
	
	}
	
	@AfterReturning(pointcut = "emblemServiceMethod()", returning = "retVal") 
	public void printString(JoinPoint joinPoint, Object retVal ) {
		Signature signature = joinPoint.getSignature(); 
		
		String name = signature.getName();
		System.out.println(name + " name");

		Map map = (Map) retVal;
		if (name.equals("setRestAndArticleSave")) {
			RestVO rest = (RestVO) map.get("RestVO");
			ArticleVO article = rest.getArticles().get(0);
			String uid = article.getUid();
			
			int restNum = cockEmblemDAO.increaseRestCount(uid);
			
			if (restNum == 1) {
				cockEmblemDAO.achiveFirstRest(uid);
				System.out.println("achive firstRest : " + uid);
			}
			
		}
		
	}
}
