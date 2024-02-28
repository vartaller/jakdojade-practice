package com.volodymyr.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(dao: Dao): Repository {
        return RepositoryImpl(dao)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun provideDao(roomDatabase: DatabaseApp): Dao {
        return roomDatabase.dao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule  {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): DatabaseApp {
        return DatabaseApp.getDatabase(context = context, scope = CoroutineScope(SupervisorJob()))
    }
}