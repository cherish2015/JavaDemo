package test.mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.mybatis.mappers.TestMapper;
import test.po.TOrder;

public class TestMybatis {

	private static Logger log = LoggerFactory.getLogger(TestMybatis.class);

	private static SqlSessionFactory sqlSessionFactory = null;
	
	public static void doImportData(List<TOrder> orders) throws Exception {
		SqlSession ss = getSqlSessionFactory().openSession();
		try {
			TestMapper mapper = ss.getMapper(TestMapper.class);
			for (TOrder order : orders) {
				mapper.insertOrder(order);
				Long c_user_qq = mapper.getUserQQBeforeInsert(order);
				if (c_user_qq == null) {
					continue;
				}
				order.setC_user_qq(c_user_qq);
				if (order.getC_status() == TOrder.Order_Payed) {
					mapper.insertOrder(order);
				} else if (order.getC_status() == TOrder.Order_Overdue) {
					mapper.updateOrder(order);
				} else if (order.getC_status() == TOrder.Order_Completed){
					mapper.updateOrder(order);
					mapper.updateUserCash(order);
				}
			}
		} finally{
			ss.close();
		}
	}

	private static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory != null) {
		}else{
			InputStream input = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("mybatis-conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
			sqlSessionFactory.getConfiguration().addMappers("test.mybatis.mappers");
			log.info("initialized sqlSessionFactory");
		}
		return sqlSessionFactory;
	}

}
