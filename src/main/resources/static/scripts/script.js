
function getSpecificPokemon(){
    const name = document.getElementById("pokemonName").value;
    fetch(`/pokemon?name=${name}`)
        .then(response => {
            if(!response.ok){
                throw new Error('Error');
            }

         return response.json();
         })
        .then(pokemon => {

            let name = pokemon.name;
            
            let card = document.getElementById("card");
            card.classList.remove('cardContainer');
            card.offsetWidth;
            card.classList.add('cardContainer');

            document.getElementById("imageID").src = `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemon.id}.png`;
            document.getElementById("pokemonCardName").innerText = name.charAt(0).toUpperCase() + name.slice(1);
            document.getElementById("pokemonCardHeight").innerText = "Height: " + pokemon.height;
            document.getElementById("pokemonCardWeight").innerText = "Weight: " + pokemon.weight;
            document.getElementById("pokemonCardBaseExperience").innerText = "Base Experience: " +
            pokemon.base_experience;
           
        })
        .catch(error => {
         console.log(error);
        });

}

document.getElementById("getCard").addEventListener("click", getSpecificPokemon);