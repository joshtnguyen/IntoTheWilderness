package main;

import java.io.File;
import java.util.Scanner;

public class FileManager {
	
	public static String getOption(String fileDirectory, String option) {
		
		try {
			File file = new File(fileDirectory);
			Scanner scanner = new Scanner(file);
			
			while (scanner.hasNextLine()) {
				String scan = scanner.nextLine();
				if (scan.substring(0, scan.indexOf(":")).equals(option)) {
					scanner.close();
					return scan.substring(scan.indexOf(":") + 2);
				}
			}
			String scan = scanner.nextLine();
			if (scan.substring(0, scan.indexOf(":")).equals(option)) {
				scanner.close();
				return scan.substring(scan.indexOf(":") + 2);
			}
			
			scanner.close();
			return null;
		} catch (Exception e) {
			return null;
		}
		
	}

}
