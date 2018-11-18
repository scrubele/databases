//package com.scrubele.controller;
//
//import com.scrubele.exceptions.*;
//import scrubele.DTO.PatientDTO;
//import scrubele.domain.Patient;
//import scrubele.exceptions.*;
//import com.scrubele.service.PatientService;
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
//public class PatientController {
//    @Autowired
//    PatientService patientService;
//
//    @GetMapping(value = "/api/patient/diagnosis/{diagnosis_id}")
//    public ResponseEntity<List<PatientDTO>> getPatientsByDiagnosisID(@PathVariable Long diagnosis_id) throws NoSuchDiagnosisException, NoSuchPatientException, NoSuchMedicineException {
//        List<Patient> patientList = patientService.getPatientByDiagnosisId(diagnosis_id);
//
//        Link link = linkTo(methodOn(PatientController.class).getAllPatients()).withSelfRel();
//
//        List<PatientDTO> patientsDTO = new ArrayList<>();
//        for (Patient entity : patientList) {
//            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
//            PatientDTO dto = new PatientDTO(entity, selfLink);
//            patientsDTO.add(dto);
//        }
//
//        return new ResponseEntity<>(patientsDTO, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/api/patient/{patient_id}")
//    public ResponseEntity<PatientDTO> getPatient(@PathVariable Long patient_id) throws NoSuchPatientException, NoSuchMedicineException {
//        Patient patient = patientService.getPatient(patient_id);
//        Link link = linkTo(methodOn(PatientController.class).getPatient(patient_id)).withSelfRel();
//
//        PatientDTO patientDTO = new PatientDTO(patient, link);
//
//        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/api/patient")
//    public ResponseEntity<List<PatientDTO>> getAllPatients() throws NoSuchPatientException, NoSuchMedicineException {
//        List<Patient> patientList = patientService.getAllPatients();
//        Link link = linkTo(methodOn(PatientController.class).getAllPatients()).withSelfRel();
//
//        List<PatientDTO> patientsDTO = new ArrayList<>();
//        for (Patient entity : patientList) {
//            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
//            PatientDTO dto = new PatientDTO(entity, selfLink);
//            patientsDTO.add(dto);
//        }
//
//        return new ResponseEntity<>(patientsDTO, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/api/patient/medicine/{medicine_id}")
//    public ResponseEntity<List<PatientDTO>> getPatientsByMedicineID(@PathVariable Long medicine_id) throws NoSuchMedicineException, NoSuchPatientException {
//        Set<Patient> patientList = patientService.getPatientsByMedicineId(medicine_id);
//        Link link = linkTo(methodOn(PatientController.class).getAllPatients()).withSelfRel();
//
//        List<PatientDTO> patientsDTO = new ArrayList<>();
//        for (Patient entity : patientList) {
//            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
//            PatientDTO dto = new PatientDTO(entity, selfLink);
//            patientsDTO.add(dto);
//        }
//
//        return new ResponseEntity<>(patientsDTO, HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/api/patient/diagnosis/{diagnosis_id}")
//    public  ResponseEntity<PatientDTO> addPatient(@RequestBody Patient newPatient, @PathVariable Long diagnosis_id)
//            throws NoSuchDiagnosisException, NoSuchPatientException, NoSuchMedicineException {
//        patientService.createPatient(newPatient, diagnosis_id);
//        Link link = linkTo(methodOn(PatientController.class).getPatient(newPatient.getId())).withSelfRel();
//
//        PatientDTO patientDTO = new PatientDTO(newPatient, link);
//
//        return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
//    }
//
//    @PutMapping(value = "/api/patient/{patient_id}/diagnosis/{diagnosis_id}")
//    public  ResponseEntity<PatientDTO> updatePatient(@RequestBody Patient uPatient,
//                                                   @PathVariable Long patient_id, @PathVariable Long diagnosis_id)
//            throws NoSuchDiagnosisException, NoSuchPatientException, NoSuchMedicineException {
//        patientService.updatePatient(uPatient, patient_id, diagnosis_id);
//        Patient patient =patientService.getPatient(patient_id);
//        Link link = linkTo(methodOn(PatientController.class).getPatient(patient_id)).withSelfRel();
//
//        PatientDTO patientDTO = new PatientDTO(patient, link);
//
//        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/api/patient/{patient_id}")
//    public  ResponseEntity deletePatient(@PathVariable Long patient_id) throws NoSuchPatientException, ExistsMedicinesForPatientException {
//        patientService.deletePatient(patient_id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/api/patient/{patient_id}/medicine/{medicine_id}")
//    public  ResponseEntity<PatientDTO> addMedicineForPatient(@PathVariable Long patient_id, @PathVariable Long medicine_id)
//            throws NoSuchPatientException, NoSuchMedicineException, AlreadyExistsMedicineInPatientException, MedicineAbsentException {
//        patientService.addMedicineForPatient(patient_id,medicine_id);
//        Patient patient = patientService.getPatient(patient_id);
//        Link link = linkTo(methodOn(PatientController.class).getPatient(patient_id)).withSelfRel();
//
//        PatientDTO patientDTO = new PatientDTO(patient, link);
//
//        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/api/patient/{patient_id}/medicine/{medicine_id}")
//    public  ResponseEntity<PatientDTO> removeMedicineForPatient(@PathVariable Long patient_id, @PathVariable Long medicine_id)
//            throws NoSuchPatientException, NoSuchMedicineException, PatientHasNotMedicineException {
//        patientService.removeMedicineForPatient(patient_id,medicine_id);
//        Patient patient = patientService.getPatient(patient_id);
//        Link link = linkTo(methodOn(PatientController.class).getPatient(patient_id)).withSelfRel();
//
//        PatientDTO patientDTO = new PatientDTO(patient, link);
//
//        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
//    }
//
//}
//
