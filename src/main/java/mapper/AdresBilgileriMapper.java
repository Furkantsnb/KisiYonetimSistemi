package mapper;

import dto.AdresBilgileriCreateDto;
import dto.AdresBilgileriDto;
import dto.AdresBilgileriUpdateDto;
import entity.AdresBilgileri;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdresBilgileriMapper {

    AdresBilgileriDto adresBilgileriToAdresBilgileriDto(AdresBilgileri adresBilgileri);

    AdresBilgileri adresBilgileriCreateDtoToAdresBilgileri(AdresBilgileriCreateDto adresBilgileriCreateDto);

    AdresBilgileri adresBilgileriUpdateDtoToAdresBilgileri(AdresBilgileriUpdateDto adresBilgileriUpdateDto);

    void updateAdresBilgileriFromDto(AdresBilgileriUpdateDto adresBilgileriUpdateDto, @MappingTarget AdresBilgileri adresBilgileri);

    // İhtiyaç duyulan özel dönüşüm metotları veya afterMapping metotları buraya eklenebilir.
}