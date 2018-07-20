package ifpb.pdm.pets

import android.content.ContentValues
import android.content.Context



class PetDAO {
    lateinit var banco: Banco
    val TABELA = "pet"
    lateinit var pet: Pet

    constructor(contexto: Context){
        this.banco = Banco(contexto)
    }

    fun insert(p: Pet){
        val banco = this.banco.writableDatabase // SQLiteDabatase
        val cv = ContentValues()
        cv.put("nome", p.nome)
        cv.put("idade", p.idade)
        cv.put("informe", p.informe)
        cv.put("imagem", p.imagem)
        cv.put("local", p.local)
        cv.put("data", p.data)
        banco.insert(TABELA, null, cv)



    }

    fun select(): List<Pet>{

        val lista = arrayListOf<Pet>()
        val banco = this.banco.readableDatabase
        val colunas = arrayOf("id", "nome", "idade", "informe", "imagem", "local", "data")
        val c = banco.query(TABELA, colunas, null, null, null, null, null)

        c.moveToFirst()
        if (c.count > 0)
        {
            do {
                // recuperar id, nome, idade, informe, imagem, local, data
                val id = c.getInt(c.getColumnIndex("id"))
                val nome = c.getString(c.getColumnIndex("nome"))
                val idade = c.getFloat(c.getColumnIndex("idade"))
                val informe = c.getString(c.getColumnIndex("informe"))
                val imagem = c.getString(c.getColumnIndex("imagem"))
                val local = c.getString(c.getColumnIndex("local"))
                val data = c.getString(c.getColumnIndex("data"))

                // instanciar o pet
                val p = Pet(id, nome, idade.toFloat(), informe, imagem, local, data)

                // adicionando o pet a lista
                lista.add(p)
            } while (c.moveToNext())
        }
        // retornando a lista
        return lista
    }

    fun delete(id:Int){
        val banco = this.banco.writableDatabase
        val where = arrayOf(Integer.toString(id))
        banco.delete(TABELA, "id = ?", where)

    }


    fun buscarPorId(id:Int): Pet {

        val banco = this.banco.readableDatabase
        val where = arrayOf(Integer.toString(id))
        val cursor = banco.query("PET", arrayOf("id", "nome", "idade", "informe", "imagem", "local", "data"), "id" + "= ? ", where, null, null, null, null)

        val colunas = arrayOf("id", "nome", "idade", "informe", "imagem", "local", "data")

        if(cursor!=null){
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val nome = cursor.getString(cursor.getColumnIndex("nome"))
                val idade = cursor.getFloat(cursor.getColumnIndex("idade"))
                val informe = cursor.getString(cursor.getColumnIndex("informe"))
                val imagem = cursor.getString(cursor.getColumnIndex("imagem"))
                val local = cursor.getString(cursor.getColumnIndex("local"))
                val data = cursor.getString(cursor.getColumnIndex("data"))

               pet = Pet(id, nome, idade, informe, imagem, local, data)
            } else {

            }
        }
        banco.close();
        return pet
    }

    fun update(p: Pet){

    }
}