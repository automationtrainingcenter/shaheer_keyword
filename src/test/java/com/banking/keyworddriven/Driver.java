package com.banking.keyworddriven;

import java.lang.reflect.Method;

public class Driver {

	public static void main(String[] args) {
		// create an object of keywords class
		Keywords keywords = new Keywords();

		// get all the methods of the Keywords class
		Method[] methods = keywords.getClass().getMethods();

		// create an object of excel helper class for test case document
		ExcelHelper tcdoc = new ExcelHelper();
		tcdoc.openExcel("resources", "hrmkeywords.xls", "test cases");

		// creat an object of excel helper class for test step document
		ExcelHelper tsdoc = new ExcelHelper();
		tsdoc.openExcel("resources", "hrmkeywords.xls", "test steps");

		// get the number of rows in test case doc
		int tcrows = tcdoc.getRows();
		// iterate over every row of the tcdoc
		for (int i = 1; i <= tcrows; i++) {
			String tcd_tcname = tcdoc.readData(i, 1);
			String run_mode = tcdoc.readData(i, 2);
			if (run_mode.equalsIgnoreCase("yes")) {
				// iterate over every row of test step document
				for (int j = 1; j <= tsdoc.getRows(); j++) {
					String tsd_tcname = tsdoc.readData(j, 0);
					if (tcd_tcname.equalsIgnoreCase(tsd_tcname)) {
						String stepName = tsdoc.readData(j, 1);
						String locType = tsdoc.readData(j, 2);
						String locValue = tsdoc.readData(j, 3);
						String testData = tsdoc.readData(j, 5);
						String keyword = tsdoc.readData(j, 4);
						System.out.println(keyword);
						// iterate over every method in your keywords class
						for (Method method : methods) {
							if (method.getName().equals(keyword)) {
								try {
									System.out.println(stepName);
									method.invoke(keywords, locType, locValue, testData);
									break; // methods loop
								} catch (Exception e) {
									System.out.println(e.getMessage());
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}

	}

}
