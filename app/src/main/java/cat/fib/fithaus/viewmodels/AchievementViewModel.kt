package cat.fib.fithaus.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.fib.fithaus.data.models.Achievement
import cat.fib.fithaus.data.source.AchievementRepository
import cat.fib.fithaus.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor(
    private val achievementRepository: AchievementRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    lateinit var achievement : LiveData<Resource<Achievement>>

    fun getAchievementById(id: Int){
        achievement = achievementRepository.getAchievementById(id)
    }
}