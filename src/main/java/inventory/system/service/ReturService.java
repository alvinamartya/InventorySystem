package inventory.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import inventory.system.entity.Retur;
import inventory.system.entity.ReturDetail;
import inventory.system.entity.ReturDetailInput;
import inventory.system.entity.ReturInput;
import inventory.system.repository.ReturDetailRepository;
import inventory.system.repository.ReturRepository;
import inventory.system.utils.GeneratorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReturService {

    @Autowired
    ReturRepository returRepository;

    @Autowired
    ReturDetailRepository returDetailRepository;

    public List<Retur> getAllRetur() {
        return (List<Retur>) returRepository.findAll();
    }

    public List<Retur> saveRetur(ReturInput returInput) {
        Retur retur = new Retur();
        String returId = generateId(returInput.getOrigin_warehouse_id(),
                returInput.getDest_warehouse_id(),
                returInput.getDest_type(),
                returInput.getOrigin_type());
        retur.setId(returId);

        retur.setOrigin_id(returInput.getOrigin_warehouse_id());
        retur.setOrder_type(returInput.getOrigin_type());

        retur.setDest_id(returInput.getDest_warehouse_id());
        retur.setDest_type(returInput.getDest_type());

        retur.setDate(new Date());
        retur.setDriver_id(returInput.getDriver_id());

        retur.setChecked_at(new Date());
        retur.setChecked_by("-");

        retur.setApproved_at(new Date());
        retur.setApproved_by("-");

        retur.setCreated_at(new Date());
        retur.setCreated_by("Admin Transaksi");

        retur.setUpdated_at(new Date());
        retur.setUpdated_by("Admin Transaksi");

        returRepository.save(retur);

        ObjectMapper objectMapper = new ObjectMapper();

        List<ReturDetailInput> detailList = null;
        try {
            detailList = objectMapper.readValue(returInput.getDetailJSON(), new TypeReference<List<ReturDetailInput>>() {
            });
            insertDetail(returId, detailList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return getAllRetur();
    }

    public void insertDetail(String returId, List<ReturDetailInput> detailList) {

        List<ReturDetail> arrayReturDetail = new ArrayList<ReturDetail>();

        for (ReturDetailInput returDetailInput : detailList) {
            ReturDetail returDetail = new ReturDetail();
            returDetail.setRetur_id(returId);
            returDetail.setProduct_id(returDetailInput.getProductID());
            returDetail.setOrigin_shelf_id(returDetailInput.getProductOrigin());
            returDetail.setDest_shelf_id(returDetailInput.getProductDest());
            returDetail.setQty(returDetailInput.getProductQty());
            arrayReturDetail.add(returDetail);
        }

        returDetailRepository.saveAll(arrayReturDetail);

    }

    public List<ReturDetail> getReturDetail(String id) {
        return returDetailRepository.findReturDetailByReturId(id);
    }

    public Retur getReturById(String id) {
        Optional<Retur> optional = returRepository.findById(id);
        Retur retur = null;
        if (optional.isPresent()) {
            retur = optional.get();
        } else {
            throw new RuntimeException(" Retur not found for id :: " + id);
        }

        return retur;
    }

    //ID Retur Origin Otomatis
    private String generateId(String originId, String destId, String destType, String originType) {
        int lastCounter = getLastCounter(originType);

        String typeId = originType.equals("W") ? "W" : "T";
        String typeDest = destType.equals("W") ? "W" : "S";
        String dateId = LocalDate.now().toString().replace("-", "");
        return "FR" + "-" + typeId + originId + "-" + typeDest + destId + "-" + dateId + "-" + GeneratorId.generateMasterId(lastCounter);
    }

    private int getLastCounter(String originType) {
        List<Retur> returList = getAllRetur();

        if (returList.size() > 0) {
            List<Retur> returFiltered = returList
                    .stream()
                    .filter(x ->
                            x.getOrder_type().equals(originType))
                    .collect(Collectors.toList());

            if (returFiltered.size() > 0) {
                returFiltered.sort(Comparator.comparing(Retur::getId));

                String[] returIdArr = returFiltered.get(returFiltered.size() - 1).getId().split("-");
                return Integer.parseInt(returIdArr[returIdArr.length - 1]);
            } else {
                return 0;
            }
        }

        return 0;
    }

    //checked
    public void check(String id, String staffName) {
        Retur retur = returRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        retur.setChecked_at(new Date());
        retur.setChecked_by(staffName);
        retur.setUpdated_at(new Date());
        retur.setUpdated_by(staffName);
        returRepository.save(retur);
    }

    //approved
    public void approve(String id, String staffName) {
        Retur retur = returRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        retur.setApproved_at(new Date());
        retur.setApproved_by(staffName);
        retur.setUpdated_at(new Date());
        retur.setUpdated_by(staffName);
        returRepository.save(retur);
    }

    @Transactional
    public void delete(Retur retur) {
        returRepository.delete(retur);
    }
}
