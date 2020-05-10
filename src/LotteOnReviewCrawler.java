import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class LotteOnReviewCrawler {
	
	public static List<LotteOnReviewVO> get(String productNo, int maxCnt) {
		List<LotteOnReviewVO> list = new ArrayList<LotteOnReviewVO>();
		
		Map<String, String> cookies = new HashMap<String, String>();
		cookies.put("_ga", "GA1.1.845898384.1588997877");
		cookies.put("_ga_4D4NCCP4FX", "GS1.1.1589031491.3.1.1589031514.37");
		cookies.put("_fbp", "fb.1.1588997877541.1623609653");
		cookies.put("aam_seg", "aam_seg%3D18416774%7C18441945%7C18416621%7C18416620%7C18416637%7C18416629%7C18416635%7C18205142");
		cookies.put("at_check", "true");
		cookies.put("cto_bundle", "1w-8xl9qWjBaanE0dlMyQzJlV2xVcENYSUM4a0RtalZQT3lQc3FoV1kxMElveGs2eDd0UG9mUHB3bUM0ZlBxU2xzWk1YbnEwSXgzOW4zMEl1OE9yVGtYS2NIN1FXOGZMZTlPTXRaJTJGOU5ieWMlMkY4QSUyQmJHWHoyWkJpR1FtYnoySXNFaWRVcg");
		cookies.put("mbox", "PC#ab27e253126943a98e2534ff50f92c55.28_0#1652276293|session#2ea51093769b4d04805d1db436a8727b#1589033354");
		cookies.put("AMCV_443A1C095C0A82400A495E92%40AdobeOrg", "-408604571%7CMCIDTS%7C18392%7CMCMID%7C08875047726516142644216441441196943012%7CMCAAMLH-1589636292%7C11%7CMCAAMB-1589636292%7C6G1ynYcLPuiQxYZrsz_pkqfLG9yMXBpb2zX5dvJdYQJzPXImdj0y%7CMCCIDH%7C1517268723%7CMCOPTOUT-1589038692s%7CNONE%7CMCSYNCSOP%7C411-18399%7CvVersion%7C4.6.0");
		cookies.put("ch_dtl_no", "1000617");
		cookies.put("ch_no", "100195");
		cookies.put("ch_typ_cd", "DI02");
		cookies.put("crss_rte_nm", "LM");
		cookies.put("mall_no", "4");
		cookies.put("site_no", "1");
		cookies.put("ppup_pc_yn", "Y");
		cookies.put("AMCVS_443A1C095C0A82400A495E92%40AdobeOrg", "1");
		cookies.put("_gcl_au", "1.1.1448143488.1588997877");
		cookies.put("ch_csf_cd", "DI");
		cookies.put("crss_ntm", "1");
		cookies.put("fnl_crss_rte_nm", "");
		cookies.put("infw_mall_no", "4");
		cookies.put("infw_mdia_cd", "PC");
		cookies.put("pcs_grp", "ETC");
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("sitmNoList","[\""+productNo+"_001\"]");
		data.put("mallNo","4");
		data.put("searchKeyword","");
		data.put("synList","[]");
		data.put("ctntDpYn","");
		data.put("repurchaseRvYn","");
		data.put("myStoreYn","");
		data.put("pdStfdVal","");
		data.put("custAdtnArtl","[]");
		data.put("itmOpt","[]");
		data.put("lrtrNoList","[]");
		data.put("sort","RANK");
		data.put("pageNo","1");
		data.put("rowsPerPage","5");
		
		try {
			Connection.Response response = Jsoup.connect("https://pbf.lotteon.com/review/v1/productDetail/getProductReviewSearchList")
					.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1 Safari/605.1.15")
					.referrer("https://www.lotteon.com/")
					.header("Content-Type", "application/json;charset=utf-8")
					.header("Pragma", "no-cache")
					.header("Accept", "application/json, text/plain, */*")
					.header("Expires", "0")
					.header("Host", "pbf.lotteon.com")
					.header("Cache-Control", "no-cache, no-store, must-revalidate")
					.header("Accept-Language", "ko-kr")
					.header("Accept-Encoding", "br, gzip, deflate")
					.header("Origin", "https://www.lotteon.com")
					.header("Content-Length", "234")
					.header("Connection", "keep-alive")
					.cookies(cookies)
					.data(data)
					.method(Connection.Method.POST)
					.execute();
			
			System.out.println(response.body());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
