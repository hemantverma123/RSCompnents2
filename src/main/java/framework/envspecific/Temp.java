package framework.envspecific;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Temp {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader("c:\\output.json.txt"));
		JsonMessageData JMData = gson.fromJson(reader, JsonMessageData.class);
		String campus = JMData.toString();
		System.out.println(campus);
	}

}
