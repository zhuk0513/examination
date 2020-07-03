package com.zhuk.examination.common.file;

import java.io.InputStream;

import com.aspose.slides.IParagraph;
import com.aspose.slides.IParagraphCollection;
import com.aspose.slides.ISlide;
import com.aspose.slides.ISlideCollection;
import com.aspose.slides.ITextFrame;
import com.aspose.slides.License;
import com.aspose.slides.Presentation;
import com.aspose.slides.SlideUtil;

public class PptTest {
public static void main(String[] args) throws Exception {

		InputStream is = PptTest.class.getClassLoader().getResourceAsStream("\\license.xml");
        License aposeLic = new License();
        aposeLic.setLicense(is);

        Presentation ppt = new Presentation("ppt.pptx");

        ISlideCollection slides = ppt.getSlides();
        for(ISlide slide : slides){
        	 ITextFrame[] textFrames = SlideUtil.getAllTextBoxes(slide);
             for(ITextFrame textFrame : textFrames){
                 IParagraphCollection paragraphs = textFrame.getParagraphs();
                 for(IParagraph paragraph : paragraphs){
                     System.out.println(paragraph.getText());
//                     IPortionCollection portions = paragraph.getPortions();
//                     for(IPortion portion : portions){
//                         System.out.println(portion.getText());
//                     }
                 }
             }
        }

		ppt.save("ppt.pdf", com.aspose.slides.SaveFormat.Pdf);

	}
}
