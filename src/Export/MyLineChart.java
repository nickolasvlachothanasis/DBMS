package Export;

public class MyLineChart extends Exporter{
	private String command = "/usr/bin/python3 /home/nvlacho/eclipse-workspace/databaseMYE030/exportToLineChart.py";

	@Override
	
	public void doExport() {
		exportTo(this.command);
	}
	
}
