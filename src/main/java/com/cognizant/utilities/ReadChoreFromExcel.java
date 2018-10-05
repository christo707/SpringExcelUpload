package com.cognizant.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.cognizant.entities.Chore;
import com.cognizant.entities.Task;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadChoreFromExcel {
	
	private static String[] columns = {"Chore_Id", "Task_Id", "Emp_Id", "Item", "Weight", "Quantity"};
	
	private String inputFile;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
    @SuppressWarnings("deprecation")
	private static Object getCellValue(Cell cell) {
		System.out.println(cell);
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			System.out.println("Row No.: " + cell.getNumericCellValue());
			return cell.getNumericCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return cell.getBooleanCellValue();
		}

		return null;

	}

    public List<Chore> read(MultipartFile multipartFile) throws IOException {
    	 try {
    		 
    		 File file = convertMultiPartToFile(multipartFile);
    		 
    		 List<Chore> chores = new ArrayList<Chore>();

             //FileInputStream excelFile = new FileInputStream(new File(this.inputFile));
             Workbook workbook = WorkbookFactory.create(file);
             //Workbook workbook = new XSSFWorkbook(excelFile);
             Sheet datatypeSheet = workbook.getSheetAt(0);
             Iterator<Row> iterator = datatypeSheet.iterator();
             if(iterator.hasNext())  // For first row
            	 iterator.next();
             while (iterator.hasNext()) {
            	 
            	 Chore t = new Chore();
                 Row currentRow = iterator.next();
                 Iterator<Cell> cellIterator = currentRow.iterator();

                 while (cellIterator.hasNext()) {
     				Cell nextCell = cellIterator.next();
     				int columnIndex = nextCell.getColumnIndex();

     				switch (columnIndex) {
     				case 0:
     					t.setTaskid(((Double) getCellValue(nextCell)).intValue());
     					break;
     				case 1:
     					t.setEmpid(((Double) getCellValue(nextCell)).intValue());
     					break;
     				case 2:
     					t.setItem(((String) getCellValue(nextCell)));
     					break;
     				case 3:
     					t.setWeight(((String) getCellValue(nextCell)));
     					break;
     				case 4:
     					t.setQuantity(((Double) getCellValue(nextCell)).intValue());
     					break;
     				}

     			}
                 chores.add(t);
             }
             return chores;
         } catch (FileNotFoundException e) {
             e.printStackTrace();
             return null;
         } catch (IOException e) {
             e.printStackTrace();
             return null;
         } catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return null;
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return null;
		}
    }
    
    public boolean write(List<Chore> chores) throws IOException, InvalidFormatException {
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Chores");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

//        Create Cell Style for formatting Date
//        CellStyle dateCellStyle = workbook.createCellStyle();
//        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(Chore chore: chores) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(chore.getId());

            row.createCell(1)
                    .setCellValue(chore.getTaskid());
            
            row.createCell(2)
            .setCellValue(chore.getEmpid());
            
            row.createCell(3)
            .setCellValue(chore.getItem());
            
            row.createCell(4)
            .setCellValue(chore.getWeight());

            row.createCell(5)
            .setCellValue(chore.getQuantity());

        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("chores.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
        
        return true;
        
    }
    
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
    
}
