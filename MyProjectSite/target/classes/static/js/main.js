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

function checkSuccessSO(data){
    switch (data.key){
        case "200":{
            document.location.href = "/main_authorized";
            break;
        }
        default:{
            alert("Error" + data.key + " " + data.status);
        }
    }
}