package DAO.itf;


import model.NhanVien;
import java.util.List;

public interface IDaoNhanVien {
    List<NhanVien> read();
    int create(NhanVien nv);
    int update(NhanVien nv);
    int delete(String MaNhanVien);
}
