package com.zhuk.examination.common.utils;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;


/**
 * @author Administrator
 */
public class ExcelUtil {
	/**
	 * 限制导出条数
	 */
	private static final int exportMaxCounts =  100000;

	public static void exportExcel(String json, OutputStream outputStream){

		try {

			Gson g = new Gson();
			Map m = g.fromJson(json, Map.class);
			//表头
			String colcnNames = "";
			//数据值
			List<List<String>> bodyList = new ArrayList<List<String>>();
			//查询得到的基础数据
			List<Map<String,Object>> dataList = (List<Map<String,Object>>)m.get("dataList");
			for (Map<String, Object> map : dataList) {

				colcnNames = map.get("title").toString();
				colcnNames = colcnNames.replace("[", "").replace("]", "");

				outputStream.write((colcnNames+"\t\r").getBytes("GBK"));

				bodyList = ((List<List<String>>)map.get("dataList"));

				int j = 1;
				for (List<String> list1 : bodyList) {
					if (j > exportMaxCounts) {
						break;
					}
					try {
						for(int i=0;i<list1.size();i++){

							String value = String.valueOf(list1.get(i));
							if (value.equalsIgnoreCase("null")) {
								value = "--";
							}

							value = value.replaceAll("<br/>", "\n");

							value = value.replaceAll("\"", "\"\"");
							value = "\"" + value + "\"\t,";
							outputStream.write((value).getBytes("gbk"));
						}

						outputStream.write((""+"\r").getBytes("gbk"));

						j++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
