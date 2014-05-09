package com.company.test;

import com.company.java.ReadCSV;


/**
 * @author Indresh
 * 
 */
public class APP {
public static void main(String[] args) {
	// Reading data from a csv file and List for each Company year and month in which the share price was highest
			System.out.println("Reading data from csv :");
			ReadCSV csvloader = new ReadCSV();
			csvloader.bindCompaniesData();
			
		}
}
