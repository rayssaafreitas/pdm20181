package ifpb.pdm.pets

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import android.widget.AdapterView.OnItemLongClickListener
import ifpb.pdm.pets.R
import kotlinx.android.synthetic.main.item_pet.view.*


class MainActivity : AppCompatActivity() {

    lateinit var lista: ListView
    lateinit var btCadastro: Button
    lateinit var btPesquisa: Button
    lateinit var dao: PetDAO

    val CADASTROU = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.dao = PetDAO(this)
        this.btCadastro = findViewById(R.id.btMainCadastro)
        this.btPesquisa = findViewById(R.id.btMainPesquisar)
        this.btCadastro.setOnClickListener({ CadastrarSuspeito(it) })
        this.btPesquisa.setOnClickListener({ PesquisarSuspeito(it) })
        this.lista = findViewById(R.id.lista)
        val adapter = ListaPetsAdapter(this, this.dao.select())
        this.lista.adapter = adapter



        this.lista.setOnItemClickListener({parent, view, position, id ->

            var id_selecionado: Int = parent.tvItemId.text.toString().toInt()
            exibirCadastro(id_selecionado)
        })


        this.lista.onItemLongClickListener = OnItemLongClickListener({parent, view, position, id ->
            var id_apagar = parent.tvItemId.text.toString().toInt()
            apagarCadastro(id_apagar)
            true
        })
    }

    private fun exibirCadastro(id_selecionado: Int) {
        val it = Intent(this, ExibePetActivity::class.java)
        it.putExtra("SELECIONADO", id_selecionado.toString())
        startActivity(it)
    }


    private fun apagarCadastro(id: Int) {
        val alertDilog = AlertDialog.Builder(this).create()
        alertDilog.setTitle("Excluir")
        alertDilog.setMessage("Deseja remover o pet ?")

        alertDilog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim", {
            dialogInterface, i ->
            Toast.makeText(applicationContext, "Pet Excluido", Toast.LENGTH_SHORT).show()
            dao.delete(id)
            atualizaLista()
        })

        alertDilog.setButton(AlertDialog.BUTTON_NEGATIVE, "Não", {
            dialogInterface, i ->
            Toast.makeText(applicationContext, "O pet não foi excluido", Toast.LENGTH_SHORT).show()
        })

        alertDilog.show()
    }









    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CADASTROU) {
            if (resultCode == Activity.RESULT_OK) {
                val msg = data?.getStringExtra("MENSAGEM")
                Log.i ("CADASTRO",msg)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        atualizaLista()
    }

    private fun CadastrarSuspeito(view: View) {
        val it = Intent(this, CadastroActivity::class.java)
        startActivityForResult(it, CADASTROU)
    }

    private fun PesquisarSuspeito(view: View) {
        val it = Intent(this, BuscarActivity::class.java)
        startActivity(it)
    }
    fun atualizaLista(){
        val adapter = ListaPetsAdapter(this, this.dao.select())

        this.lista.adapter = adapter
    }
}

