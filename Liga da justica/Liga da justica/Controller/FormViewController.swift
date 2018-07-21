//
//  ViewController.swift
//  Liga da justica
//
//  Created by IFPB on 21/07/2018.
//  Copyright © 2018 IFPB. All rights reserved.
//

import UIKit

class FormViewController: UIViewController {

    @IBOutlet weak var tfPersonagem: UITextField!
    
    
    @IBOutlet weak var tfImagem: UITextField!
    
    @IBOutlet weak var lbNota: UILabel!
    
    
    @IBOutlet weak var stNota: UIStepper!
    
    @IBOutlet weak var swPoder: UISwitch!
    

    var index: Int!
    var cadastro: Cadastro!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let delegate = UIApplication.shared.delegate as! AppDelegate
        self.cadastro = delegate.cadastro
    }
    
    @IBAction func definirNota(_ sender: Any) {
        let nota = Int(self.stNota.value)
        self.lbNota.text = String(nota)
    }
    
    @IBAction func salvar(_ sender: Any) {
        let nome = self.tfPersonagem.text!
        let image = self.tfImagem.text!
        let nota = Int(self.stNota.value)
        let poder = self.swPoder.isOn
        
        let personagem = Personagem(nome: nome, image: image, nota: nota, poder: poder)
        
        if (self.index != nil){
            self.cadastro.update(index: self.index, personagem: personagem)
        }else{
            self.cadastro.add(personagem: personagem)
        }
        
        self.navigationController?.popViewController(animated: true)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        
        if (self.index != nil){
            let personagem = self.cadastro.get(index: self.index)
            self.tfPersonagem.text = personagem.nome
            self.tfImagem.text = personagem.image
            self.lbNota.text = String(personagem.nota)
            self.stNota.value = Double(personagem.nota)
            self.swPoder.isOn = personagem.poder
            
            let url = URL(string: personagem.image!)
            let data = try? Data(contentsOf: url!)
            //if let imageData = data {
              //  self.tfImagem.image = UIImage(data: imageData)
            }
        }
        
        //self.tfPersonagem.becomeFirstResponder()
    }
    
//}
