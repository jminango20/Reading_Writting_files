package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	Scanner sc = new Scanner(System.in);
	List<Product> list = new ArrayList<Product>();
	
	System.out.println("Enter the file path: ");
	String sourceFilePath = sc.nextLine();
	
	File sourceFile = new File(sourceFilePath);
	String folderFilePath = sourceFile.getParent();
	
	boolean success = new File(folderFilePath + "\\out").mkdir();
	String targetFile = folderFilePath + "\\out\\summary.csv";
	
	try(BufferedReader br = new BufferedReader(new FileReader(sourceFilePath))){
		
		String lineCsv = br.readLine();
		while(lineCsv != null) {
			String[] fields = lineCsv.split(",");
			String name = fields[0];
			double price = Double.parseDouble(fields[1]);
			int quantity = Integer.parseInt(fields[2]);
			Product p = new Product(name, price, quantity);
			list.add(p);
			lineCsv = br.readLine();
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))){
			for(Product p : list) {
				bw.write(p.getName() + ", " + String.format("%.2f", p.total()));
				bw.newLine();
			}
			
		}
		catch(IOException e) {
			System.out.println("Error writing file: " + e.getMessage());
		}		
	}
	catch(IOException e) {
		System.out.println("Error reading file: " + e.getMessage());	
	}	
	sc.close();
	
	}

}
