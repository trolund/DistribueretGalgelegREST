$(document).ready(function () {
    keyboard();
    $('.popup').hide();
    $('.list').hide();
    newGame();
    console.log("Game ready!");
    $("#playerinfo").html("<p>" + user.username + "</p>");
    GameOver(getlife());


    $("#newGamebtn").click(function (){
        destroyGame();   // slet det spil som spilles nu
        newGame(); // lav et nyt spil.
        resetKeyboard(); // update UI ->
        updateWord();
        getlife();
        $(".popup").hide(300);
    });
    $("#resumebtn").click(function (){
        $(".popup").hide(300);
    });

    $('#listbtn').click(function (){
        buildList();
        $('.list').show(300);
    });

    $('.closebtn').click(function () {
        $('.list').hide(300);
    });

    updateWord();
    updateKeyboard();
});

function newGame(){
     $.ajax({
        url: 'api/game/newGame' + "?userid=" + user.username,
        type: 'POST',
        contentType: 'plain/text',
        async: false,
        success: function (data, textStatus, jQxhr) {
            getlife();
            updateWord();
            $(".gamelabel").html("");
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
        async: false,
        success: function (data, textStatus, jQxhr) {


        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log("fail...");
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
            let word = data.replaceAll('*', '_ ');

            $('.visWord').html(word);

            if(!word.includes('*')){
                tjekWin();
            }else {
                getlife();
            }
        },
        error: function (jqXhr, textStatus, errorThrown) {
            $('.visWord').text("fail...");
        }
    });
}

function tjekWin() {
    $.ajax({
        url: 'api/game/tjekWin' + "?userid=" + user.username,
        type: 'GET',
        contentType: 'plain/text',
        success: function (data, textStatus, jQxhr) {
            if(JSON.parse(data)){
                $(".popupLabel").html("You Win!");
                $("#resumebtn").hide();
                $('.popup').show(300);
                console.log("win!");
            }
        },
        error: function (jqXhr, textStatus, errorThrown) {
            $('.visWord').text("fail...");
        }
    });
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
        'z',
        'æ',
        'ø',
        'å'
    ];

    // lav alle knapperne
    $.each(abc, function (index, value) {
        $('.keyboard').append("<span><button class='keyboardbtn' data=" + value + ">" + value + "</button></span>");
    });

    // set én clicklisner på dem alle.

    $('.keyboardbtn').click(function () {
        let gess = $(this).attr('data');
        var thisbtn = $(this);
        console.log('gess: ' + gess);

        $.ajax({
            url: 'api/game/geatbogstav?letter=' + gess + "&userid=" + user.username,
            type: 'POST',
            contentType: 'plain/text',
            success: function (data, textStatus, jQxhr) {

                if (JSON.parse(data)) {  // det er ikke kønt men js opfatter det
                    $(".gamelabel").css("color", "green");
                    $(".gamelabel").html("Correct! " + gess +  " is in the word!");
                } else {
                    getlife();
                    $(".gamelabel").css("color", "red");
                    $(".gamelabel").html("Nope! " + gess + " is not in the word.");
                }

                updateWord();
                thisbtn.addClass('keyboardbtnDisable');
            },
            error: function (jqXhr, textStatus, errorThrown) {
                console.log('failed');
            }
        });
        
    });
}

function updateKeyboard() {  // set de knapper med bugstaver som der er gættet på.
    $.ajax({
        url: 'api/game/usedLetters' + "?userid=" + user.username,
        type: 'GET',
        contentType: 'application/json',
        async: false,
        success: function (data, textStatus, jQxhr) {
            var jsonData = data.toString().split(",");
            for (var i = 0; i < jsonData.length; i++) {
                var counter = jsonData[i];

                $.each($('.keyboardbtn'), function (){
                    let key = $(this).attr('data');

                    if(counter === key){
                        $(this).addClass('keyboardbtnDisable');
                    }
                })
            }
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log('failed' + data);
        }
    });
}

function resetKeyboard() {
    $(".keyboardbtn").removeClass("keyboardbtnDisable");
}


function buildList() {
    $.ajax({
        url: 'api/game/getScoreboard',
        type: 'GET',
        contentType: 'application/json',
        async: false,
        success: function (data, textStatus, jQxhr) {

            $('.scrollDiv').empty();
            $('.scrollDiv').append("<div class='row'><span class='userid'>Studie nr</span><span>Ordet</span><span>antal bugstaverbrugt</span><span>tidspunkt</span></div>");
            data.forEach(function (item) {
                $('.scrollDiv').append("<div class='row'><span class='userid'>" + item.userid+ "</span><span>" + item.word + "</span><span data-toggle='tooltip' data-placement='top' title=" + item.usedLetters + ">" + item.usedLetters.length + "</span><span>" + item.timeStamp + "</span></div>");
            });

            $('[data-toggle="tooltip"]').tooltip();
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log('failed' + data);
        }
    });
}