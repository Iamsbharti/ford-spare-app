package com.lendingcart.timetable.service;

import com.lendingcart.timetable.model.*;
import com.lendingcart.timetable.repository.ClassModelRepository;
import com.lendingcart.timetable.repository.SubjectRepository;
import com.lendingcart.timetable.repository.TeacherRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class TimeTableService {
    @Value("${subjects}")
    private String subjects;

    @Autowired
    ClassModelRepository classModelRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public List<ClassModel> getAllClassNamesService(){
        log.info("Get All Class Names service");
        return classModelRepository.findAll();
    }

    public ClassModel getClassDetailByIdService(String classId){
        log.info("Get Class Details By Id::"+classId);
        Optional<ClassModel> classModelOptional = classModelRepository.findClassModelById(classId);
        return classModelOptional.orElse(null);
    }

    public String deleteClassDataByIdService(String classId){
        log.info("Delete Class Data"+classId);
        Optional<ClassModel> optionalClassModel = classModelRepository.findClassModelById(classId);
        if(!optionalClassModel.isPresent()){
            return "No Class Data Found";
        }else{
            classModelRepository.deleteClassModelById(classId);
            return "Class Number " + optionalClassModel.get().getClassNumber().toString()+" deleted";
        }
    }

    public ClassModel saveClassDataService(ClassModel classModel){
        log.info("Save Class Data");

        // get all saved subjects
        List<Subject> subjectList = subjectRepository.findAll();

        ClassModel newClassData = new ClassModel();
        newClassData.setClassNumber(classModel.getClassNumber());
        newClassData.setSubjectList(subjectList);

        log.info("New Class Data::"+newClassData);

        return classModelRepository.save(newClassData);
    }

    public Teacher saveTeacherDataService(TeacherDataRequest teacherDataRequest){
        log.info("Save Teacher Data service::");

        // get class info
        Optional<ClassModel> classModelOptional = classModelRepository.findClassModelById(teacherDataRequest.getClassId());
        if(!classModelOptional.isPresent()){
            return null;
        }else{
            // save subject if not present
            List<Subject> subjects = subjectRepository.findSubjectByName(teacherDataRequest.getSubjectName());
            log.info("matching subjects::"+subjects);
            Subject savedSubject = subjects.get(0);
            if(subjects.isEmpty()){
                // save new subject
                Subject newSubject = new Subject();
                newSubject.setSubjectName(teacherDataRequest.getSubjectName());
                savedSubject = subjectRepository.save(newSubject);
            }
            log.info("savedSubject::"+savedSubject);
            Teacher teacherData = new Teacher();
            teacherData.setTeacherName(teacherDataRequest.getTeacherName());
            teacherData.setSubject(savedSubject);
            teacherData.setClassId(classModelOptional.get().getClassId());

            // save
            return teacherRepository.save(teacherData);
        }
    }


    public Teacher getTeacherDataByIdService(String teacherId){
        log.info("Get Teacher Data By Id service::"+teacherId);
        Optional<Teacher> teacherOptional = teacherRepository.findTeacherById(teacherId);
        return teacherOptional.orElse(null);
    }

    public List<Subject> saveSubjects(){
        log.info("Save Subjects::"+subjects);
        subjectRepository.deleteAll();
        String subjectArray [] =subjects.split(",");
        for(String sub : subjectArray){
            Subject subject = new Subject();
            subject.setSubjectName(sub);
            subjectRepository.save(subject);
        }

        return subjectRepository.findAll();
    }
    public List<Teacher> getAllTeacherDataService(){
        log.info("Get All teacher Data Service::");
        return teacherRepository.findAll();
    }
    public GeneratedTimeTable generatedTimeTableService(){
        log.info("Get Generated Time Table service::");
        return new GeneratedTimeTable();
    }

}
