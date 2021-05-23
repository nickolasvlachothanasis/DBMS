package Export;

public class MyBarChart  extends Exporter {

	private String command = "/usr/bin/python3 /home/nvlacho/eclipse-workspace/databaseMYE030/exportToBarChart.py";

	@Override
	public void doExport() {
		exportTo(this.command);
	}
	
	

}
