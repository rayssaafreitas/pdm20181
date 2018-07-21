//
//  Personagem.swift
//  Liga da justica
//
//  Created by IFPB on 21/07/2018.
//  Copyright © 2018 IFPB. All rights reserved.
//

import Foundation
class Personagem: NSObject, NSCoding {
    var nome: String!
    var image: String!
    var nota: Int!
    var poder: Bool!
    
    override var description: String{
        return self.nome!
    }
    
    // construtor memória
    init(nome: String, image: String, nota: Int, poder: Bool) {
        self.nome = nome
        self.image = image
        self.nota = nota
        self.poder = poder
    }
    
    // construtor arquivo
    required init?(coder aDecoder: NSCoder) {
        self.nome = aDecoder.decodeObject(forKey: "nome") as! String
        self.image = aDecoder.decodeObject(forKey: "image") as! String
        self.nota = aDecoder.decodeObject(forKey: "nota") as! Int
        self.poder = aDecoder.decodeBool(forKey: "poder") 
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(self.nome, forKey: "nome")
        aCoder.encode(self.image, forKey: "imagem")
        aCoder.encode(self.nota, forKey: "nota")
        aCoder.encode(self.poder, forKey: "poder")
    }
}
