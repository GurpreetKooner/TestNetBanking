package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadCustomerData {

	XSSFWorkbook workbook;

	public ReadCustomerData() {
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\testData\\customers_data.xlsx");

			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object[][] getnewCustomerData() {

		XSSFSheet sheet = workbook.getSheet("newCustomers");

		int numOfRows = sheet.getPhysicalNumberOfRows() - 1;
//		System.out.println(numOfRows);
		Object[][] data = new Object[numOfRows][10];
		Iterator<Row> row = sheet.rowIterator();
		row.next();

		while (row.hasNext()) {
			Row myRow = row.next();
			Iterator<Cell> cell = myRow.cellIterator();

			while (cell.hasNext()) {
				Cell myCell = cell.next();
				DataFormatter formatter = new DataFormatter();
				int rowNum = myRow.getRowNum();
				int colNum = myCell.getColumnIndex();
				String val = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));

				data[rowNum - 1][colNum] = val;
			}
		}

		return data;
	}
}
