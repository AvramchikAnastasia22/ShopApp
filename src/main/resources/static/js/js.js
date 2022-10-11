function hidden_object(data){
    data.hidden=true;
}
function background_default(data){
    data.style.backgroundColor="";
}
function color_default(date){
    date.style.color="";
}
function color_border_default(date){
    data.style.border="";
}
function check_photo_employee(){
    document.getElementById('label_employe').style  .color="red";
    setTimeout(color_default,5000,document.getElementById('label_employe'));
}
function check_employee_position(){
    document.getElementById('position_employee').style.border="2px solid red";
    setTimeout(color_border_default,5000, document.getElementById('position_employee'));
}