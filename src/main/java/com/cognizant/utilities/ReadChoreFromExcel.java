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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cognizant.entities.Chore;
import com.cognizant.entities.Task;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadChoreFromExcel {
	
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
     				case 5:
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
    
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
    
}
