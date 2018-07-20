package ifpb.pdm.pets

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class ExibePetActivity : AppCompatActivity() {

    lateinit var dao: PetDAO
    lateinit var ivExibeFoto : ImageView
    lateinit var tvExibeNome : TextView
    lateinit var tvExibeData : TextView
    lateinit var tvExibeLoca : TextView
    lateinit var tvExibeInfo : TextView
    lateinit var btFechar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibe_pet)

        this.ivExibeFoto = findViewById(R.id.ivConsultaImagem)
        this.tvExibeNome = findViewById(R.id.tvConsultaNome)
        this.tvExibeData = findViewById(R.id.tvConsultaData)
        this.tvExibeLoca = findViewById(R.id.tvConsultaLocal)
        this.tvExibeInfo = findViewById(R.id.tvConsultaInformacoes)
        this.btFechar = findViewById(R.id.btConsultaVoltar)

        btFechar.setOnClickListener({
            finish()
        })

        this.dao = PetDAO(this)
        val it = intent
        val id = it.getStringExtra("SELECIONADO").toInt()

        exibirPet(id)

        //  Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }

    private fun exibirPet(id: Int) {

        var pet: Pet = dao.buscarPorId(id)

        var imagemURI: Uri =  Uri.parse(pet.imagem)

        this.ivExibeFoto.setImageURI(imagemURI)
        this.tvExibeNome.text = pet.nome
        this.tvExibeData.text = pet.data
        this.tvExibeLoca.text = pet.local
        this.tvExibeInfo.text = pet.informe
    }

}
