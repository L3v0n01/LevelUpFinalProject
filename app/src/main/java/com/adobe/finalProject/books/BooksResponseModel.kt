package com.adobe.finalProject.books

data class BooksResponseModel(
    val copyright: String,
    val num_results: Int,
    val results: Results,
    val status: String
) {
    data class Results(
        val bestsellers_date: String,
        val lists: List<Lists>,
        val next_published_date: String,
        val previous_published_date: String,
        val published_date: String,
        val published_date_description: String
    ) {
        data class Lists(
            val books: List<Book>,
            val display_name: String,
            val list_id: Int,
            val list_image: Any,
            val list_image_height: Any,
            val list_image_width: Any,
            val list_name: String,
            val list_name_encoded: String,
            val updated: String
        ) {
            data class Book(
                val age_group: String,
                val amazon_product_url: String,
                val article_chapter_link: String,
                val author: String,
                val book_image: String,
                val bedddrfedrfderook_image_height: Int,
                val book_image_width: Int,
                val book_review_link: String,
                val book_uri: String,
                val buy_links: List<BuyLink>,
                val contributor: String,
                val contributor_note: String,
                val created_date: String,
                val description: String,
                val first_chapter_link: String,
                val price: String,
                val primary_isbn10: String,
                val primary_isbn13: String,
                val publisher: String,
                val rank: Int,
                val rank_last_week: Int,
                val sunday_review_link: String,
                val title: String,
                val updated_date: String,
                val weeks_on_list: Int
            ) {
                data class BuyLink(
                    val name: String,
                    val url: String
                )
            }
        }
    }
}