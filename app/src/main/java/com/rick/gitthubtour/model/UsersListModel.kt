package com.rick.githubtour.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable


//@Entity //dao   //@PrimaryKey
@Serializable
@Parcelize
data class UserListResponse(
    @SerializedName("gists_url")
    val gistsUrl: String? = "",
    @SerializedName("repos_url")
    val reposUrl: String? = "",
    @SerializedName("following_url")
    val followingUrl: String? = "",
    @SerializedName("starred_url")
    val starredUrl: String? = "",
    @SerializedName("login")
    val login: String = "",
    @SerializedName("followers_url")
    val followersUrl: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String? = "",
    @SerializedName("received_events_url")
    val receivedEventsUrl: String? = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("events_url")
    val eventsUrl: String? = "",
    @SerializedName("html_url")
    val htmlUrl: String? = "",
    @SerializedName("site_admin")
    val siteAdmin: Boolean = false,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("gravatar_id")
    val gravatarId: String? = "",
    @SerializedName("node_id")
    val nodeId: String? = "",
    @SerializedName("organizations_url")
    val organizationsUrl: String? = ""
): Parcelable

@Serializable
@Parcelize
data class UserInfo(
    @SerializedName("gists_url")
    val gists_url: String? = "",
    @SerializedName("repos_url")
    val repos_url: String? = "",
    @SerializedName("following_url")
    val following_url: String? = "",
    @SerializedName("twitter_username")
    val twitter_username: String? = "",
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("created_at")
    val created_at: String? = "",
    @SerializedName("login")
    val login: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("blog")
    val blog: String? = "",
    @SerializedName("subscriptions_url")
    val subscriptions_url: String? = "",
    @SerializedName("updated_at")
    val updated_at: String? = "",
    @SerializedName("site_admin")
    val site_admin: Boolean = false,
    @SerializedName("company")
    val company: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("public_repos")
    val public_repos: Int? = 0,
    @SerializedName("gravatar_id")
    val gravatar_id: String? = "",
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("organizations_url")
    val organizations_url: String? = "",
    @SerializedName("hireable")
    val hireable: String? = null,
    @SerializedName("starred_url")
    val starred_url: String? = "",
    @SerializedName("followers_url")
    val followers_url: String? = "",
    @SerializedName("public_gists")
    val public_gists: Int? = 0,
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("received_events_url")
    val received_events_url: String? = "",
    @SerializedName("followers")
    val followers: Int? = 0,
    @SerializedName("avatar_url")
    val avatar_url: String? = "",
    @SerializedName("events_url")
    val events_url: String? = "",
    @SerializedName("html_url")
    val html_url: String? = "",
    @SerializedName("following")
    val following: Int? = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("location")
    val location: String? = "",
    @SerializedName("node_id")
    val node_id: String? = ""
): Parcelable