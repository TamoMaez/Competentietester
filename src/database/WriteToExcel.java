package database;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import domain.Category;
import domain.Option;
import domain.Question;
import domain.YesNoQuestion;
import domain.facade.CompetentieTesterFacade;

public class WriteToExcel implements FileWriter{
	private int skipRows = 2;
	
	public void write(File targetFile, CompetentieTesterFacade facade){
		//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("hierKomtMaincategory");
        
        int rownum = 0;
        
        while(skipRows > 0){
        	sheet.createRow(rownum++);
        	skipRows --;
        }
        
        for (Question q : facade.getAllQuestions()) {
            Row row = sheet.createRow(rownum++);
            int cellnum = 0;
            
            String vraagType = "";
            if(q instanceof YesNoQuestion) vraagType = "JaNeenVraag";
            else vraagType = "MeerkeuzeVraag";
            
            row.createCell(cellnum++).setCellValue(vraagType);
            row.createCell(cellnum++).setCellValue(q.getQuestion());
            
            for(Option option : q.getCorrectOptions()){
            	row.createCell(cellnum++).setCellValue(option.getStatement());
            }
            
            for(Option option : q.getOptions()){
            	if(!q.getCorrectOptions().contains(option)){
            		row.createCell(cellnum++).setCellValue(option.getStatement());
            	}
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
            FileOutputStream out = new FileOutputStream(targetFile);
            workbook.write(out);
            out.close();
            System.out.println(targetFile + " written successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
}
