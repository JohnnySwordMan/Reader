package com.mars.reader.core.config

import com.mars.core.AppContext
import com.mars.core.ext.pref
import com.mars.reader.R
import com.mars.reader.account.AccountManager

object Settings {

    var lastPage: Int
        get() = if (lastPageId.isEmpty()) 0 else AppContext.resources.getIdentifier(
            lastPageId,
            "id",
            AppContext.packageName
        )
        set(value) {
            lastPageId = AppContext.resources.getResourceEntryName(value)
        }

    val defaultPage: Int
        get() = if (AccountManager.isLogin()) defaultPageForUser else defaultPageForVisitor

    private var lastPageId by pref("")

    private var defaultPageForUser by pref(R.id.home)

    private var defaultPageForVisitor by pref(R.id.home)
}