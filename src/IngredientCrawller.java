import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class IngredientCrawller {

	public void get() {
		for (int i = 0; i <= 1100; i++) {
			try {
				Document data = Jsoup
						.connect("https://www.10000recipe.com/bbs/ajax.html?q_mode=getMaterialContents&seq=" + i).get();
				String doc = decode(data.html());
				
				doc = doc.replaceAll("(\\\\r|\\\\n|\\\\&quot;)", "");
				doc = doc.replaceAll("&lt;", "<");
				doc = doc.replaceAll("&gt;", ">");
				//System.out.println(doc);
				
				IngredientVO vo = new IngredientVO();
				
				System.out.println("no:"+i+" =======================================================");
				vo.setNo(i);
				
				String mname = doc.substring(doc.indexOf("mname\":\"")+8,doc.indexOf("\",\""));
				System.out.println("mname:" + mname);
				vo.setMname(mname);
				
				try {
					doc = doc.substring(doc.indexOf("ingredient_pic"));
					doc = doc.substring(doc.indexOf("background: url(")+16);
					String imgSrc = doc.substring(0,doc.indexOf(")")).replaceAll("\\\\ ", "/");
					System.out.println("imgSrc:"+imgSrc);
					vo.setImgSrc(imgSrc);
					//System.out.println(doc);
				} catch (Exception e2) {}
				
				try {
					doc = doc.substring(doc.indexOf("제철"));
					doc = doc.substring(doc.indexOf("<td>")+4);
					String season = doc.substring(0,doc.indexOf("<"));
					System.out.println("season:"+season);
					vo.setSeason(season);
					//System.out.println(doc);
				} catch (Exception e1) {}
				
				try {
					doc = doc.substring(doc.indexOf("보관온도"));
					doc = doc.substring(doc.indexOf("<td>")+4);
					String temprt = doc.substring(0,doc.indexOf("<")-1);
					System.out.println("temprt:"+temprt);
					vo.setTemprt(temprt);
					//System.out.println(doc);
				} catch (Exception e1) {}
				
				try {
					doc = doc.substring(doc.indexOf("당 열량")-15);
					doc = doc.substring(doc.indexOf("200\">")+5);
					String calUnit = doc.substring(0,doc.indexOf("<"));
					System.out.println("calUnit:"+calUnit);
					vo.setCalUnit(calUnit);
					//System.out.println(doc);
					
					doc = doc.substring(doc.indexOf("<td>")+4);
					String unitCal = doc.substring(0,doc.indexOf("<"));
					System.out.println("unitCal:"+unitCal);
					vo.setUnitCal(unitCal);
					//System.out.println(doc);
				} catch (Exception e1) {}
				
				try {
					doc = doc.substring(doc.indexOf("잘맞는 음식"));
					doc = doc.substring(doc.indexOf("<td>")+4);
					String fit = doc.substring(0,doc.indexOf("<"));
					System.out.println("fit:"+fit);
					vo.setFit(fit);
					//System.out.println(doc);
				} catch (Exception e1) {}
				
				try {
					doc = doc.substring(doc.indexOf("ingredient_cont_tag"));
					String effectsHtml = doc.substring(0,doc.indexOf("<dl"));
					String effects = "";
					try {
						while(true) {
							effectsHtml = effectsHtml.substring(effectsHtml.indexOf("q=")+2);
							String effect = effectsHtml.substring(0,effectsHtml.indexOf("\""));
							effects += effect+"|";
							effectsHtml = effectsHtml.substring(effectsHtml.indexOf("target"));
						}
					} catch (Exception e) {}
					effects = effects.substring(0,effects.lastIndexOf("|"));
					effects = effects.substring(0,effects.lastIndexOf("|"));
					System.out.println("effects:"+effects);
					vo.setEffects(effects);
				} catch (Exception e) {}
				
				try {
					doc = doc.substring(doc.indexOf("구입요령"));
					doc = doc.substring(doc.indexOf("<dd>")+4);
					String guide = doc.substring(0,doc.indexOf("<")).trim();
					System.out.println("guide:"+guide);
					vo.setGuide(guide);
					//System.out.println(doc);
				} catch (Exception e) {}
				
				try {
					doc = doc.substring(doc.indexOf("손질법"));
					doc = doc.substring(doc.indexOf("<dd>")+4);
					String trim = doc.substring(0,doc.indexOf("<div")).trim();
					if(trim.contains("<")) trim = trim.substring(0,trim.indexOf("<")).trim();
					System.out.println("trim:"+trim);
					vo.setTrim(trim);
					//System.out.println(doc);
				} catch (Exception e) {}
				
				try {
					doc = doc.substring(doc.indexOf("조리법"));
					doc = doc.substring(doc.indexOf("dd")+3);
					String cookery = doc.substring(0,doc.indexOf("<")).trim();
					System.out.println("cookery:"+cookery);
					//System.out.println(doc);
				} catch (Exception e) {}
				
				try {
					doc = doc.substring(doc.indexOf("보관법"));
					doc = doc.substring(doc.indexOf("dd>")+3);
					String storage = doc.substring(0,doc.indexOf("<")).trim();
					System.out.println("storage:"+storage);
					vo.setStorage(storage);
				} catch (Exception e) {}
				System.out.println("====================================================");
				
				new DAO().ingredientInsert(vo);
				
				//if(true) return;
			} catch (Exception e) {
				//return;
			}
		}
	}

	public String decode(String uni) {
		StringBuffer str = new StringBuffer();
		for (int i = uni.indexOf("\\u"); i > -1; i = uni.indexOf("\\u")) {
			str.append(uni.substring(0, i));
			str.append(String.valueOf((char) Integer.parseInt(uni.substring(i + 2, i + 6), 16)));
			uni = uni.substring(i + 6);
		}
		str.append(uni);
		return str.toString();
	}

	public static void main(String[] args) {
		new IngredientCrawller().get();
	}

}
