package com.Practice.MyBlog.bean;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Practice.MyBlog.enums.ConfigPath;

@Configuration("DataSourceBean")
public class DataSourceBean {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceBean.class);
	/*
	 * mybatis life cycle
	 * 1. SqlSessionFactoryBuilder 	:  이 클래스는 인스턴스회되어 사용되고 던져질 수 있다. SqlSessionFactory 를 생성한 후 유지할 필요는 없다. 그러므로 SqlSessionFactoryBuilder 인스턴스의 가장 좋은 스코프는 메소드 스코프(예를들면 메소드 지역변수)이다. 여러개의 SqlSessionFactory 인스턴스를 빌드하기 위해 SqlSessionFactoryBuilder를 재사용할 수도 있지만 유지하지 않는 것이 가장 좋다.
	 * 2. SqlSessionFactory 		:  이 클래스는 인스턴스회되어 사용되고 던져질 수 있다. SqlSessionFactory 를 생성한 후 유지할 필요는 없다. 그러므로 SqlSessionFactoryBuilder 인스턴스의 가장 좋은 스코프는 메소드 스코프(예를들면 메소드 지역변수)이다. 여러개의 SqlSessionFactory 인스턴스를 빌드하기 위해 SqlSessionFactoryBuilder를 재사용할 수도 있지만 유지하지 않는 것이 가장 좋다.
	 * 3. SqlSessionFactory 		:  각각의 쓰레드는 자체적으로 SqlSession인스턴스를 가져야 한다. SqlSession인스턴스는 공유되지 않고 쓰레드에 안전하지도 않다. 그러므로 가장 좋은 스코프는 요청 또는 메소드 스코프이다. SqlSession 을 static 필드나 클래스의 인스턴스 필드로 지정해서는 안된다. 그리고 서블릿 프레임워크의 HttpSession 과 같은 관리 스코프에 둬서도 안된다. 어떠한 종류의 웹 프레임워크를 
	 *                                 사용한다면 HTTP 요청과 유사한 스코프에 두는 것으로 고려해야 한다. 달리 말해서 HTTP 요청을 받을때마다 만들고 응답을 리턴할때마다 SqlSession 을 닫을 수 있다. SqlSession 을 닫는 것은 중요하다. 언제나 finally 블록에서 닫아야만 한다. 다음은 SqlSession을 닫는 것을 확인하는 표준적인 형태다.
	 */
	@Bean
	public SqlSessionFactory getSessionFactory() throws IOException
	{
		logger.info("create Bean : {}",ConfigPath.MYBATIS_CONFIG.getValue());
		InputStream inputStream = Resources.getResourceAsStream(ConfigPath.MYBATIS_CONFIG.getValue());
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		return sqlSessionFactory;
	}
	

}
