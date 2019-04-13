package com.demin.data.network

interface Dto<T> {
    fun convert(): T
}