package com.estefaniapinheiro.dailypulse.articles.data

// Aqui contém a lógica de onde os arquvios devem vir
// como vamos facilitar a comunicação entre a nossa fonte de dados local e o serviço remoto
// Vamos colocar 2 constructores:

class ArticlesRepository(
    // primeiro = nossa fonte de dados local
    private val dataSource: ArticlesDataSource,
    // segundo = serviço de artigo
    private val service: ArticlesService
) {
    // será uma suspend fun, pois não queremos bloquear o thread principal
    suspend fun getArticles(forceFetch: Boolean): List <ArticleRaw> {
            if (forceFetch) {
                dataSource.clearArticles()
                return fetchArticles()
            }


        // Aqui nessa val, tentamos primeiro encontrar os artigos no banco de dados local
        // dataSource.getAllArticles = obtêm todos os artigos
        val articlesDb = dataSource.getAllArticles()
        println("Got ${articlesDb.size} from the database!!")
            if(articlesDb.isEmpty()){
                // se a busca for vazia, usaremos o nosso serviço para buscar os artigos remotamente no
                //back-end
                return fetchArticles()
            }
        // No caso de termos conseguido encontrar artigos em nosso banco de dados, o retornaremos
        return fetchArticles()
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchedArticle = service.fetchArticles()
        dataSource.insertArticles(fetchedArticle)
        return fetchedArticle
    }

}