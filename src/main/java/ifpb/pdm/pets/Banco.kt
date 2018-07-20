package ifpb.pdm.pets

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


val VERSAO = 1

class Banco(context: Context?) : SQLiteOpenHelper(context, "pet.sql3", null, VERSAO) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table pet(id integer primary key autoincrement, nome text, idade float, informe text, imagem text, local text, data text);"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table pet")
        this.onCreate(db)
    }



}