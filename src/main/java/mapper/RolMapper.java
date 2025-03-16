package mapper;

import dto.RolCreateDto;
import dto.RolDto;
import dto.RolUpdateDto;
import entity.Rol;
import entity.Kullanici;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(target = "kullaniciIds", expression = "java(mapKullanicilarToKullaniciIds(rol.getKullanicilar()))")
    RolDto rolToRolDto(Rol rol);

    Rol rolCreateDtoToRol(RolCreateDto rolCreateDto);

    Rol rolUpdateDtoToRol(RolUpdateDto rolUpdateDto);

    void updateRolFromDto(RolUpdateDto rolUpdateDto, @MappingTarget Rol rol);

    // Yardımcı metot
    default List<Long> mapKullanicilarToKullaniciIds(List<Kullanici> kullanicilar) {
        if (kullanicilar == null || kullanicilar.isEmpty()) {
            return null;
        }
        return kullanicilar.stream()
                .map(Kullanici::getId)
                .collect(Collectors.toList());
    }
}