package database;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import domain.Category;
import domain.Option;
import domain.Question;
import domain.QuestionAnswerType;
import domain.facade.AdministratorFacade;

public class ReadFromExcel implements FileReader{
	private int skipRows = 2;
	private AdministratorFacade facade;
	private Iterator<Row> rowIterator;
	private Iterator<Cell> cellIterator;
	
	public void read(String fileLocation, AdministratorFacade facade) {
		this.facade = facade;
		try {
			FileInputStream file = new FileInputStream(new File(fileLocation));

			// Maak Workbook instantie met een referentie naar de excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Neem een sheet vast van het workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Itereren over de rijen
			rowIterator = sheet.iterator();
			
			while (rowIterator.hasNext() && skipRows > 0) {
				rowIterator.next();
				skipRows --;
			}
			
			if(rowIterator.hasNext()){
				Row row = rowIterator.next();
				cellIterator = row.cellIterator();
				createQuestion(row, cellIterator.next().getStringCellValue());
			}
			
			file.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createQuestion(Row row, String qType){
		String statement = cellIterator.next().getStringCellValue();
		Cell cell = cellIterator.next();
		String correctOption = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				correctOption = cell.getNumericCellValue() + "";
				break;
			case Cell.CELL_TYPE_STRING:
				correctOption = cell.getStringCellValue();
				break;
		}
		Option trueOption = facade.createOption(correctOption);
		
		ArrayList<Option> falseOptions = new ArrayList<>();	
		if(correctOption.equals("Ja")){
			falseOptions.add(facade.createOption("Nee"));
		}else if(correctOption.equals("Nee") || correctOption.equals("Neen")) {
			falseOptions.add(facade.createOption("ja"));
		}else {
			while (cellIterator.hasNext()) {
				cell = cellIterator.next();
				
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						falseOptions.add(facade.createOption(cell.getNumericCellValue() + ""));
						break;
					case Cell.CELL_TYPE_STRING:
						falseOptions.add(facade.createOption(cell.getStringCellValue()));
						break;
				}
			}
		}	
		
		facade.addQuestion(addCategories(trueOption, falseOptions, facade.createQuestion(statement, QuestionAnswerType.enumOf(qType))));
	}
	
	private Question addCategories(Option trueOption, ArrayList<Option> falseOptions, Question question) {
		while(rowIterator.hasNext()){
			Row rij = rowIterator.next();
			cellIterator = rij.cellIterator();
			String categorie = cellIterator.next().getStringCellValue();
			
			if(QuestionAnswerType.getQuestionTypes().contains(categorie)) {
				createQuestion(rij, categorie); // categorie is in dit geval qType
				break;
			}
			
			int maxPoints = (int) cellIterator.next().getNumericCellValue(); // !! nakijken
			if(!facade.getCategories().contains(categorie)){
				facade.addCategory(categorie, cellIterator.next().getStringCellValue(), new ArrayList<String>());
			}		
			question.addCategory(facade.getCategory(categorie));
			
			// geef score aan correcte optie
			trueOption.addScore(facade.createScore(maxPoints, maxPoints, categorie));
			// geef score aan foute opties
			for(Option option : falseOptions){
				option.addScore(facade.createScore(maxPoints, 0, categorie));
			}
		}

		question.addOption(trueOption);
		for(Option option : falseOptions){
			question.addOption(option);
		}
		
		return question;
	}

	public static void main(String[] args) throws IOException {
		ReadFromExcel reader = new ReadFromExcel();
		AdministratorFacade facade = new AdministratorFacade();
		reader.read("res/vragen-uit-excel.xlsx", facade);
		for(Question q : facade.getQuestions()){
			System.out.print("(");
			for(Category c : q.getCategories()){
				System.out.print(" " + c.getTitle().toUpperCase());
			}
			System.out.print(" )");
			System.out.println();
			System.out.print(q.getQuestion() +": (" + q.getCorrectOptions().get(0).getStatement() + ")\n\t");
			String options = "";
			for(String option : q.getStatementsShuffled()) {
				options += " " + option + ",";
			}
			System.out.println(options.substring(0, options.length()-1));
			System.out.println();
		}
	  }
}