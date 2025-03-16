package dto;

import lombok.Data;

@Data
public class IletisimBilgileriUpdateDto {
    private Long id;
    private String telefon;
    private String eposta;
    private Long kullaniciId;
}