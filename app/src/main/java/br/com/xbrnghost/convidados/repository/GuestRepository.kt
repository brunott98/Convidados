package br.com.xbrnghost.convidados.repository

import android.content.Context
import br.com.xbrnghost.convidados.GuestModel


class GuestRepository(context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()
    //private val guestDataBase = GuestDataBase(context)

    fun insert(guest: GuestModel): Boolean {

        return guestDataBase.insert(guest) > 0

    }


    fun update(guest: GuestModel): Boolean {

        return guestDataBase.update(guest) >0

    }


    fun delete(id:Int) {

        val guest =  get(id)
        return guestDataBase.delete(guest)

    }

    fun getAllGuests(): List<GuestModel> {

        return guestDataBase.getAll()

    }

    fun getPresentGuests(): List<GuestModel> {

        return guestDataBase.getPresent()

    }


    fun getAbsentGuests(): List<GuestModel> {

        return guestDataBase.getAbsent()

    }

    fun get(id: Int): GuestModel {

        return guestDataBase.get(id)

    }

    /*

    companion object {

        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {

            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }

            return repository

        }

    }

    fun insert(guest: GuestModel): Boolean {

        return try {

            val db = guestDataBase.writableDatabase

            val values = ContentValues()
            val presence = if (guest.presence) 1 else 0

            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)

            true

        } catch (e: Exception) {
            false
        }

    }


    fun update(guest: GuestModel): Boolean {

        return try {

            val db = guestDataBase.writableDatabase

            val values = ContentValues()
            val presence = if (guest.presence) 1 else 0

            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)


            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?" //Onde id for igual a
            val args = arrayOf(guest.id.toString())               //Valor do id passado

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            true

        } catch (e: Exception) {
            false
        }

    }


    fun delete(id: Int): Boolean {

        return try {

            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true

        } catch (e: Exception) {
            false
        }

    }


    fun getAllGuests(): List<GuestModel> {

        val guestList = mutableListOf<GuestModel>()

        try{

            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, null, null,
                null, null, DataBaseConstants.GUEST.COLUMNS.NAME + " ASC"
            )

            //Cursor pode ser nulo se eu passar um valor que não existe
            // E sua contagem de registros da tabela seja maior que zero

            if(cursor!=null && cursor.count >0){

                while(cursor.moveToNext()){

                    val id = cursor.getInt(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))

                    val name = cursor.getString(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))

                    val presence = cursor.getInt(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE))


                    guestList.add(GuestModel(id,name,presence == 1))

                }

            }

            cursor.close()

        } catch(e: Exception){

            return guestList

        }

        return guestList

    }


    fun getPresentGuests(): List<GuestModel> {

        val guestList = mutableListOf<GuestModel>()

        try{

            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = ?"
            val args = arrayOf("1")

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args,
                null, null, DataBaseConstants.GUEST.COLUMNS.NAME + " ASC"
            )

            /*
             outra forma de executar a query é através de val cursor = db.rawQuery("SELECT id, name,
             presence FROM Guest WHERE presence = 1",null)

             */



            //Cursor pode ser nulo se for passado um valor que não existe
            // E sua contagem de registros da tabela deve ser maior que zero(existência de dados)

            if(cursor!=null && cursor.count >0){

                while(cursor.moveToNext()){

                    val id = cursor.getInt(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))

                    val name = cursor.getString(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))

                    val presence = cursor.getInt(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE))


                    guestList.add(GuestModel(id,name,presence == 1))

                }

            }

            cursor.close()

        } catch(e: Exception){

            return guestList

        }

        return guestList

    }


    fun getAbsentGuests(): List<GuestModel> {

        val guestList = mutableListOf<GuestModel>()

        try{

            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = ?"
            val args = arrayOf("0")

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args,
                null, null, DataBaseConstants.GUEST.COLUMNS.NAME + " ASC"
            )

            /*
             outra forma de executar a query é através de val cursor = db.rawQuery("SELECT id, name,
             presence FROM Guest WHERE presence = 1",null)

             */



            //Cursor pode ser nulo se for passado um valor que não existe
            // E sua contagem de registros da tabela deve ser maior que zero(existência de dados)

            if(cursor!=null && cursor.count >0){

                while(cursor.moveToNext()){

                    val id = cursor.getInt(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))

                    val name = cursor.getString(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))

                    val presence = cursor.getInt(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE))


                    guestList.add(GuestModel(id,name,presence == 1))

                }

            }

            cursor.close()

        } catch(e: Exception){

            return guestList

        }

        return guestList

    }

    fun get(id: Int): GuestModel? {

        var guest: GuestModel? = null

        try{

            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args,
                null, null, null
            )

            //Cursor pode ser nulo se eu passar um valor que não existe
            // E sua contagem de registros da tabela seja maior que zero

            if(cursor!=null && cursor.count >0){

                while(cursor.moveToNext()){



                    val name = cursor.getString(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))

                    val presence = cursor.getInt(cursor.
                    getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE))


                    guest = (GuestModel(id,name,presence == 1))

                }

            }

            cursor.close()

        } catch(e: Exception){

            return guest

        }

        return guest

    }

    * */

}