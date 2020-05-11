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
					try {
						String no = data.selectFirst("a").attr("href");
						no = no.substring(no.indexOf("product/")+8,no.indexOf("?"));
						if(no.startsWith("/displayad")) continue;
						System.out.println("no : " + no);
					} catch (Exception e) {}
					
					String company;
					try {
						company = data.selectFirst("strong.srchProductUnitVendor").text();
						System.out.println("company : " + company);
						
						try {
							String name = data.selectFirst("div.srchProductUnitTitle").text().substring(company.length()).replace("¨Ï ", "");
							System.out.println("product : " + name);
						} catch (Exception e) {}
					} catch (Exception e) {}
					
					try {
						String price = data.selectFirst("span.srchCurrentPrice").text();
						System.out.println("price : " + price);
					} catch (Exception e) {}
					
					try {
						String unitPrice = data.selectFirst("span.srchPerPrice").text();
						System.out.println("unitPrice : " + unitPrice);
					} catch (Exception e) {}
					
					try {
						String imgSrc = data.selectFirst("div.srchThumbImageWrap img").attr("src");
						System.out.println("imgSrc : " + imgSrc);
					} catch (Exception e) {}
					
					Element rating;
					try {
						rating = data.selectFirst("span.srchRatingScore");
						double score = Double.parseDouble(rating.text().substring(0,rating.text().indexOf("(")));
						System.out.println("score : " + score);
						
						try {
							String strRvCnt = rating.selectFirst("strong").text();
							int rvCnt = Integer.parseInt(strRvCnt.replaceAll("[^0-9]", ""));
							System.out.println("rvCnt : " + rvCnt);
						} catch (Exception e) {}
					} catch (Exception e) {}
					
					try {
						String strMP = data.selectFirst("strong.srchProductMonthlyPurchaseCount").text();
						int monthlyPurchase = Integer.parseInt(strMP.replaceAll(",", ""));
						System.out.println("monthlyPurchase : " + monthlyPurchase);
					} catch (Exception e) {}
					
					System.out.println("================");
					
					//if(true) return list;
				} catch (Exception e) {}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static void main(String[] args) {
		
		getDataBySearch("¼Ò¼¼Áö");

	}

}
