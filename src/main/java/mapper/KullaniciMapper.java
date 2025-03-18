package mapper;

import dto.KullaniciCreateDto;
import dto.KullaniciDto;
import dto.KullaniciUpdateDto;
import entity.Kullanici;
import entity.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import repository.RolRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class KullaniciMapper {

    @Autowired
    private RolRepository rolRepository;

    @Mapping(target = "rolIds", expression = "java(mapRollerToRolIds(kullanici.getRoller()))")
    @Mapping(target = "kisiBilgileriId", source = "kisiBilgileri.id")
    @Mapping(target = "iletisimBilgileriIds", expression = "java(mapIletisimBilgileriToIds(kullanici.getIletisimBilgileri()))")
    @Mapping(target = "adresBilgileriIds", expression = "java(mapAdresBilgileriToIds(kullanici.getAdresBilgileri()))")
    @Mapping(target = "yazilimDeneyimleriIds", expression = "java(mapYazilimDeneyimleriToIds(kullanici.getYazilimDeneyimleri()))")
    public abstract KullaniciDto kullaniciToKullaniciDto(Kullanici kullanici);

    @Mapping(target = "roller", expression = "java(mapRolIdsToRoller(kullaniciCreateDto.getRolIds()))")
    public abstract Kullanici kullaniciCreateDtoToKullanici(KullaniciCreateDto kullaniciCreateDto);

    @Mapping(target = "roller", expression = "java(mapRolIdsToRoller(kullaniciUpdateDto.getRolIds()))")
    public abstract Kullanici kullaniciUpdateDtoToKullanici(KullaniciUpdateDto kullaniciUpdateDto);

    public abstract void updateKullaniciFromDto(KullaniciUpdateDto kullaniciUpdateDto, @MappingTarget Kullanici kullanici);

    // Yardımcı metotlar
    protected List<Long> mapRollerToRolIds(List<Rol> roller) {
        if (roller == null) {
            return null;
        }
        return roller.stream()
                .map(Rol::getId)
                .collect(Collectors.toList());
    }

    protected List<Long> mapIletisimBilgileriToIds(List<entity.IletisimBilgileri> iletisimBilgileri) {
        if (iletisimBilgileri == null) {
            return null;
        }
        return iletisimBilgileri.stream()
                .map(entity.IletisimBilgileri::getId)
                .collect(Collectors.toList());
    }

    protected List<Long> mapAdresBilgileriToIds(List<entity.AdresBilgileri> adresBilgileri) {
        if (adresBilgileri == null) {
            return null;
        }
        return adresBilgileri.stream()
                .map(entity.AdresBilgileri::getId)
                .collect(Collectors.toList());
    }

    protected List<Long> mapYazilimDeneyimleriToIds(List<entity.YazilimDeneyimleri> yazilimDeneyimleri) {
        if (yazilimDeneyimleri == null) {
            return null;
        }
        return yazilimDeneyimleri.stream()
                .map(entity.YazilimDeneyimleri::getId)
                .collect(Collectors.toList());
    }

    public abstract KullaniciDto toDto(Kullanici kullanici);

    @Mapping(target = "sifre", source = "sifre")
    public abstract Kullanici toEntity(KullaniciCreateDto kullaniciCreateDto);

    @Mapping(target = "sifre", source = "sifre")
    public abstract Kullanici toEntity(KullaniciUpdateDto kullaniciUpdateDto);

    protected List<Rol> mapRolIdsToRoller(List<Long> rolIds) {
        if (rolIds == null || rolIds.isEmpty()) {
            return null;
        }
        return rolIds.stream()
                .map(rolRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}