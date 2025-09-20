package utils;

import java.security.MessageDigest;

public class Validacoes {

    public static boolean validarCPF(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public static String hashSHA256(String senha) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(senha.getBytes());
        StringBuilder sb = new StringBuilder();
        for(byte b: hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static void animacaoCarregamento() {
        System.out.print("\u001B[33mCarregando");
        for(int i=0;i<3;i++){
            try{ Thread.sleep(400); } catch(Exception e){}
            System.out.print(".");
        }
        System.out.println("\u001B[0m");
    }
}
