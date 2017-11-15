package com.hanbit.cock.api.aop;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hanbit.cock.api.data.Chickens;
import com.hanbit.cock.api.data.DataCollection;
import com.hanbit.cock.api.data.Meats;
import com.hanbit.cock.api.data.Noodles;
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
	
	@AfterReturning(pointcut="emblemServiceMethod()", returning = "retVal") 
	public void afterEmblem(JoinPoint joinPoint, Object retVal ) throws Throwable {
		Signature signature = joinPoint.getSignature(); 
		
		String name = signature.getName();
		System.out.println(name + "() Method");

		if (name.equals("setRestAndArticleSave")) {
			Map map = (Map) retVal;
			setRestAndArticleSaveEmblemService(map);
		}
		else if (name.equals("removeArticle")) {
			
		}
	}
	
	@Around("execution(public * com.hanbit.cock.api.insert.dao.CockInsertDAO.insertRest(..))")
    public Object aroundTargetMethod(ProceedingJoinPoint pjp) throws Throwable {
		RestVO rest = (RestVO) pjp.getArgs()[0];
		ArticleVO article = rest.getArticles().get(0);
		Object retVal = null;
		
		try {
			retVal = pjp.proceed();
			System.out.println("insertRest() Method");
			// 식당 입력 카운트 증가
			DataCollection collection = new DataCollection();
			
			collection.setUid(article.getUid());
			collection.setAttribute("regist_rest");
			
			int restNum = cockEmblemDAO.increaseCollection(collection);
			
			if (restNum == 1) {
				collection.setTitle("firstRest");
				cockEmblemDAO.insertCollection(collection);
			}
			else if (restNum == 100) {
				collection.setTitle("hundredRests");
				cockEmblemDAO.insertCollection(collection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
        return retVal;
	}
	
	private boolean setRestAndArticleSaveEmblemService(Map map) throws Throwable {
		RestVO rest = (RestVO) map.get("RestVO");
		ArticleVO article = rest.getArticles().get(0);
		String uid = article.getUid();
		DataCollection collection = new DataCollection();
		
		collection.setUid(uid);
		collection.setAttribute("regist_article");
		int articleNum = cockEmblemDAO.updateArticleCount(collection);
		
		try {
			if (articleNum == 1) { // 기사 첫 입력 - firstArticle
				collection.setTitle("firstArticle");
				cockEmblemDAO.insertCollection(collection);
				System.out.println("achive firstArticle : " + uid);
			}
			else if (articleNum == 100) { // 기사 100 입력 - hundredArticles
				collection.setTitle("hundredArticles");
				cockEmblemDAO.insertCollection(collection);
				System.out.println("achive hundredArticles : " + uid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rest.getTags() != null && rest.getMenus() != null) {
			Noodles noodles = new Noodles();
			Meats meat = new Meats();
			Chickens chickens = new Chickens();
			
			try {
				if (matchCollection(rest, noodles)) {
					noodles.setUid(uid);
					System.out.println("increase noodle : " + uid);
					if (cockEmblemDAO.increaseCollection(noodles) == 100) {
						cockEmblemDAO.insertCollection(noodles);
						System.out.println("achive noodle : " + uid);
					}
				}
				else if (matchCollection(rest, meat)) {
					meat.setUid(uid);
					System.out.println("increase meat : " + uid);
					if (cockEmblemDAO.increaseCollection(meat) == 100) {
						cockEmblemDAO.insertCollection(meat);
						System.out.println("achive meat : " + uid);
					}
				}
				else if (matchCollection(rest, chickens)) {
					chickens.setUid(uid);
					System.out.println("increase chicken : " + uid);
					if (cockEmblemDAO.increaseCollection(chickens) == 100) {
						cockEmblemDAO.insertCollection(chickens);
						System.out.println("achive chicken : " + uid);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	private boolean matchCollection(RestVO rest, DataCollection collection) {
		int matchCount = 0;
		
		for (int i=0; i<rest.getTags().size(); i++) {
			for (String str : collection.getCollection()) {
				if (rest.getTags().get(i).getTag().contains(str)) {
					matchCount++;
					
					if (matchCount >= 2) {
						return true;
					}
				}
			}
			
		}
		
		for (int i=0; i<rest.getMenus().size(); i++) {
			for (String str : collection.getCollection()) {
				if (rest.getMenus().get(i).getMenu().contains(str)) {
					matchCount++;
					
					if (matchCount >= 2) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
