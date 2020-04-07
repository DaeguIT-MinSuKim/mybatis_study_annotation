package mybatis_study_annotation.jdbc;

import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPFactory extends UnpooledDataSourceFactory {
	private static HikariDataSource dataSrouce;
	private static int minIdle = 10;
	private static int maxPoolSize = 100;
	
	public HikariCPFactory() {
		try {
			Properties prop = Resources.getResourceAsProperties("application.properties");
			HikariConfig cfg = new HikariConfig(prop);
            dataSrouce = new HikariDataSource(cfg);
            dataSrouce.setMinimumIdle(minIdle);
            dataSrouce.setMaximumPoolSize(maxPoolSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HikariDataSource getDataSource() {
		return dataSrouce;
	}

}
