/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package database;

import java.io.File;

import domain.facade.CompetentieTesterFacade;

public interface FileWriter {
	void write(File chooseFile, CompetentieTesterFacade facade);
}
