package test.mybatis.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import test.po.TOrder;

public interface TestMapper {

	@Select("select t.c_user_qq from  t_commodity t "
			+ "where t.c_orders_code=#{c_orders_code} "
			+ "order by c_create_date dsc limit 0,1 ")
	Long getUserQQBeforeInsert(TOrder order);
	
	@Insert("insert into t_orders(c_orders_code,c_user_qq,c_goods_code,c_commission,c_status) "
			+ "values(#{c_orders_code},#{c_user_qq},#{c_goods_code},#{c_commission},#{c_status})")
	void insertOrder(TOrder order);
	
	@Update("update t_orders set c_status='1' "
			+ "where c_orders_code=#{c_orders_code}")
	void updateOrder(TOrder order);
	
	@Update("update t_user set c_cash=c_cash+#{c_commission} "
			+ "where c_qq=#{c_user_qq}")
	void updateUserCash(TOrder order);
}
