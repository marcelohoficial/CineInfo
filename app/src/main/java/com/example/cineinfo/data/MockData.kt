package com.example.cineinfo.data

import com.example.cineinfo.R

data class Movie(
    val id: Int,
    val title: String,
    val posterResId: Int,
    val year: Int,
    val duration: String,
    val rating: Double,
    val synopsis: String,
    val quality: String = "HD"
)

fun getMockMovies(): List<Movie> {
    return listOf(
        Movie(
            id = 0,
            title = "O Auto da Compadecida",
            posterResId = R.drawable.poster_exemplo,
            year = 2000,
            duration = "1h 44m",
            rating = 9.5,
            synopsis = "As aventuras dos nordestinos João Grilo, um sertanejo pobre e mentiroso, e Chicó, o mais covarde dos homens. A dupla luta para sobreviver aplicando golpes no pequeno vilarejo de Taperoá, no sertão da Paraíba."
        ),
        Movie(
            id = 1,
            title = "American Hustle",
            posterResId = R.drawable.poster_american_hustle,
            year = 2013,
            duration = "2h 18m",
            rating = 7.2,
            synopsis = "Um vigarista e sua parceira são forçados a trabalhar para um agente do FBI, que os empurra para um mundo de poder e máfia de Nova Jersey."
        ),
        Movie(
            id = 2,
            title = "A Star Is Born",
            posterResId = R.drawable.poster_star_is_born,
            year = 2018,
            duration = "2h 16m",
            rating = 7.6,
            synopsis = "Um músico experiente ajuda uma jovem artista a encontrar a fama, enquanto ele luta com o alcoolismo e seus próprios demônios internos."
        ),
        Movie(
            id = 3,
            title = "500 Days of Summer",
            posterResId = R.drawable.poster_500_days_of_summer,
            year = 2009,
            duration = "1h 35m",
            rating = 7.7,
            synopsis = "Após sua namorada Summer terminar com ele, um escritor de cartões comemorativos relembra os 500 dias de relacionamento para tentar descobrir o que deu errado."
        ),
        Movie(
            id = 4,
            title = "Crazy, Stupid, Love",
            posterResId = R.drawable.poster_crazy_stupid_love,
            year = 2011,
            duration = "1h 58m",
            rating = 7.4,
            synopsis = "A vida de um homem de meia-idade muda drasticamente quando sua esposa pede o divórcio. Ele busca redescobrir sua masculinidade com a ajuda de um novo amigo."
        ),
        Movie(
            id = 5,
            title = "Passengers",
            posterResId = R.drawable.poster_passengers,
            year = 2016,
            duration = "1h 56m",
            rating = 7.0,
            synopsis = "Uma falha em uma nave espacial, viajando para um planeta colônia distante, desperta um passageiro 90 anos antes do previsto, levando-o a tomar uma decisão fatídica."
        ),
        Movie(
            id = 6,
            title = "Joy",
            posterResId = R.drawable.poster_joy,
            year = 2015,
            duration = "2h 4m",
            rating = 6.6,
            synopsis = "A história da matriarca de uma poderosa dinastia de negócios, Joy Mangano, e sua jornada para se tornar a fundadora e inventora do 'Miracle Mop'."
        ),
        Movie(
            id = 7,
            title = "The Notebook",
            posterResId = R.drawable.poster_the_notebook,
            year = 2004,
            duration = "2h 3m",
            rating = 7.8,
            synopsis = "Um jovem pobre, porém apaixonado, se apaixona por uma jovem rica, mas eles são separados por suas diferenças sociais. A história é contada a uma idosa por um homem em um asilo."
        ),
        Movie(
            id = 8,
            title = "Black Swan",
            posterResId = R.drawable.poster_black_swan,
            year = 2010,
            duration = "1h 48m",
            rating = 8.0,
            synopsis = "Uma bailarina dedicada ganha o papel principal em 'O Lago dos Cisnes', mas a pressão a faz perder o controle da realidade, mergulhando em um pesadelo de rivalidade e loucura."
        ),
        Movie(
            id = 9,
            title = "Perks of Being a Wallflower",
            posterResId = R.drawable.poster_perks_of_being_a_wallflower,
            year = 2012,
            duration = "1h 43m",
            rating = 7.9,
            synopsis = "Um calouro introvertido do ensino médio é acolhido por dois veteranos que o ajudam a descobrir o mundo da amizade, do primeiro amor e a lidar com traumas do passado."
        ),
        Movie(
            id = 10,
            title = "Argo",
            posterResId = R.drawable.poster_argo,
            year = 2012,
            duration = "2h 0m",
            rating = 7.7,
            synopsis = "Agindo sob o disfarce de um produtor de Hollywood, um agente da CIA lança uma operação arriscada para resgatar seis americanos em Teerã durante a crise dos reféns no Irã."
        ),
        Movie(
            id = 11,
            title = "Disenchanted",
            posterResId = R.drawable.poster_disenchanted,
            year = 2022,
            duration = "1h 58m",
            rating = 6.1,
            synopsis = "Dez anos depois de seu 'felizes para sempre', Giselle questiona sua felicidade, inadvertidamente virando a vida de todos no mundo real e em Andalasia de cabeça para baixo."
        ),
        Movie(
            id = 12,
            title = "Strange World",
            posterResId = R.drawable.poster_strange_world,
            year = 2022,
            duration = "1h 42m",
            rating = 5.6,
            synopsis = "A lendária família de exploradores, os Clades, tenta navegar por uma terra desconhecida e traiçoeira ao lado de uma equipe eclética, incluindo uma bolha travessa e um cachorro de três patas."
        )
    )
}

fun findMovieById(id: Int?): Movie? {
    if (id == null) return null
    return getMockMovies().find { it.id == id }
}