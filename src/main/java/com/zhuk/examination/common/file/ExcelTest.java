package com.zhuk.examination.common.file;

import java.io.InputStream;

import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import com.aspose.cells.License;
import com.zhuk.examination.common.utils.CodeGenerator;

public class ExcelTest {

	public static void main(String[] args) throws Exception {

		InputStream is = CodeGenerator.class.getClassLoader().getResourceAsStream("\\license.xml");
        License aposeLic = new License();
        aposeLic.setLicense(is);

        //for(int m=0;m<1000;m++){

			Workbook wb = new Workbook("file/复用分析.xlsx");

			WorksheetCollection sheets = wb.getWorksheets();

			for(Object sheet : sheets) {
				Worksheet workSheet = (Worksheet)sheet;
				Cells cells = workSheet.getCells();
				for (int i = 0; i < cells.getMaxRow() + 1; i++)
	            {
	                for (int j = 0; j < cells.getMaxColumn() + 1; j++)
	                {
	                    String string = cells.get(i, j).getStringValueWithoutFormat().trim();
	                    System.out.print(string+"\t");
	                }

	                System.out.println();
	            }
			}
        //}
        //Workbook wb = new Workbook("excel.xls");
		//wb.save("excel.pdf", SaveFormat.PDF);

	}

}
