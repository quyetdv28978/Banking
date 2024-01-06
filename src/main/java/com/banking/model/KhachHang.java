package com.banking.model;

import com.banking.dto.KhachHangDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "{khachhang.not.blank}")
    @Size(max = 11, message = "{khachhang.sdt.maxlength}")
    @Pattern(regexp = "\\d+", message = "{khachhang.sdt.regex}")
    private String sdt;

    @NotBlank(message = "{khachhang.cccd.notblank}")
    @Size(max = 12, message = "{khachhang.cccd.maxlength}")
    @Pattern(regexp = "\\d+", message = "{khachhang.cccd.regex}")
    @Column(unique = true)
    private String cccd;

    @NotBlank(message = "{khachhang.hoten.notblank}")
    private String hoTen;

    @Enumerated(EnumType.STRING)
    private GioiTinh gioiTinh;

    @NotNull(message = "{khachhang.ngaysinh.notnull}")
    @Past(message = "{khachhang.ngaysinh.past}")
    private LocalDate ngaySinh;

    public void loadFromDTO(KhachHangDTO khachHangDTO) {
        this.sdt = khachHangDTO.getSdt();
        this.cccd = khachHangDTO.getCccd();
        this.hoTen = khachHangDTO.getHoTen();
        this.gioiTinh = khachHangDTO.getGioiTinh();
        this.ngaySinh = khachHangDTO.getNgaySinh();
    }
}
