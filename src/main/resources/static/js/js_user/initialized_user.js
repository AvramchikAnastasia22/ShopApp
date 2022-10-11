function initialized_block(){
    document.getElementById('open_list_service_user').style.color="#866c03";
    document.getElementById('nearest_day_user').style.color="#866c03";
    if(document.getElementById('trigger_user').textContent=="service"){
        open_service_user();
    }
    if(document.getElementById('trigger_user').textContent=="settins"){
        open_settins_user();
    }
    if(document.getElementById('trigger_user').textContent=="history"){
        open_history_user();
    }
}
function initialized_user(){
    initialized_block();
}
initialized_user();