package shop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class Admin_controller {

    private String trigger="";
    private int personal_id;
    private void close_record() throws ParseException {
            List<Records>list=record_repository.findAll();
            for (int i=0;i<list.size();i++){
                Date date1=new Date();
                SimpleDateFormat smpl= new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date date=smpl.parse(list.get(i).getDate());
                if(date.before(date1)&&list.get(i).getStatus().equals("Ожидается")){
                    History history=new History();
                    history.setId_user(list.get(i).getId_user());
                    history.setType(list.get(i).getType());
                    history.setDate(list.get(i).getDate());
                    history.setFIO_employee(list.get(i).getFIO_employee());
                    history.setPrice(list.get(i).getPrice());
                    list.get(i).setStatus("Завершено");
                    history_repository.save(history);
                }
            }
            record_repository.saveAll(list);
    }

    @Autowired
    repository_history history_repository;
    @Autowired
    repository_user user_repository;
    @Autowired
    repository_service service_repository;
    @Autowired
    repository_Ban_list ban_list_repository;
    @Autowired
    repository_employee employee_repository;
    @Autowired
    repository_record record_repository;

    @Value("${upload.path_user}")
    private String load_user;
    @Value("${upload.path_service}")
    private String load_service;
    @Value("${upload.path_employee}")
    private  String load_employee;

    @GetMapping("/admin_room:{id}")
    private String admin_menu(Model model, @PathVariable(value = "id")Integer id_admin) throws ParseException {
        close_record();
        User pers_info=user_repository.findById(id_admin).get();
        List<User> list_user=user_repository.findAll();
        List<Service>list_service=service_repository.findAll();
        List<Ban_list>list_ban=ban_list_repository.findAll();
        List<Employee>list_employee=employee_repository.findAll();
        personal_id=id_admin;
        String tr=trigger;
        model.addAttribute("trigger",tr);
        model.addAttribute("pers_info",pers_info);
        model.addAttribute("all_user",list_user);
        model.addAttribute("service",list_service);
        model.addAttribute("ban",list_ban);
        model.addAttribute("employee",list_employee);
        trigger="";
        return "Admin_menu";
    }

    @PostMapping("/update_settins")
    private String update_settins(@RequestParam String name_set, @RequestParam String sur_set, @RequestParam String patr_set,
                                  @RequestParam String phone_set, @RequestParam String login_set, @RequestParam String password_set,
                                  @RequestParam(required = false) MultipartFile pers_photo
    ) throws IOException {
        User employee=user_repository.findById(personal_id).get();
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
        return "redirect:/admin_room:"+personal_id;
    }

    @PostMapping("/add_service")
    private String add_service(@RequestParam String type,@RequestParam String name_service,@RequestParam double price,@RequestParam MultipartFile photo_service) throws IOException {
        Service service =new Service();
        service.setName_service(name_service);
        service.setType(type);
        service.setPrice(price);
        File file=new File(load_service);
        if(!photo_service.isEmpty()){
            String uuid_file= UUID.randomUUID().toString();
            String Or_file_name=photo_service.getOriginalFilename().substring(photo_service.getOriginalFilename().indexOf("."),photo_service.getOriginalFilename().length());
            String file_name=uuid_file+"_"+Or_file_name;
            photo_service.transferTo(new File(file.getAbsolutePath()+"/"+file_name));
            service.setName_photo_file(file_name);
        }
        trigger="service";
        service_repository.save(service);
        return "redirect:/admin_room:"+personal_id;
    }

    @GetMapping("/deleted_user:{id}")
    private String deleted_user(@PathVariable(value = "id")Integer user_id){
        if(!user_repository.findById(user_id).get().getName_photo_file().equals("no_name.png")){
            File file=new File(load_user);
            File file1=new File(file.getAbsolutePath()+"/"+user_repository.findById(user_id).get().getName_photo_file());
            file1.delete();
        }
        user_repository.deleteById(user_id);
        trigger="users";
        return "redirect:/admin_room:"+personal_id;
    }
    @GetMapping("/do_administration:{id}")
    private String do_administration(@PathVariable(value = "id")Integer user_id){
        User user=user_repository.findById(user_id).get();
        user.setType("Admin");
        user_repository.save(user);
        trigger="users";
        return "redirect:/admin_room:"+personal_id;
    }
    @GetMapping("/unblock_user:{id}")
    private String block_or_unblock_user(@PathVariable(value = "id")Integer user_id){
        User user=user_repository.findById(user_id).get();
        user.setStatus("Активно");
        ban_list_repository.deleteById(user_id);
        trigger="ban_list";
        user_repository.save(user);
        return "redirect:/admin_room:"+personal_id;
    }

    @PostMapping("/block_user")
    private String unblock_user(@RequestParam String comment,@RequestParam int id_user){
        User user=user_repository.findById(id_user).get();
            user.setStatus("Заблокировано");
            Ban_list ban_list=new Ban_list();
            ban_list.setFIO(user.getName()+" "+user.getSur_name()+" "+user.getPatronymic());
            ban_list.setReason(comment);
            ban_list.setName_photo(user.getName_photo_file());
            ban_list.setId_employee(id_user);
            ban_list_repository.save(ban_list);
        trigger="users";
        user_repository.save(user);
        ban_list_repository.save(ban_list);
        return "redirect:/admin_room:"+personal_id;
    }

    @GetMapping("/deleted_employee:{id}")
    private String deleted_employee(@PathVariable(value = "id")Integer employee_id){
            File file=new File(load_employee);
            File file1=new File(file.getAbsolutePath()+"/"+employee_repository.findById(employee_id).get().getName_photo_file());
            file1.delete();
        employee_repository.deleteById(employee_id);
        trigger="employee";
        return "redirect:/admin_room:"+personal_id;
    }


    @PostMapping("/add_employee")
    private String add_employee(@RequestParam String name,@RequestParam String sur_name,@RequestParam String patronymic
                                ,@RequestParam String phone,@RequestParam String position,@RequestParam int id_service,
                                @RequestParam MultipartFile photo_employee
    ) throws IOException {
        Date date=new Date();
        SimpleDateFormat simpl=new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dat=simpl.format(date);
        Service service=service_repository.findById(id_service).get();
        Employee employee=new Employee();
        employee.setName(name);
        employee.setSecond_name(sur_name);
        employee.setPatronymic(patronymic);
        employee.setPhone(phone);
        employee.setPosition(service.getType());
        employee.setDate_settled(dat);
        employee.setId_service(id_service);
        File file=new File(load_employee);
        if(!photo_employee.isEmpty()){
            String uuid_file= UUID.randomUUID().toString();
            String Or_file_name=photo_employee.getOriginalFilename().substring(photo_employee.getOriginalFilename().indexOf("."),photo_employee.getOriginalFilename().length());
            String file_name=uuid_file+"_"+Or_file_name;
            photo_employee.transferTo(new File(file.getAbsolutePath()+"/"+file_name));
            employee.setName_photo_file(file_name);
        }
        employee_repository.save(employee);
        trigger="employee";
        return "redirect:/admin_room:"+personal_id;
    }
    @GetMapping("/deleted_service:{id}")
    private String deleted_service(@PathVariable(value = "id")Integer id_service){
        File file=new File(load_service);
        File file1=new File(file.getAbsolutePath()+"/"+service_repository.findById(id_service).get().getName_photo_file());
        file1.delete();
        List<Employee>list=employee_repository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId_service()==id_service){
                list.get(i).setId_service(-1);
            }
        }
        employee_repository.saveAll(list);
        service_repository.deleteById(id_service);
        trigger="service";
        return "redirect:/admin_room:"+personal_id;
    }
}
