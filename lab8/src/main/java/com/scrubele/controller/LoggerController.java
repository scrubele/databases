//package com.scrubele.controller;
//
//import com.scrubele.domain.Logger;
//import com.scrubele.exceptions.NoSuchLogException;
//import com.scrubele.DTO.LoggerDTO;
//import com.scrubele.service.LoggerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.Link;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
//
//@RestController
//public class LoggerController {
//    @Autowired
//    LoggerService loggerService;
//
//    @GetMapping(value = "/api/logger")
//    public ResponseEntity<List<LoggerDTO>> getAllLogger() {
//        List<Logger> loggerEntities = loggerService.getAllLogger();
//        Link link = linkTo(methodOn(LoggerController.class).getAllLogger()).withSelfRel();
//
//        List<LoggerDTO> loggersDTO = new ArrayList<>();
//        for (Logger entity : loggerEntities) {
//            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
//            LoggerDTO dto = new LoggerDTO(entity,selfLink);
//            loggersDTO.add(dto);
//        }
//
//        return new ResponseEntity<>(loggersDTO, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/api/logger/{log_id}")
//    public ResponseEntity<LoggerDTO> getLog(@PathVariable Long log_id) throws NoSuchLogException {
//        Logger logger =loggerService.getLog(log_id);
//        Link link = linkTo(methodOn(LoggerController.class).getLog(log_id)).withSelfRel();
//
//        LoggerDTO loggerDTO = new LoggerDTO(logger,link);
//
//        return new ResponseEntity<>(loggerDTO, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/api/logger/filter/surname/{surname}")
//    public ResponseEntity<List<LoggerDTO>> getLoggerFilterBySurname(@PathVariable String surname) {
//        List<Logger> loggerEntities = loggerService.getLoggerFilterBySurname(surname);
//        Link link = linkTo(methodOn(LoggerController.class).getAllLogger()).withSelfRel();
//
//        List<LoggerDTO> loggersDTO = new ArrayList<>();
//        for (Logger entity : loggerEntities) {
//            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
//            LoggerDTO dto = new LoggerDTO(entity,selfLink);
//            loggersDTO.add(dto);
//        }
//
//        return new ResponseEntity<>(loggersDTO, HttpStatus.OK);
//    }
//}
//
