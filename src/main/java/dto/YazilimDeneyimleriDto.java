// YazilimDeneyimleriDto.java
package dto;

import lombok.Data;

@Data
public class YazilimDeneyimleriDto {
    private Long id;
    private String sirketAdi;
    private String pozisyon;
    private String baslangicTarihi;
    private String bitisTarihi;
    private Long kullaniciId;
    private String olusturmaTarihi;
    private String guncellemeTarihi;
}