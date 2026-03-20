package br.com.exercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PessoaDAO {
    private String url = "jdbc:postgresql://localhost:5432/exercicio_db";
    private String user = "postgres";
    private String password = "admin"; 

    public void salvar(Pessoa p) {
        String sql = "INSERT INTO pessoas (nome, documento, tipo) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, p.getNome());

            if (p instanceof PessoaFisica) {
                stmt.setString(2, ((PessoaFisica) p).getCpf());
                stmt.setString(3, "PF");
            } else if (p instanceof PessoaJuridica) {
                stmt.setString(2, ((PessoaJuridica) p).getCnpj());
                stmt.setString(3, "PJ");
            }

            stmt.executeUpdate();
            System.out.println("Sucesso: " + p.getNome() + " salvo!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}