package com.banking.service.Impl;

import com.banking.dto.KhachHangDTO;
import com.banking.exception.CCCDisExistException;
import com.banking.exception.KhachHangNotFoundException;
import com.banking.model.KhachHang;
import com.banking.repository.IKhachHangRepository;
import com.banking.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements IKhachHangService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IKhachHangRepository khachHangRepository;

    @Override
    public List<KhachHangDTO> findAllKhachHang() {
        return khachHangRepository.findAll().stream().map(KhachHangDTO::loadFromEntity).toList();
    }

    @Override
    public void insertKhachHang(KhachHangDTO khachHangDTO) {
        Optional<KhachHang> khachHang = khachHangRepository.findKhachHangByCccd(khachHangDTO.getCccd());
        if (khachHang.isPresent()){
            throw  new CCCDisExistException(messageSource.getMessage("cccd.exists",null,null));
        }else {
            KhachHang newKhachHang = new KhachHang();
            newKhachHang.loadFromDTO(khachHangDTO);
            khachHangRepository.save(newKhachHang);
        }

    }
    @Override
    public void updateKhachHang(Long id, KhachHangDTO khachHangDTO) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new KhachHangNotFoundException(messageSource.getMessage("khachhang.notfound",null,null)));

        khachHang.loadFromDTO(khachHangDTO);
        khachHangRepository.save(khachHang);
    }

    @Override
    public void deleteKhachHang(Long id) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new KhachHangNotFoundException(messageSource.getMessage("khachhang.notfound",null,null)));

        khachHangRepository.delete(khachHang);
    }

    @Override
    public KhachHangDTO findKhachHangById(Long id) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new KhachHangNotFoundException(messageSource.getMessage("khachhang.notfound",null,null)));
        return KhachHangDTO.loadFromEntity(khachHang);
    }
}
