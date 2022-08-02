package com.example.task1.service;

import com.example.task1.enity.FileInfo;
import com.example.task1.enity.User;
import com.example.task1.repository.FileRepository;
import com.example.task1.repository.UserRepository;
import com.fasterxml.classmate.Annotations;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepo;
    private final UserRepository userRepo;
    public void saveFileInfo(MultipartFile file) throws IOException {

        String name = "";
        String department = "";
        String inn = "";
        String telNumber = "";
        Sheet sheet = null;
        Workbook workbook= null;
        FileInputStream inputStream = new FileInputStream(new File(file.getOriginalFilename()));
        String extString = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (".xlsx".equals(extString)){
            workbook = new XSSFWorkbook(inputStream);
        }else if(".xls".equals(extString)){
            workbook = new HSSFWorkbook(inputStream);
        }
        sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {

            Row row = rowIterator.next();
            if (row.getRowNum() > 1) {
                Iterator<Cell> cellIterator = row.cellIterator();
                int index = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    switch (index) {
                        case 0:
                            name = checkFormat(cell, cellType);
                            break;
                        case 1:
                            department = checkFormat(cell, cellType);
                            break;
                        case 2:
                            inn = checkFormat(cell, cellType);
                            break;
                        case 3:
                            telNumber = checkFormat(cell, cellType);
                            break;
                    }
                    index++;
                }
                if (!name.equals("") || !department.equals("") || !inn.equals("")) {
                    Optional<FileInfo> fileInfoFinded = fileRepo.findByTelNumber(telNumber);
                    if (fileInfoFinded.isPresent()) {
                        if (!fileInfoFinded.get().getName().equals(name) || !fileInfoFinded.get().getInn().equals(inn) || !fileInfoFinded.get().getDepartment().equals(department)) {
                            fileRepo.updateFile(name, department, inn,fileInfoFinded.get().getId());
                        }
                    } else {
                        FileInfo fileInfo = FileInfo.builder()
                                .name(name)
                                .department(department)
                                .inn(inn)
                                .telNumber(telNumber)
                                .build();
                        fileRepo.save(fileInfo);
                    }
                }

            }
        }
            System.out.println("Excel успешно считан");
    }

    public String checkFormat(Cell cell, CellType cellType) {
        switch (cellType) {
            case NUMERIC:
                long num = (long) cell.getNumericCellValue();
                return String.valueOf(num);
            case STRING:
                return cell.getStringCellValue();
            case _NONE:
                break;
            case BLANK:
                break;
            case ERROR:
                break;
            case BOOLEAN:
                break;
            case FORMULA:
                break;
            default:
                break;
        }
        return "";
    }
}



