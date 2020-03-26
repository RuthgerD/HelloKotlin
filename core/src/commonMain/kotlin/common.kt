package com.example.hellocore

expect fun platformFunc(): String

fun commonFunc(): String {
    return "A multi-platform function"
}