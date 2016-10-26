package org.inno;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.inno.vo.ReturnObject;

@Path("/service")
public class RestService {

	private Scanner scanner;
	
	final String FILE_NAME = "codes.txt";

	/**
	 * This method to take the string as Unicode in the database and return it
	 * with Unicode value of the string with the defined logic.
	 * 
	 * @param registerId
	 *            - Unique ID
	 * 
	 * @return - Registered Value.
	 */
	@POST
	@Path("{registerId}")
	@Produces("application/json")
	public ReturnObject registerUnicode(
			@PathParam(value = "registerId") String registerId) {
		writeIntoFile(registerId);
		ReturnObject returnObject = new ReturnObject();
		returnObject.setStringId(getUnicodeCount(registerId));
		return returnObject;
	}

	public static void main(String args[]) {
		System.out.println(getUnicodeCount("albert"));
	}
	
	/**
	 * This method will check if string exists or not. If it does not exists then only it will write.
	 * 
	 * @param registerId
	 * @return
	 */
	private synchronized boolean writeIntoFile(String registerId) {
		File file = new File(FILE_NAME);
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			if (file.exists()) {
				scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					if (scanner.nextLine().contains(registerId)) {
						throw new Exception("Name already exists");
					}
				}
				scanner.close();
			} else {
				file.createNewFile();
			}
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.print(registerId + "\n");
		} catch (IOException e) {
			return false;
		} catch (Exception e) {
			return false;
		} finally {
			if (out != null)
				out.close();
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				// exception handling left as an exercise for the reader
			}
			try {
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				// exception handling left as an exercise for the reader
			}
		}
		return true;
	}

	/**
	 * This method will generate the Sum of Unicode like below 
	 * "abc" => 97 + (97 + 98) + (98 + 99) 
	 * "abbc" => 97 + (97 + 98) + (98) + (98 + 99)
	 * 
	 * @param unicode
	 * @return
	 */
	private static int getUnicodeCount(String unicode) {
		char[] array = unicode.toCharArray();
		int count = 0;
		int temp;
		for (int i = 0; i < array.length; i++) {
			temp = (int) array[i];
			if (i > 0) {
				if (array[i] != array[i - 1]) {
					temp = (int) array[i - 1] + (int) array[i];
				}
			}
			count += temp;
		}
		return count;
	}


	/**
	 * This method to take the string as Unicode in the database and return it
	 * with Unicode value of the string with the defined logic.
	 * 
	 * @param registerId
	 *            - Unique ID
	 * 
	 * @return - Registered Value.
	 */
	@GET
	@Path("{stringId}")
	@Produces("application/json")
	public List<ReturnObject> getRegisteredStrings(
			@PathParam(value = "stringId") int stringId) {
		return checkAndGetTheString(stringId);
	}
	

	/**
	 * This method will retrieve all the registered string if they matches with String id.
	 * 
	 * @param stringId
	 * @return
	 */
	private List<ReturnObject> checkAndGetTheString(int stringId) {
		File file = new File(FILE_NAME);
		List<ReturnObject> listOfRegisterId = new ArrayList<ReturnObject>();
		ReturnObject returnObject = null;
		String checkingString = null;
		try {
			if (!file.exists()) {
				scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					checkingString = scanner.nextLine();
					if (stringId == getUnicodeCount(checkingString)) {
						returnObject = new ReturnObject();
						returnObject.setStringValue(checkingString);
						listOfRegisterId.add(returnObject);
					}
				}
				scanner.close();
			}
		} catch (IOException e) {
			return null;
		}
		return listOfRegisterId;
	}
	
}
