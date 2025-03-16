package mapper;

import dto.KullaniciCreateDto;
import dto.KullaniciDto;
import dto.KullaniciUpdateDto;
import entity.Kullanici;
import entity.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface KullaniciMapper {

    @Mapping(target = "rolIds", expression = "java(mapRollerToRolIds(kullanici.getRoller()))")
    @Mapping(target = "kisiBilgileriId", source = "kisiBilgileri.id")
    @Mapping(target = "iletisimBilgileriIds", expression = "java(mapIletisimBilgileriToIds(kullanici.getIletisimBilgileri()))")
    @Mapping(target = "adresBilgileriIds", expression = "java(mapAdresBilgileriToIds(kullanici.getAdresBilgileri()))")
    @Mapping(target = "yazilimDeneyimleriIds", expression = "java(mapYazilimDeneyimleriToIds(kullanici.getYazilimDeneyimleri()))")
    KullaniciDto kullaniciToKullaniciDto(Kullanici kullanici);

    Kullanici kullaniciCreateDtoToKullanici(KullaniciCreateDto kullaniciCreateDto);

    Kullanici kullaniciUpdateDtoToKullanici(KullaniciUpdateDto kullaniciUpdateDto);

    void updateKullaniciFromDto(KullaniciUpdateDto kullaniciUpdateDto, @MappingTarget Kullanici kullanici);

    // Yardımcı metotlar
    default List<Long> mapRollerToRolIds(List<Rol> roller) {
        if (roller == null) {
            return null;
        }
        return roller.stream()
                .map(Rol::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapIletisimBilgileriToIds(List<entity.IletisimBilgileri> iletisimBilgileri) {
        if (iletisimBilgileri == null) {
            return null;
        }
        return iletisimBilgileri.stream()
                .map(entity.IletisimBilgileri::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapAdresBilgileriToIds(List<entity.AdresBilgileri> adresBilgileri) {
        if (adresBilgileri == null) {
            return null;
        }
        return adresBilgileri.stream()
                .map(entity.AdresBilgileri::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapYazilimDeneyimleriToIds(List<entity.YazilimDeneyimleri> yazilimDeneyimleri) {
        if (yazilimDeneyimleri == null) {
            return null;
        }
        return yazilimDeneyimleri.stream()
                .map(entity.YazilimDeneyimleri::getId)
                .collect(Collectors.toList());
    }
}