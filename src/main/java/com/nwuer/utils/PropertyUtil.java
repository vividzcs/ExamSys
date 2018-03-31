package com.nwuer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.nwuer.entity.School;

@Component("propertyUtil")
public class PropertyUtil {
	private Properties prop = new Properties();
	
	/**
	 * 修改信息
	 * @throws UnsupportedEncodingException 
	 */
	public void update(School school){
		
		try {
			prop.setProperty("sch_number", school.getSch_number());
			prop.setProperty("sch_name", new String(school.getSch_name().getBytes("UTF-8"),"ISO-8859-1"));
			prop.setProperty("sch_address", new String(school.getSch_address().getBytes("UTF-8"),"ISO-8859-1"));
			prop.setProperty("sch_website", school.getSch_website());
			prop.setProperty("sch_desc", new String(school.getSch_desc().getBytes("UTF-8"),"ISO-8859-1"));
			prop.setProperty("sch_aca_count", String.valueOf(school.getSch_aca_count()));
			prop.setProperty("sch_major_count", String.valueOf(school.getSch_major_count()));
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		
		String url;
		OutputStream out = null;
		try {
			url = ServletActionContext.getServletContext().getResource("/data/web.properties").getPath();
			System.out.println(url);
			File file = new File(url);
			out = new FileOutputStream(file);
			if(out != null)
				prop.store(out,"");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
				try {
					if(out != null)
						out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	public School get() {
		InputStream input = null;
		String url = null;
		try {
			url = ServletActionContext.getServletContext().getResource("/data/web.properties").getPath();
//			System.out.println(url);
			File file = new File(url);
			input = new FileInputStream(file);
			
			prop.load(input);
			//chinese = new String(chinese.getBytes("ISO-8859-1"), "utf-8");
			School school = new School();
			school.setSch_number(new String(prop.getProperty("sch_number")));
			school.setSch_name(new String(prop.getProperty("sch_name").getBytes("ISO-8859-1"),"utf-8"));
			school.setSch_address(new String(prop.getProperty("sch_address").getBytes("ISO-8859-1"),"utf-8"));
//			System.out.println(new String(prop.getProperty("sch_name").getBytes("ISO-8859-1"),"utf-8"));
			school.setSch_website(prop.getProperty("sch_website"));
			school.setSch_desc(new String(prop.getProperty("sch_desc").getBytes("ISO-8859-1"),"utf-8"));
			school.setSch_aca_count(Integer.parseInt(prop.getProperty("sch_aca_count")));
			school.setSch_major_count(Integer.parseInt(prop.getProperty("sch_major_count")));
			return school;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
