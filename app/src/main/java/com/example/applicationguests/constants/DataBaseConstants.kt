package com.example.applicationguests.constants

class DataBaseConstants private constructor() {
    object GUEST {
        const val NEW_ID = "guestid"

        object FILTER {
            const val EMPTY = 0
            const val PRESENT = 1
            const val ABSENT = 2
        }
    }
}