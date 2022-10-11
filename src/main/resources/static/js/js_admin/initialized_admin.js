function initialized_user(){
    mas_user=document.querySelectorAll(".users");
    for(i=0;i<mas_user.length;i++){
        console.log(mas_user[i].childNodes[7].childNodes[1].value);
        if(mas_user[i].childNodes[7].childNodes[3].value=="Admin"){
            mas_user[i].childNodes[9].childNodes[5].hidden=true;
            if(mas_user[i].childNodes[7].childNodes[1].value=="Активно"){
            }
            else {
                mas_user[i].childNodes[7].childNodes[1].style.backgroundColor="#da4c4c";
                mas_user[i].childNodes[9].childNodes[1].hidden=true;
            }
        }
        else {
            if(mas_user[i].childNodes[7].childNodes[1].value=="Активно"){
            }
            else {
                mas_user[i].childNodes[7].childNodes[1].style.backgroundColor="#da4c4c";
                mas_user[i].childNodes[9].childNodes[1].hidden=true;
            }
        }
    }
}
function initialized_dispay(){
    if(document.getElementById('trigger').textContent=="users"){
        open_user();
    }
    if(document.getElementById('trigger').textContent=="service"){
        open_service()
    }
    if(document.getElementById('trigger').textContent=="employee"){
        open_employee()
    }
    if(document.getElementById('trigger').textContent=="settins"){
        open_settins();
        document.getElementById('but_update_settins').style.backgroundColor="#00FF00FF";
        setTimeout(background_default,5000, document.getElementById('but_update_settins'));
    }
    if(document.getElementById('trigger').textContent=="ban_list"){
        open_ban_list();
    }
}
function initialized(){
    initialized_dispay();
    initialized_user();
    document.getElementById('list_sev').style.color="#866c03";
    document.getElementById('lis_employee').style.color="#866c03";
}
initialized();