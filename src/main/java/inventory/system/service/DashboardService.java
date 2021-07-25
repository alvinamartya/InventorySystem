package inventory.system.service;

import inventory.system.entity.*;
import inventory.system.model.DashboardCardAdminMasterModel;
import inventory.system.model.DashboardCardOrderModel;
import inventory.system.model.DashboardCardOrderWarehouseModel;
import inventory.system.model.DashboardCardSuperAdminModel;
import inventory.system.repository.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Resource
    private DriverRepository driverRepository;

    @Resource
    private ProductCategoryRepository productCategoryRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private ShelfRepository shelfRepository;

    @Resource
    private StaffsRepository staffsRepository;

    @Resource
    private StoresRepository storesRepository;

    @Resource
    private SupplierRepository supplierRepository;

    @Resource
    private WarehousesRepository warehousesRepository;

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private ReturRepository returRepository;

    public DashboardCardOrderModel getDashboardCardAdminTransaction(int userId) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        DashboardCardOrderModel dashboardCardTransactionModel = new DashboardCardOrderModel();

        Optional<Staffs> staffs = null;
        Optional<Warehouses> warehouses = null;

        if (userId > 0) {
            staffs = staffsRepository.findById(userId);
            if (staffs.isPresent()) {
                warehouses = warehousesRepository.findById(staffs.get().getWarehouse_id());
            }
        }

        String warehouseId = warehouses.get().getId();
        List<Order> orderCheckingList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getStatus_order_id() == 1 && x.getDest_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Retur> returs = ((List<Retur>) returRepository.findAll())
                .stream()
                .filter(x -> x.getDest_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Retur::getDate))
                .collect(Collectors.toList());
        List<Order> orderApprovedList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getStatus_order_id() == 4 && x.getDest_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Order> orderRejectedList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getStatus_order_id() == 3 && x.getDest_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Order> orderInList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getDest_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Order> orderOutList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getOrigin_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());

        // set total
        System.out.println(orderCheckingList.size());
        dashboardCardTransactionModel.setTotalCheckingList(orderCheckingList.size());
        dashboardCardTransactionModel.setTotalRetur(returs.size());
        dashboardCardTransactionModel.setTotalApproved(orderApprovedList.size());
        dashboardCardTransactionModel.setTotalRejected(orderRejectedList.size());
        dashboardCardTransactionModel.setTotalIn(orderInList.size());
        dashboardCardTransactionModel.setTotalOut(orderOutList.size());

        // set last update
        if (orderCheckingList.size() > 0) {
            Order lastOrderChecking = orderCheckingList.get(orderCheckingList.size() - 1);
            dashboardCardTransactionModel.setLastUpdateChecking(formatter.format(lastOrderChecking.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateChecking(formatter.format(new Date()));
        }

        if (returs.size() > 0) {
            Retur lastRetur = returs.get(returs.size() - 1);
            dashboardCardTransactionModel.setLastUpdateRetur(formatter.format(lastRetur.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateRetur(formatter.format(new Date()));
        }

        if (orderApprovedList.size() > 0) {
            Order lastOrderApproved = orderApprovedList.get(orderApprovedList.size() - 1);
            dashboardCardTransactionModel.setLastUpdateApproved(formatter.format(lastOrderApproved.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateApproved(formatter.format(new Date()));
        }

        if (orderRejectedList.size() > 0) {
            Order lastOrderRejected = orderRejectedList.get(orderRejectedList.size() - 1);
            dashboardCardTransactionModel.setLastUpdateRejected(formatter.format(lastOrderRejected.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateRejected(formatter.format(new Date()));
        }

        if (orderInList.size() > 0) {
            Order lastOrder = orderInList.get(orderInList.size() - 1);
            dashboardCardTransactionModel.setLastUpdateIn(formatter.format(lastOrder.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateIn(formatter.format(new Date()));
        }

        if (orderOutList.size() > 0) {
            Order lastOrder = orderOutList.get(orderOutList.size() - 1);
            dashboardCardTransactionModel.setLastUpdateOut(formatter.format(lastOrder.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateOut(formatter.format(new Date()));
        }

        return dashboardCardTransactionModel;
    }
    public DashboardCardOrderWarehouseModel getDashboardCardAdminWarehouse(int userId) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        DashboardCardOrderWarehouseModel dashboardCardTransactionModel = new DashboardCardOrderWarehouseModel();

        Optional<Staffs> staffs = null;
        Optional<Warehouses> warehouses = null;

        if (userId > 0) {
            staffs = staffsRepository.findById(userId);
            if (staffs.isPresent()) {
                warehouses = warehousesRepository.findById(staffs.get().getWarehouse_id());
            }
        }

        String warehouseId = warehouses.get().getId();
        List<Order> orderWaiting = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getStatus_order_id() == 2 && x.getDest_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Retur> returs = ((List<Retur>) returRepository.findAll())
                .stream()
                .filter(x -> x.getDest_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Retur::getDate))
                .collect(Collectors.toList());
        List<Order> orderApprovedList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getStatus_order_id() == 4 && x.getDest_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Order> orderRejectedList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getStatus_order_id() == 3 && x.getDest_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Order> orderInList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getDest_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Order> orderOutList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getOrigin_id().equals(warehouseId) && x.getDest_type().equals("Gudang"))
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());

        // set total
        dashboardCardTransactionModel.setTotalWaitingList(orderWaiting.size());
        dashboardCardTransactionModel.setTotalRetur(returs.size());
        dashboardCardTransactionModel.setTotalApproved(orderApprovedList.size());
        dashboardCardTransactionModel.setTotalRejected(orderRejectedList.size());
        dashboardCardTransactionModel.setTotalIn(orderInList.size());
        dashboardCardTransactionModel.setTotalOut(orderOutList.size());

        // set last update
        if (orderWaiting.size() > 0) {
            Order lastOrderWaiting = orderWaiting.get(orderWaiting.size() - 1);
            dashboardCardTransactionModel.setLastUpdateWaiting(formatter.format(lastOrderWaiting.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateWaiting(formatter.format(new Date()));
        }

        if (returs.size() > 0) {
            Retur lastRetur = returs.get(returs.size() - 1);
            dashboardCardTransactionModel.setLastUpdateRetur(formatter.format(lastRetur.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateRetur(formatter.format(new Date()));
        }

        if (orderApprovedList.size() > 0) {
            Order lastOrderApproved = orderApprovedList.get(orderApprovedList.size() - 1);
            dashboardCardTransactionModel.setLastUpdateApproved(formatter.format(lastOrderApproved.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateApproved(formatter.format(new Date()));
        }

        if (orderRejectedList.size() > 0) {
            Order lastOrderRejected = orderRejectedList.get(orderRejectedList.size() - 1);
            dashboardCardTransactionModel.setLastUpdateRejected(formatter.format(lastOrderRejected.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateRejected(formatter.format(new Date()));
        }

        if (orderInList.size() > 0) {
            Order lastOrder = orderInList.get(orderInList.size() - 1);
            dashboardCardTransactionModel.setLastUpdateIn(formatter.format(lastOrder.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateIn(formatter.format(new Date()));
        }

        if (orderOutList.size() > 0) {
            Order lastOrder = orderOutList.get(orderOutList.size() - 1);
            dashboardCardTransactionModel.setLastUpdateOut(formatter.format(lastOrder.getDate()));
        } else {
            dashboardCardTransactionModel.setLastUpdateOut(formatter.format(new Date()));
        }

        return dashboardCardTransactionModel;
    }
    public DashboardCardSuperAdminModel getDashboardCardSuperAdmin() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        DashboardCardSuperAdminModel dashboardCardSuperAdminModel = new DashboardCardSuperAdminModel();

        List<Order> orderChecking = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getStatus_order_id() == 1)
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Order> orderWaiting = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getStatus_order_id() == 2)
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Order> orderApprovedList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getStatus_order_id() == 4)
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Order> orderRejectedList = ((List<Order>) orderRepository.findAll())
                .stream()
                .filter(x -> x.getStatus_order_id() == 3)
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Order> orders = ((List<Order>) orderRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
        List<Retur> returs = ((List<Retur>) returRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Retur::getDate))
                .collect(Collectors.toList());

        // set total
        dashboardCardSuperAdminModel.setTotalChecking(orderChecking.size());
        dashboardCardSuperAdminModel.setTotalWaiting(orderWaiting.size());
        dashboardCardSuperAdminModel.setTotalApproved(orderApprovedList.size());
        dashboardCardSuperAdminModel.setTotalRejected(orderRejectedList.size());
        dashboardCardSuperAdminModel.setTotalOrder(orders.size());
        dashboardCardSuperAdminModel.setTotalRetur(returs.size());

        // set last update
        if (orderChecking.size() > 0) {
            Order lastOrderChecking = orderChecking.get(orderChecking.size() - 1);
            dashboardCardSuperAdminModel.setLastUpdateChecking(formatter.format(lastOrderChecking.getDate()));
        } else {
            dashboardCardSuperAdminModel.setLastUpdateChecking(formatter.format(new Date()));
        }

        if (orderWaiting.size() > 0) {
            Order lastOrderWaiting = orderWaiting.get(orderChecking.size() - 1);
            dashboardCardSuperAdminModel.setLasUpdateWaiting(formatter.format(lastOrderWaiting.getDate()));
        } else {
            dashboardCardSuperAdminModel.setLasUpdateWaiting(formatter.format(new Date()));
        }

        if (orderApprovedList.size() > 0) {
            Order lastOrderApproved = orderApprovedList.get(orderApprovedList.size() - 1);
            dashboardCardSuperAdminModel.setLastUpdateApproved(formatter.format(lastOrderApproved.getDate()));
        } else {
            dashboardCardSuperAdminModel.setLastUpdateApproved(formatter.format(new Date()));
        }

        if (orderRejectedList.size() > 0) {
            Order lastOrderRejected = orderRejectedList.get(orderRejectedList.size() - 1);
            dashboardCardSuperAdminModel.setLastUpdateRejected(formatter.format(lastOrderRejected.getDate()));
        } else {
            dashboardCardSuperAdminModel.setLastUpdateRejected(formatter.format(new Date()));
        }

        if (orders.size() > 0) {
            Order lastOrder = orders.get(orders.size() - 1);
            dashboardCardSuperAdminModel.setLastUpdateOrder(formatter.format(lastOrder.getDate()));
        } else {
            dashboardCardSuperAdminModel.setLastUpdateOrder(formatter.format(new Date()));
        }

        if (returs.size() > 0) {
            Retur lastRetur = returs.get(returs.size() - 1);
            dashboardCardSuperAdminModel.setLastUpdateRetur(formatter.format(lastRetur.getDate()));
        } else {
            dashboardCardSuperAdminModel.setLastUpdateRetur(formatter.format(new Date()));
        }

        return dashboardCardSuperAdminModel;
    }
    public DashboardCardAdminMasterModel getDashboardCardAdminMaster() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        DashboardCardAdminMasterModel dashboardCardAdminMasterModel = new DashboardCardAdminMasterModel();

        List<ProductCategory> productCategoryList = ((List<ProductCategory>) productCategoryRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(ProductCategory::getCreated_at))
                .collect(Collectors.toList());
        List<Product> productList = ((List<Product>) productRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Product::getCreated_at))
                .collect(Collectors.toList());
        List<Shelf> shelfList = ((List<Shelf>) shelfRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Shelf::getCreated_at))
                .collect(Collectors.toList());
        List<Stores> stores = (storesRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Stores::getCreated_at))
                .collect(Collectors.toList());
        List<Supplier> suppliers = ((List<Supplier>) supplierRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Supplier::getCreated_at))
                .collect(Collectors.toList());
        List<Warehouses> warehouses = ((List<Warehouses>) warehousesRepository.findAll())
                .stream()
                .sorted(Comparator.comparing(Warehouses::getCreated_at))
                .collect(Collectors.toList());

        // set total
        dashboardCardAdminMasterModel.setTotalProductCategory(productCategoryList.size());
        dashboardCardAdminMasterModel.setTotalProduct(productList.size());
        dashboardCardAdminMasterModel.setTotalShelf(shelfList.size());
        dashboardCardAdminMasterModel.setTotalStore(stores.size());
        dashboardCardAdminMasterModel.setTotalSupplier(suppliers.size());
        dashboardCardAdminMasterModel.setTotalWarehouse(warehouses.size());

        // set last update
        if (productCategoryList.size() > 0) {
            ProductCategory lastProductCategory = productCategoryList.get(productCategoryList.size() - 1);
            dashboardCardAdminMasterModel.setLastUpdateProductCategory(formatter.format(lastProductCategory.getCreated_at()));
        } else {
            dashboardCardAdminMasterModel.setLastUpdateProductCategory(formatter.format(new Date()));
        }

        if (productList.size() > 0) {
            Product lastProduct = productList.get(productList.size() - 1);
            dashboardCardAdminMasterModel.setLastUpdateProduct(formatter.format(lastProduct.getCreated_at()));
        } else {
            dashboardCardAdminMasterModel.setLastUpdateProduct(formatter.format(new Date()));
        }

        if (shelfList.size() > 0) {
            Shelf lastShelf = shelfList.get(shelfList.size() - 1);
            dashboardCardAdminMasterModel.setLastUpdateShelf(formatter.format(lastShelf.getCreated_at()));
        } else {
            dashboardCardAdminMasterModel.setLastUpdateShelf(formatter.format(new Date()));
        }

        if (stores.size() > 0) {
            Stores lastStore = stores.get(stores.size() - 1);
            dashboardCardAdminMasterModel.setLastUpdateStore(formatter.format(lastStore.getCreated_at()));
        } else {
            dashboardCardAdminMasterModel.setLastUpdateStore(formatter.format(new Date()));
        }

        if (suppliers.size() > 0) {
            Supplier lastSupplier = suppliers.get(suppliers.size() - 1);
            dashboardCardAdminMasterModel.setLastUpdateSupplier(formatter.format(lastSupplier.getCreated_at()));
        } else {
            dashboardCardAdminMasterModel.setLastUpdateSupplier(formatter.format(new Date()));
        }

        if (warehouses.size() > 0) {
            Warehouses lastWarehouse = warehouses.get(warehouses.size() - 1);
            dashboardCardAdminMasterModel.setLastUpdateWarehouse(formatter.format(lastWarehouse.getCreated_at()));
        } else {
            dashboardCardAdminMasterModel.setLastUpdateWarehouse(formatter.format(new Date()));
        }

        return dashboardCardAdminMasterModel;
    }
}
