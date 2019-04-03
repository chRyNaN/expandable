package com.chrynan.expandable

fun runThenTrue(function: () -> Unit): Boolean {
    function.invoke()
    return true
}