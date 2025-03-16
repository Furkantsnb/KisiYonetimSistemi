package mapper;

import dto.KisiBilgileriCreateDto;
import dto.KisiBilgileriDto;
import dto.KisiBilgileriUpdateDto;
import entity.KisiBilgileri;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KisiBilgileriMapper {

    KisiBilgileriDto kisiBilgileriToKisiBilgileriDto(KisiBilgileri kisiBilgileri);

    KisiBilgileri kisiBilgileriCreateDtoToKisiBilgileri(KisiBilgileriCreateDto kisiBilgileriCreateDto);

    KisiBilgileri kisiBilgileriUpdateDtoToKisiBilgileri(KisiBilgileriUpdateDto kisiBilgileriUpdateDto);

    void updateKisiBilgileriFromDto(KisiBilgileriUpdateDto kisiBilgileriUpdateDto, @MappingTarget KisiBilgileri kisiBilgileri);

    // Özel dönüşüm metotları (örnek)
    default String formatDate(java.time.LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    // afterMapping metodu (örnek)
    default void afterKisiBilgileriDto(KisiBilgileri kisiBilgileri, @MappingTarget KisiBilgileriDto kisiBilgileriDto) {
        // İhtiyaç duyduğunuz ek işlemleri burada yapabilirsiniz
    }
}