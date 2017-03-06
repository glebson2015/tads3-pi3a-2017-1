
package br.senac.tads.pi3a.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GLEBSON
 */
public class MetodoDao {
    public ArrayList listarPessoas() {
        Statement stmt = null;
        Connection conn = null;
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        Conexao conexao = new Conexao();
        String sql = "SELECT ID_CONTATO, NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO FROM TB_CONTATO";
        try {
            conn = conexao.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);
            DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

            while (resultados.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(resultados.getLong("ID_CONTATO"));
                pessoa.setNome(resultados.getString("NM_CONTATO"));
                pessoa.setDt_nascimento(resultados.getDate("DT_NASCIMENTO"));
                pessoa.setEmail(resultados.getString("VL_EMAIL"));
                pessoa.setTelefone(resultados.getString("VL_TELEFONE"));
                pessoa.setDt_cadastro(resultados.getTimestamp("DT_CADASTRO"));
                pessoas.add(pessoa);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return pessoas;
    }

    /*
    @params entra com uma objeto pessoa para cadastrar
    @return um booleano verdadeiro ou falso
    */
    public boolean cadastrarPessoas(Pessoa pessoa) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO)"
                + "VALUES(?,?,?,?,?)";
        try {
            conn = conexao.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setDate(2, pessoa.getDt_nascimento());
            stmt.setString(3, pessoa.getTelefone());
            stmt.setString(4, pessoa.getEmail());
            stmt.setTimestamp(5, pessoa.getDt_cadastro());
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
    /*
    @params entra com um id para buscar um contato
    @return um objeto pessoa
    */
    public Pessoa buscarPessoaPorId(int id){
        String sql = "SELECT * FROM TB_CONTATO WHERE ID_CONTATO = ?";
        PreparedStatement stmt = null;
        Connection conn = null;
        Pessoa pessoa = null;
        Conexao conexao = new Conexao();
        try{
            conn = conexao.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()){
                pessoa = new Pessoa();
                pessoa.setNome(resultado.getString("NM_CONTATO"));
                pessoa.setEmail(resultado.getString("VL_EMAIL"));
                pessoa.setTelefone(resultado.getString("VL_TELEFONE"));
                pessoa.setDt_nascimento(resultado.getDate("DT_NASCIMENTO"));
            }
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return pessoa;
    }
    //MÃ©todo que altera os dados do contato de acordo com o ID
    public boolean alteraPessoas(Pessoa pessoa) {

        Conexao conexao = new Conexao();
        String sql = "UPDATE TB_CONTATO SET (NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO)"
                + "VALUES(?,?,?,?,?) WHERE ID_CONTATO = ?";

        PreparedStatement stmt = null;
        Connection conn = null;

        try {

            conn = conexao.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setDate(2, pessoa.getDt_nascimento());
            stmt.setString(3, pessoa.getTelefone());
            stmt.setString(4, pessoa.getEmail());
            stmt.setTimestamp(5, pessoa.getDt_cadastro());
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        return false;
    }
    //MÃ©todo que remove o contato de acordo com seu ID
    public boolean removePessoas(int id) {

        String sql = "DELETE FROM TB_CONTATO WHERE ID_CONTATO = ?";
        Conexao conexao = new Conexao();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            
            conn = conexao.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            if (stmt.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        return false;
    }

}
