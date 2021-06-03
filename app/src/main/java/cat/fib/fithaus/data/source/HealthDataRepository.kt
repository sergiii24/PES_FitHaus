package cat.fib.fithaus.data.source

import androidx.lifecycle.LiveData
import cat.fib.fithaus.data.models.HealthData
import cat.fib.fithaus.utils.Resource

interface HealthDataRepository {

    fun createHealthData(healthData: HealthData): LiveData<Resource<HealthData>>

    fun getHealthDataByUserId(userId: Int): LiveData<Resource<HealthData>> //List

}