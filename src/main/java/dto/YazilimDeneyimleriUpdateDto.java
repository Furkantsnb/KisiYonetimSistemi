package dto;

import lombok.Data;

@Data
public class YazilimDeneyimleriUpdateDto {
    private Long id;
    private String sirketAdi;
    private String pozisyon;
    private String baslangicTarihi;
    private String bitisTarihi;
    private Long kullaniciId;
}