import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LotteOnCrawler {
	
	public static List<LotteOnVO> getDataBySearch(String keyword) {
		List<LotteOnVO> list = new ArrayList<LotteOnVO>();
		
		try {
			Document srchDoc = Jsoup.connect("https://www.lotteon.com/search/search/search.ecn?render=search&platform=pc&q="+keyword).get();
			String rawHtml = srchDoc.html();
			String srchRes = rawHtml.substring(rawHtml.indexOf("#srchTabProduct"));
			srchRes = srchRes.substring(srchRes.indexOf("<"));
			srchRes = srchRes.substring(0,srchRes.indexOf("noResultHtml")-4);
			srchRes = srchRes.replaceAll("\\\\\"", "\"");
			srchRes = srchRes.replaceAll("(\\\\r|\\\\n)", "");
			srchRes = srchRes.replaceAll("\\\\/", "/");
			
			Document dataDoc = Jsoup.parse(srchRes);
			Elements datas = dataDoc.select("li.srchProductItem");
			for(Element data : datas) {
				try {
					String company = data.selectFirst("strong.srchProductUnitVendor").text();
					String product = data.selectFirst("div.srchProductUnitTitle").text().substring(company.length()).replace("¨Ï ", "");
					String price = data.selectFirst("span.srchCurrentPrice").text();
					String unitPrice = data.selectFirst("span.srchPerPrice").text();
					
					System.out.println("company : " + company);
					System.out.println("product : " + product);
					System.out.println("price : " + price);
					System.out.println("unitPrice : " + unitPrice);
					System.out.println("================");
				} catch (Exception e) {}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static void main(String[] args) {
		
		getDataBySearch("ÇÞ¹Ý");

	}

}
