document.getElementById('panal_position').onclick=function (event){
    target=event.target;
    if(target.classList==".position_all" || target.tagName=="H5"){
        mas_pos=document.querySelectorAll(".position_all");
        for(i=0;i<mas_pos.length;i++){
            mas_pos[i].style.backgroundColor="";
        }
        if(target.tagName=="H5"){
            target=target.closest(".position_all");
            target.style.backgroundColor="#ec5252";
            document.getElementById('id_service_employye').value=target.childNodes[1].textContent;
            document.getElementById('position_employee').value=target.childNodes[3].textContent
            console.log(document.getElementById('id_service_employye').value)
        }
        else {
            target.style.backgroundColor="#ec5252";
            document.getElementById('id_service_employye').value=target.childNodes[1].textContent;
            document.getElementById('position_employee').value=target.childNodes[3].textContent
            console.log(document.getElementById('id_service_employye').value)
        }
    }
}