package edu.example.composition.domain.entity

data class GameResult(
    val winner:Boolean,
    val countOfRightAnswer: Int,
    val countOfRightQuestion: Int,
    val gameSettings: GameSettings
)