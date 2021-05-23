package Export;

import java.io.IOException;

public abstract class Exporter {

	public abstract void doExport();
	
	public void exportTo(String command) {
		try {
			Process p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
