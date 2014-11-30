package database;

import java.io.File;

import javax.swing.JFileChooser;

import domain.facade.AdministratorFacade;

import org.apache.commons.io.FilenameUtils;

public class FileManager {
	
	private FileReader reader;
	private FileWriter writer;

	public void write(AdministratorFacade facade) {
		writer.write(chooseFile(), facade);
	}

	public void read(AdministratorFacade facade) {
		File file = null;
		while(file == null || !file.exists() || file.isDirectory()){
			file = chooseFile();
		}
		setReader(file);
		reader.read(file, facade);
	}
	
	private void setReader(File file) {
		if(FilenameUtils.getExtension(file.getName()).equals("xlsx")){
			this.reader = new ReadFromExcel();
			this.writer = new WriteToExcel();
		}
	}

	public File chooseFile(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("res"));
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    return selectedFile;
		}
		return null;
	}

}
