package com.todobackend

class TodoSource() {

    fun add(todo: Todo) {
        todos.put(todo.uid, todo)
    }

    fun update(todo: Todo, patch: Patch): Todo {
        val todoPatched = todo.copy(
            title = if (null !== patch.title) {patch.title} else todo.title ,
            order = if (null !== patch.order) {patch.order} else todo.order,
            completed = if (null !== patch.completed) {patch.completed} else todo.completed
        )

        todos.put(todo.uid, todoPatched)

        return todoPatched
    }

    fun remove(todo: Todo) {
        todos.remove(todo.uid)
    }

    fun find(uid: String): Todo? {
        return todos[uid]
    }

    fun findAll(): Collection<Todo> {
        return todos.values
    }

    fun clear() {
        todos.clear()
    }

    companion object {
        val todos: MutableMap<String,Todo> = mutableMapOf()
    }
}