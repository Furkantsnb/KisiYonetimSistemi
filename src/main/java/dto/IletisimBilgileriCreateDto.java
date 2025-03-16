package dto;

import lombok.Data;

@Data
public class IletisimBilgileriCreateDto {
    private String telefon;
    private String eposta;
    private Long kullaniciId;
}