let name = "Spiel-0";
let name_counter;
let pollTimer;


function requestJSON(url, callback) {
    fetch(url)
        .then(r => r.json())
        .then(function (data) {
            callback(data)
        })
        .catch(e => console.log('Could not process request ' + url + '\n' + e)
        )
}


/**
 *
 * @param data
 * @todo updateGame schreiben
 */
let global_data={
    "width":3,
        "height":3,
        "name":"spiel",
        "elements":[
        [null,null,'o'],
        [null,'x','o'],
        ['x',null,null]
    ]}
function updateGame(data) {
    data=global_data

    for (let row_index = 0; row_index < data.height; row_index++) {
        for (let col_index = 0; col_index < data.width; col_index++) {
            const cell = document.getElementById('cell-row-' + row_index + '-col-' + col_index)
                switch(data.elements[row_index][col_index]){
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

function gameName(name_counter, name){
    if (name_counter == null){
        name_counter =0;
        name ='Spiel-'+name_counter;
    }
    else{
        name_counter++;
        name ='Spiel-'+name_counter;
    }
}

function checkForUpdate(){
    requestJSON("/spiele/list", function (data) {
        if (data.includes(name)) {
            requestJSON('/spielebrett/' + name + '/show', updateGame)
        }else{
            console.log('Game '+name+'disappeared')
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

function startGame(data) {
    gameName(name_counter, name);
    tableCreate(data.width, data.height);
    pollTimer = window.setInterval( checkForUpdate, 500);
}

requestJSON("/spiele/list", function (data) {
    if (data.includes(name)) {
        console.log('game ' + name + ' already exists')
        requestJSON('/spielebrett/'+name+'/show', startGame)
    } else {
        console.log('creating game ' + name)
        requestJSON('/spielebrett/create/' + name + '/3/3', startGame)
    }
})


