package com.mars.common_base.user.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * "admin": false,
 * "chapterTops": [],
 * "collectIds": [],
 * "email": "",
 * "icon": "",
 * "id": 32250,
 * "nickname": "xxx",
 * "password": "",
 * "publicName": "xxx",
 * "token": "",
 * "type": 0,
 * "username": "xxx"
 */

@Parcelize
data class User(
    var admin: Boolean,
    var email: String,
    var icon: String,
    var id: Int,
    var nickname: String,
    var password: String,
    var publicName: String,
    var token: String,
    var type: Int,
    var username: String,
    var collectIds: List<Int>
) : Parcelable