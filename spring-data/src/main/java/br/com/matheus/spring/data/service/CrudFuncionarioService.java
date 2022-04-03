package br.com.matheus.spring.data.service;

import br.com.matheus.spring.data.orm.Cargo;
import br.com.matheus.spring.data.orm.Funcionario;
import br.com.matheus.spring.data.orm.UnidadeTrabalho;
import br.com.matheus.spring.data.repository.CargoRepository;
import br.com.matheus.spring.data.repository.FuncionarioRepository;
import br.com.matheus.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {
    private boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final CargoRepository cargoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
                                  CargoRepository cargoRepository,
                                  UnidadeTrabalhoRepository unidadeTrabalhoRepository){
        this.cargoRepository = cargoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner){
        system = true;
        while(system){
            System.out.println("Qual acao de funcionario deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1 -> salvar(scanner);
                case 2 -> atualizar(scanner);
                case 3 -> visualizar(scanner);
                case 4 -> deletar(scanner);
                default -> {
                    System.out.println("Você selecionou uma opção inválida!");
                    system = false;
                }
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
        System.out.println("Data de Contratacao");
        String dataContratacao = scanner.next();
        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        List<UnidadeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));

        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        cargo.ifPresent(funcionario::setCargo);
        funcionario.setUnidadeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    private List<UnidadeTrabalho> unidade(Scanner scanner){
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while(isTrue){
            System.out.println("Digite o unidadeId (para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0){
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidade.ifPresent(unidades::add);
            }else{
                isTrue = false;
            }
        }
        return unidades;
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
        System.out.println("Data de Contratacao");
        String dataContratacao = scanner.next();
        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        List<UnidadeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));

        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        cargo.ifPresent(funcionario::setCargo);
        funcionario.setUnidadeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Alterado");
    }

    private void visualizar(Scanner scanner){
        System.out.println("Qual pagina voce deseja visualizar?");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

        System.out.println(funcionarios);
        System.out.println("Pagina atual: " + funcionarios.getNumber());
        System.out.println("Total elemento: "+ funcionarios.getTotalElements());

        funcionarios.forEach(System.out::println);
    }


    private void deletar(Scanner scanner){
        System.out.println("Id");
        Integer id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Deletado");
    }


}
