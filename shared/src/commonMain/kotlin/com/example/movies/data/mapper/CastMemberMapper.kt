package com.example.movies.data.mapper

import com.example.movies.data.network.IMAGE_SMALL_BASE_URL
import com.example.movies.data.network.model.CastMemberResponse
import com.example.movies.domain.model.CastMember

fun CastMemberResponse.toModel() = CastMember(
    id = this.id,
    name = this.name,
    mainRole = this.department,
    character = this.character,
    profileUrl = "$IMAGE_SMALL_BASE_URL/${this.profilePath}"
)