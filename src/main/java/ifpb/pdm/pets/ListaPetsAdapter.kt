package ifpb.pdm.pets

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class ListaPetsAdapter (private var activity: Activity, private var pets: List<Pet>): BaseAdapter() {

    init {
        this.activity = activity
        this.pets = pets
    }

    private class ViewHolder(row: View?) {
        var id: TextView? = null
        var nome: TextView? = null
        var idade: TextView? = null
        var foto: ImageView? = null
        var data: TextView? = null
        var local: TextView? = null

        init {
            this.id  = row?.findViewById<TextView>(R.id.tvItemId)
            this.nome  = row?.findViewById<TextView>(R.id.tvItemNome)
            this.idade = row?.findViewById<TextView>(R.id.tvItemIdade)
            this.foto  = row?.findViewById<ImageView>(R.id.ivItemFoto)
            this.data  = row?.findViewById<TextView>(R.id.tvItemData)
            this.local = row?.findViewById<TextView>(R.id.tvItemLocal)

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_pet, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }


        var lista_pet = pets[position]
        viewHolder.id?.text = lista_pet.id.toString()
        viewHolder.nome?.text = lista_pet.nome
        viewHolder.idade?.text = lista_pet.idade.toString()
        viewHolder.foto?.setImageURI(Uri.parse(lista_pet.imagem))
        viewHolder.data?.text = lista_pet.data
        viewHolder.local?.text = lista_pet.local

        return view as View
    }

    override fun getItem(i: Int): Pet {
        return pets[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return pets.size
    }


}


