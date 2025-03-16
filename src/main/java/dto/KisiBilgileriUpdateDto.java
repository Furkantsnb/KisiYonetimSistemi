package dto;

import lombok.Data;

@Data
public class KisiBilgileriUpdateDto {
    private Long id;
    private String tcKimlikNo;
    private String ad;
    private String soyad;
    private String dogumTarihi;
    private String dogumYeri;
    private String anneAdi;
    private String babaAdi;
    private String cinsiyet;
    private String uyruk;
    private Long kullaniciId;
}