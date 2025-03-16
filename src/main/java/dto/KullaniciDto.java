// KullaniciDto.java
package dto;

import lombok.Data;
import java.util.List;

@Data
public class KullaniciDto {
    private Long id;
    private String kullaniciAdi;
    private String eposta;
    private List<Long> rolIds;
    private Long kisiBilgileriId;
    private List<Long> iletisimBilgileriIds;
    private List<Long> adresBilgileriIds;
    private List<Long> yazilimDeneyimleriIds;
    private String olusturmaTarihi;
    private String guncellemeTarihi;
}