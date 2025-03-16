package service;

import dto.IletisimBilgileriCreateDto;
import dto.IletisimBilgileriDto;
import dto.IletisimBilgileriUpdateDto;
import entity.IletisimBilgileri;
import mapper.IletisimBilgileriMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.IletisimBilgileriRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IletisimBilgileriService {

    private final IletisimBilgileriRepository iletisimBilgileriRepository;
    private final IletisimBilgileriMapper iletisimBilgileriMapper;

    @Autowired
    public IletisimBilgileriService(IletisimBilgileriRepository iletisimBilgileriRepository, IletisimBilgileriMapper iletisimBilgileriMapper) {
        this.iletisimBilgileriRepository = iletisimBilgileriRepository;
        this.iletisimBilgileriMapper = iletisimBilgileriMapper;
    }

    @Transactional(readOnly = true)
    public List<IletisimBilgileriDto> getAllIletisimBilgileriDtos() {
        return iletisimBilgileriRepository.findAll().stream()
                .map(iletisimBilgileriMapper::iletisimBilgileriToIletisimBilgileriDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<IletisimBilgileriDto> getIletisimBilgileriDtoById(Long id) {
        return iletisimBilgileriRepository.findById(id)
                .map(iletisimBilgileriMapper::iletisimBilgileriToIletisimBilgileriDto);
    }

    @Transactional
    public IletisimBilgileriDto createIletisimBilgileri(IletisimBilgileriCreateDto iletisimBilgileriCreateDto) {
        IletisimBilgileri iletisimBilgileri = iletisimBilgileriMapper.iletisimBilgileriCreateDtoToIletisimBilgileri(iletisimBilgileriCreateDto);
        IletisimBilgileri savedIletisimBilgileri = iletisimBilgileriRepository.save(iletisimBilgileri);
        return iletisimBilgileriMapper.iletisimBilgileriToIletisimBilgileriDto(savedIletisimBilgileri);
    }

    @Transactional
    public Optional<IletisimBilgileriDto> updateIletisimBilgileri(Long id, IletisimBilgileriUpdateDto iletisimBilgileriUpdateDto) {
        return iletisimBilgileriRepository.findById(id)
                .map(existingIletisimBilgileri -> {
                    iletisimBilgileriMapper.updateIletisimBilgileriFromDto(iletisimBilgileriUpdateDto, existingIletisimBilgileri);
                    IletisimBilgileri updatedIletisimBilgileri = iletisimBilgileriRepository.save(existingIletisimBilgileri);
                    return iletisimBilgileriMapper.iletisimBilgileriToIletisimBilgileriDto(updatedIletisimBilgileri);
                });
    }

    @Transactional
    public boolean deleteIletisimBilgileri(Long id) {
        if (iletisimBilgileriRepository.existsById(id)) {
            iletisimBilgileriRepository.deleteById(id);
            return true;
        }
        return false;
    }
}