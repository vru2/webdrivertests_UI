package ct_config;

import static org.testng.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.analysis.function.Log;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import org.apache.log4j.*;

import edu.emory.mathcs.backport.java.util.Arrays;
import groovy.util.logging.Log4j;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Fetch_ctconfig {
	String respnfs;
	String respgfs;
	String resourcename;
	String rn=resourcename;
	Response resp1;
	Response resp2;
	Logger log;
	String url1 = "http://172.17.26.11:8413/hq/ct-config/api/resource/fetch/";
	String url2 = "http://172.17.26.11:8413/hq/ct-config/api/resource/test/fetch/";

	@Test
	public void fetchResourcefromNFS_GFS() throws IOException {
		String projectpath = System.getProperty("user.dir");
		XSSFWorkbook workbook = new XSSFWorkbook(projectpath + "/excel/ResourceName.xlsx");
		XSSFSheet sheet = workbook.getSheet("sheet1");
		int row = sheet.getPhysicalNumberOfRows();
		System.out.println(row);
		Iterator<Row> rows = sheet.iterator();
		Row firstrow = rows.next();
		int column = 0;
		while (rows.hasNext()) {
			Row r = rows.next();
			resourcename = r.getCell(column).getStringCellValue();
			System.out.println(resourcename);
			resp1 = RestAssured.given().when().log().all().headers("source", "/usr/local/cal/production/json/")
					.headers("Content-Type", "application/json").get(url1 + resourcename);
			System.out.println(resp1.asString());
			if (resp1.statusCode() == 200) {
				ResponseBody body = resp1.getBody();
				respnfs = body.asString();
				Reporter.log(resp1.asString());
				Reporter.log("Status code " + resp1.statusCode());
				Assert.assertNotNull(resp1);

			} else {
				Reporter.log("Status code " + resp1.statusCode());
				assertTrue(false);
			}
			resp2 = RestAssured.given().when().log().all().headers("cache-control", "no-cache")
					.headers("Content-Type", "application/json").get(url2 + resourcename);
			System.out.println(resp2.asString());
			if (resp2.statusCode() == 200) {
				ResponseBody body = resp2.getBody();
				respgfs = body.asString();
				Reporter.log(resp2.asString());
				Reporter.log("Status code " + resp2.statusCode());
				Assert.assertNotNull(resp2);

			} else {
				Reporter.log("Status code " + resp2.statusCode());
				assertTrue(false);
			}
			if (respnfs.equals(respgfs)) {
				System.out.println("Content of nfs and gfs are same for resource file " + resourcename);
				 log = Logger.getLogger(Fetch_ctconfig.class);
				  log.info("Select value from drop down.");
				  Reporter.log("Content of nfs and gfs is same for resource file " + resourcename);
				  File file = new File(projectpath + "/excel/ResourceFetchoutputpassed.xlsx"); 
				  FileInputStream fis=new FileInputStream(file);
				  XSSFWorkbook w=new XSSFWorkbook(fis);
				  XSSFSheet s=w.getSheet("Sheet1");
				  XSSFRow rw=null;
				  XSSFCell c=null;
				  int colNum=-1;
				  int j=1;
				  int i=0;
				  rw=s.getRow(0);
				  for(i=0;i<rw.getLastCellNum();i++)
				  {
					  if(rw.getCell(i).getStringCellValue().trim().equals("Resourcename"))
					  {
						  colNum=i;
					  }
				  }
				  if(rw.getCell(colNum).getStringCellValue().equals(null))
				  {
				  rw=s.getRow(j);
				  if(rw==null)
					  rw=s.createRow(j);
				   c=rw.getCell(j);
					
					  if(c==null) 
					c=rw.createCell(j);
					 
				  c.setCellValue(resourcename);
				  s.rowIterator().next();
				} 
				  j++;
				  FileOutputStream fos=new FileOutputStream(file);
				  w.write(fos);
				  fos.close();
				  
			} 
				  else{
				  System.out.println("Content of nfs and gfs is not same for resource file " +
				  resourcename);
				  
				/*
				 * File file=new File(projectpath+"/excel/ResourceFetchoutputpassed.xlsx");
				 * FileOutputStream fos = new FileOutputStream(file); workbook.write(fos);
				 * fos.close();
				 */
				  
				  }
				 
		}
		column++;
		String st[]=resourcename.split(" ");
		int count=0;
		List<String> list=new ArrayList<String>();
	    list.add(count, rn);
	    count++;
		/*//List<String> list = new ArrayList<String>()
		list=Arrays.asList(st);
		for(String str:list)
		{*/
			System.out.println(list);
		/* } */
	}
	
}