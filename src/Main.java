import dao.UsuarioDAO;
import model.Usuario;
import utils.Validacoes;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final UsuarioDAO dao = new UsuarioDAO();

    private static final String RED="\u001B[31m", GREEN="\u001B[32m", BLUE="\u001B[34m", YELLOW="\u001B[33m", RESET="\u001B[0m";

    public static void main(String[] args) throws Exception {
        if(!loginAdmin()) return;

        while(true){
            System.out.println(BLUE + "\n=== SISTEMA DE USUÁRIOS ===" + RESET);
            System.out.println(YELLOW + "1 - Adicionar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Deletar usuário");
            System.out.println("0 - Sair" + RESET);
            System.out.print("Escolha: ");
            int opc = sc.nextInt(); sc.nextLine();

            switch(opc){
                case 1 -> adicionarUsuario();
                case 2 -> listarUsuarios();
                case 3 -> atualizarUsuario();
                case 4 -> deletarUsuario();
                case 0 -> { System.out.println(GREEN + "Saindo..." + RESET); return; }
                default -> System.out.println(RED + "Opção inválida!" + RESET);
            }
        }
    }

    private static boolean loginAdmin() throws Exception {
        System.out.println(BLUE + "=== LOGIN ADMIN ===" + RESET);
        System.out.print("Login: "); String login=sc.nextLine();
        System.out.print("Senha: "); String senha=sc.nextLine();
        senha = Validacoes.hashSHA256(senha);

        Usuario admin = dao.loginAdmin(login, senha);
        if(admin!=null){
            System.out.println(GREEN + "Login bem-sucedido!" + RESET);
            Validacoes.animacaoCarregamento();
            return true;
        } else {
            System.out.println(RED + "Login inválido!" + RESET);
            return false;
        }
    }

    private static void adicionarUsuario() throws Exception {
        System.out.print("Nome: "); String nome=sc.nextLine();
        String cpf;
        while(true){
            System.out.print("CPF: "); cpf=sc.nextLine();
            if(Validacoes.validarCPF(cpf)) break;
            System.out.println(RED + "CPF inválido!" + RESET);
        }
        System.out.print("Email: "); String email=sc.nextLine();
        System.out.print("Cargo: "); String cargo=sc.nextLine();
        System.out.print("Login: "); String login=sc.nextLine();
        System.out.print("Senha: "); String senha=sc.nextLine();
        senha = Validacoes.hashSHA256(senha);

        dao.adicionar(new Usuario(nome, cpf, email, cargo, login, senha, false));
        System.out.println(GREEN + "Usuário adicionado!" + RESET);
        Validacoes.animacaoCarregamento();
    }

    private static void listarUsuarios() throws Exception {
        List<Usuario> lista = dao.listar();
        System.out.printf("%-3s %-20s %-15s %-25s %-15s %-10s %-5s%n","ID","Nome","CPF","Email","Cargo","Login","Admin");
        for(Usuario u: lista){
            System.out.printf("%-3d %-20s %-15s %-25s %-15s %-10s %-5s%n",
                u.getId(), u.getNome(), u.getCpf(), u.getEmail(), u.getCargo(), u.getLogin(), u.isAdmin()?"SIM":"NAO");
        }
        Validacoes.animacaoCarregamento();
    }

    private static void atualizarUsuario() throws Exception {
        System.out.print("ID do usuário: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Novo nome (vazio p/ manter): "); String nome=sc.nextLine();
        System.out.print("Novo CPF: "); String cpf=sc.nextLine();
        if(!cpf.isEmpty() && !Validacoes.validarCPF(cpf)) { System.out.println(RED+"CPF inválido!"+RESET); return; }
        System.out.print("Novo email: "); String email=sc.nextLine();
        System.out.print("Novo cargo: "); String cargo=sc.nextLine();
        System.out.print("Novo login: "); String login=sc.nextLine();
        System.out.print("Nova senha: "); String senha=sc.nextLine();
        if(!senha.isEmpty()) senha=Validacoes.hashSHA256(senha);

        Usuario u = new Usuario(id, 
            nome.isEmpty()?null:nome,
            cpf.isEmpty()?null:cpf,
            email.isEmpty()?null:email,
            cargo.isEmpty()?null:cargo,
            login.isEmpty()?null:login,
            senha.isEmpty()?null:senha,
            false);

        if(dao.atualizar(u)) System.out.println(GREEN+"Usuário atualizado!"+RESET);
        else System.out.println(RED+"Usuário não encontrado!"+RESET);
        Validacoes.animacaoCarregamento();
    }

    private static void deletarUsuario() throws Exception {
        System.out.print("ID do usuário: "); int id=sc.nextInt(); sc.nextLine();
        if(dao.deletar(id)) System.out.println(GREEN+"Usuário deletado!"+RESET);
        else System.out.println(RED+"Usuário não encontrado!"+RESET);
        Validacoes.animacaoCarregamento();
    }
}
