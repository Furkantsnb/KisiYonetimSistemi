package dto;

import lombok.Data;
import java.util.List;

@Data
public class KullaniciUpdateDto {
    private Long id;
    private String kullaniciAdi;
    private String eposta;
    private List<Long> rolIds;

}