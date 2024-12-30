package com.estefaniapinheiro.dailypulse.articles.data

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
        if (forceFetch) {
            dataSource.clearArticles()
            return fetchArticles()
        }

        val articlesDb = dataSource.getAllArticles()
        println("Got ${articlesDb.size} from the database!!")
        if (articlesDb.isEmpty()) {
            return fetchArticles()
        }
        return fetchArticles()
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchedArticle = service.fetchArticles()
        dataSource.insertArticles(fetchedArticle)
        return fetchedArticle
    }

}