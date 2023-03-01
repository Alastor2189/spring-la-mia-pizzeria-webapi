const UrlParams = new URLSearchParams(window.location.search);
const pizzaId = UrlParams.get('id');
let boxShow = document.querySelector('#box_show');


axios.get(`http://localhost:8080/api/pizze/${id}`).then((response) => {
    console.log("Richiesta ok", response);
    const pizza = response.data;
    const listaIngredienti = pizza.ingrediente;
    //console.log(listaIngredienti);
    const listaOfferte = pizza.listaOfferte;
    console.log(listaOfferte);
    document.getElementById('id_pizza').src = pizza.foto;
    document.getElementById('immagine_pizza').alt = pizza.foto+'\'s pizza';
    document.getElementById('nome_p').innerHTML = pizza.id+' - '+pizza.nome;
    document.getElementById('descrizione_p').innerHTML = pizza.descrizione;
    if(listaIngredienti.length > 0){
        document.getElementById('ingredienti_pizza').innerHTML = "Ingredienti:";
        listaIngredienti.forEach(ingrediente => {
           document.getElementById('ingrediente_pizza').innerHTML += ingrediente.name + " ";
        });
    }
    document.getElementById('prezzo').innerHTML = pizza.prezzo + ' €';
    if (listaOfferte.length > 0){
        listaOfferte.forEach(offerta => {
            console.log(offerta.name);
            document.getElementById('offerte').innerHTML += `
            <li id="offerta_pizza"> ${offerta.nome} - ${offerta.inizioOfferta} / ${offerta.fineOfferta}
				<span><a><i class="fa-regular fa-pen-to-square fs-4"></i></a> Modifica offerta</span>
			</li>
            `;
        })
    } else {
        document.getElementById('offerte_vuote').innerHTML = "Nessuna offerta presente"
    }
}).catch((error) => {
    console.error("Richiesta del dettaglio errata");
})