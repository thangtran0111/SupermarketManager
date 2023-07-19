package DAO.itf;
import model.MatHang;

import java.util.List;

public interface IDaoMatHang {
    int create(MatHang mh);
    List<MatHang> read();
    MatHang get(String maMatHang);
    int update(MatHang mh);
    int delete(String maMatHang);
}
