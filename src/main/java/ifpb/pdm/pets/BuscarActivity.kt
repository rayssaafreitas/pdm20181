package ifpb.pdm.pets

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import ifpb.pdm.pets.R

class BuscarActivity : AppCompatActivity() {

    lateinit var etBuscarPalavra : EditText
    lateinit var btBuscar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        this.etBuscarPalavra = findViewById(R.id.etPesquisaArgumento)
        this.btBuscar = findViewById(R.id.btPesquisaBuscar)

        btBuscar.setOnClickListener({ Buscar() })
    }

    private fun Buscar() {
        finish()
    }
}
