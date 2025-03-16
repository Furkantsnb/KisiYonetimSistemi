// KisiBilgileriService.java
package service;

import dto.KisiBilgileriCreateDto;
import dto.KisiBilgileriDto;
import dto.KisiBilgileriUpdateDto;
import entity.KisiBilgileri;
import mapper.KisiBilgileriMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.KisiBilgileriRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KisiBilgileriService {

    @Autowired
    private KisiBilgileriRepository kisiBilgileriRepository;

    @Autowired
    private KisiBilgileriMapper kisiBilgileriMapper;

    public List<KisiBilgileriDto> getAllKisiBilgileriDtos() {
        return kisiBilgileriRepository.findAll().stream()
                .map(kisiBilgileriMapper::kisiBilgileriToKisiBilgileriDto)
                .collect(Collectors.toList());
    }

    public KisiBilgileriDto getKisiBilgileriDtoById(Long id) {
        return kisiBilgileriRepository.findById(id)
                .map(kisiBilgileriMapper::kisiBilgileriToKisiBilgileriDto)
                .orElse(null);
    }

    public KisiBilgileriDto createKisiBilgileri(KisiBilgileriCreateDto kisiBilgileriCreateDto) {
        KisiBilgileri kisiBilgileri = kisiBilgileriMapper.kisiBilgileriCreateDtoToKisiBilgileri(kisiBilgileriCreateDto);
        KisiBilgileri savedKisiBilgileri = kisiBilgileriRepository.save(kisiBilgileri);
        return kisiBilgileriMapper.kisiBilgileriToKisiBilgileriDto(savedKisiBilgileri);
    }

    public KisiBilgileriDto updateKisiBilgileri(Long id, KisiBilgileriUpdateDto kisiBilgileriUpdateDto) {
        return kisiBilgileriRepository.findById(id)
                .map(existingKisiBilgileri -> {
                    kisiBilgileriMapper.updateKisiBilgileriFromDto(kisiBilgileriUpdateDto, existingKisiBilgileri);
                    KisiBilgileri updatedKisiBilgileri = kisiBilgileriRepository.save(existingKisiBilgileri);
                    return kisiBilgileriMapper.kisiBilgileriToKisiBilgileriDto(updatedKisiBilgileri);
                })
                .orElse(null);
    }

    public void deleteKisiBilgileri(Long id) {
        kisiBilgileriRepository.deleteById(id);
    }
}