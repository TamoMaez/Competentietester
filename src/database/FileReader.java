package database;

import domain.facade.AdministratorFacade;

public interface FileReader {
	void read(String file, AdministratorFacade facade);
}
