package dto;

import lombok.Data;

@Data
public class AdresBilgileriCreateDto {
    private String adres;
    private String sehir;
    private String ilce;
    private Long kullaniciId;
}