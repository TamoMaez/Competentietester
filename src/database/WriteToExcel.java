package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import domain.Category;
import domain.Question;
import domain.YesNoQuestion;
import domain.facade.AdministratorFacade;

public class WriteToExcel implements FileWriter{
	private int skipRows = 2;
	
	public void write(String targetFile, AdministratorFacade facade){
		//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("hierKomtMaincategory");
        
        int rownum = 0;
        
        while(skipRows > 0){
        	sheet.createRow(rownum++);
        	skipRows --;
        }
        
        for (Question q : facade.getQuestions()) {
            Row row = sheet.createRow(rownum++);
            int cellnum = 0;
            
            String vraagType = "";
            if(q instanceof YesNoQuestion) vraagType = "JaNeenVraag";
            else vraagType = "MeerkeuzeVraag";
            
            row.createCell(cellnum++).setCellValue(vraagType);
            row.createCell(cellnum++).setCellValue(q.getQuestion());
            
            row.createCell(cellnum++).setCellValue(q.getCorrectOptions().get(0).getStatement());
            for(String option : q.getStatements()){
            	if(q.getCorrectOptions().contains(option)) continue;
            	row.createCell(cellnum++).setCellValue(option);
            }
            
            for(Category c : q.getCategories()){
            	Row rij = sheet.createRow(rownum++);
            	cellnum = 1;
            	rij.createCell(cellnum++).setCellValue(c.getTitle());
            	rij.createCell(cellnum++).setCellValue(q.getMaxPointsForCategory(c));
            	rij.createCell(cellnum++).setCellValue(c.getDescription());
            }
        }
        
        try{
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("res/"+ targetFile));
            workbook.write(out);
            out.close();
            System.out.println(targetFile + " written successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) throws IOException {
		ReadFromExcel reader = new ReadFromExcel();
		AdministratorFacade facade = new AdministratorFacade();
		reader.read("res/vragen-uit-excel.xlsx", facade);
		
		WriteToExcel writer = new WriteToExcel();
		writer.write("test.xlsx", facade);	
	  }
}
