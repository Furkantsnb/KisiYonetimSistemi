package mapper;

import dto.IletisimBilgileriCreateDto;
import dto.IletisimBilgileriDto;
import dto.IletisimBilgileriUpdateDto;
import entity.IletisimBilgileri;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IletisimBilgileriMapper {

    @Mapping(target = "kullaniciId", source = "kullanici.id")
    IletisimBilgileriDto iletisimBilgileriToIletisimBilgileriDto(IletisimBilgileri iletisimBilgileri);

    IletisimBilgileri iletisimBilgileriCreateDtoToIletisimBilgileri(IletisimBilgileriCreateDto iletisimBilgileriCreateDto);

    IletisimBilgileri iletisimBilgileriUpdateDtoToIletisimBilgileri(IletisimBilgileriUpdateDto iletisimBilgileriUpdateDto);

    void updateIletisimBilgileriFromDto(IletisimBilgileriUpdateDto iletisimBilgileriUpdateDto, @MappingTarget IletisimBilgileri iletisimBilgileri);

    // Özel dönüşüm metotları (örnek - gerekirse)
    // Örnek: Telefon numarasını formatlamak için bir metot
    default String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }
        // Telefon numarasını istediğiniz formata dönüştüren kod
        // Örnek: "1234567890" -> "(123) 456-7890"
        return phoneNumber; // Şu anda basitçe aynı numarayı döndürüyor
    }

    // afterMapping metodu (örnek - gerekirse)
    default void afterIletisimBilgileriDto(IletisimBilgileri iletisimBilgileri, @MappingTarget IletisimBilgileriDto iletisimBilgileriDto) {
        // İhtiyaç duyduğunuz ek işlemleri burada yapabilirsiniz.
        // Örneğin, hesaplanmış alanları eklemek veya ilişkili entity'leri manuel olarak ayarlamak gibi.
    }
}