package com.demin.pagingimplementationcomparison.entity

fun <T> getOrDie(source: T?, bindingString: String): T {
    return source ?: throw ConvertException("$bindingString can not be null")
}