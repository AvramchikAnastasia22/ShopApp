document.getElementById('la').onclick=function (event){
    target=event.target;
    console.log(target.classList);
    if(target.classList=="service_user_a" || target.tagName=="IMG" || target.tagName=="LABEL"){
            if(target.classList=="service_user_a"){
                mas_emp=document.querySelectorAll(".emp_order");
                for (i=0;i<mas_emp.length;i++) {
                    console.log(mas_emp[i].childNodes[3])
                    console.log(target.childNodes[1].textContent)
                    if(mas_emp[i].childNodes[3].textContent!=target.childNodes[1].textContent){
                        mas_emp[i].hidden=true;
                    }
                }
            document.getElementById('take_order').hidden=false;
            document.getElementById('id_ser').value=target.childNodes[1].textContent;
        }
        else{
            target=target.closest(".service_user_a");
                mas_emp=document.querySelectorAll(".emp_order");
                for (i=0;i<mas_emp.length;i++) {
                    console.log(mas_emp[i].childNodes[3])
                    console.log(target.childNodes[1].textContent)
                    if(mas_emp[i].childNodes[3].textContent!=target.childNodes[1].textContent){
                        mas_emp[i].hidden=true;
                    }
                }
            document.getElementById('take_order').hidden=false;
            document.getElementById('id_ser').value=target.childNodes[1].textContent;
        }
    }
}
document.getElementById('emp_order_list').onclick=function (event){
    target=event.target;
    mas_emp=document.querySelectorAll(".emp_order");
    for (i=0;i<mas_emp.length;i++){
        mas_emp[i].style.backgroundColor="";
    }
    if(target.classList=="emp_order"||target.tagName=="IMG"||target.tagName=="LABEL"){
        if(target.classList=="emp_order"){
            document.getElementById('id_emp').value=target.childNodes[1].textContent;
            document.getElementById('FIO_employee').value=target.childNodes[7].textContent;
            target.style.backgroundColor="#B94D4DCC";
        }
        else {
            target=target.closest(".emp_order");
            document.getElementById('id_emp').value=target.childNodes[1].textContent;
            document.getElementById('FIO_employee').value=target.childNodes[7].textContent;
            target.style.backgroundColor="#B94D4DCC";
        }
    }
}