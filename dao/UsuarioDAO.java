package dao;

import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final String URL = "jdbc:mysql://localhost:3306/sistema";
    private final String USER = "root";          // seu usuário MySQL
    private final String PASSWORD = "Atacante10.";  // sua senha MySQL

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void adicionar(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (nome_completo, cpf, email, cargo, login, senha, admin) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getCpf());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getCargo());
            stmt.setString(5, u.getLogin());
            stmt.setString(6, u.getSenha());
            stmt.setBoolean(7, u.isAdmin());
            stmt.executeUpdate();
        }
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                lista.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome_completo"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("cargo"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getBoolean("admin")
                ));
            }
        }
        return lista;
    }

    public boolean atualizar(Usuario u) throws SQLException {
        String sql = "UPDATE usuarios SET nome_completo=?, cpf=?, email=?, cargo=?, login=?, senha=? WHERE id=?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getCpf());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getCargo());
            stmt.setString(5, u.getLogin());
            stmt.setString(6, u.getSenha());
            stmt.setInt(7, u.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public Usuario loginAdmin(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE login=? AND senha=? AND admin=TRUE";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login); stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return new Usuario(
                    rs.getInt("id"), rs.getString("nome_completo"),
                    rs.getString("cpf"), rs.getString("email"),
                    rs.getString("cargo"), rs.getString("login"),
                    rs.getString("senha"), rs.getBoolean("admin")
                );
            }
        }
        return null;
    }
}

