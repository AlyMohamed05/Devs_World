package com.silverbullet.devsworld.feature_profile.data.mapper

import com.silverbullet.devsworld.core.data.remote.dto.response.ProfileDto
import com.silverbullet.devsworld.core.domain.model.Profile

fun ProfileDto.toProfile(): Profile {
    return Profile(
        email = email,
        username = username,
        bio = bio,
        isOwnProfile = isOwnProfile,
        followingCount = followingCount,
        followersCount = followersCount,
        postsCount = postsCount,
        profileImageUrl = profileImageUrl,
        skills = skills,
        githubUrl = githubUrl,
        linkedinUrl = linkedinUrl,
        instagramUrl = instagramUrl
    )
}