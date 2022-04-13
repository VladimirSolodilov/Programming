
let inWork = false;
setInterval(function fillProfileInfo(){
    if (inWork === true)
        return;
    inWork = true;
    $.ajax({
        url: '/profile/my',
        method: 'GET',
        contentType: "application/json",
        success: function (data) {
            console.log(data.toString());
            switch (data.key){
                case "200":{
                    document.getElementById("firstname").innerHTML  = "Имя: " + data.first_name;
                    document.getElementById("secondname").innerHTML = "Фамилия: " + data.second_name;
                    document.getElementById("patronymic").innerHTML = "Отчество: " + data.patronymic;
                    document.getElementById("nickname").innerHTML   = "Псевдоним: " + data.nickname;
                    document.getElementById("birthdate").innerHTML  = "Дата рождения: " + data.birthdate;
                    document.getElementById("grade").innerHTML      = "Ранг: " + data.grade;
                    $('#physImp').val(data.physical_value);
                    $('#mentImp').val(data.mental_value);
                    inWork = false;
                    break;
                }
                default:{
                    signout();
                    inWork = false;
                }
            }
        },
        error: function (){
            signout();
            inWork = false;
        }
    });
}, 1000 * 5);

function signout(){
    $.ajax({
        url: '/signouts',
        method: 'GET',
        contentType: "application/json",
        success: function (data) {
            checkSuccessSO(data);
        },
        error: function (data){
            alert("Error" + data.key + " " + data.status);
        }
    })
}
function gotomain(){

}
function battlesearch(){

}

function checkSuccessSO(data){
    switch (data.key){
        case "200":{
            document.location.href = "/signin";
            break;
        }
        default:{
            alert("Error" + data.key + " " + data.status);
        }
    }
}

function setPhysImp(){
    let physImp = document.getElementById("physImp").value

    if (physImp > 0 && physImp < 21){
        $.ajax({
            url: '/impactSet/set/physical',
            method: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                "newValue": physImp
            })
        })
    }
}

function setMentImp(){
    let mentImp = document.getElementById("mentImp").value

    if (physImp > 0 && physImp < 21){
        $.ajax({
            url: '/impactSet/set/mental',
            method: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                "newValue": mentImp
            })
        })
    }
}