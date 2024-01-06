package com.banking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoanNganHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\d+",message = "Số tài khoản chỉ nhập số")
    @Column(name = "soTaiKhoan")
    private String soTK;

    private Long soDu;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "loaiTaiKhoan")
    private LoaiTK loaiTK;

    @ManyToOne
    @JoinColumn(name = "khacHangID", nullable = false)
    private KhachHang khachHangTK;
}
