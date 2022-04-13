function signup() {
    let password = document.getElementById('password').value
    let password_repeat = document.getElementById('password-repeat').value
    let email = document.getElementById('email').value
    let first_name = document.getElementById('first_name').value
    let second_name = document.getElementById('second_name').value
    let patronymic = document.getElementById('patronymic').value
    let nickname = document.getElementById('nickname').value
    let birthdate = new Date(document.getElementById('birthdate').value)
    let strBD = String(birthdate.getFullYear()+"-"+birthdate.getMonth()+"-"+birthdate.getDate())

    if( password === password_repeat) {
        $.ajax({
            url: '/register',
            method: 'POST',
            contentType: "application/json",
            data: JSON.stringify({
                "email": email,
                "password": password,
                "first_name": first_name,
                "second_name": second_name,
                "patronymic": patronymic,
                "nickname": nickname,
                "birthdate": strBD
            }),
            success: function (data) {
                checkSuccess(data);
            },
            error: function (data){
                alert(data.status);
            }
        })
    }
}

function checkSuccess(data){
    switch (data.key){
        case "200":{
            document.location.href = "/signin";
            break;
        }
        default:{
            alert("Error " + data.key + " " + data.status);
        }
    }
}