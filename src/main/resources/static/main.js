"use strict";

/*$(function () {

    const name = "foo";
    const typ = 'einfach';
    const breite = 3;
    const hoehe = 3;


    jQuery.ajax("/spielebrett/create/" + name + typ +  "/" + breite + "/" + hoehe)
        .done(function (i) {

            console.log(i);
            for (let row_index = 0; row_index < i.height; row_index++) {
                let l_row = $("<tr/>");
                $("#game-table").append(l_row);

                for (let col_index = 0; col_index < i.width; col_index++) {
                    l_row.append(
                        $("<td/>").append(
                            $("<img src='' />")
                                .addClass("isclickable")
                                .attr("id", "field_" + row_index + "_" + col_index)
                        )
                    );
                }
            }


        })
        .fail(function (i) {
            console.log(i);
        });


    $(document).on('click','body *', function () {
        console.log('here')
        console.log()
        // this ist der img-Tag
        //console.log(jQuery(this).attr("id").split("_"));
        //jQuery(this).att("src", "<URL zum Bild")
        //jQuery(this).attr("src", "</cross.svg");
        // entferne die leer Klasse, damit ist das Clickable weg
        //jQuery(this).removeClass("isclickable")
        // und tausche das Bild
        //.attr(„src“, „<hier die neue URL zum Bild>“ );


    });

    $("#einfach").click(function () {
        console.log("Der Button einfach wurde geklickt!");
        jQuery.ajax("/spielebrett/create/" + name + "einfach" +  "/" + breite + "/" + hoehe)
    });
    $("#mittel").click(function () {
        console.log("Der Button mittel wurde geklickt!");
    });
    $("#schwer").click(function () {
        console.log("Der Button schwer wurde geklickt!");
    });
    $("#PvP").click(function () {
        console.log("Der Button PvP wurde geklickt!");
    })
});


*/
const notifier = new AWN();

let pollTimer;


function requestJSON(url, callback) {
    fetch(url)
        .then(r => r.json())
        .then(function (data) {
            callback(data)
        })
        .catch(e => console.log('Could not process request ' + url + '\n' + e))

}


/**
 *
 * @param data


 let global_data = {
    "width": 3,
    "height": 3,
    "name": "spiel",
    "elements": [
        [null, null, 'o'],
        [null, 'x', 'o'],
        ['x', null, null]
    ]
}
 */


function updateGame(data) {
    data = global_data

    for (let row_index = 0; row_index < data.height; row_index++) {
        for (let col_index = 0; col_index < data.width; col_index++) {
            const cell = document.getElementById('cell-row-' + row_index + '-col-' + col_index)
            switch (data.elements[row_index][col_index]) {
                case 'x':
                    cell.innerHTML = '<img src="cross.svg">'
                    break;

                case 'o':
                    cell.innerHTML = '<img src="circle.svg">'
                    break;

                case null:
                    cell.innerHTML = ''
                    break;
            }
        }
    }
}


function checkForUpdate() {
    requestJSON("/spiele/list", function (data) {
        if (data.includes(name)) {
            requestJSON('/spielebrett/' + name + '/show', updateGame)
        } else {
            console.log('Game ' + name + 'disappeared')
            clearInterval(pollTimer)
        }
    })
}

/**
 * Diese Funktion erstellt eine Tabelle nach den param.
 * @param width
 * @param height
 */
function tableCreate(width, height) {
    const table = document.getElementById('game-table');

    for (let row_index = 0; row_index < height; row_index++) {
        const row = table.insertRow(row_index);
        for (let col_index = 0; col_index < height; col_index++) {
            const col = row.insertCell(col_index);
            col.id = 'cell-row-' + row_index + '-col-' + col_index;
            //  col.addEventListener("click",  ()= > console.log('Clicked r:' + row_index + ' c:' + col_index))
            col.addEventListener("click", () => requestJSON("/spielebrett/" + name + "/set-mark/" + row_index + "/" + col_index, updateGame))
        }
    }
}

/*
function setMark(data , idOfCell) {
    const cell = idOfCell
    const currentData = data

//const cell = document.getElementById('cell-row-' + row_index + '-col-' + col_index)
    notifier.alert(cell)
    if (currentData. ==) {
        //    if (cell.innerHTML == '') {
        console.log('Zelle richtig erkannt.')
        notifier.success('Es wurde die Zelle '+ cell)
        cell.innerHTML = '<img src="cross.svg">'

    }
    else {notifier.alert('Hier ist bereits ein Stein gesetzt. Wähle ein freies Feld.')}
    updateGame(data);
}
*/
function gameName(name) {
    document.getElementById("spiel-name").innerHTML = name;
}


function startGame(data) {
    tableCreate(data.width, data.height);
    gameName(name);

    pollTimer = window.setInterval(checkForUpdate, 500);
}


/**Player vs Player wird ausgewählt*/
document.getElementById('PvP').addEventListener('click', () => notifier.alert('Player vs Player ist noch nicht implementiert.'))
document.getElementById('PvP').addEventListener('click', () => requestJSON("/spielebrett/" + name + "/PvP", tableCreate))

/**Einfaches Spiel soll gestartet werden*/
document.getElementById('einfach').addEventListener('click', () => notifier.success('Ein einfaches Spiel gegen einen Bot wird gestartet.'))
document.getElementById('einfach').addEventListener('click', () => requestJSON("/spielebrett/" + name + "/einfach", tableCreate))

/**Spiel mit mittlerer Schwierigkeit soll gestartet werden*/
document.getElementById('mittel').addEventListener('click', () => notifier.success('Ein mittleres Spiel gegen einen Bot wird gestartet.'))
document.getElementById('mittel').addEventListener('click', () => requestJSON("/spielebrett/" + name + "/mittel", tableCreate))

/**Spiel gegen den Bot soll gestartet werden*/
document.getElementById('schwer').addEventListener('click', () => notifier.success('Ein schweres Spiel gegen einen Bot wird gestartet. '))
document.getElementById('schwer').addEventListener('click', () => requestJSON("/spielebrett/" + name + "/schwer", tableCreate))


requestJSON("/spiele/list", function (data) {
    if (data.includes(name)) {
        console.log('game ' + name + ' already exists')
        requestJSON('/spielebrett/' + name + '/show', startGame)
    } else {
        //console.log('creating game ' + name  )
        //requestJSON('/spielebrett/create/' + name + '/3/3', startGame)
        notifier.info('Es existert noch kein Spiel. Bitte wählen sie einen Schwierigkeitsgrad aus der Navigation aus.')
    }
})