package com.todobackend

import org.koin.dsl.module

val TodoModule = module {
    single { TodoSource() }
}