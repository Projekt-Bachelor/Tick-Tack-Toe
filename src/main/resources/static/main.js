function requestJSON(url, callback) {
    fetch(url)
        .then(r => r.json())
        .then(function (data) {
            callback(data)
})
        .catch(e => console.log('Could not process request ' + url))
}


function tableCreate(width, height){
    const table = document.getElementById('game-table')

    for (let row_index=0; row_index<height; row_index++){
        const row = table.insertRow(row_index);
        for (let col_index=0; col_index<height; col_index++){
            const col = row.insertCell(col_index);
            col.id = 'field-row-'+row_index+'-col-'+col_index
            col.addEventListener("click", ()=>console.log('Clicked r:'+row_index +' c:'+col_index));
        }
    }
}










requestJSON("/spiele/list", console.log)
tableCreate(3,3);
