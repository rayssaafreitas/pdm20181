
package ifpb.pdm.pets

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.net.Uri
import android.os.Environment
import android.support.v4.content.FileProvider
import android.widget.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.location.LocationListener
import android.location.LocationManager

class CadastroActivity() : AppCompatActivity() {

    val FOTO = 1
    lateinit var caminhoFoto: String
    lateinit var arquivoFoto: File
    lateinit var foto: File
    lateinit var fotoURI: Uri
    lateinit var dao: PetDAO
    lateinit var btNovoCadastro: Button
    lateinit var etNome: EditText
    lateinit var etIdade: EditText
    lateinit var etInformes: EditText
    lateinit var ivfoto: ImageView
    lateinit var tvLocal: TextView
    lateinit var tvHora: TextView
    var coordenada: String = "desconhecido"
    lateinit var gpsManager: LocationManager
    lateinit var gpsListener: LocationListener


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        Views()

        this.gpsManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        this.gpsListener = GPSListener()
    }


    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()

        this.gpsManager.requestLocationUpdates((LocationManager.GPS_PROVIDER), 0, 0f, this.gpsListener)

        var latitude = gpsManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).latitude.toString()
        var longitude =gpsManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).longitude.toString()
        coordenada = latitude+" - "+longitude
        tvLocal.text = coordenada
        tvHora.text = ObterHoraAtual()
    }

    override fun onStop() {
        super.onStop()

        this.gpsManager.removeUpdates(this.gpsListener)
    }

    fun Obterfoto() {
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intentCamera.resolveActivity(getPackageManager()) != null) {
            arquivoFoto = createfotoFile()
            if (arquivoFoto != null) {
                fotoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", arquivoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoURI);
                startActivityForResult(intentCamera, FOTO)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ((resultCode == Activity.RESULT_OK) && (requestCode == FOTO)) {

            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val f = File(caminhoFoto)
            val contentUri = Uri.fromFile(f)
            mediaScanIntent.data = contentUri
            this.sendBroadcast(mediaScanIntent)
            ivfoto.setImageURI(contentUri)
        }
    }

    fun Cadastro(view: View) {
        var fotoURI = fotoURI.toString()
        var nome = etNome.text.toString()
        var idade = etIdade.text.toString().toFloat()
        var informes = etInformes.text.toString()
        var local = coordenada
        var data = ObterHoraAtual().toString()
        this.dao.insert(criarPet(nome, idade, informes, fotoURI, local, data))

        finish()
    }

    fun criarPet(nome: String, idade: Float, informe: String, foto: String, local: String, data: String): Pet {
        return Pet(nome, idade, informe, foto, local, data)
    }

    @Throws(IOException::class)
    private fun createfotoFile(): File {
        val timeStamp = SimpleDateFormat("ddMMyyy_HHmmss").format(Date())
        val fotoFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        foto = File.createTempFile(fotoFileName, ".jpg", storageDir)
        caminhoFoto = foto.getAbsolutePath()
        return foto
    }

    private fun ObterHoraAtual(): CharSequence? {
        var hora: Date = Date()
        var sdf: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
        var dataFormatada: String = sdf.format(hora);
        return dataFormatada
    }

    fun Views() {
        this.dao = PetDAO(this)
        this.etNome = findViewById(R.id.etCadastroNome)
        this.etIdade = findViewById(R.id.etCadastroIdade)
        this.etInformes = findViewById(R.id.etCadastroNome)
        this.tvLocal = findViewById(R.id.tvCadastroLocal)
        this.tvHora = findViewById(R.id.tvCadastroHora)
        this.ivfoto = findViewById(R.id.ivCadastroImagem)
        this.ivfoto.setOnClickListener({ Obterfoto() })
        this.btNovoCadastro = findViewById(R.id.btCadastroCadastro)
        this.btNovoCadastro.setOnClickListener({ Cadastro(it) })
    }


    inner class GPSListener : LocationListener {

        override fun onLocationChanged(location: Location?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}

