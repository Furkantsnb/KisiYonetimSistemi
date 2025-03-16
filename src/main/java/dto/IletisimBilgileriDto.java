// IletisimBilgileriDto.java
package dto;

import lombok.Data;

@Data
public class IletisimBilgileriDto {
    private Long id;
    private String telefon;
    private String eposta;
    private Long kullaniciId;
    private String olusturmaTarihi;
    private String guncellemeTarihi;
}