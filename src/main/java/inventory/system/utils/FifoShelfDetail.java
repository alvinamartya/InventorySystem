package inventory.system.utils;

import inventory.system.entity.ShelfDetail;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FifoShelfDetail {
    // get product in shelf origin
    public static ShelfDetail getShelfOrigin(List<ShelfDetail> shelfDetails, boolean isCanBeStale, Integer productId) {
        ShelfDetail shelfDetail = null;
        if (isCanBeStale) {
            shelfDetail = shelfDetails
                    .stream()
                    .filter(x -> x.getProduct_id() != null && x.getProduct_id().equals(productId))
                    .min(
                            Comparator
                                    .comparing(ShelfDetail::getRow_shelf)
                                    .thenComparing(ShelfDetail::getCol_shelf)
                                    .thenComparing(ShelfDetail::getExpired_at)
                    )
                    .orElse(null);
        } else {
            shelfDetail = shelfDetails
                    .stream()
                    .filter(x -> x.getProduct_id() != null && x.getProduct_id().equals(productId))
                    .min(
                            Comparator
                                    .comparing(ShelfDetail::getRow_shelf)
                                    .thenComparing(ShelfDetail::getCol_shelf)
                    )
                    .orElse(null);
        }

        return shelfDetail;
    }

    // change row and column to test in unit test
    public static ShelfDetail getShelfDest(List<ShelfDetail> shelfDetails) {
        List<ShelfDetail> emptyShelfDetail = shelfDetails
                .stream()
                .filter(x -> x.getProduct_id() == null)
                .sorted(
                        Comparator
                                .comparing(ShelfDetail::getRow_shelf)
                                .thenComparing(ShelfDetail::getCol_shelf)
                )
                .collect(Collectors.toList());

        if (emptyShelfDetail.size() > 0) {
            List<ShelfDetail> filledShelfDetail = shelfDetails
                    .stream()
                    .filter(x -> x.getProduct_id() != null)
                    .sorted(
                            Comparator
                                    .comparing(ShelfDetail::getRow_shelf)
                                    .thenComparing(ShelfDetail::getCol_shelf)
                    )
                    .collect(Collectors.toList());

            if (filledShelfDetail.size() > 0) {
                ShelfDetail lastFilledShelfDetail = filledShelfDetail.get(filledShelfDetail.size() - 1);
                int indexOfLastShelfDetail = shelfDetails
                        .indexOf(lastFilledShelfDetail);

                if (indexOfLastShelfDetail >= (shelfDetails.size() - 1)) {
                    ShelfDetail firstEmptyShelfDetail = emptyShelfDetail.get(0);
                    return firstEmptyShelfDetail;
                } else {
                    ShelfDetail nextShelfDetail = shelfDetails.get(indexOfLastShelfDetail + 1);
                    return nextShelfDetail;
                }
            } else {
                ShelfDetail firstEmptyShelfDetail = emptyShelfDetail.get(0);
                return firstEmptyShelfDetail;
            }
        }

        return null;
    }

    // has error when move product from origin shelf to dest shelf
    public static boolean moveShelfHasError(ShelfDetail originShelfDetail, ShelfDetail destShelfDetail) {
        return originShelfDetail == null || destShelfDetail == null;
    }

    // quantity product in origin shelf is same with quantity input
    public static boolean isQuantityOriginGreaterOrEquals(
            List<ShelfDetail> originShelfDetail,
            int quantityOrder,
            int productId
    ) {
        List<ShelfDetail> selectedProductInOriginShelf = originShelfDetail
                .stream()
                .filter(x -> x.getProduct_id() != null && x.getProduct_id() == productId)
                .collect(Collectors.toList());

        return selectedProductInOriginShelf.size() >= quantityOrder;
    }

    // quantity product in dest shelf is same with quantity input
    public static boolean isQuantityDestGreaterOrEquals(
            List<ShelfDetail> destShelfDetail,
            int quantityOrder
    ) {
        List<ShelfDetail> selectedEmptyShelfDetail = destShelfDetail
                .stream()
                .filter(x -> x.getProduct_id() == null)
                .collect(Collectors.toList());

        return selectedEmptyShelfDetail.size() >= quantityOrder;
    }
}
