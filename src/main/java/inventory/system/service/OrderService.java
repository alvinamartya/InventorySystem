package inventory.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import inventory.system.entity.*;
import inventory.system.model.OrderDetailInputModel;
import inventory.system.repository.OrderDetailRepository;
import inventory.system.repository.OrderRepository;
import inventory.system.repository.ShelfDetailRepository;
import inventory.system.utils.FifoShelfDetail;
import inventory.system.utils.GeneratorId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Resource
    OrderRepository ordersRepository;

    @Resource
    OrderDetailRepository orderDetailsRepository;

    @Resource
    ShelfDetailRepository shelfDetailRepository;

    public List<Order> getAllOrder() {
        return (List<Order>) ordersRepository.findAll();
    }

    public List<Order> getAllOrderByWarehouse(String warehouse_id, int level) {
        if (level == 1) {
            return ordersRepository.findByWarehouseLv1(warehouse_id);
        } else if (level == 2) {
            return ordersRepository.findByWarehouseLv2(warehouse_id);
        } else if (level == 3) {
            return ordersRepository.findByWarehouseLv3(warehouse_id);
        }
        return (List<Order>) ordersRepository.findAll();
    }

    public void saveOrder(OrderInput orderinput, LoggedUser loggedUser) {
        Order orders = new Order();
        String orderId = generateId(orderinput.getOrigin_warehouse_id()
                , orderinput.getDest_warehouse_id()
                , orderinput.getOrigin_type()
                , orderinput.getDest_type());
        orders.setId(orderId);
        orders.setOrigin_id(orderinput.getOrigin_warehouse_id());
        orders.setOrigin_type(orderinput.getOrigin_type());
        orders.setDest_id(orderinput.getDest_warehouse_id());
        orders.setDest_type(orderinput.getDest_type());
        orders.setDate(new Date());
        if (!orderinput.getDriver_id().equals(0)) {
            orders.setDriver_id(orderinput.getDriver_id());
        }

        orders.setCreated_at(new Date());
        orders.setCreated_by(loggedUser.getName());
        orders.setChecked_at(new Date());
        orders.setChecked_by("-");
        orders.setApproved_at(new Date());
        orders.setApproved_by("-");
        orders.setUpdated_at(new Date());
        orders.setUpdated_by(loggedUser.getName());
        orders.setStatus_order_id(1);
        orders.setWarehouse_at(loggedUser.getWarehouse_id());
        ordersRepository.save(orders);

        ObjectMapper objectMapper = new ObjectMapper();
        List<OrderDetailInputModel> detailList = null;
        try {
            detailList = objectMapper.readValue(
                    orderinput.getDetailJSON(),
                    new TypeReference<List<OrderDetailInputModel>>() {
                    });
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        insertDetail(orderId, Objects.requireNonNull(detailList));
        getAllOrder();
    }

    public void insertDetail(String orderId, List<OrderDetailInputModel> detailList) {
        List<OrderDetail> arrayOrderDetail = new ArrayList<OrderDetail>();
        for (OrderDetailInputModel orderDetailInput : detailList) {
            OrderDetail orderdetail = new OrderDetail();
            orderdetail.setOrder_id(orderId);
            orderdetail.setProduct_id(orderDetailInput.getProductID());
            orderdetail.setOrigin_shelf_id(orderDetailInput.getProductOrigin());
            orderdetail.setDest_shelf_id(orderDetailInput.getProductDest());
            orderdetail.setQuantity(orderDetailInput.getProductQty());
            arrayOrderDetail.add(orderdetail);
        }

        orderDetailsRepository.saveAll(arrayOrderDetail);
    }

    public boolean moveShelfDetailOrder(String id) {
        boolean isError = false;
        List<ShelfDetail> toShelfWarehouseList = new ArrayList<>();
        List<ShelfDetail> fromShelfWarehouseList = new ArrayList<>();

        List<OrderDetail> listOrderDetail = orderDetailsRepository.findAllByOrder(id);
        for (OrderDetail orderDetail : listOrderDetail) {
            Product product = orderDetail.getProductList();
            ProductCategory productCategory = product.getProductCategory();
            boolean isCanStale = productCategory.getIs_can_be_stale() == 1;
            boolean isFromWarehouse = orderDetail.getOrderList().getOrigin_type().equals("Gudang");
            boolean isToWarehouse = orderDetail.getOrderList().getDest_type().equals("Gudang");

            for (int i = 0; i < orderDetail.getQuantity(); i++) {
                List<ShelfDetail> shelfOriginDetails = shelfDetailRepository.findAllByShelf(orderDetail.getOrigin_shelf_id());
                List<ShelfDetail> shelfDestDetails = shelfDetailRepository.findAllByShelf(orderDetail.getDest_shelf_id());

                // move product to dest
                if(isToWarehouse) {
                    ShelfDetail shelfDest = FifoShelfDetail.getShelfDest(shelfDestDetails);
                    if(shelfDest != null) {
                        toShelfWarehouseList.add(shelfDest);
                        Date date = null;

                        if (isCanStale) {
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DATE, 30);
                            date = cal.getTime();
                        }

                        shelfDest.setProduct_id(orderDetail.getProduct_id());
                        shelfDest.setExpired_at(date);
                        shelfDetailRepository.save(shelfDest);
                    } else {
                        isError = true;
                        break;
                    }
                }

                // delete product from origin
                if(isFromWarehouse) {
                    ShelfDetail shelfOrigin = FifoShelfDetail.getShelfOrigin(shelfOriginDetails, isCanStale, orderDetail.getProduct_id());
                    if(shelfOrigin != null) {
                        fromShelfWarehouseList.add(shelfOrigin);
                        shelfOrigin.setExpired_at(null);
                        shelfOrigin.setProduct_id(null);
                        shelfDetailRepository.save(shelfOrigin);
                    } else {
                        isError = true;
                        break;
                    }
                }
            }

            if(isError) break;
        }

        if(isError) reverseOrder(toShelfWarehouseList, fromShelfWarehouseList);

        return isError;
    }

    private void reverseOrder(List<ShelfDetail> toWarehouseShelf, List<ShelfDetail> fromWarehouseShelf) {
        shelfDetailRepository.saveAll(toWarehouseShelf);
        shelfDetailRepository.saveAll(fromWarehouseShelf);
    }

    public List<OrderDetail> getOrderDetail(String id) {
        return orderDetailsRepository.findAllByOrder(id);
    }

    public Order getOrderById(String id) {
        Optional<Order> optional = ordersRepository.findById(id);
        Order order = null;
        if (optional.isPresent()) {
            order = optional.get();
        } else {
            throw new RuntimeException(" Order not found for id :: " + id);
        }
        return order;
    }

    private String generateId(String originId, String destId, String originType, String destType) {
        int lastCounter = getLastCounter(originType);
        String typeOrId = originType.equals("Gudang") ? "W" : "S";
        String typeDestId = destType.equals("Gudang") ? "W" : "T";

        String dateId = LocalDate.now().toString().replace("-", "");
        return "FO" + "-" + typeOrId + originId + "-" + typeDestId + destId + "-" + dateId + "-" + GeneratorId.generateMasterId(lastCounter);

    }

    private int getLastCounter(String originType) {
        List<Order> orderList = getAllOrder();

        if (orderList.size() > 0) {
            List<Order> orderFiltered = orderList
                    .stream()
                    .filter(x ->
                            x.getOrigin_type().equals(originType))
                    .collect(Collectors.toList());

            if (orderFiltered.size() > 0) {
                orderFiltered.sort(Comparator.comparing(Order::getId));

                String[] orderIdArr = orderFiltered.get(orderFiltered.size() - 1).getId().split("-");
                return Integer.parseInt(orderIdArr[orderIdArr.length - 1]);
            } else {
                return 0;
            }
        }

        return 0;
    }

    public void check(String id, String staffName) {
        Order orders = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + id));
        orders.setStatus_order_id(2);
        orders.setChecked_at(new Date());
        orders.setChecked_by(staffName);
        orders.setUpdated_at(new Date());
        orders.setUpdated_by(staffName);
        ordersRepository.save(orders);
    }

    public void approve(String id, String staffName) {
        Order orders = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + id));
        orders.setStatus_order_id(4);
        orders.setApproved_at(new Date());
        orders.setApproved_by(staffName);
        orders.setUpdated_at(new Date());
        orders.setUpdated_by(staffName);
        ordersRepository.save(orders);
    }

    public void reject(String id, String staffName) {
        Order orders = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + id));
        orders.setStatus_order_id(3);
        orders.setUpdated_at(new Date());
        orders.setUpdated_by(staffName);
        ordersRepository.save(orders);
    }

    @Transactional
    public void delete(Order order) {
        ordersRepository.delete(order);
    }

}


