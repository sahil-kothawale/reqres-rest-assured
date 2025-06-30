package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResponseHelper {
	
	public static void writeResponseToJsonFile(String responseBody, String fileName) 
	{
		String jsonFileName = fileName + ".json";
		try{
			Path folderPath = Paths.get("target/test-output/responses/");
			Files.createDirectories(folderPath);
			
			String fullFilePath = folderPath.resolve(jsonFileName).toString();
			FileWriter file = new FileWriter(fullFilePath);
			file.write(responseBody);
			file.close();
			System.out.println("Successfully updated file: " + jsonFileName);
		}
		catch(IOException e) {
			System.out.println("Failed to update file: " + jsonFileName);
			e.printStackTrace();
		}
	}
}
