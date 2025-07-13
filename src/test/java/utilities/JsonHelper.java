package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

	public static <T> T convertJsonFileToObject(String fileName, Class<T> modelClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new File("src/test/resources/testdata/" + fileName);
			return mapper.readValue(file, modelClass);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read " + fileName + " file", e);
		}
	}

	public static void writeResponseToJsonFile(String responseBody, String fileName) {
		String jsonFileName = fileName + ".json";
		try {
			Path folderPath = Paths.get("target/test-output/responses/");
			Files.createDirectories(folderPath);

			String fullFilePath = folderPath.resolve(jsonFileName).toString();
			FileWriter file = new FileWriter(fullFilePath);
			file.write(responseBody);
			file.close();
			System.out.println("Successfully updated file: " + jsonFileName);
		} catch (IOException e) {
			System.out.println("Failed to update file: " + jsonFileName);
			e.printStackTrace();
		}
	}
}
