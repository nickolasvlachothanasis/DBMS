package Export;

public class MyScatterPlot extends Exporter{
	
	private String command = "/usr/bin/python3 /home/nvlacho/eclipse-workspace/databaseMYE030/exportToScatterPlot.py";

	@Override
	public void doExport() {
		exportTo(this.command);
	}
		
}
