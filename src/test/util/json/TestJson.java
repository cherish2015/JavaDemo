package test.util.json;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.ITest;

public class TestJson implements ITest {
	
	private static Logger log = LoggerFactory.getLogger(TestJson.class);

	@SuppressWarnings("unchecked")
	@Test
	@Override
	public void test() {
		log.info("test josn");
		String raw2 = "{\"data\":{\"paginator\":"
				+ "{\"length\":1,\"offset\":0,\"beginIndex\":1,"
				+ "\"endIndex\":1,\"page\":1,\"items\":1,\"itemsPerPage\":40,"
				+ "\"previousPage\":1,\"nextPage\":1,\"pages\":1,\"firstPage\":1,"
				+ "\"lastPage\":1,\"slider\":[1]},"
				+ "\"qpResult\":{\"rewrite\":null,\"navCats\":null,"
				+ "\"relaSearchArr\":[\"detail.com item.htm\",\"item\","
				+ "\"item\",\"michael kors jet set item\","
				+ "\"http item.com\",\"item\",\"item.com\","
				+ "\"http item.com ite\"]},"
				+ "\"searchurl\":\"http://tatistic=field=prop_vid.kv,cusvalue=403,cattype=custom,"
				+ "count=400&_ps=commend&ss=ends&s=0&n=40&src=--10.1\","
				+ "\"keyword\":\"http://.htm?id=409011\",\"pidvidMap\":null,"
				+ "\"searchType\":\"1\",\"pagelist\":[{\"groupId\":null,"
				+ "\"userId\":\"50bd00e7645407ccfe1ad230694e01e5\","
				+ "\"auctionUrl\":\"http://item.htm?id=40901129676\","
				+ "\"userNumberId\":\"777777525\",\"zkPrice\":62.79,"
				+ "\"pictUrl\":\"http://gFVXXXXXIapXXXXXXXXXX_!!0-item_pic.jpg\","
				+ "\"title\":\"20148#\",\"groupRate\":0,\"userType\":0,"
				+ "\"commissionRatePercent\":20.0,\"nick\":\"逍\","
				+ "\"shopUrl\":\"http://htm?user_number_id=7777\","
				+ "\"auctionId\":\"4090112\",\"auctionType\":\"b\","
				+ "\"commission\":0.0,\"p4pPrice\":\"\",\"creativeRate\":\"\","
				+ "\"zkRate\":10.0,\"biz30day\":0,\"shopKeeperCardURL\":null,"
				+ "\"reservePrice\":62.8,\"commentCount\":\"\",\"tradeCount\":0,"
				+ "\"commissionRate\":2000.0,\"creativeCommissionRatePercent\":0.0,"
				+ "\"creativeRealRatePercent\":0.0,\"creativeCalCommission\":0.0,"
				+ "\"commissionMoney\":null,\"totalFeeMoney\":{\"currency\":\"CNY\","
				+ "\"currencyCode\":\"CNY\",\"amount\":0.00,\"cent\":0,"
				+ "\"centFactor\":100,\"displayUnit\":\"元\"},\"totalFee\":0.0,"
				+ "\"totalNum\":0,\"dbId\":null,\"calCommission\":12.56,"
				+ "\"groupCommission\":0.0,\"calCommissionMoney\":{\"currency\":\"CNY\","
				+ "\"currencyCode\":\"CNY\",\"amount\":12.56,\"cent\":1256,"
				+ "\"centFactor\":100,\"displayUnit\":\"元\"},\"paysubsidyRate\":null,"
				+ "\"paysubsidyCommission\":null,\"oldCommissionRate\":0.0,"
				+ "\"hasCommonCampaign\":false,\"groupIds\":\"0\",\"groupRates\":\"0\","
				+ "\"p4pPriceYuan\":0.0,\"zkType\":\"\"}]},\"info\":{\"message\":null,"
				+ "\"ok\":true},\"ok\":true}";
		
		ObjectMapper om = new ObjectMapper();
		try {
			Map<String, Map<String, Object>> maps = om.readValue(raw2, Map.class);
			System.out.println(maps);
			Object OK = maps.get("ok");
			System.out.println(OK);
			Map<String, Object> dataMap = maps.get("data");
			Map<String, Object>  paginatorMap= (Map<String, Object>) dataMap.get("paginator");
			System.out.println(paginatorMap.get("length"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
