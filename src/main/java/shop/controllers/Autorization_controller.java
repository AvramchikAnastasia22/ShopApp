package shop.controllers;

import shop.model.User;
import shop.repository.repository_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class Autorization_controller {

    private String trigger="";


    private void first_registration(){
        List<User>users=user_repository.findAll();
        if(users.size()==0){
            Date date=new Date();
            SimpleDateFormat simpl=new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String dat=simpl.format(date);
            User user=new User();
            user.setName("Анастасия");
            user.setSur_name("Панфилова");
            user.setPatronymic("Сергеевна");
            user.setName_photo_file("Women.png");
            user.setPhone("293708501");
            user.setType("Admin");
            user.setStatus("Активно");
            user.setLogin("123");
            user.setPassword("123");
            user.setData_registration(dat);
            user_repository.save(user);
        }
    }

    @Autowired
    repository_user user_repository;

    @GetMapping("/shop")
    private String shop(Model model){
        first_registration();
        String tr=trigger;
        model.addAttribute("trigger",tr);
        trigger="";
        return "Autorization";
    }

    @PostMapping("/registration_user")
    private String registration_user(@RequestParam String name,@RequestParam String sur_name,@RequestParam String patronymic,
                                     @RequestParam String phone,@RequestParam String login,@RequestParam String password
    ){
        Date date=new Date();
        SimpleDateFormat simpl=new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dat=simpl.format(date);
        User user=new User();
        user.setName(name);
        user.setSur_name(sur_name);
        user.setPatronymic(patronymic);
        user.setPhone(phone);
        user.setLogin(login);
        user.setPassword(password);
        user.setName_photo_file("no_name");
        user.setStatus("Активно");
        user.setType("User");
        user.setData_registration(dat);
        trigger="registration";
        user_repository.save(user);
        return "redirect:/shop";
    }

    @PostMapping("/autorization_user")
    private String autorization_user(@RequestParam String login, @RequestParam String password){
        List<User> list_employee=user_repository.findAll();
        Boolean check=false;
        for(int i=0;i<list_employee.size();i++){
            if(list_employee.get(i).getLogin().equals(login)&&list_employee.get(i).getPassword().equals(password)){
                check=true;
                if(list_employee.get(i).getStatus().equals("Заблокировано")){
                    trigger="ban";
                    return ("redirect:/shop");
                }
                else {
                    if(list_employee.get(i).getType().equals("Admin")){
                        return "redirect:/admin_room:"+list_employee.get(i).getId();
                    }
                    else {
                        return "redirect:/user_room:"+list_employee.get(i).getId();
                    }
                }
            }
        }
        if(check!=true){
            trigger="no found";
        }
        return ("redirect:/shop");
    }
}
