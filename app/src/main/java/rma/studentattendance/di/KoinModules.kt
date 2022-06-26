package rma.studentattendance.di

import android.app.Application

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rma.studenattendance.presentation.SubjectListViewModel
import rma.studentattendance.data.SubjectDao
import rma.studentattendance.data.SubjectRepository
import rma.studentattendance.data.SubjectRepositoryImpl
import rma.studentattendance.data.room.SubjectDatabase
import rma.studentattendance.presentation.SubjectDetailsViewModel
import rma.studentattendance.presentation.SubjectNewViewModel

val databaseModule = module {
    fun provideDatabase(application: Application): SubjectDatabase {
        return SubjectDatabase.getDatabase(application)
    }

    fun provideSubjectDao(database: SubjectDatabase): SubjectDao {
        return database.getSubjectDao()
    }
    single<SubjectDatabase> { provideDatabase(get()) }
    single<SubjectDao> { provideSubjectDao(get()) }
}

val repositoryModule = module {
    single<SubjectRepository> { SubjectRepositoryImpl(get()) }
}

val viewmodelModule = module {
    viewModel { SubjectListViewModel(get()) }
    viewModel { SubjectDetailsViewModel(get()) }
    viewModel { SubjectNewViewModel(get()) }
}