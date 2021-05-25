package cat.fib.fithaus.ui.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RanquingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ranking Fragment"
    }
    val text: LiveData<String> = _text
}