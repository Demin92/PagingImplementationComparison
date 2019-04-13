package com.demin.data.network

import com.demin.data.network.exceptions.ConvertException

fun <T> getOrDie(source: T?, bindingString: String): T {
    return source ?: throw ConvertException("$bindingString can not be null")
}