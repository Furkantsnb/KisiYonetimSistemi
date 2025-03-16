// RolDto.java
package dto;

import lombok.Data;
import java.util.List;

@Data
public class RolDto {
    private Long id;
    private String rolAdi;
    private List<Long> kullaniciIds;
    private String olusturmaTarihi;
    private String guncellemeTarihi;
}