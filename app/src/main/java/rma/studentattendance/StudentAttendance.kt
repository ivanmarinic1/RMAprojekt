package rma.studentattendance

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rma.studentattendance.di.databaseModule
import rma.studentattendance.di.repositoryModule
import rma.studentattendance.di.viewmodelModule


class StudentAttendance: Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
        startKoin{
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@StudentAttendance)
            modules(
                databaseModule,
                repositoryModule,
                viewmodelModule
            )
        }
    }
    companion object{
        lateinit var application: Application
    }
}