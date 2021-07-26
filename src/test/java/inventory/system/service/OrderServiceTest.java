package inventory.system.service;

import inventory.system.entity.OrderDetail;
import inventory.system.entity.ShelfDetail;
import inventory.system.model.RowColumnModel;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    /*
    Skenario fifo
    1. jika rak dalam kondisi kosong
    2. jika rak dalam kondisi penuh
    3. jika rak pada baris 1 kolom 1 keisi dan baris 1 kolom 2 tidak keiisi
    4. jika rak pada baris 1 kolom 1 tidak diisi dan baris 1 kolom 2 keiisi
    5. jika rak pada baris 1 kolom 1 tidak diisi dan baris 1 kolom 2 keiisi dan bariks 1 kolom 3 tidak diisi
     */

    @Test
    public void isShelfIsEmpty() {
        OrderService orderService = new OrderService();
        List<ShelfDetail> shelfDetais = new ArrayList<>();
        shelfDetais.add(new ShelfDetail(1, "RO-00002-00002-00001", null, null, 1, 1));

//        RowColumnModel rowColumnModel = orderService.getRowAndColumn(shelfDetais);
//        assertNotNull(rowColumnModel);
    }


    @Test
    public void isShelfIsFilled() {
        OrderService orderService = new OrderService();
        List<ShelfDetail> shelfDetais = new ArrayList<>();
        shelfDetais.add(new ShelfDetail(1, "RO-00002-00002-00001", 1, new Date(), 1, 1));

//        RowColumnModel rowColumnModel = orderService.getRowAndColumn(shelfDetais);
//        assertNull(rowColumnModel);
    }


    @Test
    public void isShelfCase1() {
        OrderService orderService = new OrderService();
        List<ShelfDetail> shelfDetais = new ArrayList<>();
        shelfDetais.add(new ShelfDetail(1, "RO-00002-00002-00001", 1, new Date(), 1, 1));
        shelfDetais.add(new ShelfDetail(2, "RO-00002-00002-00001", null, null, 1, 2));

//        RowColumnModel rowColumnModel = orderService.getRowAndColumn(shelfDetais);
//        boolean isEquals = rowColumnModel.row == 1 && rowColumnModel.column == 2;
//        assertTrue(isEquals);
    }

    @Test
    public void isShelfCase2() {
        OrderService orderService = new OrderService();
        List<ShelfDetail> shelfDetais = new ArrayList<>();
        shelfDetais.add(new ShelfDetail(1, "RO-00002-00002-00001", null, null, 1, 1));
        shelfDetais.add(new ShelfDetail(2, "RO-00002-00002-00001", 1, new Date(), 1, 2));

//        RowColumnModel rowColumnModel = orderService.getRowAndColumn(shelfDetais);
//        boolean isEquals = rowColumnModel.row == 1 && rowColumnModel.column == 1;
//        assertTrue(isEquals);
    }

    @Test
    public void isShelfCase3() {
        OrderService orderService = new OrderService();
        List<ShelfDetail> shelfDetais = new ArrayList<>();
        shelfDetais.add(new ShelfDetail(1, "RO-00002-00002-00001", null, null, 1, 1));
        shelfDetais.add(new ShelfDetail(2, "RO-00002-00002-00001", 1, new Date(), 1, 2));
        shelfDetais.add(new ShelfDetail(3, "RO-00002-00002-00001", null, null, 1, 3));

//        RowColumnModel rowColumnModel = orderService.getRowAndColumn(shelfDetais);
//        System.out.println(rowColumnModel.row + " " + rowColumnModel.column);
//        boolean isEquals = rowColumnModel.row == 1 && rowColumnModel.column == 3;
//        assertTrue(isEquals);
    }
}