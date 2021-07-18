package inventory.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import inventory.system.entity.*;
import inventory.system.repository.OrderDetailRepository;
import inventory.system.repository.OrderRepository;
import inventory.system.utils.GeneratorId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepository ordersRepository;

    @Autowired
    OrderDetailRepository orderdetailsRepository;


    public List<Order> getAllOrder() {
        List<Order> ordersList = (List<Order>) ordersRepository.findAll();
//        ordersList.sort(
//                Comparator
//                        .comparing(Order::getStatus)
//                        .thenComparing(Order::getName)
//        );

        return ordersList;
    }

    public List<Order> saveOrder(OrderInput orderinput) {
        Order orders = new Order();
        String orderid = generateId(orderinput.getOrigin_warehouse_id()
                , orderinput.getDest_warehouse_id()
                , orderinput.getOrigin_type());
        orders.setId(orderid);

        orders.setOrigin_id(orderinput.getOrigin_warehouse_id());
        orders.setOrigin_type(orderinput.getOrigin_type());

        orders.setDest_id(orderinput.getDest_warehouse_id());
        orders.setDest_type(orderinput.getDest_type());

        orders.setDate(new Date());
        orders.setDriver_id(orderinput.getDriver_id());

        orders.setCreated_at(new Date());
        orders.setCreated_by("Admin Transaksi");

        orders.setChecked_at(new Date());
        orders.setChecked_by("-");

        orders.setApproved_at(new Date());
        orders.setApproved_by("-");

        orders.setUpdated_at(new Date());
        orders.setUpdated_by("Admin Transaksi");

        orders.setStatus_order_id(1);

        ordersRepository.save(orders);


        ObjectMapper objectMapper = new ObjectMapper();

        List<OrderDetailInput> detailList = null;
        try {
            detailList = objectMapper.readValue(orderinput.getDetailJSON(), new TypeReference<List<OrderDetailInput>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        insertDetail(orderid, detailList);


        return getAllOrder();
    }

    public int insertDetail(String orderid, List<OrderDetailInput> detailList){

        //List<Student> participantJsonList = mapper.readValue(jsonString, new TypeReference<List<Student>>(){});

        List<OrderDetail> arrayOrderDetail = new ArrayList<OrderDetail>();

        for(int k = 0; k<detailList.size(); k++){
            OrderDetail orderdetail = new OrderDetail();
            orderdetail.setOrder_id(orderid);
            orderdetail.setProduct_id(detailList.get(k).getProductID());
            orderdetail.setOrigin_shelf_id(detailList.get(k).getProductOrigin());
            orderdetail.setDest_shelf_id(detailList.get(k).getProductDest());
            orderdetail.setQuantity(detailList.get(k).getProductQty());
            arrayOrderDetail.add(orderdetail);
        }

        orderdetailsRepository.saveAll(arrayOrderDetail);

        return 1;
    }

    public List<OrderDetail> getOrderDetail(String id) {
        List<OrderDetail> listDetail = orderdetailsRepository.findAllByOrder(id);
        return listDetail;
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

    private String generateId(String originId, String destId, String originType) {
        int lastCounter = getLastCounter(originType);

        String typeId = originType.equals("Gudang") ? "W" : "S";
        String dateId = LocalDate.now().toString().replace("-","");
        return "FO" + "-" + typeId + originId + "-" + destId + "-" + dateId + "-" + GeneratorId.generateMasterId(lastCounter);
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

    @Transactional
    public void delete(Order order) {
        //orderdetailsRepository.deleteDetail(order.getId());
        ordersRepository.delete(order);
    }



}


