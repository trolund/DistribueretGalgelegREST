var word;
var win = false;

$(document).ready(function () {

    keyboard();
    $('.popup').hide();
    $('.list').hide();
    newGame();

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

    console.log("Spillet er Loaded og klar! :)");
    // update per 5sek if checkbox i checked
        setInterval(function () {
            if(JSON.parse($('#pollingCheck').prop('checked'))) {
                $.ajax({
                    url: 'api/game/gameExist' + "?userid=" + user.username,
                    type: 'GET',
                    contentType: 'plain/text',
                    success: function (data, textStatus, jQxhr) {
                        if (JSON.parse(data)) {
                            updateWord();
                            updateKeyboard();
                        }
                        /*
                        else {
                                if(win) {
                                    $('.popupLabel').html('Det ser ud til at dit spil ikke findes mere? tjek loggen for at se om du har vundet spillet.');
                                    $(".popup").show(300);
                                }
                            }
                            */
                    },
                    error: function (jqXhr, textStatus, errorThrown) {
                        console.log("fail...");
                        $(".popup").show(300);
                    }
                });
            }
        }, 5000);
});

function getword() {
    $.ajax({
        url: 'api/game/word' + "?userid=" + user.username,
        type: 'GET',
        contentType: 'plain/text',
        success: function (data, textStatus, jQxhr) {
            word = data;
            console.log("Ordet er: " + word);
        },
        error: function (jqXhr, textStatus, errorThrown) {
            word = "Det fik jeg ikke fat i?";
        }
    });
}

function newGame(){
     $.ajax({
        url: 'api/game/newGame' + "?userid=" + user.username,
        type: 'POST',
        contentType: 'plain/text',
        async: false,
        success: function (data, textStatus, jQxhr) {
            getlife();
            getword();
            updateWord();
            win = false;
            $(".gamelabel").html("");
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log("fejl...");
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
            console.log(data);
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

            if(!word.includes('*') && win != true){
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
                $(".popupLabel").html("Du vandt spillet!");
                $("#resumebtn").hide();
                $('.popup').show(300);
                console.log("Spillet er Vundet! med ord: " + word);
                win = JSON.parse(data);
            }
        },
        error: function (jqXhr, textStatus, errorThrown) {
            $('.visWord').text("fejl...");
        }
    });
}

function getlife() {
    $.ajax({
        url: 'api/game/getAntalForkerteBogstaver' + "?userid=" + user.username,
        type: 'GET',
        contentType: 'plain/text',
        async: false,
        success: function (data, textStatus, jQxhr) {
            $('.LifeCount').html('<p>Liv tilbage: <b>' + (7-parseInt(data)) + '</b></p>');
            updateImg(parseInt(data));
            GameOver(data);
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log('failed' + data);
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

function GameOver(life){
    if((7-life) <= 0){
        //destroyGame();
    $(".gamelabel").css("color", "red");
    $(".gamelabel").html(word);
    $(".popupLabel").html("spillet er tabt, ordet var: " + word);
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

    $('.keyboard').empty();

    // lav alle knapperne
    $.each(abc, function (index, value) {
        $('.keyboard').append("<span><button class='keyboardbtn' data=" + value + ">" + value + "</button></span>");


    });

    // set én clicklisner på dem alle.

    $('.keyboardbtn').click(function () {
        let gess = $(this).attr('data');
        var thisbtn = $(this);
        console.log('Gæt på bogstavet: ' + gess);

        $.ajax({
            url: 'api/game/geatbogstav?letter=' + gess + "&userid=" + user.username,
            type: 'POST',
            contentType: 'plain/text',
            success: function (data, textStatus, jQxhr) {

                if (JSON.parse(data)) {  // det er ikke kønt men js opfatter det
                    $(".gamelabel").css("color", "green");
                    $(".gamelabel").html("Rigtigt! " + gess +  " er i ordet.");
                } else {
                    getlife();
                    $(".gamelabel").css("color", "red");
                    $(".gamelabel").html("Nope! " + gess + " er ikke i ordet.");
                }

                updateWord();
                thisbtn.addClass('keyboardbtnDisable');
            },
            error: function (jqXhr, textStatus, errorThrown) {
                console.log('failed');
            }
        });
        
    });

    // set keypreses
    $(document).keypress(function(e){
        $.each(abc, function (item) {
            if (e.which == item.charCode) {
                var element = $(".keyboardbtn").find("[data='" + item + "']");
                if (element != null) {
                    element.click();
                }
            }
        })
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