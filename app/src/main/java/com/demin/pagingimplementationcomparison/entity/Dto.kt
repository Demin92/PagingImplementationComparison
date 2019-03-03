package com.demin.pagingimplementationcomparison.entity

interface Dto<T> {
    fun convert(): T
}