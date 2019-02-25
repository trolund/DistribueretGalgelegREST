var user;

$(document).ready(function () {
    console.log("ready!");
    $("#loginbtn").click(function () {
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
                if (textStatus == 'success') {
                    goToAPP();
                }
            },
            error: function (jqXhr, textStatus, errorThrown) {
                $('.msg').html(textStatus);
                $('.msg').css('color', 'red');
            }
        });
    });
});

function goToAPP() {
    $('#formContent').hide(200, function () {
        $(".wrapper").load("game.html").ajaxComplete(getGameScipts());
    });
}

function getGameScipts(){
    $.getScript("assets/game.js", function (data, textStatus, jqxhr) {
            console.log("Load was performed.");
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
