package mapper;

import dto.AdresBilgileriCreateDto;
import dto.AdresBilgileriDto;
import dto.AdresBilgileriUpdateDto;
import entity.AdresBilgileri;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdresBilgileriMapper {

    @Mapping(target = "kullaniciId", source = "kullanici.id")
    AdresBilgileriDto adresBilgileriToAdresBilgileriDto(AdresBilgileri adresBilgileri);

    @Mapping(target = "kullanici.id", source = "kullaniciId")
    AdresBilgileri adresBilgileriCreateDtoToAdresBilgileri(AdresBilgileriCreateDto adresBilgileriCreateDto);

    @Mapping(target = "kullanici.id", source = "kullaniciId")
    AdresBilgileri adresBilgileriUpdateDtoToAdresBilgileri(AdresBilgileriUpdateDto adresBilgileriUpdateDto);

    void updateAdresBilgileriFromDto(AdresBilgileriUpdateDto adresBilgileriUpdateDto, @MappingTarget AdresBilgileri adresBilgileri);

    // İhtiyaç duyulan özel dönüşüm metotları veya afterMapping metotları buraya eklenebilir.
}