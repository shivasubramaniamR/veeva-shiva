package com.veeva.automation.framework.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVUtility {
	
	/**
	 * 
	 * @param hyperlinks - A list of Strings which needs to be logged into CSV File
	 * @param filePath - Path of the file where it needs to be created
	 */
	public static void writeLinksToCSV(List<String> hyperlinks, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String link : hyperlinks) {
                writer.append(link).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
