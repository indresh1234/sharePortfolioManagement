package com.company.java;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.company.domain.Company;

/**
 * @author Indresh
 * 
 */

public class ReadCSV {
	   private List<Company> companies;
	   private int lastReadIdx=0;

	   public ReadCSV() {
	   }

	   // Simply read the CSV and return the next set of company data
	   private Company getNextCompanyData() {
	      if (companies == null) {
	         lastReadIdx = 0;
	         try {
	            loadCompanies();
	         } catch (Exception e) {
	         }
	      }
	      if (companies == null) return null;
	      if (lastReadIdx < companies.size()) return companies.get(lastReadIdx++);
	      return null;
	   }

	   public void loadCompanies() throws Exception {
	      Scanner s = null;
	      try {
	         companies = new ArrayList<Company>();
	         File f = new File("csvFile/csvToRead.csv");
	         System.out.println(f.getAbsolutePath());
	         s = new Scanner(new FileInputStream(f));
	         String[] headers = readLine(s);
	         System.out.println("Headers Items: " + Arrays.toString(headers));
	         if (headers != null && headers.length >0) {
	            String[] data = null;
	            while ((data = readLine(s)) != null) {
	               System.out.println("data: " + Arrays.toString(data));
	               if (data.length != headers.length) {
	                  companies = null;
	                  throw new Exception("Invalid Data - headers count " + headers.length + " does not match with data count "+data.length);
	               }
	               String year = data[0];
	               String month = data[1];
	               for (int x=2; x<data.length; x++) {
	                  double price = new Double(data[x]).doubleValue();
	                  Company company = new Company(headers[x], year, month, price);
	                  companies.add(company);
	               }
	            }
	         }
	      } finally {
	         if (s != null) s.close();
	      }
	   }

	   private String[] readLine(Scanner s) {
	      if (s.hasNextLine()) {
	         return s.nextLine().trim().split(",");
	      }
	      return null;
	   }

	  public void bindCompaniesData() {
	      Map<String, Company> companies = new HashMap<String, Company>();
	      Company newCompany = null;

	      // repeat until all company data processed from CSV file
	      while ((newCompany = getNextCompanyData()) != null) {
	         Company oldCompany = companies.get(newCompany.getName());
	         if (oldCompany == null || newCompany.getPrice() > oldCompany.getPrice())
	            companies.put(newCompany.getName(), newCompany);
	      }
	         System.out.println("================Company year and month in which the share price was highest================");
	      for (String name : companies.keySet()) {
	         Company company = companies.get(name);

	         System.out.println(company.getName() + " highest price " + company.getPrice() + " is " + company.getMonth() + " " + company.getYear());
	      }
	   }

	

}
