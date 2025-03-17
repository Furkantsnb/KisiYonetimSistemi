package service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import repository.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class ExcelService {

    private final KullaniciRepository kullaniciRepository;
    private final KisiBilgileriRepository kisiBilgileriRepository;
    private final AdresBilgileriRepository adresBilgileriRepository;
    private final IletisimBilgileriRepository iletisimBilgileriRepository;
    private final RolRepository rolRepository;
    private final YazilimDeneyimleriRepository yazilimDeneyimleriRepository;

    public ExcelService(KullaniciRepository kullaniciRepository,
                        KisiBilgileriRepository kisiBilgileriRepository,
                        AdresBilgileriRepository adresBilgileriRepository,
                        IletisimBilgileriRepository iletisimBilgileriRepository,
                        RolRepository rolRepository,
                        YazilimDeneyimleriRepository yazilimDeneyimleriRepository) {
        this.kullaniciRepository = kullaniciRepository;
        this.kisiBilgileriRepository = kisiBilgileriRepository;
        this.adresBilgileriRepository = adresBilgileriRepository;
        this.iletisimBilgileriRepository = iletisimBilgileriRepository;
        this.rolRepository = rolRepository;
        this.yazilimDeneyimleriRepository = yazilimDeneyimleriRepository;
    }

    public ByteArrayInputStream generateExcel() throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            createSheet(workbook, "Kullanicilar", kullaniciRepository.findAll());
            createSheet(workbook, "KisiBilgileri", kisiBilgileriRepository.findAll());
            createSheet(workbook, "AdresBilgileri", adresBilgileriRepository.findAll());
            createSheet(workbook, "IletisimBilgileri", iletisimBilgileriRepository.findAll());
            createSheet(workbook, "Roller", rolRepository.findAll());
            createSheet(workbook, "YazilimDeneyimleri", yazilimDeneyimleriRepository.findAll());

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private <T> void createSheet(Workbook workbook, String sheetName, List<T> data) {
        Sheet sheet = workbook.createSheet(sheetName);
        Row headerRow = sheet.createRow(0);

        if (data.isEmpty()) {
            return;
        }

        Field[] fields = data.get(0).getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fields[i].getName());
        }

        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                Cell cell = row.createCell(j); // cell değişkenini burada tanımlayın
                try {
                    Object value = fields[j].get(data.get(i));
                    if (value != null) {
                        if (value instanceof List) {
                            cell.setCellValue("LIST");
                        } else {
                            cell.setCellValue(String.valueOf(value));
                        }
                    } else {
                        cell.setCellValue("");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    cell.setCellValue("ERROR");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    cell.setCellValue("");
                }
            }
        }
    }
}