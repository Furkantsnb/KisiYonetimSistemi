package dto;

import lombok.Data;

@Data
public class YazilimDeneyimleriCreateDto {
    private String sirketAdi;
    private String pozisyon;
    private String baslangicTarihi;
    private String bitisTarihi;
    private Long kullaniciId;
}