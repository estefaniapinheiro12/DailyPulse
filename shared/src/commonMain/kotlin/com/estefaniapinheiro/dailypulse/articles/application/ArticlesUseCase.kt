package com.estefaniapinheiro.dailypulse.articles.application

import com.estefaniapinheiro.dailypulse.articles.data.ArticleRaw
import com.estefaniapinheiro.dailypulse.articles.data.ArticlesRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

// como argumento do construtor, receberá uma instância do ArticleRepository
class ArticlesUseCase(private val repo: ArticlesRepository) {

    // será uma função de suspensão, pois não queremos bloquear o thread principal
    // retorna uma lista de artigos
    suspend fun getArticles(forceFetch: Boolean): List<Article> {
        // recupera a linha de artigo do ArticlesRepository
        val articlesRaw = repo.getArticles(forceFetch)
        return mapArticles(articlesRaw)

    }

    // essa função recebe a linha de artigos e retorna os artigos normais de domínio
    // Obtem a linha de artigos e chama a função a mapArticles para aplicar a transformação a cada linha,
    // de artigo dentro dessa lista
    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> = articlesRaw.map { raw ->
        // Para cada linha de artigo, vamos construir um artigo real, precisamos de um título, descrição,
        // data e a imagemUrl
        Article(
            raw.title,
            raw.desc ?: "Click to find out more",
            // passando a função da data
            getDaysAgoString(raw.date),
            raw.imageUrl?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
        )
    }
    // Função que edita a data para uma mais apropriada
    private fun getDaysAgoString(date: String): String{
        // sistema de relógio today in, aqui estamos colocando o fuso horário atual como padrão do
        // sistema atual
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil (
            // retornando, a date que estamos usando como argumento é comparada a hoje, essa linha vai
            // retornar 0 se essa data for a data atual, ou retornará um número inteiro negativo, que indicará
            // quantos dias atrás esse dia foi comparado a hoje
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )
        // Construindo o resultado da String
        val result = when {
            // Últimos dias são um número inteiro negativo, estamos obtendo o valor absoluto, e estamos
            // dizendo que a se a diferença for maior que um dia, o resultado será o valor absoluto
            abs(days) > 1 -> "${abs(days)} days ago"
            // se for igual a 1, apresentará o ontem
            abs(days) == 1 -> "Yesterday"
            // se não, o hoje.
            else -> "Today"
        }
        return result
    }

}