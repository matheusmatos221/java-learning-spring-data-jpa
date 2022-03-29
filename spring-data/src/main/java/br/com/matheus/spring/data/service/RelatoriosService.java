package br.com.matheus.spring.data.service;

import br.com.matheus.spring.data.orm.Funcionario;
import br.com.matheus.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {
    private Boolean system = true;
    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        while (system){
            System.out.println("Qual ação você deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario nome");

            int action = scanner.nextInt();

            switch (action){
                case 1 -> buscaFuncionarioNome(scanner);
                default -> system = false;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner){
        System.out.println("Qual nome deseja pesquisar");
        String nome = scanner.next();
        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);
    }

}
