package com.example.applicationguests.repository
//CONEXÃO COM O BANCO DE DADOS

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [GuestData::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    //AQUI NOS FALAMOS QUEM SÃO AS ENTIDADES QUE VAMOS USAR NO NOSSO BANCO DE DADOS
    companion object {

        private lateinit var INSTANCE: GuestDataBase

        fun getDataBase(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .allowMainThreadQueries() //permitir que as operações de banco de dados sejam executadas na thread principal
                        .addMigrations(MIGRATION_1_2)//FAZEMOS UMA MIGRAÇÃO DA VERSION DO BANCO DE DADOS
                        .build()
                }
            }
            return INSTANCE
        }

        //FAZEMOS A MIGRAÇÃO DO VERSION DO BANCO DE DADOS
        //ATUALIZAMOS O BANCO DA VERSAO 1 PARA 2
        private val MIGRATION_1_2 : Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DELETE FROM Guest")
            }
        }
    }
}


//O BANCO DE DADOS COM O OPEN HELPER

//    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL(CREATE_TABLE_GUEST)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
//
//    companion object {
//        private const val VERSION = 1
//        private const val NAME = "guestdb"
//
//        private const val CREATE_TABLE_GUEST =
//            ("create table " + DataBaseConstants.GUEST.TABLE_NAME + " ("
//                    + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
//                    + DataBaseConstants.GUEST.COLUMNS.NAME + " text, "
//                    + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
//    }
