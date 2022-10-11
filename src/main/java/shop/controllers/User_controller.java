package shop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import shop.model.*;
import shop.repository.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class User_controller {

    private String trigger="";
    private int pers_id;
    private List<History> sort_History(List<History>list){
        for (int i=list.size()-1;i>=0;i--){
            if(list.get(i).getId_user()!=pers_id){
                list.remove(i);
            }
        }
        return list;
    }
    private List<Records> sort_record(List<Records>list){
        for (int i=list.size()-1;i>=0;i--){
            if(list.get(i).getId_user()!=pers_id){
                list.remove(i);
            }
            else{
                if(list.get(i).getStatus().equals("Завершено")){
                    list.remove(i);
                }
            }
        }
        return list;
    }
    private void close_record() throws ParseException {
        List<Records>list=record_repository.findAll();
        for (int i=0;i<list.size();i++){
            Date date1=new Date();
            SimpleDateFormat smpl= new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date date=smpl.parse(list.get(i).getDate());
            if(date.before(date1)&&list.get(i).getStatus().equals("Ожидается")){
                History history=new History();
                history.setId_user(list.get(i).getId_user());
                history.setDate(list.get(i).getDate());
                history.setType(list.get(i).getType());
                history.setFIO_employee(list.get(i).getFIO_employee());
                history.setPrice(list.get(i).getPrice());
                list.get(i).setStatus("Завершено");
                history_repository.save(history);
            }
        }
        record_repository.saveAll(list);
    }

    @Value("${upload.path_user}")
    private String load_user;
    @Autowired
    repository_employee employee_repository;
    @Autowired
    repository_service service_repository;
    @Autowired
    repository_user user_repository;
    @Autowired
    repository_history history_repository;
    @Autowired
    repository_record record_repository;

    @PostMapping("/update_settins_user")
    private String update_settins(@RequestParam String name_set, @RequestParam String sur_set, @RequestParam String patr_set,
                                  @RequestParam String phone_set, @RequestParam String login_set, @RequestParam String password_set,
                                  @RequestParam(required = false) MultipartFile pers_photo
    ) throws IOException {
        User employee=user_repository.findById(pers_id).get();
        employee.setName(name_set);
        employee.setSur_name(sur_set);
        employee.setPatronymic(patr_set);
        employee.setPhone(phone_set);
        employee.setLogin(login_set);
        employee.setPassword(password_set);
        if(!pers_photo.getOriginalFilename().isEmpty()&&!pers_photo.getOriginalFilename().equals(employee.getName_photo_file())){
            File file=new File(load_user);
            if(!file.exists()) {
                file.mkdir();
            }
            if(!pers_photo.isEmpty()){
                if(!employee.getName_photo_file().equals("Meni.png")&&!employee.getName_photo_file().equals("Women.png")){
                    File file1=new File(file.getAbsolutePath()+"/"+employee.getName_photo_file());
                    file1.delete();
                }
                String uuid_file= UUID.randomUUID().toString();
                String Or_file_name=pers_photo.getOriginalFilename().substring(pers_photo.getOriginalFilename().indexOf("."),pers_photo.getOriginalFilename().length());
                String file_name=uuid_file+"_"+employee.getId()+Or_file_name;
                pers_photo.transferTo(new File(file.getAbsolutePath()+"/"+file_name));
                employee.setName_photo_file(file_name);
            }
        }
        user_repository.save(employee);
        trigger="settins";
        return "redirect:/user_room:"+pers_id;
    }

    @GetMapping("/user_room:{id}")
    private String user_menu(Model model, @PathVariable(value = "id")Integer id_user) throws ParseException {
        close_record();
        pers_id=id_user;
        User user=user_repository.findById(id_user).get();
        List<Employee> employeeList=employee_repository.findAll();
        List<History> list_history=history_repository.findAll();
        List<Records>list_records=record_repository.findAll();
        List<Service>list_service=service_repository.findAll();
        String tr=trigger;
        model.addAttribute("trigger",tr);
        model.addAttribute("pers_info",user);
        model.addAttribute("all_employee",employeeList);
        model.addAttribute("list_history",sort_History(list_history));
        model.addAttribute("list_record",sort_record(list_records));
        model.addAttribute("list_service",list_service);
        trigger="";
        return "User_menu";
    }

    @PostMapping("/add_record")
    private String add_record(@RequestParam int id_service,@RequestParam int id_employee,@RequestParam String fio_employee,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                      LocalDateTime date
    )
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dat = date.format(formatter);
        User user=user_repository.findById(pers_id).get();
        Records records=new Records();
        records.setStatus("Ожидается");
        records.setId_user(pers_id);
        records.setId_employee(id_employee);
        records.setPrice(service_repository.findById(id_service).get().getPrice());
        records.setId_service(id_service);
        records.setFIO_employee(fio_employee);
        records.setFIO_user(user.getName()+" "+user.getSur_name()+" "+user.getPatronymic());
        records.setDate(dat);
        records.setType(service_repository.findById(id_service).get().getType());
        record_repository.save(records);
        trigger="service";
        return "redirect:/user_room:"+pers_id;
    }
    @GetMapping("/cancel_record:{id}")
    private String cancel_record(@PathVariable(value = "id")Integer id_service){
        record_repository.deleteById(id_service);
        trigger="history";
        return "redirect:/user_room:"+pers_id;
    }

}
