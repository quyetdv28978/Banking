package com.banking.repository;

import com.banking.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IKhachHangRepository extends JpaRepository<KhachHang,Long > {
    Optional<KhachHang> findKhachHangByCccd(String cccd);
    Optional<KhachHang> findKhachHangById(Long id);
}
