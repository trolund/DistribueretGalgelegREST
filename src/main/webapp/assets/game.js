$(document).ready(function () {
    keyboard();
    $('.popup').hide();
    newGame();
    console.log("Game ready!");
    $('.playerinfo').html("<p>" + user.username + "</p>");
    GameOver(getlife());


    $("#newGamebtn").click(function (){
        newGame();
        resetKeyboard();
        $(".popup").hide(300);
    });
    $("#resumebtn").click(function (){
        $(".popup").hide(300);
    });

});

function setupGameUI() {
    updateWord();
    let btn = $("#gessbtn");
    btn.click(function () {
        console.log('gess: ' + $('#geas').val());
        $.ajax({
            url: 'api/game/geatbogstav?letter=' + $('#geas').val() + "&userid=" + user.username,
            type: 'POST',
            contentType: 'plain/text',
            success: function (data, textStatus, jQxhr) {
                if (data) {
                    $(".gamelabel").css("color", "green");
                    $(".gamelabel").html("Correct!"); 
                } else {
                    $(".gamelabel").css("color", "red");
                    $(".gamelabel").html("Nope!" + $('#geas').val() + " is not in the word.");
                }
                updateWord();
            },
            error: function (jqXhr, textStatus, errorThrown) {
                console.log('failed' + data);
                updateWord();
            }
        });
    });
}

function newGame(){
     $.ajax({
        url: 'api/game/newGame' + "?userid=" + user.username,
        type: 'POST',
        contentType: 'plain/text',
        async: false,
        success: function (data, textStatus, jQxhr) {
            setupGameUI();
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log("fail...");
            newGame();
        }
    }); 
}

function destroyGame(){
    $.ajax({
        url: 'api/game/destroyGame' + "?userid=" + user.username,
        type: 'DELETE',
        contentType: 'plain/text',
        async: true,
        success: function (data, textStatus, jQxhr) {
            setupGameUI();
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log("fail...");
            destroyGame();
        }
    });
}

function updateWord() {
    $.ajax({
        url: 'api/game/synligtord' + "?userid=" + user.username,
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
    
    getlife();
}

function getlife() {
    // api/game/getAntalForkerteBogstaver
    $.ajax({
        url: 'api/game/getAntalForkerteBogstaver' + "?userid=" + user.username,
        type: 'GET',
        contentType: 'plain/text',
        async: false,
        success: function (data, textStatus, jQxhr) {
            $('.LifeCount').html('<p>Lifes left: ' + (7-parseInt(data)) + '</p>');
            updateImg(parseInt(data));
            GameOver(data);
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log('failed' + data);
            getlife();
        }
    });
}

function updateImg(life) {
    console.log(life);
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

function GameOver(life){
    if((7-life) <= 0){
        destroyGame();
    $(".gamelabel").css("color", "red");
    $(".gamelabel").html("The game is over!");
    $(".popupLabel").html("Game over!");
    $("#resumebtn").hide();
    $('.popup').show(300);
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
        let gess = $(this).attr('data');
        console.log('gess: ' + gess);

        $.ajax({
            url: 'api/game/geatbogstav?letter=' + gess + "&userid=" + user.username,
            type: 'POST',
            contentType: 'plain/text',
            success: function (data, textStatus, jQxhr) {
                let life = getlife();
                console.log('data:' + data);
                if (JSON.parse(data)) {  // det er ikke kønt men js opfatter det 
                    let life = getlife();
                    $(".gamelabel").css("color", "green");
                    $(".gamelabel").html("Correct! " + gess +  " is in the word!");
                    $('.LifeCount').html('Lifes left: ' + life);
                } else {
                    let life = getlife();
                    $(".gamelabel").css("color", "red");
                    $(".gamelabel").html("Nope! " + gess + " is not in the word.");
                    $('.LifeCount').html('Lifes left: ' + life);
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
        
        $(this).addClass('keyboardbtnDisable');
        
    });
}

function resetKeyboard() {
    $(".keyboardbtn").removeClass("keyboardbtnDisable");
}
