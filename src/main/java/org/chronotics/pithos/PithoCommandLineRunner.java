package org.chronotics.pithos;

import java.util.Map;

import javax.annotation.Resource;

import org.chronotics.db.mybatis.MapperMySql;
import org.chronotics.db.mybatis.SqlStatement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PithoCommandLineRunner implements CommandLineRunner {
	
	@Resource(name = "mapperSimpleMySql")
	private MapperMySql mapper;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		if(mapper == null) {
			System.out.println("mapper is null");
			return;
		} else {
			System.out.println(mapper);
		}
		
		SqlStatement sqlStatement = null;
		try {
			sqlStatement = new SqlStatement.Builder()
			.select("*")
			.from("user")
			.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(sqlStatement != null) {
			Map<String, Object> result = 
					mapper.selectOne(sqlStatement);
			System.out.println(result.size());
		}
	}

}

