//package com.scrubele.controller;
//
//import scrubele.DTO.MedicineDTO;
//import scrubele.domain.Medicine;
//import com.scrubele.exceptions.ExistsPatientForMedicineException;
//import com.scrubele.exceptions.NoSuchMedicineException;
//import com.scrubele.exceptions.NoSuchPatientException;
//import com.scrubele.service.MedicineService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.Link;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
//
//@RestController
//public class MedicineController {
//    @Autowired
//    MedicineService medicineService;
//
//    @GetMapping(value = "/api/medicine/patient/{patient_id}")
//    public ResponseEntity<List<MedicineDTO>> getMedicinesByPatientID(@PathVariable Long patient_id) throws NoSuchPatientException, NoSuchMedicineException {
//        Set<Medicine> medicineList = medicineService.getMedicinesByPatientId(patient_id);
//        Link link = linkTo(methodOn(MedicineController.class).getAllMedicines()).withSelfRel();
//
//        List<MedicineDTO> medicinesDTO = new ArrayList<>();
//        for (Medicine entity : medicineList) {
//            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
//            MedicineDTO dto = new MedicineDTO(entity, selfLink);
//            medicinesDTO.add(dto);
//        }
//
//        return new ResponseEntity<>(medicinesDTO, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/api/medicine/{medicine_id}")
//    public ResponseEntity<MedicineDTO> getMedicine(@PathVariable Long medicine_id) throws NoSuchMedicineException, NoSuchPatientException {
//        Medicine medicine = medicineService.getMedicine(medicine_id);
//        Link link = linkTo(methodOn(MedicineController.class).getMedicine(medicine_id)).withSelfRel();
//
//        MedicineDTO medicineDTO = new MedicineDTO(medicine, link);
//
//        return new ResponseEntity<>(medicineDTO, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/api/medicine")
//    public ResponseEntity<List<MedicineDTO>> getAllMedicines() throws NoSuchMedicineException, NoSuchPatientException {
//        List<Medicine> medicineList = medicineService.getAllMedicines();
//        Link link = linkTo(methodOn(MedicineController.class).getAllMedicines()).withSelfRel();
//
//        List<MedicineDTO> medicinesDTO = new ArrayList<>();
//        for (Medicine entity : medicineList) {
//            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
//            MedicineDTO dto = new MedicineDTO(entity, selfLink);
//            medicinesDTO.add(dto);
//        }
//
//        return new ResponseEntity<>(medicinesDTO, HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/api/medicine")
//    public ResponseEntity<MedicineDTO> addMedicine(@RequestBody Medicine newMedicine) throws NoSuchMedicineException, NoSuchPatientException {
//        medicineService.createMedicine(newMedicine);
//        Link link = linkTo(methodOn(MedicineController.class).getMedicine(newMedicine.getId())).withSelfRel();
//
//        MedicineDTO medicineDTO = new MedicineDTO(newMedicine, link);
//
//        return new ResponseEntity<>(medicineDTO, HttpStatus.CREATED);
//    }
//
//    @PutMapping(value = "/api/medicine/{medicine_id}")
//    public ResponseEntity<MedicineDTO> updateMedicine(@RequestBody Medicine uMedicine, @PathVariable Long medicine_id) throws NoSuchMedicineException, NoSuchPatientException {
//        medicineService.updateMedicine(uMedicine, medicine_id);
//        Medicine medicine = medicineService.getMedicine(medicine_id);
//        Link link = linkTo(methodOn(MedicineController.class).getMedicine(medicine_id)).withSelfRel();
//
//        MedicineDTO medicineDTO = new MedicineDTO(medicine, link);
//
//        return new ResponseEntity<>(medicineDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/api/medicine/{medicine_id}")
//    public  ResponseEntity deleteMedicine(@PathVariable Long medicine_id) throws ExistsPatientForMedicineException, NoSuchMedicineException {
//        medicineService.deleteMedicine(medicine_id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//}
