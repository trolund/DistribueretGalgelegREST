$(document).ready(function () {
    setupGameUI();
    console.log("Game ready!");
});

function setupGameUI() {
    keyboard();
    updateWord();
    let btn = $("#gessbtn");
    btn.click(function () {
        console.log('gess: ' + $('#geas').val());
        $.ajax({
            url: 'api/game/geatbogstav?letter=' + $('#geas').val(),
            type: 'POST',
            contentType: 'plain/text',
            success: function (data, textStatus, jQxhr) {
                let life = getlife();
                console.log('data:' + data);
                $('.LifeCount').html('Lifes left: ' + life);
                if (data) {
                    $(".gamelabel").css("color", "green");
                    $(".gamelabel").html("Correct!");
                } else {
                    $(".gamelabel").css("color", "red");
                    $(".gamelabel").html("Nope!" + $('#geas').val() + " is not in the word.");
                }
                $('#geas').val('');
                updateWord();
                updateImg(life);
            },
            error: function (jqXhr, textStatus, errorThrown) {
                let life = getlife();
                console.log('failed' + data);
                $('.LifeCount').html('Lifes left:' + life);
                updateImg(life);
            }
        });
    });
}

function updateWord() {
    $.ajax({
        url: 'api/game/synligtord',
        type: 'GET',
        contentType: 'plain/text',
        async: false,
        success: function (data, textStatus, jQxhr) {
            $('.visWord').html(data.replaceAll('*', '_ '));
        },
        error: function (jqXhr, textStatus, errorThrown) {
            $('.visWord').text("fail...");
        }
    });
}

function getlife() {
    // api/game/getAntalForkerteBogstaver
    $.ajax({
        url: 'api/game/getAntalForkerteBogstaver',
        type: 'GET',
        contentType: 'plain/text',
        async: false,
        success: function (data, textStatus, jQxhr) {
            console.log('data:' + data);
            return 7 - data;
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log('failed' + data);
            return 7 - data;
        }
    });
}

function updateImg(life) {
    switch (life) {
        case 0:
            $(".img").attr("src", "assets/img/00.png");
            break;
        case 1:
            $(".img").attr("src", "assets/img/01.png");
            break;
        case 2:
            $(".img").attr("src", "assets/img/02.png");
            break;
        case 3:
            $(".img").attr("src", "assets/img/03.png");
            break;
        case 4:
            $(".img").attr("src", "assets/img/04.png");
            break;
        case 5:
            $(".img").attr("src", "assets/img/05.png");
            break;
        case 6:
            $(".img").attr("src", "assets/img/06.png");
            break;
    }
}


function keyboard() {
    let abc = [
        'a',
        'b',
        'c',
        'd',
        'e',
        'f',
        'g',
        'h',
        'i',
        'j',
        'k',
        'l',
        'm',
        'n',
        'o',
        'p',
        'q',
        'r',
        's',
        't',
        'u',
        'v',
        'w',
        'x',
        'y',
        'z'
    ];

    // lav alle knapperne
    $.each(abc, function (index, value) {
        $('.keyboard').append("<span><button class='keyboardbtn' data=" + value + ">" + value + "</button></span>");
    });

    // set én clicklisner på dem alle.

    $('.keyboardbtn').click(function () {
        console.log('gess: ' + $(this).attr('data'));

        $.ajax({
            url: 'api/game/geatbogstav?letter=' + $(this).attr('data'),
            type: 'POST',
            contentType: 'plain/text',
            success: function (data, textStatus, jQxhr) {
                let life = getlife();
                console.log('data:' + data);
                $('.LifeCount').html('Lifes left: ' + life);
                if (data) {
                    $(".gamelabel").css("color", "green");
                    $(".gamelabel").html("Correct!");
                } else {
                    $(".gamelabel").css("color", "red");
                    $(".gamelabel").html("Nope!" + $(this).attr('data') + " is not in the word.");
                }
                $('#geas').val('');
                $(this).addClass('keyboardbtnDisable');
                updateWord();
                updateImg(life);
            },
            error: function (jqXhr, textStatus, errorThrown) {
                let life = getlife();
                console.log('failed' + data);
                $('.LifeCount').html('Lifes left:' + life);
                updateImg(life);
            }
        });
    });
}
