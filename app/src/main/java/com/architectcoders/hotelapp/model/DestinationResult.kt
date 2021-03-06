package com.architectcoders.hotelapp.model

data class DestinationResult(
        val term: String,
        val suggestions: List<Group>
)

data class Group(
        val group: String,
        val entities: List<Hotel>
)