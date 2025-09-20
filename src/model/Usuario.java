package model;

public class Usuario {
    private int id;
    private String nome, cpf, email, cargo, login, senha;
    private boolean admin;

    public Usuario(int id, String nome, String cpf, String email, String cargo, String login, String senha, boolean admin) {
        this.id = id; this.nome = nome; this.cpf = cpf; this.email = email; 
        this.cargo = cargo; this.login = login; this.senha = senha; this.admin = admin;
    }

    public Usuario(String nome, String cpf, String email, String cargo, String login, String senha, boolean admin) {
        this(0, nome, cpf, email, cargo, login, senha, admin);
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }
}
