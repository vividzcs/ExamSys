package com.nwuer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.nwuer.entity.School;

@Component("propertyUtil")
public class PropertyUtil {
	private Properties prop = new Properties();
	
	/**
	 * ÐÞ¸ÄÐÅÏ¢
	 */
	public void update(School school) {
		prop.setProperty("sch_number", school.getSch_number());
		prop.setProperty("sch_name", school.getSch_name());
		prop.setProperty("sch_address", school.getSch_address());
		prop.setProperty("sch_website", school.getSch_website());
		prop.setProperty("sch_desc", school.getSch_desc());
		prop.setProperty("sch_aca_count", String.valueOf(school.getSch_aca_count()));
		prop.setProperty("sch_major_count", String.valueOf(school.getSch_major_count()));
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
			System.out.println(url);
			File file = new File(url);
			input = new FileInputStream(file);
			
			prop.load(input);
			//chinese = new String(chinese.getBytes("ISO-8859-1"), "utf-8");
			School school = new School();
			school.setSch_number(new String(prop.getProperty("sch_number").getBytes("utf-8"),"utf-8"));
			school.setSch_name(new String(prop.getProperty("sch_name").getBytes("utf-8"),"utf-8"));
			school.setSch_address(new String(prop.getProperty("sch_address").getBytes("utf-8"),"utf-8"));
			school.setSch_website(prop.getProperty("sch_website"));
			school.setSch_desc(new String(prop.getProperty("sch_desc").getBytes("utf-8"),"utf-8"));
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
