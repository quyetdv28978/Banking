package com.banking.controller;

import com.banking.dto.KhachHangDTO;
import com.banking.model.KhachHang;
import com.banking.service.Impl.KhachHangServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Khach hang", description = "KhachHang APIs")
@RequestMapping("/api/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangServiceImpl khachHangService;

    @GetMapping()
    public ResponseEntity<List<KhachHangDTO>> getAll() {

        List<KhachHangDTO> khachHangList= khachHangService.findAllKhachHang();
        return new ResponseEntity<>(khachHangList, HttpStatus.OK);
    }

    @GetMapping("/{khachHangId}")
    public ResponseEntity<KhachHangDTO> getKhachHangById(
            @PathVariable(name = "khachHangId") Long id
    ) {

        KhachHangDTO khachHang = khachHangService.findKhachHangById(id);
        return new ResponseEntity<>(khachHang, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KhachHangDTO> createKhachHang(
            @RequestBody @Valid KhachHangDTO khachHangDTO
    ) {

        khachHangService.insertKhachHang(khachHangDTO);
        return new ResponseEntity<>(khachHangDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateKhachHang(
            @PathVariable Long id,
            @RequestBody @Valid KhachHangDTO khachHangDTO){
        khachHangService.updateKhachHang(id,khachHangDTO);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteKhachHang(
            @PathVariable Long id
    ){
        khachHangService.deleteKhachHang(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
