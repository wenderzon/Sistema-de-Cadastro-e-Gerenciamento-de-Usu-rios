# Sistema-de-Cadastro-e-Gerenciamento-de-Usu-rios
# 🖥️ Sistema de Cadastro de Usuários em Java

Este projeto é um sistema de cadastro de usuários desenvolvido em **Java + MySQL**, com funcionalidades avançadas de segurança, interatividade e CRUD completo. Foi estruturado para rodar no **VSCode**, utilizando **MySQL Connector/J 9.4.0**.

---

## 🚀 Funcionalidades

- 🔑 **Login de administrador** antes de acessar o sistema  
- 🗄️ **CRUD completo** (criar, listar, atualizar e remover usuários)  
- 🧾 **Tabela formatada** ao listar usuários, fácil leitura  
- 🔒 **Validação de CPF** e **senhas criptografadas com SHA-256**  
- 🎨 **Sistema interativo com cores** no console  
- ⏳ **Mensagens animadas de carregamento**  

---

## 📂 Estrutura de Pastas

ProjetoUsuarios/
│
├─ lib/
│ └─ mysql-connector-j-9.4.0.jar # Driver do MySQL
├─ src/
│ ├─ Main.java # Classe principal
│ ├─ model/
│ │ └─ Usuario.java # Classe modelo da entidade
│ ├─ dao/
│ │ └─ UsuarioDAO.java # Classe de acesso ao banco
│ └─ utils/
│ └─ Validacoes.java # Validações e utilitários
└─ bin/ # Arquivos .class compilados


---

## 🛠️ Pré-requisitos

- ☕ **Java JDK 17+** instalado  
- 🐬 **MySQL Server** rodando localmente  
- 📦 **MySQL Connector/J 9.4.0** (adicionado em `/lib`)  
- 🖥️ **VSCode** configurado com extensão de Java  

---

## 🗄️ Banco de Dados

Crie o banco e a tabela antes de rodar o sistema:  

```sql
CREATE DATABASE sistema_usuarios;

USE sistema_usuarios;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(100) NOT NULL,![Uploading c7ee28bc-ad36-4a82-980f-692adbbb22b5.png…]()

    cargo VARCHAR(50),
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(256) NOT NULL
);


[README.md](https://github.com/user-attachments/files/22447651/README.md)
