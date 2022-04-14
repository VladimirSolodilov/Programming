
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

function signout() {
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
function gotomain() {
    function signout() {
        $.ajax({
            url: '/gotomain',
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
}
function battlesearch(){

}

function checkSuccessSO(data){
    switch (data.key){
        case "200":{
            document.location.href = "/main_non-authorized";
            break;
        }
        default:{
            alert("Error" + data.key + " " + data.status);
        }
    }
}
