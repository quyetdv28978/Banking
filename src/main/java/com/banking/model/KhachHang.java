package com.banking.model;

import com.banking.constant.Constant;
import com.banking.dto.KhachHangDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// class map với bảng khachhang trong db
// Thuộc tính : 
/*id: primary key kiểu long, giá trị tự động tăng
 * sdt: ràng buộc not blank, maxlength = 11, chỉ chứa số
 * cccd: ràng buộc not blank, maxlength = 12, chi chứa số
 * hoTen: ràng buộc not blank
 * ngaySinh: ràng buộc not null, không được là ngày trong tương lai 9/1/2024 -> error
 */
// Hàm loadFromDTO chuyển đỗi dữ liệu từ khachhangDTO sang khachhang
// Tham số nhận vào là 1 khachhangDTO
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = Constant.MessageResponse.KH_PHONE_NOT_BLANK)
    @Size(max = 11, message = Constant.MessageResponse.KH_PHONE_MAX_LENGHT)
    @Pattern(regexp = "\\d+", message = Constant.MessageResponse.KH_PHONE_REGEX)
    @Schema(example = "09127362")
    private String sdt;

    @NotBlank(message = Constant.MessageResponse.KH_CCCD_NOT_BLANK)
    @Size(max = 12, message = Constant.MessageResponse.KH_CCCD_MAX_LENGHT)
    @Pattern(regexp = "\\d+", message = Constant.MessageResponse.KH_CCCD_REGEX)
    @Column(unique = true)
    @Schema(example = "0123456789")
    private String cccd;

    @NotBlank(message = Constant.MessageResponse.KH_HOTEN_NOT_BLANK)
    @Schema(example = "Nguyễn Văn A")
    private String hoTen;

    @Enumerated(EnumType.STRING)
    @Schema(example = "Nam")
    private GioiTinh gioiTinh;

    @NotNull(message = Constant.MessageResponse.KH_NGAYSINH_NOT_NULL)
    @Past(message = Constant.MessageResponse.KH_NGAYSINH_PAST)
    @Schema(example = "1992-06-26")
    private LocalDate ngaySinh;


    public void loadFromDTO(KhachHangDTO khachHangDTO) {
        this.sdt = khachHangDTO.getSdt();
        this.cccd = khachHangDTO.getCccd();
        this.hoTen = khachHangDTO.getHoTen();
        this.gioiTinh = khachHangDTO.getGioiTinh();
        this.ngaySinh = khachHangDTO.getNgaySinh();
    }
}
