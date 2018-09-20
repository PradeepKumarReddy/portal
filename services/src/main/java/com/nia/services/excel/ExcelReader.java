package com.nia.services.excel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.springframework.stereotype.Component;

import com.nia.services.controller.QuestionController;
import com.nia.services.entity.Question;
import com.nia.services.entity.QuestionOption;

@Component
public class ExcelReader {
	
	private static final Logger logger = LogManager.getLogger(ExcelReader.class);

	public static final String SAMPLE_XLSX_FILE_PATH = "C:\\Projects\\Sep\\services\\src\\main\\java\\com\\nia\\services\\excel\\questions.xlsx";

	public static void main(String[] args) throws IOException, InvalidFormatException {
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		
		// 3. Or you can use a Java 8 forEach with lambda
        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });
        
        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        
     // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
        sheet.forEach(row -> {
            row.forEach(cell -> {
                String cellValue = dataFormatter.formatCellValue(cell);
                CellStyle style = cell.getCellStyle();
                //System.out.println(style.getFillForegroundColor());
                //System.out.println(style.getFillBackgroundColor());
                System.out.print(cellValue + "\t");
                Color color = style.getFillForegroundColorColor();
                if (color != null) {
                 if (color instanceof XSSFColor) {
                  System.out.println(cell.getAddress() + ": " + ((XSSFColor)color).getARGBHex());
                 } else if (color instanceof HSSFColor) {
                  if (! (color instanceof HSSFColor.AUTOMATIC))
                   System.out.println(cell.getAddress() + ": " + ((HSSFColor)color).getHexString());
                 }
                }
            });
            System.out.println();
        });

        // Closing the workbook
        workbook.close();

	}
	
	public List<Question> convertExcelToEntity(InputStream inputStream) throws IOException, InvalidFormatException {
		System.out.println("readExcel called");
		
		List<Question> questions = new ArrayList<>();
		
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(inputStream);
		
		// 3. Or you can use a Java 8 forEach with lambda
		logger.debug("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
        	logger.debug("=> " + sheet.getSheetName());
        });
        
        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        
        // 3. Or you can use Java 8 forEach loop with lambda
        logger.debug("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
        
     // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
        	Question question = new Question();
        	int i =0;
            for(Cell cell: row) {
            	String cellValue = dataFormatter.formatCellValue(cell);
            	CellStyle style = cell.getCellStyle();
            	Color color = style.getFillForegroundColorColor();
            	String colorRGBCode = null;
                if (color != null) {
                 if (color instanceof XSSFColor) {
                	 // System.out.println(cell.getAddress() + ": " + ((XSSFColor)color).getARGBHex());
                	 colorRGBCode = ((XSSFColor)color).getARGBHex();
                 } else if (color instanceof HSSFColor) {
                  if (! (color instanceof HSSFColor.AUTOMATIC))
                     // System.out.println(cell.getAddress() + ": " + ((HSSFColor)color).getHexString());
                     colorRGBCode = ((HSSFColor)color).getHexString();
                 }
                }
                
                
            	QuestionOption questionOption = new QuestionOption();
            	if (i==0) {
            		question.setQuestionDesc(cellValue);
            	} else {
            		questionOption.setOptionDesc(cellValue);
            		if( colorRGBCode != null && "FF00B050".equals(colorRGBCode)) {
            			questionOption.setAnswer(true);
            		}
            		questionOption.setQuestion(question);
            		question.getOptions().add(questionOption);
            	}
            	i++;
                // System.out.print(cellValue + "\t");
            }
            /*System.out.println(question.getQuestionDesc());
            for(QuestionOption option : question.getOptions()) {
            	System.out.println(option.getOptionDesc());
            	System.out.println(option.isAnswer());
            }*/
            questions.add(question);
        }
        
	        /*sheet.forEach(row -> {
	        	Question question = new Question();
	            row.forEach(cell -> {
	                String cellValue = dataFormatter.formatCellValue(cell);
	                CellStyle style = cell.getCellStyle();
	                System.out.println(cellValue + "\t");
	                Color color = style.getFillForegroundColorColor();
	                if (color != null) {
	                 if (color instanceof XSSFColor) {
	                	 logger.debug(cell.getAddress() + ": " + ((XSSFColor)color).getARGBHex());
	                 } else if (color instanceof HSSFColor) {
	                  if (! (color instanceof HSSFColor.AUTOMATIC))
	                	  logger.debug(cell.getAddress() + ": " + ((HSSFColor)color).getHexString());
	                 }
	                }
	            });
	        });
	        */
        // Closing the workbook
        workbook.close();
        
        return questions;
	}
}
