package com.estefaniapinheiro.dailypulse.articles.data

import estefaniapinheiro.dailypulse.db.DailyPulseDatabase

// Vai se comunicar com o nosso banco de dados, então ele recebe um argumento do constructor por meio
// da injeção de dependência
class ArticlesDataSource(private val database: DailyPulseDatabase) {

    // A API pública que essa fonte de dados vai expor será 3 funções

    // Retorna uma lista de articles
    fun getAllArticles(): List<ArticleRaw> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    // insere todos os artigos buscados
    fun insertArticles(articles: List<ArticleRaw>) {
        // aqui ele usa o banco de dado, aqui ele obtem as consultas do banco de dados
        //transation = todas as consultas que ocorrem dentro da transação devem ser bem-sucedidas, e
        // se apenas uma delas não for bem-sucedida, a transação inteira com todas as consultas deverá ser
        // revertida
        database.dailyPulseDatabaseQueries.transaction {
            // Para cada um desses artigos vamos inserir dentro do banco de dados
            articles.forEach { articleRaw ->
                insertArticle(articleRaw)

            }

        }
    }

    // exclui todos os artigos da tabela do banco de dados
    fun clearArticles() =
        // database = obtêm a instância do banco de dados
        database.dailyPulseDatabaseQueries.removeAllArticles()


    // insere uma linha vertical
    private fun insertArticle(articleRaw: ArticleRaw) {
        // Obtem acesso as consultas de banco de dados
        database.dailyPulseDatabaseQueries.insertArticle(
            // Informações que serão armazenadas nas colunas
            articleRaw.title,
            articleRaw.desc,
            articleRaw.date,
            articleRaw.imageUrl
        )


    }

    // função que mapeia a função acima
    private fun mapToArticleRaw(
        // Recebe como argumento o que quer que venha do banco de dados
        title: String,
        desc: String?,
        date: String,
        url: String?
        // Retorna, a mapeação para a linha do artigo
    ): ArticleRaw =
        ArticleRaw(
            title,
            desc,
            date,
            url
        )


}
// Resumo: Essa é a versão final da função gell all articles que nossa fonte de dados vai expor repositório
