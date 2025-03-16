package mapper;

import dto.YazilimDeneyimleriCreateDto;
import dto.YazilimDeneyimleriDto;
import dto.YazilimDeneyimleriUpdateDto;
import entity.YazilimDeneyimleri;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface YazilimDeneyimleriMapper {

    YazilimDeneyimleriDto yazilimDeneyimleriToYazilimDeneyimleriDto(YazilimDeneyimleri yazilimDeneyimleri);

    YazilimDeneyimleri yazilimDeneyimleriCreateDtoToYazilimDeneyimleri(YazilimDeneyimleriCreateDto yazilimDeneyimleriCreateDto);

    YazilimDeneyimleri yazilimDeneyimleriUpdateDtoToYazilimDeneyimleri(YazilimDeneyimleriUpdateDto yazilimDeneyimleriUpdateDto);

    void updateYazilimDeneyimleriFromDto(YazilimDeneyimleriUpdateDto yazilimDeneyimleriUpdateDto, @MappingTarget YazilimDeneyimleri yazilimDeneyimleri);

    // İhtiyaç duyulan özel dönüşüm metotları veya afterMapping metotları buraya eklenebilir.
}