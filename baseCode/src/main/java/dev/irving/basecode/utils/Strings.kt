package dev.irving.basecode.utils

object Strings {
    fun noneIsEmpty(vararg strings: String?): Boolean {
        strings.forEach { if (it.isNullOrBlank()) return false }
        return true
    }
}
