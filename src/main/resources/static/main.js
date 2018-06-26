"use strict";

const notifier = new AWN();

let pollTimer;
const name = "Spiel-0"
var width = 3;
var height = 3;

function requestJSON(url, callback) {
    //console.log(url)
    fetch(url)
        .then(r => r.json())
        .then(function (data) {
            callback(data)
        })
        .catch(e => console.log('Could not process request ' + url + '\n' + e))
}

function updateGame(data) {
    for (let row_index = 0; row_index < data.height; row_index++) {
        for (let col_index = 0; col_index < data.width; col_index++) {
            const cell = document.getElementById('cell-row-' + row_index + '-col-' + col_index);
            consloe.log(data)
            switch (data.elements[row_index][col_index]) {
                case 'x':
                    cell.innerHTML = '<img src="cross.svg">';
                    break;

                case 'o':
                    cell.innerHTML = '<img src="circle.svg">';
                    break;

                case null:
                    cell.innerHTML = '';
                    break;
            }
        }
    }
}


function checkForUpdate(data) {
    requestJSON("/spiele/list", function (data) {
        if (data.includes(name)) {
            requestJSON('/spielebrett/' + name + '/show', updateGame)
        } else {
            console.log('Game ' + name + 'disappeared');
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
    while(table.hasChildNodes())
    {
        table.removeChild(table.firstChild)
    }

    for (let row_index = 0; row_index < height; row_index++) {
        const row = table.insertRow(row_index);
        for (let col_index = 0; col_index < height; col_index++) {
            const col = row.insertCell(col_index);
            col.id = 'cell-row-' + row_index + '-col-' + col_index;
            //  col.addEventListener("click",  ()= > console.log('Clicked r:' + row_index + ' c:' + col_index))
            col.addEventListener("click", () => requestJSON("/spielebrett/" + name + "/set-mark/" + row_index + "/" + col_index, checkForUpdate))
        }
    }
}

function gameName(name) {
    document.getElementById("spiel-name").innerHTML = name;
}


function startGame(data) {
    if (data && data.width && data.height) {
        notifier.success('Ein Spiel wurde erfolgreich erstellt')
        tableCreate(width, height);
        pollTimer = window.setInterval(checkForUpdate, 500);
    } else {
        notifier.alert('Das Spiel konnte nicht erstellt werden.')
    }
}

//const name='Spiel-0'
/**Player vs Player wird ausgewählt*/
document.getElementById('PvP').addEventListener('click', () => notifier.alert('Player vs Player ist noch nicht implementiert.'));
//document.getElementById('PvP').addEventListener('click', () => requestJSON("/spielebrett/" + name + "/PvP/" + width + "/" + height, startGame));

/**Einfaches Spiel soll gestartet werden*/
document.getElementById('einfach').addEventListener('click', () => notifier.success('Ein einfaches Spiel gegen einen Bot wird gestartet.'));
document.getElementById('einfach').addEventListener('click', () => requestJSON("/spielebrett/create/" + name + "/einfach/" + width + "/" + height, startGame));

/**Spiel mit mittlerer Schwierigkeit soll gestartet werden*/
document.getElementById('mittel').addEventListener('click', () => notifier.success('Ein mittleres Spiel gegen einen Bot wird gestartet.'));
document.getElementById('mittel').addEventListener('click', () => requestJSON("/spielebrett/create/" + name + "/mittel/" + width + "/" + height, startGame));

/**Spiel gegen den Bot soll gestartet werden*/
document.getElementById('schwer').addEventListener('click', () => notifier.success('Ein schweres Spiel gegen einen Bot wird gestartet.'));
document.getElementById('schwer').addEventListener('click', () => requestJSON("/spielebrett/create/" + name + "/schwer/" + width + "/" + height, startGame));


requestJSON("/spiele/list", function (data) {
    if (data.includes(name)) {
        console.log('game ' + name + ' already exists');
        requestJSON('/spielebrett/' + name + '/show', startGame)
    } else {
        notifier.info('Es existert noch kein Spiel. Bitte wählen sie einen Schwierigkeitsgrad aus der Navigation aus.')
    }
});


