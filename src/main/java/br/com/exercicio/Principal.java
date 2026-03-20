package br.com.exercicio;

import static spark.Spark.*;

public class Principal {

    public static void main(String[] args) {
        // Define a porta do servidor
        port(4567);

        PessoaDAO dao = new PessoaDAO();

        System.out.println("Servidor iniciado em http://localhost:4567/formulario");

        // ROTA 1: Mostra o formulário no navegador
        get("/formulario", (req, res) -> {
            return "<html><body>" +
                   "<h2>Cadastro de Pessoa</h2>" +
                   "<form action='/salvar' method='post'>" +
                   "  Nome: <input type='text' name='nome'><br><br>" +
                   "  Documento: <input type='text' name='doc'><br><br>" +
                   "  Tipo: <select name='tipo'>" +
                   "    <option value='PF'>Pessoa Física</option>" +
                   "    <option value='PJ'>Pessoa Jurídica</option>" +
                   "  </select><br><br>" +
                   "  <input type='submit' value='Salvar no Banco'>" +
                   "</form></body></html>";
        });

        // ROTA 2: Recebe os dados e salva no banco
        post("/salvar", (req, res) -> {
            String nome = req.queryParams("nome");
            String doc = req.queryParams("doc");
            String tipo = req.queryParams("tipo");

            if ("PF".equals(tipo)) {
                dao.salvar(new PessoaFisica(nome, doc));
            } else {
                dao.salvar(new PessoaJuridica(nome, doc));
            }

            return "<h1>Sucesso!</h1><p>" + nome + " foi salvo.</p><a href='/formulario'>Voltar</a>";
        });
    }
}