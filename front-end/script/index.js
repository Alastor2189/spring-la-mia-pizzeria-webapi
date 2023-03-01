let pizze;
let tablePizze = document.querySelector('#pizze_table');

function pizzeList(){
    axios.get('http://localhost:8080/api/pizze').then((response) => {
        pizze = response.data;
        tablePizze.innerHTML ='';
        pizze.forEach(pizza => {
            tablePizze.innerHTML += `
            <tr>
                <td>${pizza.id}</td>
                <td><a href="./show.html?id=${pizza.id}">${pizza.nome}</a></td>
                <td>${pizza.descrizione}</td>
                <td>${pizza.prezzo}</td>
          </tr>
            `; 
        });
    }).catch((error) => {
        console.error("richiesta errata", error);
    });
}

pizzeList();