package inventory.system.service;

import inventory.system.entity.*;
import inventory.system.model.MonitoringOrderModel;
import inventory.system.model.MonitoringReturModel;
import inventory.system.repository.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonitoringService {
    @Resource
    private StaffsRepository staffsRepository;

    @Resource
    private OrderDetailRepository orderDetailRepository;

    @Resource
    private ReturDetailRepository returDetailRepository;

    @Resource
    private WarehousesRepository warehousesRepository;

    @Resource
    private SupplierRepository supplierRepository;

    @Resource
    private StoresRepository storesRepository;

    public List<MonitoringOrderModel> getMonitoringModel(Integer staffId) {
        List<MonitoringOrderModel> monitoringModels = new ArrayList<>();
        Optional<Staffs> staffs = staffsRepository.findById(staffId);
        if (staffs.isPresent()) {
            List<MonitoringOrderModel> monitoringInList = getInMonitoringModel(orderDetailRepository.findAllByWarehouseDest(staffs.get().getWarehouse_id()));
            List<MonitoringOrderModel> monitoringOutList = getOutMonitoringModel(orderDetailRepository.findAllByWarehouseOrigin(staffs.get().getWarehouse_id()));

            monitoringModels.addAll(monitoringInList);
            monitoringModels.addAll(monitoringOutList);
        }

        return monitoringModels
                .stream()
                .sorted(
                        Comparator
                                .comparingLong(x -> x.getDate().getTime())
                )
                .collect(Collectors.toList());
    }
    private List<MonitoringOrderModel> getOutMonitoringModel(List<OrderDetail> monitoringOrderOutList) {
        return monitoringOrderOutList
                .stream()
                .map(x -> {
                    MonitoringOrderModel model = new MonitoringOrderModel();
                    model.setOrder_id(x.getOrder_id());
                    model.setProduct_name(x.getProductList().getName());

                    switch (x.getOrderList().getOrigin_type()) {
                        case "Gudang":
                            Optional<Warehouses> originWarehouse = warehousesRepository.findById(x.getOrderList().getOrigin_id());
                            originWarehouse.ifPresent(warehouses -> model.setOrigin_name(warehouses.getName()));
                            break;
                        case "Pemasok":
                            Optional<Supplier> originSupplier = supplierRepository.findById(x.getOrderList().getOrigin_id());
                            originSupplier.ifPresent(supplier -> model.setOrigin_name(supplier.getName()));
                            break;
                        default:
                            model.setOrigin_name("-");
                            break;
                    }

                    switch (x.getOrderList().getDest_type()) {
                        case "Toko":
                            Optional<Stores> destStore = storesRepository.findById(Integer.valueOf(x.getOrderList().getDest_id()));
                            destStore.ifPresent(store -> model.setDest_name(store.getName()));
                            break;
                        case "Gudang":
                            Optional<Warehouses> destWarehouse = warehousesRepository.findById(x.getOrderList().getDest_id());
                            destWarehouse.ifPresent(warehouses -> model.setDest_name(warehouses.getName()));
                            break;
                        default:
                            model.setOrigin_name("-");
                            break;
                    }

                    model.setOrigin_shelf(x.getOrigin_shelf_id() == null ? "-" : x.getOrigin_shelf_id());
                    model.setDest_shelf(x.getDest_shelf_id() == null ? "-" : x.getDest_shelf_id());
                    model.setDate(x.getOrderList().getDate());
                    model.setQuantity(x.getQuantity());
                    model.setType("out");
                    return model;
                })
                .collect(Collectors.toList());
    }
    private List<MonitoringOrderModel> getInMonitoringModel(List<OrderDetail> monitoringOrderInList) {
        return monitoringOrderInList
                .stream()
                .map(x -> {
                    MonitoringOrderModel model = new MonitoringOrderModel();
                    model.setOrder_id(x.getOrder_id());
                    model.setProduct_name(x.getProductList().getName());

                    switch (x.getOrderList().getOrigin_type()) {
                        case "Gudang":
                            Optional<Warehouses> originWarehouse = warehousesRepository.findById(x.getOrderList().getOrigin_id());
                            originWarehouse.ifPresent(warehouses -> model.setOrigin_name(warehouses.getName()));
                            break;
                        case "Pemasok":
                            Optional<Supplier> originSupplier = supplierRepository.findById(x.getOrderList().getOrigin_id());
                            originSupplier.ifPresent(supplier -> model.setOrigin_name(supplier.getName()));
                            break;
                        default:
                            model.setOrigin_name("-");
                            break;
                    }

                    switch (x.getOrderList().getDest_type()) {
                        case "Toko":
                            Optional<Stores> destStore = storesRepository.findById(Integer.valueOf(x.getOrderList().getDest_id()));
                            destStore.ifPresent(store -> model.setDest_name(store.getName()));
                            break;
                        case "Gudang":
                            Optional<Warehouses> destWarehouse = warehousesRepository.findById(x.getOrderList().getDest_id());
                            destWarehouse.ifPresent(warehouses -> model.setDest_name(warehouses.getName()));
                            break;
                        default:
                            model.setOrigin_name("-");
                            break;
                    }

                    model.setOrigin_shelf(x.getOrigin_shelf_id() == null ? "-" : x.getOrigin_shelf_id());
                    model.setDest_shelf(x.getDest_shelf_id() == null ? "-" : x.getDest_shelf_id());
                    model.setDate(x.getOrderList().getDate());
                    model.setQuantity(x.getQuantity());
                    model.setType("in");
                    return model;
                })
                .collect(Collectors.toList());
    }
    public List<MonitoringReturModel> getMonitoringReturModel(Integer staffId) {
        List<MonitoringReturModel> monitoringModels = new ArrayList<>();
        Optional<Staffs> staffs = staffsRepository.findById(staffId);
        if (staffs.isPresent()) {
            List<MonitoringReturModel> monitoringInList = getInMonitoringReturModel(returDetailRepository.findAllByWarehouseDest(staffs.get().getWarehouse_id()));
            List<MonitoringReturModel> monitoringOutList = getOutMonitoringReturModel(returDetailRepository.findAllByWarehouseOrigin(staffs.get().getWarehouse_id()));

            monitoringModels.addAll(monitoringInList);
            monitoringModels.addAll(monitoringOutList);
        }

        return monitoringModels
                .stream()
                .sorted(
                        Comparator
                                .comparingLong(x -> x.getDate().getTime())
                )
                .collect(Collectors.toList());
    }
    private List<MonitoringReturModel> getOutMonitoringReturModel(List<ReturDetail> monitoringReturOutList) {
        return monitoringReturOutList
                .stream()
                .map(x -> {
                    MonitoringReturModel model = new MonitoringReturModel();
                    model.setRetur_id(x.getRetur_id());
                    model.setProduct_name(x.getProductList().getName());

                    switch (x.getReturList().getOrder_type()) {
                        case "W":
                            Optional<Warehouses> originWarehouse = warehousesRepository.findById(x.getReturList().getOrigin_id());
                            originWarehouse.ifPresent(warehouses -> model.setOrigin_name(warehouses.getName()));
                            break;
                        case "T":
                            Optional<Stores> originStore = storesRepository.findById(Integer.parseInt(x.getReturList().getOrigin_id()));
                            originStore.ifPresent(store -> model.setOrigin_name(store.getName()));
                            break;
                        default:
                            model.setOrigin_name("-");
                            break;
                    }

                    switch (x.getReturList().getDest_type()) {
                        case "S":
                            Optional<Supplier> destSupplier = supplierRepository.findById(x.getReturList().getDest_id());
                            destSupplier.ifPresent(supplier -> model.setDest_name(supplier.getName()));
                            break;
                        case "W":
                            Optional<Warehouses> destWarehouse = warehousesRepository.findById(x.getReturList().getDest_id());
                            destWarehouse.ifPresent(warehouses -> model.setDest_name(warehouses.getName()));
                            break;
                        default:
                            model.setOrigin_name("-");
                            break;
                    }

                    model.setOrigin_shelf(x.getOrigin_shelf_id() == null ? "-" : x.getOrigin_shelf_id());
                    model.setDest_shelf(x.getDest_shelf_id() == null ? "-" : x.getDest_shelf_id());
                    model.setDate(x.getReturList().getDate());
                    model.setQuantity(x.getQty());
                    model.setType("out");
                    return model;
                })
                .collect(Collectors.toList());
    }
    private List<MonitoringReturModel> getInMonitoringReturModel(List<ReturDetail> monitoringReturInList) {
        return monitoringReturInList
                .stream()
                .map(x -> {
                    MonitoringReturModel model = new MonitoringReturModel();
                    model.setRetur_id(x.getRetur_id());
                    model.setProduct_name(x.getProductList().getName());

                    switch (x.getReturList().getOrder_type()) {
                        case "W":
                            Optional<Warehouses> originWarehouse = warehousesRepository.findById(x.getReturList().getOrigin_id());
                            originWarehouse.ifPresent(warehouses -> model.setOrigin_name(warehouses.getName()));
                            break;
                        case "T":
                            Optional<Stores> originStore = storesRepository.findById(Integer.parseInt(x.getReturList().getOrigin_id()));
                            originStore.ifPresent(store -> model.setOrigin_name(store.getName()));
                            break;
                        default:
                            model.setOrigin_name("-");
                            break;
                    }

                    switch (x.getReturList().getDest_type()) {
                        case "S":
                            Optional<Supplier> destSupplier = supplierRepository.findById(x.getReturList().getDest_id());
                            destSupplier.ifPresent(supplier -> model.setDest_name(supplier.getName()));
                            break;
                        case "W":
                            Optional<Warehouses> destWarehouse = warehousesRepository.findById(x.getReturList().getDest_id());
                            destWarehouse.ifPresent(warehouses -> model.setDest_name(warehouses.getName()));
                            break;
                        default:
                            model.setOrigin_name("-");
                            break;
                    }

                    model.setOrigin_shelf(x.getOrigin_shelf_id() == null ? "-" : x.getOrigin_shelf_id());
                    model.setDest_shelf(x.getDest_shelf_id() == null ? "-" : x.getDest_shelf_id());
                    model.setDate(x.getReturList().getDate());
                    model.setQuantity(x.getQty());
                    model.setType("in");
                    return model;
                })
                .collect(Collectors.toList());
    }
}
