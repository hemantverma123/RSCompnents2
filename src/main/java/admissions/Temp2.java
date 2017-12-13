package admissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Temp2 {

	public static void main(String[] args) throws FileNotFoundException {

//		JsonObject student = null;
//		
		FileReader reader = new FileReader(new File("c:\\output1.json"));
		JsonParser parser = new JsonParser();
		
		JsonObject rootObj = parser.parse(reader).getAsJsonObject();
		JsonArray paymentsArray = rootObj.getAsJsonArray("message");
		
		System.out.println(paymentsArray);
		
		for (JsonElement pa : paymentsArray) {
		    JsonObject paymentObj = pa.getAsJsonObject();
		    String     quoteid     = paymentObj.get("visa").getAsString();
		    String     dateEntered = paymentObj.get("sex").getAsString();
		    
		    System.out.println(quoteid);
		    System.out.println(dateEntered);
		}
		
//		
//		JsonElement element = parser.parse(reader);
//		
//		//System.out.println(element.toString());
//		
//		if (element.isJsonObject()) {
//			
//			student = element.getAsJsonObject();
//			JsonArray arr = student.getAsJsonArray("message");
//			
//			System.out.println(arr.toString());
//			System.out.println(arr.get(1));
//		}
			
		
		
		
	}

}
