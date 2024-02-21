package edu.example.composition.domain.repository

import edu.example.composition.domain.entity.GameSettings
import edu.example.composition.domain.entity.Level
import edu.example.composition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question
    fun getGameSettings(level: Level):GameSettings
}