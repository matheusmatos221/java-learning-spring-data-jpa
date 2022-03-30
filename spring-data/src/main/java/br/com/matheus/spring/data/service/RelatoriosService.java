package br.com.matheus.spring.data.service;

import br.com.matheus.spring.data.orm.Funcionario;
import br.com.matheus.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {
    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        system = true;
        while (system){
            System.out.println("Qual ação você deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario nome");
            System.out.println("2 - Busca funcionario nome, data contratacao e salario maior");

            int action = scanner.nextInt();

            switch (action){
                case 1 -> buscaFuncionarioNome(scanner);
                case 2 -> buscaFuncionarioNomeSalarioMaiorData(scanner);
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

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner){
        System.out.println("Qual nome deseja pesquisar");
        String nome = scanner.next();

        System.out.println("Qual data contratacao deseja pesquisar");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Qual salario deseja pesquisar");
        double salario = scanner.nextDouble();

        List<Funcionario> list = funcionarioRepository
                .findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        list.forEach(System.out::println);
    }

}
