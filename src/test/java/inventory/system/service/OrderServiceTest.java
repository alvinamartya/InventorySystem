package inventory.system.service;

import inventory.system.entity.OrderDetail;
import inventory.system.entity.ShelfDetail;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    public void isShelfIsEmpty() {
        List<OrderDetail> listOrderDetail = new ArrayList<>();

        // order detail
        OrderDetail orderDetail = new OrderDetail(
                1,
                "FO-S00004-W00006-20210723-00001",
                1,
                "RO-00002-00002-00001",
                "RO-00002-00004-00001",
                5
        );

        // shelf detail
        List<ShelfDetail> shelfDetails = new ArrayList<>();
//        shelfDetails.add(new ShelfDetail())
//
//        shelfDetails.add(new ShelfDetail(1, ));
//        assertEquals(1,);
    }
}