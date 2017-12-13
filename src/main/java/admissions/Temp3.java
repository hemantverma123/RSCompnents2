package admissions;

import java.io.IOException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Temp3 {


		public static void main(String[] args) throws IOException {
			
			String StudentDataURL = "https://60x5ngk368.execute-api.eu-west-1.amazonaws.com/dev/applicants/create/";
			int NumStudents = 3;
			
			HttpCalls call = new HttpCalls();
			String StudentData = call.run(StudentDataURL + NumStudents);

			JsonParser parser = new JsonParser();
			JsonObject rootObj = parser.parse(StudentData).getAsJsonObject();
			JsonArray studentsArray = rootObj.getAsJsonArray("message");
			
			System.out.println(studentsArray);
			System.out.println();
			
			JsonObject obj = studentsArray.get(2).getAsJsonObject();
			System.out.println(obj.get("firstname").getAsString());
			System.out.println("-------------------------------------");
	
			for (JsonElement sa : studentsArray) {
			    JsonObject studentObj = sa.getAsJsonObject();
			    String     firstName     = studentObj.get("firstname").getAsString();
			    String     middleName = studentObj.get("middlename").getAsString();
			    String     lastName = studentObj.get("lastname").getAsString();
			    
			    System.out.println(firstName);
			    System.out.println(middleName);
			    System.out.println(lastName);
			
			    
			}		
			

		}
		


	

	
}
