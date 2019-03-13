var user;

$(document).ready(function () {
    $("#loginbtn").click(function () {
        tjekform();
    });

    $('#LoginForm input').on('keypress', function (e) {
        if (e.which == 13) {
            tjekform();
        }
    });

});

function tjekform() {
   // console.log( $('#username').empty());
    if ($('#password').val() == "" || $('#username').val() == "") {
        $('.msg').css('color', 'red');
        $('.msg').html('Du skal angive et brugernavn og et password.');
    } else {
        loginuser();
    }
}

function loginuser() {
    let json = toJSONString($('#LoginForm'));
    user = JSON.parse(json);
    console.log(user);
    $.ajax({
        url: 'api/login',
        type: 'POST',
        contentType: 'application/json',
        data: json,
        success: function (data, textStatus, jQxhr) {
            $('.msg').html(textStatus);
            $('.msg').css('color', 'green');
            console.log(data);
            if (textStatus == 'success') {
                goToAPP();
            }
        },
        error: function (jqXhr, textStatus, errorThrown) {
            $('.msg').html("studie nr eller kode forkert.");
            console.log(jqXhr.status);
            $('.msg').css('color', 'red');
        }
    });
}

function goToAPP() {
    $('#formContent').hide(200, function () {
        $(".wrapper").load("game.html").ajaxComplete(getGameScipts());
    });
}

function getGameScipts() {
    $.getScript("assets/game.js", function (data, textStatus, jqxhr) {
        console.log("spil-logik loaded. status: " + textStatus);
    });
}

function toJSONString(form) {
    var obj = {};
    var elements = form.find('input')
    for (var i = 0; i < elements.length; ++i) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if (name && element.name != 'btn') {
            obj[name] = value;
        }
    }
    return JSON.stringify(obj);
}

String.prototype.replaceAll = function (search, replacement) {
    var target = this;
    return target.split(search).join(replacement);
};
