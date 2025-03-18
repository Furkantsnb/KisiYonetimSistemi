package dto;

import lombok.Data;
import java.util.List;

@Data
public class KullaniciCreateDto {
    private String kullaniciAdi;
    private String sifre;
    private String eposta;
    private List<Long> rolIds;

}