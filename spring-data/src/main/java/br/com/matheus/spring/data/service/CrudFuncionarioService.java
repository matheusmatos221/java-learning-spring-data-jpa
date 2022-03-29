package br.com.matheus.spring.data.service;

import br.com.matheus.spring.data.orm.Funcionario;
import br.com.matheus.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudFuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private boolean system = true;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        while(system){
            System.out.println("Qual acao de funcionario deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch(action){
                case 0:
                    system = false;
                    break;
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    System.out.println("Você selecionou uma opção inválida!");
                    system = false;
                    break;
            }
        }
    }
    private void salvar(Scanner scanner){
        System.out.println("Nome do funcionario");
        String nome = scanner.next();
        System.out.println("Cpf do funcionario");
        String cpf = scanner.next();
        System.out.println("Salario do funcionario");
        double salario = scanner.nextDouble();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Id do funcionario");
        Integer id = scanner.nextInt();
        System.out.println("Nome do funcionario");
        String nome = scanner.next();
        System.out.println("Cpf do funcionario");
        String cpf = scanner.next();
        System.out.println("Salario do funcionario");
        double salario = scanner.nextDouble();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    private void visualizar(){
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        funcionarios.forEach(System.out::println);
    }


    private void deletar(Scanner scanner){
        System.out.println("Id");
        Integer id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Deletado");
    }


}
