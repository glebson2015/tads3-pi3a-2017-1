package br.senac.tads.pi3a.agenda;

import java.sql.Timestamp;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author glebson.lsilva1
 */
public class Agenda {

   
         

    public static String inverterDataBanco(String data) {
        String[] split = data.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            sb.append(split[i]);
            if (i != 0) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    public static void mostrarPessoa(Pessoa pessoa) {
        System.out.println("");
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println("Nome            => " + pessoa.getNome());
        System.out.println("Telefone        => " + pessoa.getTelefone());
        System.out.println("E-mail          => " + pessoa.getEmail());
        System.out.println("Data Nascimento => " + pessoa.getData_nascimento());
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println("");
    }

    public static Date getData(String data) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(true);
        Date dt = null;

        try {
            String dataInvertida = inverterDataBanco(data);
            dt = df.parse(dataInvertida);

        } catch (ParseException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    public static void listarPessoas() {
        ArrayList<Pessoa> pessoas = new MetodoDao().listarPessoas();
        for (Pessoa pessoa : pessoas) {
            System.out.println("*****************");
            System.out.println("ID            => " + pessoa.getId());
            System.out.println("Nome          => " + pessoa.getNome());
            System.out.println("Email         => " + pessoa.getEmail());
            System.out.println("Telefone      => " + pessoa.getTelefone());
            System.out.println("DT NASCIMENTO => " + pessoa.getData_nascimento());
            System.out.println("DT_CADASTRO   => " + pessoa.getData_cadastro());
        }
    }

    public static void main(String[] args) {
        Pessoa pessoa = null;
        MetodoDao metodo = new MetodoDao();
        //VariÃ¡vel de opÃ§Ã£o
        int opcao, id;

        //Scanner - Entrada de dados do usuÃ¡rio
        Scanner input = new Scanner(System.in);

        System.out.println("******************************");
        System.out.println("*     AGENDA DE CONTATOS     *");
        System.out.println("******************************");

        //Do while - Controla o menu da agenda por meio de condicionais if
        do {

            System.out.println("Escolha uma das opÃ§Ãµes do menu abaixo: \n1 - Cadastrar Contato"
                    + "\n2 - Alterar Contato\n3 - Mostrar Contato\n4 - Excluir Contatos\n5 - Listar todos os Contatos\n6 - Sair da Agenda");
            opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    pessoa = new Pessoa();
                    System.out.println("Digite um nome");
                    String nome = input.next();
                    pessoa.setNome(nome);
                    System.out.println("Digite um email");
                    String email = input.next();
                    pessoa.setEmail(email);
                    System.out.println("Digite um telefone");
                    String telefone = input.next();
                    pessoa.setTelefone(telefone);
                    System.out.println("Digite uma data no formato dd/mm/aaaa");
                    String data = input.next();
                    java.util.Date dt_nasc = getData(data);
                    java.sql.Date dt_nascimento = new java.sql.Date(dt_nasc.getTime());
                    java.util.Date dt_cad = new java.util.Date();
                    pessoa.setData_cadastro(new Timestamp(dt_cad.getTime());
                    pessoa.setData_nascimento(dt_nascimento);
                    if (metodo.cadastrarPessoas(pessoa)) {
                        System.out.println("Pessoa Cadastrado com Sucesso.");
                    } else {
                        System.out.println("Algo de errado, tentennovamente");
                    }
                    break;
                case 2:
                    listarPessoas();
                    System.out.println("Escolha uma pessoa pelo ID para alterar");
                    id = input.nextInt();
                    pessoa = metodo.buscarPessoaPorId(id);
                    System.out.println("VocÃª escolheu " + pessoa.getNome());
                    System.out.println("Altere o nome: ");
                    nome = input.next();
                    System.out.println("Altere o email: ");
                    email = input.next();
                    System.out.println("Altere o telefone");
                    telefone = input.next();
                    data = input.next();
                    dt_nasc = getData(data);
                    dt_nascimento = new java.sql.Date(dt_nasc.getTime());
                    dt_cad = new java.util.Date();
                    pessoa.setData_cadastro(new Timestamp(dt_cad.getTime()));
                    pessoa.setData_nascimento(dt_nascimento);
                    break;
                case 3:
                    listarPessoas();
                    System.out.println("Escolha uma pessoa pelo ID para mostrar");
                    id = input.nextInt();
                    pessoa = metodo.buscarPessoaPorId(id);
                    if (pessoa != null) {
                        mostrarPessoa(pessoa);
                    } else {
                        System.out.println("Contato nÃ£o existe.");
                    }
                    break;
                case 4:
                    listarPessoas();
                    System.out.println("Escolha uma pessoa pelo ID para remover");
                    id = input.nextInt();
                    if (metodo.removePessoas(id)) {
                        System.out.println("Contato removido com sucesso.");
                    } else {
                        System.out.println("NÃ£o foi possÃ­vel remover.");
                    }
                    break;
                case 5:
                    listarPessoas();
                    break;
                case 6:
                    System.out.println("Saindo do Sistema!!");
                    break;
            }

        } while (opcao != 6);

    }

}


