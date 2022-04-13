function signin() {
    let password = document.getElementById('password').value
    let email = document.getElementById('email').value

    console.log(JSON.stringify({
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    }))

    $.ajax({
        url: '/authenticate',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            "email": email,
            "password": password
        }),
        success: function (data) {
            checkSuccess(data);
        },
        error: function (data){
            alert(data.status);
        }
    })
}

function checkSuccess(data){
    switch (data.key){
        case "200":{
            document.location.href = "/profile";
            break;
        }
        default:{
            alert("Error" + data.key + " " + data.status);
        }
    }
}