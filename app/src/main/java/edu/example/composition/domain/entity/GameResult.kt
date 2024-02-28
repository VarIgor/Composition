package edu.example.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val winner:Boolean,
    val countOfRightAnswer: Int,
    val countOfRightQuestion: Int,
    val gameSettings: GameSettings
): Parcelable