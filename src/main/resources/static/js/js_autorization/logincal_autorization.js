function initialized_autorization(){
    document.getElementById('tool_aut').style.backgroundColor="#7a6910";
    if(document.getElementById('trigger').textContent=="registration"){
        document.getElementById('tool_reg').style.backgroundColor="#7a6910";
        document.getElementById('registration_block').hidden=false;
        document.getElementById('autorization_block').hidden=true;
        document.getElementById('secsessful_registration').hidden=false;
        setTimeout(hidden_object,5000,document.getElementById('secsessful_registration'));
    }
    if(document.getElementById('trigger').textContent=="ban"){
        document.getElementById('erorr_enter').textContent="Ваш аккаунт заблокирован";
        document.getElementById('erorr_enter').hidden=false;
        setTimeout(hidden_object,5000,document.getElementById('erorr_enter'));
    }
    if(document.getElementById('trigger').textContent=="no found"){
        document.getElementById('erorr_enter').textContent="Не удалось войти,повторите попытку";
        document.getElementById('erorr_enter').hidden=false;
        setTimeout(hidden_object,5000,document.getElementById('erorr_enter'));
    }
}
function open_autorization(data){
    document.getElementById('autorization_block').hidden=false;
    document.getElementById('registration_block').hidden=true;
    data.style.backgroundColor="#7a6910";
    document.getElementById("tool_reg").style.backgroundColor='';
}
function open_registration(data){
    document.getElementById('autorization_block').hidden=true;
    document.getElementById('registration_block').hidden=false;
    data.style.backgroundColor="#7a6910";
    document.getElementById("tool_aut").style.backgroundColor='';
}
initialized_autorization();