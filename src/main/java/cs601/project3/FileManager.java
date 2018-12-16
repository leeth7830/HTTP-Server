package cs601.project3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * File Manager that reads and parses json review file and stores it in the inverted index object
 * @author Taehyon
 *
 */
public class FileManager {
	private String fileName;
	private InvertedIndex index;
	
	/**
	 * Constructor that takes in the file name and the reference to the inverted index
	 * @param fileName
	 * @param index
	 */
	public FileManager(String fileName, InvertedIndex index) {
		this.fileName = fileName;
		this.index = index;
		read(this.fileName, this.index);
	}
	
	/**
	 * read method that reads each line of the json file and stores them into data object then to inverted index
	 * @param fileName
	 * @param index
	 */
	public void read(String fileName, InvertedIndex index) {
		int count = 0;
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ISO-8859-1"));
			Gson gson = new Gson();
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				try {
					Data data = gson.fromJson(currentLine, ReviewData.class);
					index.getList().add(data);
					count ++;
					for (String word : data.getData().split("\\s+")) {
						String modifiedWord = word.toLowerCase().replaceAll("[^a-z0-9]", "");
						if (index.getKeyWordMap().containsKey(modifiedWord)) { 
							HashMap<Data, Integer> temp = index.getKeyWordMap().get(modifiedWord);
							if (temp.containsKey(data)) {
								temp.put(data, temp.get(data) + 1);
								index.put(modifiedWord, temp);
							}
							else {
								temp.put(data, 1);
								index.put(modifiedWord, temp);
							}
						}
						else {
							HashMap<Data, Integer> temp = new HashMap<Data, Integer>();
							temp.put(data , 1);
							index.put(modifiedWord, temp);
						}
					}
				}
				catch (JsonSyntaxException e) {
					System.out.println("BAD DATA at row " + (count + 1) + " in the json file");
				} catch (JsonIOException e) {
					System.out.println("Error while attempting to read JSON FILE");
					System.exit(0);
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("JSON File does not exist.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error while attempting to read JSON FILE");
			System.exit(0);
		}
		System.out.println("File: " + fileName + " loaded...");
		System.out.println("Read " + count + " Rows!");
	}
}
