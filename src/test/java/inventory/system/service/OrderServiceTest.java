package inventory.system.service;

import inventory.system.entity.ShelfDetail;
import inventory.system.utils.FifoShelfDetail;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderServiceTest {
    /*
    Skenario fifo
    Level 1 ---- supplier ke gudang pusat
    1. Jika rak dalam kondisi kosong
    2. Jika rak dalam kondisi penuh
    3. Jika rak pada baris 1 kolom 1 keisi dan baris 1 kolom 2 tidak keiisi
    4. Jika rak pada baris 1 kolom 1 tidak diisi dan baris 1 kolom 2 keiisi
    5. jJika rak pada baris 1 kolom 1 tidak diisi dan baris 1 kolom 2 keiisi dan bariks 1 kolom 3 tidak diisi

    Level 2 -- gudang pusat ke gudang cabang
    1. Jika produk pada gudang pusat tidak tersedia
    2. Jika kuantiti produk pada gudang pusat tidak sama dengan kuantiti yang diinput
    3. Jika kuantiti produk pada gudang pusat sama dengan kuantiti yang diinput
    4. Jika kuantiti produk pada gudang cabang tidak sama dengan kuantiti yang diinput
    5. Jika kuantiti produk pada gudang cabang sama dengan kuantiti yang diinput
    6. Jika produk cepat basi maka produk dengan tanggal expire lebih dekat di dahului

    Level 3 -- gudang cabang ke toko
    1. Jika produk pada gudang cabang tidka tersedia
    2. Jika kuantiti produk pada gudang cabang tidak sama dengan kuantiti yang diinput
    3. Jika kuantiti produk pada gudang cabang sama dengan kuantiti yang diinput
    4. Jika produk cepat basi maka produk dengan tanggal expire lebih dekat di dahului
     */
    @Test
    public void isShelfIsEmptyLevel1() {
        List<ShelfDetail> shelfDetais = new ArrayList<>();
        shelfDetais.add(new ShelfDetail(1, "RO-00002-00002-00001", null, null, 1, 1));

        ShelfDetail selectedShelfDetail = FifoShelfDetail.getRowAndColumnDest(shelfDetais);
        boolean x = selectedShelfDetail.getRow_shelf() == 1 && selectedShelfDetail.getCol_shelf() == 1;
        assertTrue(x);
    }

    @Test
    public void isShelfIsFilledLevel1() {
        List<ShelfDetail> shelfDetails = new ArrayList<>();
        shelfDetails.add(new ShelfDetail(1, "RO-00002-00002-00001", 1, new Date(), 1, 1));

        ShelfDetail selectedShelfDetail = FifoShelfDetail.getRowAndColumnDest(shelfDetails);
        boolean x = selectedShelfDetail == null;
        assertTrue(x);
    }

    @Test
    public void isShelfCase1Level1() {
        List<ShelfDetail> shelfDetails = new ArrayList<>();
        shelfDetails.add(new ShelfDetail(1, "RO-00002-00002-00001", 1, new Date(), 1, 1));
        shelfDetails.add(new ShelfDetail(2, "RO-00002-00002-00001", null, null, 1, 2));

        ShelfDetail selectedShelfDetail = FifoShelfDetail.getRowAndColumnDest(shelfDetails);
        boolean x = selectedShelfDetail.getRow_shelf() == 1 && selectedShelfDetail.getCol_shelf() == 2;
        assertTrue(x);
    }

    @Test
    public void isShelfCase2Level1() {
        List<ShelfDetail> shelfDetails = new ArrayList<>();
        shelfDetails.add(new ShelfDetail(1, "RO-00002-00002-00001", null, null, 1, 1));
        shelfDetails.add(new ShelfDetail(2, "RO-00002-00002-00001", 1, new Date(), 1, 2));

        ShelfDetail selectedShelfDetail = FifoShelfDetail.getRowAndColumnDest(shelfDetails);
        boolean x = selectedShelfDetail.getRow_shelf() == 1 && selectedShelfDetail.getCol_shelf() == 1;
        assertTrue(x);
    }

    @Test
    public void isShelfCase3Level1() {
        List<ShelfDetail> shelfDetails = new ArrayList<>();
        shelfDetails.add(new ShelfDetail(1, "RO-00002-00002-00001", null, null, 1, 1));
        shelfDetails.add(new ShelfDetail(2, "RO-00002-00002-00001", 1, new Date(), 1, 2));
        shelfDetails.add(new ShelfDetail(3, "RO-00002-00002-00001", null, null, 1, 3));

        ShelfDetail selectedShelfDetail = FifoShelfDetail.getRowAndColumnDest(shelfDetails);
        boolean x = selectedShelfDetail.getRow_shelf() == 1 && selectedShelfDetail.getCol_shelf() == 3;
        assertTrue(x);
    }

    @Test
    public void isShelfOriginProductEmptyLevel2and3() {
        // origin shelf details
        List<ShelfDetail> originShelfDetails = new ArrayList<>();
        originShelfDetails.add(new ShelfDetail(1, "RO-00002-00002-00001", null, null, 1, 1));
        originShelfDetails.add(new ShelfDetail(2, "RO-00002-00002-00001", null, null, 1, 2));
        originShelfDetails.add(new ShelfDetail(3, "RO-00002-00002-00001", null, null, 1, 3));

        // detination shelf details
        List<ShelfDetail> destShelfDetails = new ArrayList<>();
        destShelfDetails.add(new ShelfDetail(1, "RO-00003-00002-00001", null, null, 1, 1));
        destShelfDetails.add(new ShelfDetail(2, "RO-00003-00002-00001", null, null, 1, 2));
        destShelfDetails.add(new ShelfDetail(3, "RO-00003-00002-00001", null, null, 1, 3));

        ShelfDetail selectedOriginShelfDetail = FifoShelfDetail.getRowAndColumnOrigin(originShelfDetails, false, 1);
        ShelfDetail selectedDestShelfDetail = FifoShelfDetail.getRowAndColumnDest(destShelfDetails);

        boolean err = FifoShelfDetail.moveShelfHasError(selectedOriginShelfDetail, selectedDestShelfDetail);
        assertTrue(err);
    }

    @Test
    public void isShelfOriginProductQuantityNotSameLevel2and3() {
        // origin shelf details
        List<ShelfDetail> originShelfDetails = new ArrayList<>();
        originShelfDetails.add(new ShelfDetail(1, "RO-00002-00002-00001", 1, new Date(), 1, 1));
        originShelfDetails.add(new ShelfDetail(2, "RO-00002-00002-00001", 1, new Date(), 1, 2));
        originShelfDetails.add(new ShelfDetail(3, "RO-00002-00002-00001", null, null, 1, 3));

        boolean quantityIsSame = !FifoShelfDetail.isQuantityOriginSame(originShelfDetails, 3, 1);
        assertTrue(quantityIsSame);
    }

    @Test
    public void isShelfOriginProductQuantitySameLevel2and3() {
        // origin shelf details
        List<ShelfDetail> originShelfDetails = new ArrayList<>();
        originShelfDetails.add(new ShelfDetail(1, "RO-00002-00002-00001", 1, new Date(), 1, 1));
        originShelfDetails.add(new ShelfDetail(2, "RO-00002-00002-00001", 1, new Date(), 1, 2));
        originShelfDetails.add(new ShelfDetail(3, "RO-00002-00002-00001", null, null, 1, 3));

        boolean quantityIsSame = FifoShelfDetail.isQuantityOriginSame(originShelfDetails, 2, 1);
        assertTrue(quantityIsSame);
    }

    @Test
    public void isShelfDestProductQuantityNotSameLevel2() {
        List<ShelfDetail> destShelfDetails = new ArrayList<>();
        destShelfDetails.add(new ShelfDetail(1, "RO-00002-00002-00001", 1, new Date(), 1, 1));
        destShelfDetails.add(new ShelfDetail(2, "RO-00002-00002-00001", null, null, 1, 2));
        destShelfDetails.add(new ShelfDetail(3, "RO-00002-00002-00001", null, null, 1, 3));

        boolean quantityDestIsNotSame = !FifoShelfDetail.isQuantityDestSame(destShelfDetails, 3);
        assertTrue(quantityDestIsNotSame);
    }

    @Test
    public void isShelfDestProductQuantityIsSameLevel2() {
        List<ShelfDetail> destShelfDetails = new ArrayList<>();
        destShelfDetails.add(new ShelfDetail(1, "RO-00002-00002-00001", 1, new Date(), 1, 1));
        destShelfDetails.add(new ShelfDetail(2, "RO-00002-00002-00001", null, null, 1, 2));
        destShelfDetails.add(new ShelfDetail(3, "RO-00002-00002-00001", null, null, 1, 3));

        boolean quantityDestIsNotSame = FifoShelfDetail.isQuantityDestSame(destShelfDetails, 2);
        assertTrue(quantityDestIsNotSame);
    }

    @Test
    public void isProductIsCanBeStaleLevel2And3() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            List<ShelfDetail> originShelfDetails = new ArrayList<>();
            originShelfDetails.add(new ShelfDetail(1, "RO-00002-00002-00001", 1, dateFormat.parse("2021-07-30"), 1, 1));
            originShelfDetails.add(new ShelfDetail(2, "RO-00002-00002-00001", 1, dateFormat.parse("2021-07-30"), 1, 1));
            originShelfDetails.add(new ShelfDetail(3, "RO-00002-00002-00001", 1, dateFormat.parse("2021-07-29"), 1, 1));

            ShelfDetail selectedShelfDetail = FifoShelfDetail.getRowAndColumnOrigin(originShelfDetails, true, 1);
            boolean x = dateFormat.format(selectedShelfDetail.getExpired_at()).equals("2021-07-29");
            assertTrue(x);
        } catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}