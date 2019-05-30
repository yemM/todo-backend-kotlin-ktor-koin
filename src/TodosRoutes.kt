package com.todobackend

import io.ktor.application.call
import io.ktor.features.NotFoundException
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.TodosRoutes() {
    val todoSource: TodoSource by inject()

    get("/") {
        call.respond(HttpStatusCode.OK, todoSource.findAll())
    }

    get("/{uid}") {
        val todo: Todo? = todoSource.find(call.parameters["uid"]!!)
        if(null === todo) {
            throw NotFoundException()
        }

        call.respond(HttpStatusCode.OK, todo)
    }

    post("/") {
        val todo: Todo = call.receive(Todo::class)

        todoSource.add(todo)

        call.respond(HttpStatusCode.Created, todo)
    }

    patch("/{uid}") {
        val todo: Todo? = todoSource.find(call.parameters["uid"]!!)
        val patch: Patch = call.receive(Patch::class)

        if(null === todo) {
            throw NotFoundException()
        }

        val patchedTodo: Todo = todoSource.update(todo, patch)

        call.respond(HttpStatusCode.OK, patchedTodo)
    }

    delete("/{uid}") {
        val todo: Todo? = todoSource.find(call.parameters["uid"]!!)

        if(null === todo) {
            throw NotFoundException()
        }

        todoSource.remove(todo)

        call.respond(HttpStatusCode.NoContent)
    }

    delete("/") {
        todoSource.clear()
        call.respond(HttpStatusCode.NoContent)
    }
}