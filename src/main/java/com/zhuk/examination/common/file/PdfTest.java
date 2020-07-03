package com.zhuk.examination.common.file;

import java.io.InputStream;

import com.aspose.pdf.Document;
import com.aspose.pdf.PageCollection;
import com.aspose.pdf.TextAbsorber;
import com.aspose.pdf.License;

public class PdfTest {

	public static void main(String[] args) throws Exception {

		InputStream is = PptTest.class.getClassLoader().getResourceAsStream("\\license.xml");
	    License aposeLic = new License();
	    aposeLic.setLicense(is);

		Document pdfDocument = new Document("word.pdf");
		PageCollection pages = pdfDocument.getPages();
		TextAbsorber visitor = new TextAbsorber();
		pages.accept(visitor);
		String text = visitor.getText();
		System.out.println(text);

	}

}
