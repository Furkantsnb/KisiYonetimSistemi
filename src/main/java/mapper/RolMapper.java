package mapper;

import dto.RolCreateDto;
import dto.RolDto;
import dto.RolUpdateDto;
import entity.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolDto rolToRolDto(Rol rol);

    Rol rolCreateDtoToRol(RolCreateDto rolCreateDto);

    Rol rolUpdateDtoToRol(RolUpdateDto rolUpdateDto);

    void updateRolFromDto(RolUpdateDto rolUpdateDto, @MappingTarget Rol rol);

    // İhtiyaç duyulan özel dönüşüm metotları veya afterMapping metotları buraya eklenebilir.
}