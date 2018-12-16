package cs601.project3;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Synchronized Inverted Index
 * Holds terms and its associated documents.
 * Allows searches within the invertedIndex
 * @author Tae Hyon Lee
 *
 */
public class InvertedIndex {
	private HashMap<String, HashMap<Data, Integer>> keyWordMap = new HashMap<String, HashMap<Data, Integer>>();
	private ArrayList<Data> list = new ArrayList<Data>();
	
	/**
	 * finds the ASIN in the inverted index and returns the appropriate list of data matched
	 * @param term
	 * @return
	 */
	public synchronized ArrayList<Data>  find(String term) { 
		ArrayList<Data> temp = new ArrayList<Data>();
		for (Data data :  list) {
			if (data.getAsin().toLowerCase().equals(term.toLowerCase())) {
				temp.add(data);
			}
		}
		return temp;
	}
	
	/**
	 * finds the documents that contains the specified term and returns a hashmap of all containing  documents
	 * @param term
	 * @return
	 */
	public synchronized HashMap<Data, Integer> search(String term){
		HashMap<Data, Integer> temp = new HashMap<Data, Integer>();
		if (!(term.equals("")) && keyWordMap.containsKey(term.toLowerCase())) {
			temp = keyWordMap.get(term.toLowerCase());
		}
		return temp;
	}
	
	/**
	 * Getter for the inverted index hashmap
	 * @return HashMap/Inverted Index of each word
	 */
	public synchronized HashMap<String, HashMap<Data, Integer>> getKeyWordMap() {
		return keyWordMap;
	}
	/**
	 * Getter for all the references to the QAs.
	 * @return
	 */
	public synchronized ArrayList<Data> getList() {
		return list;
	}
	/**
	 * Getter for count of how many rows are read.
	 * @return
	 */
	public synchronized void put(String key, HashMap<Data, Integer> data){
		keyWordMap.put(key, data);
	}
}
		