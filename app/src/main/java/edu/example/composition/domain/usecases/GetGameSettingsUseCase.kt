package edu.example.composition.domain.usecases

import edu.example.composition.domain.entity.GameSettings
import edu.example.composition.domain.entity.Level
import edu.example.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}