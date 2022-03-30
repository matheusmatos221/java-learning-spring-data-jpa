package br.com.matheus.spring.data.service;

import br.com.matheus.spring.data.orm.Cargo;
import br.com.matheus.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {
    private final CargoRepository cargoRepository;
    private boolean system = true;

    public CrudCargoService(CargoRepository cargoRepository){
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner){
        system = true;
        while(system){
            System.out.println("Qual acao de cargo deseja executar?");
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
        System.out.println("Descricao do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Digite o ID do Cargo");
        Integer id = scanner.nextInt();
        System.out.println("Digite a nova descricao");
        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
        System.out.println("Atualizado!");
    }

    private void visualizar(){
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(System.out::println);
    }


    public void deletar(Scanner scanner){
        System.out.println("Id");
        Integer id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Deletado");
    }

}
