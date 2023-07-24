package DAO.itf;

import model.SupplyRequest;

import java.util.Date;
import java.util.List;

public interface SupplyRequestDAOInterface {
    List<SupplyRequest> read();
    int create(SupplyRequest supplyRequest);
    int update(SupplyRequest supplyRequest);
    int update(String supplyRequestID, String supplyRequestStatus);

    int update(String supplyRequestID, String supplyRequestStatus, Date receiveDate);

    int delete(String supplyRequestID);
}
