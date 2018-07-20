package ifpb.pdm.pets



class Pet {
    var id: Int
    var nome: String
    var idade: Float
    var informe: String
    var imagem: String
    var local: String
    var data: String

    constructor (id: Int, nome: String, idade: Float, informe: String, imagem: String, local:String, data: String){
        this.id = id
        this.nome = nome
        this.idade = idade
        this.informe = informe
        this.imagem = imagem
        this.local = local
        this.data = data
    }

    constructor (nome: String, idade: Float, informe: String, imagem: String, local: String, data:String){
        this.id = -1
        this.nome = nome
        this.idade = idade
        this.informe = informe
        this.imagem = imagem
        this.local = local
        this.data = data
    }

    override fun toString(): String {
        return "${id} - ${nome} - ${idade} - ${informe} - ${imagem} - ${local} - ${data}"
    }


}