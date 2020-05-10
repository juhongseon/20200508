import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LotteOnDetailCrawler {
	
	public static LotteOnProductVO get(String no) {
		LotteOnProductVO vo = new LotteOnProductVO();
		String modelData = "";
		for(int i=0; i<no.length(); i++) {
			modelData += no.charAt(i);
			if(i%2==1) modelData += "/";
		}
		
		try {
			Document detailDoc = Jsoup.connect("https://red.lotteon.com/goodsdetail")
					.data("view","type1-raw")
					.data("model","itemdetail/"+modelData+"/DSCRP_"+no)
					.get();
			
			System.out.println(detailDoc);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return vo;
	}

}
