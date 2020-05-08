import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainCrawler {

	public static void main(String[] args) {
		
		int i = 0;
		while(true) {
			
			System.out.println(i+"================================");
			
			try {
				Document doc = Jsoup.connect("http://cu.bgfretail.com/product/view.do?category=product&gdIdx="+i).get();
				
				try {
					Elements locs = doc.select("ul.location li");
					List<String> categorys = new ArrayList<String>();
					for(Element loc : locs) {
						categorys.add(loc.text());
					}
					categorys.remove(0);
					for(String category : categorys) {
						System.out.println("category : " + category);
					}
				} catch (Exception e) {}
				
				try {
					String imgSrc = doc.selectFirst("div.prodDetail-w img").attr("src");
					System.out.println("img src : " + imgSrc);
				} catch (Exception e1) {}
				
				boolean isNew = true;
				try {
					doc.selectFirst("span.tag img").attr("src");
				} catch (Exception e) {
					isNew = false;
				}
				System.out.println("isNew : " + isNew);
				
				try {
					String title = doc.selectFirst("div.prodDetail-e p.tit").text();
					System.out.println("title : " + title);
				} catch (Exception e) {}
				
				try {
					String price = doc.selectFirst("dd.prodPrice span").text();
					System.out.println("price : " + price);
				} catch (Exception e) {}
				
				try {
					String detail = doc.selectFirst("ul.prodExplain li").text();
					System.out.println("detail : " + detail);
				} catch (Exception e) {}
				
				try {
					Elements taglist = doc.select("div.prodInfo ul.prodTag li");
					List<String> tags = new ArrayList<String>();
					for(Element tag : taglist) {
						tags.add(tag.text());
					}
					for(String tag : tags) {
						System.out.println("tag : " + tag);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {}
			
			System.out.println("================================");
			
			if(i==Integer.MAX_VALUE) i=-1;
			
			i++;
		}

	}

}






























