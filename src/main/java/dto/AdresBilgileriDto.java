// AdresBilgileriDto.java
package dto;

import lombok.Data;

@Data
public class AdresBilgileriDto {
    private Long id;
    private String adres;
    private String sehir;
    private String ilce;
    private Long kullaniciId;
    private String olusturmaTarihi;
    private String guncellemeTarihi;
}