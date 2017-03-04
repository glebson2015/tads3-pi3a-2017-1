package br.senac.tads.pi3a.agenda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glebson.lsilva1
 */
public class Agenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Conexao con = Conexao();
        
        if(con != null){
        
            System.out.println("Ok");
        
        }
            System.out.println("Falha");
        
    }

    private static Conexao Conexao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
