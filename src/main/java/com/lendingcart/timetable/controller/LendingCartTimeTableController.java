package com.lendingcart.timetable.controller;


import com.lendingcart.timetable.model.ClassModel;
import com.lendingcart.timetable.model.Subject;
import com.lendingcart.timetable.model.Teacher;
import com.lendingcart.timetable.model.TeacherDataRequest;
import com.lendingcart.timetable.service.TimeTableService;
import com.lendingcart.timetable.common.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
public class LendingCartTimeTableController {

    @Autowired
    TimeTableService timeTableService;

    @GetMapping("/health/check")
    private String healthCheck(){
        return "Time Table API Health Ok";
    }

    @GetMapping("/class")
    private ResponseEntity getAllClassData(){
        List<ClassModel> classData = timeTableService.getAllClassNamesService();
        ApiResponse response;
        if(classData.isEmpty()){
            response = new ApiResponse(HttpStatus.NO_CONTENT.toString(), "No Data Found", null);
            return new ResponseEntity(response,HttpStatus.NO_CONTENT);
        }else{
            response = new ApiResponse(HttpStatus.OK.toString(),  "All Class Data Fetched", classData);
            return new ResponseEntity(response,HttpStatus.OK);
        }
    }

    @GetMapping("/class/{classId}")
    private ResponseEntity getClassById(@PathVariable String classId){
        log.info("Get Class Data By ID Controller::"+classId);
        ClassModel classData = timeTableService.getClassDetailByIdService(classId);
        ApiResponse response;
        if(classData == null){
            response = new ApiResponse(HttpStatus.NO_CONTENT.toString(), "No Data Found", null);
            return new ResponseEntity(response,HttpStatus.NO_CONTENT);
        }else{
            response = new ApiResponse(HttpStatus.OK.toString(),  "Class Data Fetched", classData);
            return new ResponseEntity(response,HttpStatus.OK);
        }
    }

    @DeleteMapping("/class/{classId}")
    private ResponseEntity deleteClassDataById(@PathVariable String classId){
        log.info("Delete class Data Controller::"+classId);
        String deleteStatus = timeTableService.deleteClassDataByIdService(classId);
        ApiResponse response = new ApiResponse(HttpStatus.OK.toString(), "Delete Status",deleteStatus);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping("/class")
    private ResponseEntity saveClassData(@RequestBody ClassModel classModelRequest){
        log.info("Save Class Data Controller");
        ClassModel savedResult = timeTableService.saveClassDataService(classModelRequest);
        ApiResponse response;
        if(savedResult == null){
            response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error Saving Data", null);
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            response = new ApiResponse(HttpStatus.OK.toString(),  "Class Data Saved", savedResult);
            return new ResponseEntity(response,HttpStatus.OK);
        }
    }

    @PostMapping("/teacher")
    private ResponseEntity saveTeacherData(@RequestBody TeacherDataRequest teacherDataRequest){
        log.info("Save Teacher Data Controller");
        Teacher savedResult = timeTableService.saveTeacherDataService(teacherDataRequest);
        ApiResponse response;
        if(savedResult == null){
            response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error Saving Data", null);
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            response = new ApiResponse(HttpStatus.OK.toString(),  "Teacher Data Saved", savedResult);
            return new ResponseEntity(response,HttpStatus.OK);
        }
    }

    @GetMapping("/teacher/{teacherId}")
    private ResponseEntity getTeacherDataById(@PathVariable String teacherId){
        log.info("Get Teacher Data by id controller");
        Teacher teacherData = timeTableService.getTeacherDataByIdService(teacherId);
        ApiResponse response;
        if(teacherData == null){
            response = new ApiResponse(HttpStatus.NO_CONTENT.toString(), "No Data Found", null);
            return new ResponseEntity(response,HttpStatus.NO_CONTENT);
        }else{
            response = new ApiResponse(HttpStatus.OK.toString(),  "Teacher Data Fetched", teacherData);
            return new ResponseEntity(response,HttpStatus.OK);
        }
    }

    @GetMapping("/teacher")
    private ResponseEntity getAllTeachersData(){
        List<Teacher> teacherData = timeTableService.getAllTeacherDataService();
        ApiResponse response;
        if(teacherData.isEmpty()){
            response = new ApiResponse(HttpStatus.NO_CONTENT.toString(), "No Data Found", null);
            return new ResponseEntity(response,HttpStatus.NO_CONTENT);
        }else{
            response = new ApiResponse(HttpStatus.OK.toString(),  "All Teacher Data Fetched", teacherData);
            return new ResponseEntity(response,HttpStatus.OK);
        }
    }
    @PostMapping("/subject")
    private ResponseEntity saveSubjects(){
        log.info("Save Subjects controller");
        List<Subject> subjectList = timeTableService.saveSubjects();
        ApiResponse response;
        if(subjectList.isEmpty()){
            response = new ApiResponse(HttpStatus.NO_CONTENT.toString(), "No Data Found", null);
            return new ResponseEntity(response,HttpStatus.NO_CONTENT);
        }else{
            response = new ApiResponse(HttpStatus.OK.toString(),  "Subjects Saved", subjectList);
            return new ResponseEntity(response,HttpStatus.OK);
        }
    }



}
