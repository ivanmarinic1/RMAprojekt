package rma.studentattendance.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rma.studentattendance.data.SubjectDao
import rma.studentattendance.data.model.Subject


@Database(
    entities = [Subject::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SubjectConverters::class)
abstract class SubjectDatabase : RoomDatabase() {

    abstract fun getSubjectDao(): SubjectDao

    companion object {

        private const val databaseName = "subjectsDb1"

        @Volatile
        private var INSTANCE: SubjectDatabase? = null

        fun getDatabase(context: Context): SubjectDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): SubjectDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                SubjectDatabase::class.java,
                databaseName
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}