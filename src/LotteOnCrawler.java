import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.SliderUI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LotteOnCrawler {
	
	public static List<LotteOnProductVO> getDataBySearch(String keyword) {
		List<LotteOnProductVO> list = new ArrayList<LotteOnProductVO>();
		
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
					String no = data.selectFirst("a").attr("href");
					no = no.substring(no.indexOf("product/")+8,no.indexOf("?"));
					String company = data.selectFirst("strong.srchProductUnitVendor").text();
					String name = data.selectFirst("div.srchProductUnitTitle").text().substring(company.length()).replace("ⓒ ", "");
					String price = data.selectFirst("span.srchCurrentPrice").text();
					String unitPrice = data.selectFirst("span.srchPerPrice").text();
					
					System.out.println("no : " + no);
					System.out.println("company : " + company);
					System.out.println("product : " + name);
					System.out.println("price : " + price);
					System.out.println("unitPrice : " + unitPrice);
					System.out.println("================");
					
					LotteOnDetailCrawler.get(no);
					if(true) return list;
				} catch (Exception e) {}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static void main(String[] args) {
		
		getDataBySearch("햇반");

	}

}
