import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RecipeIngrdCrawler {
	
	public void get(List<String> recipeLinkList) {
		
		//int confirmedCnt = 0;
		//int cnt = 0;
		
		for(String recipeLink : recipeLinkList) {
			try {
				Document doc = Jsoup.connect("https://www.10000recipe.com"+recipeLink).get();
				System.out.println("link : " + recipeLink);
				try {
					Elements subRecipes = doc.select("#divConfirmedMaterialArea ul");
					if(subRecipes.size()==0) throw new Exception();
					for(Element subRecipe : subRecipes) {
						String title = subRecipe.selectFirst("b").text();
						System.out.println("title : " + title);
						
						Elements ingrdNo_amountList = subRecipe.select("a");
						for(Element ingrdNo_amount : ingrdNo_amountList) {
							String ingrdLink = ingrdNo_amount.attr("href");
							int ingrdNo = Integer.parseInt(ingrdLink.substring(ingrdLink.indexOf("('")+2,ingrdLink.indexOf("')")));
							
							String amount = ingrdNo_amount.selectFirst("span.ingre_unit").text();
							System.out.println(ingrdNo + " : " + amount);
						}
					}
					//confirmedCnt++;
				} catch (Exception e) {
					Elements subRecipeS = doc.select("div.cont_ingre dl");
					for(Element subRecipe : subRecipeS) {
						String title = subRecipe.selectFirst("dt").text();
						System.out.println("title : " + title);
						
						String[] ingrdName_amountArray = subRecipe.selectFirst("dd").text().split(",");
						for(String ingrdName_amount : ingrdName_amountArray) {
							ingrdName_amount = ingrdName_amount.trim();
							String ingrdName = ingrdName_amount.substring(0, ingrdName_amount.lastIndexOf(" "));
							String amount = ingrdName_amount.substring(ingrdName_amount.lastIndexOf(" ")+1);
							System.out.println(ingrdName + " : " + amount);
						}
					}
					//cnt++;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			//System.out.println("confirmedCnt : " + confirmedCnt);
			//System.out.println("cnt : " + cnt);
			System.out.println("================================");
		}
		
	}
	
	public static void main(String[] args) {
		
		List<String> recipeLinkList = new DAO().getRecipeLinkList();
		
		new RecipeIngrdCrawler().get(recipeLinkList);
		
	}

}
