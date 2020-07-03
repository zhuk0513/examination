package com.zhuk.examination.common.file;

import java.io.InputStream;

import com.aspose.words.Document;
import com.aspose.words.License;

public class WordTest {

	public static void main(String[] args) throws Exception {

		InputStream is = WordTest.class.getClassLoader().getResourceAsStream("\\license.xml");
        License aposeLic = new License();
        aposeLic.setLicense(is);

		Document doc = new Document("file/详细设计文档.doc");
		System.out.println(doc.getText());

		//doc.save("word.pdf", SaveFormat.PDF);
	}

}
