package com.zhuk.examination.common.utils;

import com.aspose.email.License;

import java.io.InputStream;

/**
 * @author zhuk
 * @description: 加载aspose的license
 * @date 2020-07-03
 */
public class AsposeLicense {

    public static final String license="\\license.xml";

    /**
     * 加载
     * @param className
     */
    public static void load(Class className){
        InputStream in = className.getClassLoader().getResourceAsStream(license);
        License aposeLic = new License();
        aposeLic.setLicense(in);
    }
}
