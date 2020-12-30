package com.soapdogg.lispInterpreter.datamodels

class MyStack<T> {

    private val elements = mutableListOf<T>()

    fun push(item: T) = elements.add(item)

    fun pop(): T {
        val item = elements.last()
        elements.remove(item)
        return item
    }

    fun peek(): T = elements.last()
}