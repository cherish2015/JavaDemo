package test.po;

public class TOrder {
	
	private String Id;
	private String c_orders_code;
	private long c_user_qq;
	private String c_goods_code;
	private int c_commission;
	private int c_status;
	
	public static final int Order_Invalid = -1;
	public static final int Order_Payed = 0;
	public static final int Order_Overdue = 1;
	public static final int Order_Completed = 2;
	
	public TOrder(String c_orders_code, String c_goods_code, int c_commission) {
		super();
		this.c_orders_code = c_orders_code;
		this.c_goods_code = c_goods_code;
		this.c_commission = c_commission;
		this.c_user_qq = 987654321;
	}
	
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getC_orders_code() {
		return c_orders_code;
	}
	public void setC_orders_code(String c_orders_code) {
		this.c_orders_code = c_orders_code;
	}
	public long getC_user_qq() {
		return c_user_qq;
	}
	public void setC_user_qq(long c_user_qq) {
		this.c_user_qq = c_user_qq;
	}
	public String getC_goods_code() {
		return c_goods_code;
	}
	public void setC_goods_code(String c_goods_code) {
		this.c_goods_code = c_goods_code;
	}
	public int getC_commission() {
		return c_commission;
	}
	public void setC_commission(int c_commission) {
		this.c_commission = c_commission;
	}
	public int getC_status() {
		return c_status;
	}
	
	public void setC_status(int c_status) {
		this.c_status = c_status;
	}
	
	public void setC_status(String orderStatus) {
		int c_status = Order_Invalid;
		if (orderStatus.equalsIgnoreCase("订单付款")) {
			c_status = Order_Payed;
		} else if(orderStatus.equalsIgnoreCase("订单失效")){
			c_status = Order_Overdue;
		} else if(orderStatus.equalsIgnoreCase("订单结算")){
			c_status = Order_Completed;
		}
		setC_status(c_status);
	}
	
	

}
