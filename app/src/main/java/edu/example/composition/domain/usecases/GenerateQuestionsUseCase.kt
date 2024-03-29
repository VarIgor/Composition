package edu.example.composition.domain.usecases

import edu.example.composition.domain.entity.Question
import edu.example.composition.domain.repository.GameRepository

class GenerateQuestionsUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int):Question{
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object{
        const val COUNT_OF_OPTIONS= 6
    }
}