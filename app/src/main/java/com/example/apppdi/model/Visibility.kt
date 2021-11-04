package com.example.apppdi.model

enum class Visibility {
    PUBLIC {
        override fun getDisplayText(): String {
            return "Públicos"
        }

        override fun getTextAsParam(): String {
            return "public"
        }
    }, PRIVATE {
        override fun getDisplayText(): String {
            return "Privados"
        }

        override fun getTextAsParam(): String {
            return "private"
        }
    };

    abstract fun getDisplayText() : String
    abstract fun getTextAsParam() : String
}