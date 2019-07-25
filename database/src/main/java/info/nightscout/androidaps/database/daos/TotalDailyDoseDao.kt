package info.nightscout.androidaps.database.daos

import androidx.room.Dao
import androidx.room.Query
import info.nightscout.androidaps.database.TABLE_TOTAL_DAILY_DOSES
import info.nightscout.androidaps.database.entities.TotalDailyDose
import io.reactivex.Single

@Suppress("FunctionName")
@Dao
abstract class TotalDailyDoseDao : BaseDao<TotalDailyDose>() {

    @Query("SELECT * FROM $TABLE_TOTAL_DAILY_DOSES WHERE id = :id")
    abstract override fun findById(id: Long): TotalDailyDose?

    @Query("DELETE FROM $TABLE_TOTAL_DAILY_DOSES")
    abstract override fun deleteAllEntries()

    @Query("SELECT * FROM $TABLE_TOTAL_DAILY_DOSES ORDER BY timestamp DESC LIMIT :amount")
    abstract fun getTotalDailyDoses(amount: Int): Single<List<TotalDailyDose>>
}