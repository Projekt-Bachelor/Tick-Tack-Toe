"use strict";

const notifier = new AWN();

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
    //.catch(e => console.log('Could not process request ' + url + '\n' + e))
}

function updateGame(data) {
    //console.log(data)
    for (let row_index = 0; row_index < data.height; row_index++) {
        for (let col_index = 0; col_index < data.width; col_index++) {
            const cell = document.getElementById('cell-row-' + row_index + '-col-' + col_index);
            console.log(data)
            if (data.elements[row_index][col_index] !== null) {
                switch (data.elements[row_index][col_index].m_item) {
                    case "KREUZ":
                        cell.innerHTML = '<img src="cross.svg">';
                        break;

                    case "KREIS":
                        cell.innerHTML = '<img src="circle.svg">';
                        break;

                    case null:
                        cell.innerHTML = '<img src="empty.svg">';
                        break;
                }
            }

            else
                cell.innerHTML = '<img src="empty.svg">';
        }
    }
    if (data.winner == "KREIS") {
        notifier.success('Leider hast du verloren. Versuch es doch einnfach nochmal!')
    }
    else if (data.winner == "KREUZ") {
        notifier.success('Yuhuu! Du hast gewonnen!')
    }

}


function checkForUpdate(data) {
    requestJSON("/spiele/list", function (data) {
        if (data.includes(name)) {
            requestJSON('/spielebrett/' + name + '/show', updateGame)
        } else {
            console.log('Game ' + name + ' disappeared');
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
    while (table.hasChildNodes()) {
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
    if (data && data.width && data.height && data.name) {
        notifier.success('Ein Spiel wurde erfolgreich erstellt')
        tableCreate(width, height);
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

/**Spiel gegen den Bot soll gestartet werden*/
document.getElementById('unmöglich').addEventListener('click', () => notifier.success('Ein unmögliches Spiel gegen einen Bot wird gestartet.'));
document.getElementById('unmöglich').addEventListener('click', () => requestJSON("/spielebrett/create/" + name + "/unmöglich/" + width + "/" + height, startGame));

requestJSON("/spiele/list", function (data) {
    if (data.includes(name)) {
        console.log('game ' + name + ' already exists');
        requestJSON('/spielebrett/' + name + '/show', startGame)
    } else {
        notifier.info('Es existert noch kein Spiel. Bitte wählen sie einen Schwierigkeitsgrad aus der Navigation aus.')
    }
});


