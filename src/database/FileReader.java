package database;

import java.io.File;

import domain.facade.AdministratorFacade;

public interface FileReader {
	void read(String file, AdministratorFacade facade);
	void read(File file, AdministratorFacade facade);
}
