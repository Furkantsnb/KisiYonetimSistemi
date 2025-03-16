package dto;

import lombok.Data;

@Data
public class AdresBilgileriUpdateDto {
    private Long id;
    private String adres;
    private String sehir;
    private String ilce;
    private Long kullaniciId;
}