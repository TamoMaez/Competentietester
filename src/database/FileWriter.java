package database;

import java.io.File;

import domain.facade.AdministratorFacade;

public interface FileWriter {
	void write(File chooseFile, AdministratorFacade facade);
}
