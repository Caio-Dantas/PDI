package com.example.apppdi.model

enum class Visibility {
    PUBLIC {
        override fun getText(): String {
            return "Públicos"
        }
    }, PRIVATE {
        override fun getText(): String {
            return "Privados"
        }
    };

    abstract fun getText() : String
}