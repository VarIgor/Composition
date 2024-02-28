package edu.example.composition.domain.entity

import java.io.Serializable

data class GameResult(
    val winner:Boolean,
    val countOfRightAnswer: Int,
    val countOfRightQuestion: Int,
    val gameSettings: GameSettings
): Serializable