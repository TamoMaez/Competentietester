package database;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.facade.CompetentieTesterFacade;

import org.apache.commons.io.FilenameUtils;

public class FileManager {
	
	private FileReader reader;
	private FileWriter writer;
	private JFileChooser fileChooser;

	public FileManager(){
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("res"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("Excel (.xlsx)","xlsx"));
	}
	
	public void write(CompetentieTesterFacade facade) {
		File file = setSaveFile();
		if(file != null){
			writer.write(file, facade);
		}
	}
	
	public void writeToCurrentFile(CompetentieTesterFacade facade) {
		writer.write(fileChooser.getSelectedFile(), facade);
	}

	/*
	 * ONLY FOR TESTING
	 *
	public void read(CompetentieTesterFacade facade) {
		File file = chooseFile();
		if(file != null && setReader(file)){
			reader.read(file, facade);
		}
	}
	*/
	
	public void read(CompetentieTesterFacade facade) {
		File file = new File("res/vragen-uit-excel.xlsx");
		
		if(file != null && setReader(file)){
			reader.read(file, facade);
		}
	}
	
	
	
	
	/**
	 * TOT HIER
	 */
	
	
	
	
	
	private boolean setReader(File file) {
		switch(FilenameUtils.getExtension(file.getName())){
			case "xlsx" :
				this.reader = new ReadFromExcel();
				this.writer = new WriteToExcel();
				return true;
			default : return false;
		}
	}

	public File chooseFile(){
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file to open: " + selectedFile.getAbsolutePath());
		    return selectedFile;
		}
		return null;
	}
	
	public File setSaveFile(){
		int result = fileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    if (!FilenameUtils.getExtension(selectedFile.getName()).equalsIgnoreCase("xlsx")) {
		    	selectedFile = new File(selectedFile.toString() + ".xlsx");
		    }
		    System.out.println("Selected file to save: " + selectedFile.getAbsolutePath());
		    return selectedFile;
		}
		return null;
	}

}
