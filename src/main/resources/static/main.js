function requestJSON(url, callback) {
    fetch(url)
        .then(r => r.json())
    .then(function (data) {
        callback(data)
    })
        .catch(e => console.log('Could not process request ' + url+' error:'+e)
)
}


function startGame(){
    console.log('Game comencing '+ name)
    const intervalID = window.setInterval(()=>requestJSON('/spielebrett/' + name + '/show', updateGame), 500);
}

/**
 *
 * @param data
 * @todo updateGame schreiben
 */
 function updateGame(data){
    for (let row_index = 0; row_index < data.height; row_index++) {
        for (let col_index = 0; col_index < data.width; col_index++) {
            const cell = document.getElementById('cell-row-'+row_index+'-col-'+col_index)

            switch('x'){
           // switch(data.elements[row_index][col_index]){
                case 'x':
                        cell.innerHTML = '<img src="cross.svg">'
                    break;

                case '0':
                    cell.innerHTML = '<img src="circle.svg">'
                    break;

                case null:
                    cell.innerHTML = ''
                    break;
            }
        }
    }
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
            col.addEventListener("click",()=>requestJSON("/spielebrett/" + name + "/set-mark/" + row_index + "/" + col_index, updateGame))
}
}
}


tableCreate(3, 3);

const name = "game";
requestJSON("/spiele/list", function(data){
    if(!data.includes(name)){
        console.log('creating game '+name)
        requestJSON('/spielebrett/create/'+name+'/3/3', (data)=>startGame())
    }else{
        console.log('game '+name + ' already exists')
        startGame()
    }
})