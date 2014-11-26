package database;

import domain.facade.AdministratorFacade;

public interface FileWriter {
	void write(String toFile, AdministratorFacade facade);
}
