package cat.fib.fithaus.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.fib.fithaus.data.models.ShareAchievement
import cat.fib.fithaus.data.source.ShareAchievementRepository
import cat.fib.fithaus.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShareAchievementViewModel @Inject constructor(
    private val shareAchievementRepository: ShareAchievementRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var shareAchievement : LiveData<Resource<List<ShareAchievement>>>? = null

    fun getShareAchievementById(id: Int){
        shareAchievement = shareAchievementRepository.getShareAchievementById(id)
    }
}