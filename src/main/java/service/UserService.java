package service;

import dto.KullaniciCreateDto;
import dto.KullaniciDto;
import dto.KullaniciUpdateDto;
import entity.Kullanici;
import mapper.KullaniciMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.KullaniciRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Autowired
    private KullaniciMapper kullaniciMapper;

    public List<KullaniciDto> getAllKullaniciDtos() {
        List<Kullanici> kullanicilar = kullaniciRepository.findAll();
        return kullanicilar.stream()
                .map(kullaniciMapper::toDto)
                .collect(Collectors.toList());
    }

    public KullaniciDto getKullaniciDtoById(Long id) {
        return kullaniciRepository.findById(id)
                .map(kullaniciMapper::toDto)
                .orElse(null);
    }

    public KullaniciDto createKullanici(KullaniciCreateDto kullaniciCreateDto) {
        Kullanici kullanici = kullaniciMapper.toEntity(kullaniciCreateDto);
        Kullanici savedKullanici = kullaniciRepository.save(kullanici);
        return kullaniciMapper.toDto(savedKullanici);
    }

    public KullaniciDto updateKullanici(Long id, KullaniciUpdateDto kullaniciUpdateDto) {
        if (kullaniciRepository.existsById(id)) {
            Kullanici kullanici = kullaniciMapper.toEntity(kullaniciUpdateDto);
            kullanici.setId(id);
            Kullanici updatedKullanici = kullaniciRepository.save(kullanici);
            return kullaniciMapper.toDto(updatedKullanici);
        }
        return null;
    }

    public void deleteKullanici(Long id) {
        kullaniciRepository.deleteById(id);
    }
}