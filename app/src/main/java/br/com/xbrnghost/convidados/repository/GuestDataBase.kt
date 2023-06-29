package br.com.xbrnghost.convidados.repository

import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.xbrnghost.convidados.GuestModel
import br.com.xbrnghost.convidados.constants.DataBaseConstants
import br.com.xbrnghost.convidados.view.GuestFormActivity

//class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION)

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDAO() : GuestDAO

    companion object {

        private lateinit var INSTANCE: GuestDataBase

        fun getDataBase(context: Context): GuestDataBase {

            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                TODO("Not yet implemented")
            }

        }

    }

    /* companion object{

         private const val NAME = "guestdb"
         private const val VERSION = 1

     }

 override fun onCreate(db: SQLiteDatabase?) {

         db!!.execSQL("CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
                     DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, " +
                     DataBaseConstants.GUEST.COLUMNS.NAME + " text, " +
                     DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")

 }

 override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
     TODO("Not yet implemented")
 }
*/

}