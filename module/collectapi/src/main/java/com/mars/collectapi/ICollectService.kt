package com.mars.collectapi

interface ICollectService {

    fun collect(articleId: Int)

    fun unCollect(articleId: Int)
}