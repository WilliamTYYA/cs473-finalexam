package com.miu.finalexam.retrofit.data.remote.dto.response

// {"type":"general","setup":"What do birds give out on Halloween?","punchline":"Tweets.","id":187}
data class JokeResponseDto(
    val type: String,
    val setup: String,
    val punchline: String,
    val id: Int
)