import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Pattern;
import java.text.DecimalFormat;

import java.util.concurrent.TimeUnit;

public class Hello {
	public static void main(String[] args) {
		System.out.println("Running");
		
		String code = "";
		String name = "";
		
		String[] websites = {
			"http://services.runescape.com/m=itemdb_oldschool/Ranarr+seed/viewitem?obj=5295",
			"http://services.runescape.com/m=itemdb_oldschool/Grimy+ranarr+weed/viewitem?obj=207",
			"http://services.runescape.com/m=itemdb_oldschool/Snapdragon+seed/viewitem?obj=5300",
			"http://services.runescape.com/m=itemdb_oldschool/Grimy+snapdragon/viewitem?obj=3051",
			"http://services.runescape.com/m=itemdb_oldschool/Torstol+seed/viewitem?obj=5304",
			"http://services.runescape.com/m=itemdb_oldschool/Grimy+torstol/viewitem?obj=219",
			"http://services.runescape.com/m=itemdb_oldschool/Dragonfruit+tree+seed/viewitem?obj=22877",
			"http://services.runescape.com/m=itemdb_oldschool/Maple+seed/viewitem?obj=5314",
			"http://services.runescape.com/m=itemdb_oldschool/Palm+tree+seed/viewitem?obj=5289",
			"http://services.runescape.com/m=itemdb_oldschool/Redwood+tree+seed/viewitem?obj=22871",
			"http://services.runescape.com/m=itemdb_oldschool/Yew+seed/viewitem?obj=5315",
			"http://services.runescape.com/m=itemdb_oldschool/Old+school+bond/viewitem?obj=13190",
			"http://services.runescape.com/m=itemdb_oldschool/Ahrim%27s+hood/viewitem?obj=4708",
			"http://services.runescape.com/m=itemdb_oldschool/Ahrim%27s+robeskirt/viewitem?obj=4714",
			"http://services.runescape.com/m=itemdb_oldschool/Ahrim%27s+robetop/viewitem?obj=4712",
			"http://services.runescape.com/m=itemdb_oldschool/Ahrim%27s+staff/viewitem?obj=4710",
			"http://services.runescape.com/m=itemdb_oldschool/Torag%27s+hammers/viewitem?obj=4747",
			"http://services.runescape.com/m=itemdb_oldschool/Torag%27s+helm/viewitem?obj=4745",
			"http://services.runescape.com/m=itemdb_oldschool/Torag%27s+platebody/viewitem?obj=4749",
			"http://services.runescape.com/m=itemdb_oldschool/Torag%27s+platelegs/viewitem?obj=4751",
			"http://services.runescape.com/m=itemdb_oldschool/Dharok%27s+greataxe/viewitem?obj=4718",
			"http://services.runescape.com/m=itemdb_oldschool/Dharok%27s+helm/viewitem?obj=4716",
			"http://services.runescape.com/m=itemdb_oldschool/Dharok%27s+platebody/viewitem?obj=4720",
			"http://services.runescape.com/m=itemdb_oldschool/Dharok%27s+platelegs/viewitem?obj=4722",
			"http://services.runescape.com/m=itemdb_oldschool/Guthan%27s+chainskirt/viewitem?obj=4730",
			"http://services.runescape.com/m=itemdb_oldschool/Guthan%27s+helm/viewitem?obj=4724",
			"http://services.runescape.com/m=itemdb_oldschool/Guthan%27s+platebody/viewitem?obj=4728",
			"http://services.runescape.com/m=itemdb_oldschool/Guthan%27s+warspear/viewitem?obj=4726",
			"http://services.runescape.com/m=itemdb_oldschool/Karil%27s+coif/viewitem?obj=4732",
			"http://services.runescape.com/m=itemdb_oldschool/Karil%27s+crossbow/viewitem?obj=4734",
			"http://services.runescape.com/m=itemdb_oldschool/Karil%27s+leatherskirt/viewitem?obj=4738",
			"http://services.runescape.com/m=itemdb_oldschool/Karil%27s+leathertop/viewitem?obj=4736",
			"http://services.runescape.com/m=itemdb_oldschool/Verac%27s+brassard/viewitem?obj=4757",
			"http://services.runescape.com/m=itemdb_oldschool/Verac%27s+flail/viewitem?obj=4755",
			"http://services.runescape.com/m=itemdb_oldschool/Verac%27s+helm/viewitem?obj=4753",
			"http://services.runescape.com/m=itemdb_oldschool/Verac%27s+plateskirt/viewitem?obj=4759",
			"http://services.runescape.com/m=itemdb_oldschool/Mithril+bar/viewitem?obj=2359",
			"http://services.runescape.com/m=itemdb_oldschool/Iron+bar/viewitem?obj=2351",
			"http://services.runescape.com/m=itemdb_oldschool/Steel+bar/viewitem?obj=2353",
			"http://services.runescape.com/m=itemdb_oldschool/Rune+platebody+%28g%29/viewitem?obj=2615",
			"http://services.runescape.com/m=itemdb_oldschool/Rune+full+helm+%28g%29/viewitem?obj=2619",
			"http://services.runescape.com/m=itemdb_oldschool/Ring+of+dueling%288%29/viewitem?obj=2552",
			"http://services.runescape.com/m=itemdb_oldschool/Amulet+of+glory/viewitem?obj=1704"
		};
		
		try {
			for (int i = 0; i < websites.length; i++) {
				code = getURLSource(websites[i]);
				
				name = setName(code);
				code = clean(code);
		
				String[] pricesStringArray = code.split("\\r?\\n");
				
				int[] prices = new int[pricesStringArray.length];
				
				for (int j = 0; j < pricesStringArray.length; j++) {
					prices[j] = Integer.parseInt(pricesStringArray[j]);
				}
				
				System.out.println(name);
				
				DecimalFormat formatter = new DecimalFormat("#,###");
				int currentPrice = prices[prices.length - 1];
				System.out.println("Current Price: 	$" + formatter.format(currentPrice));
				System.out.println("Oldest Price: 	$" + formatter.format(prices[0]));
				int middlePrice = (prices[prices.length - 1] + prices[0]) / 2;
				System.out.println("Middle Price: 	$" + formatter.format(middlePrice));
				
				if (currentPrice < middlePrice) {
					System.out.println("Current Price < Middle Price.");
					System.out.println("Buy Now\n");
				}
				else {
					System.out.println("Current Price > Middle Price.");
					System.out.println("DO NOT BUY\n");
				}
			}
		}
		catch(Exception e) {
			System.out.println("Other Error.");
		}
	}
	
	public static String getURLSource(String url) throws IOException {
		try {
			URL urlObject = new URL(url);
			URLConnection urlConnection = urlObject.openConnection();
			urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			
			return toString(urlConnection.getInputStream());
		}
		catch (Exception e) {
			System.out.println("getURLSource Failed.");
		}
		
		return "getURLSource Failed.";
	}
	
	private static String toString(InputStream inputStream) throws IOException {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
		{
			String inputLine;
			StringBuilder stringBuilder = new StringBuilder();
			while ((inputLine = bufferedReader.readLine()) != null)
			{
				stringBuilder.append(inputLine);
			}

			return stringBuilder.toString();
		}
	}
	
	public static String clean(String code) {
		
/* Remove code before first data instance */
		String clean = code.substring(code.indexOf("average180.push"));
		clean.trim();

/* Remove code after last data instance */
		clean = clean.substring(0, clean.indexOf("</script>"));
		
/* Print all data instances on a new line */
		clean = clean.replaceAll(";", ";\n");
		
/* Remove all tabs */
		clean = clean.replaceAll("	", "");

/* Remove all multi-spaces */
		clean = clean.replaceAll("                ", "");
		
		clean = removeDateTrade(clean);
		
		clean = removePriceAverage(clean);
		
		return clean;
	}
	
	public static String removeDateTrade(String clean) {
		try {
/* Count occurrences of trade data instances */

			int fromIndex = 0;
			int count = 0;
			
			while ((fromIndex = clean.indexOf("tr", fromIndex)) != -1) {
				count++;
				fromIndex++;
			}
/* Remove occurrences of trade data instances */		
	
			for (int i = 0; i < count; i++) {
				int startIndexTr = clean.indexOf("tr");
				int endIndexTr = clean.indexOf(";", startIndexTr);
				String replaceTr = clean.substring(startIndexTr, endIndexTr + 2);
				clean = clean.replaceFirst(Pattern.quote(replaceTr), "");
			}

/* Count unnecessary parts of average data occurrences */		
			if (count == 0) {
				while ((fromIndex = clean.indexOf("av", fromIndex)) != -1) {
					count++;
					fromIndex++;
				}
			}
/* Remove unnecessary parts of average data occurrences */
/*
This will always run.
If first count succeeds, the count transfers over to second for loop.
If first count fails, count is recounted, then second for loop runs.
*/
			for (int i = 0; i < count; i++) {
				int startIndexAv = clean.indexOf("av");
				int endIndexAv = clean.indexOf(", ", startIndexAv);
				String replaceAv = clean.substring(startIndexAv, endIndexAv + 2);
				clean = clean.replaceFirst(Pattern.quote(replaceAv), "");
			}
			
			return clean;
		}
		catch (Exception e) {
			System.out.println("removeDateTrade() Failed.");
		}
		
		return "removeDateTrade() Failed.";
	}
	
	public static String removePriceAverage(String clean ) {
		try {
			int fromIndex = 0;
			int count = 0;
			
			while ((fromIndex = clean.indexOf(", ", fromIndex)) != -1) {
				count++;
				fromIndex++;
			}
			
			//System.out.println("Count is: " + count);
			//System.out.println(clean);
			
	/* Remove all occurrences of price averages */
			for (int i = 0; i < count; i++) {
				//System.out.println("i is: " + i);
				
				int startIndexCo = clean.indexOf(", ", 0);
				int endIndexCo = clean.indexOf(");", 0);
				
				int fromIndexTest = 0;
				int countTest = 0;
						
				String replaceCo = clean.substring(startIndexCo, endIndexCo + 2);
				clean = clean.replaceFirst(Pattern.quote(replaceCo), "");
			}
			
			return clean;
		}
		catch (Exception e) {
			System.out.println("removePriceAverage() Failed.");
		}
		
		return "removePriceAverage() Failed.";
	}
	
	public static String setName(String code) {
		try {
			String name = code.substring(code.indexOf("<h2>") + 4);
			name.trim();
			
			name = name.substring(0, name.indexOf("</h2>"));
			
			return name;
		}
		catch (Exception e) {
			System.out.println("setName() Failed.");
		}
		
		return "setName() Failed.";
	}
}