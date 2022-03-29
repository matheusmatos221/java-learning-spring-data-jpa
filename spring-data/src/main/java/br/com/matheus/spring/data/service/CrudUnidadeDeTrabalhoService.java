package br.com.matheus.spring.data.service;

import br.com.matheus.spring.data.orm.Cargo;
import br.com.matheus.spring.data.orm.UnidadeDeTrabalho;
import br.com.matheus.spring.data.repository.UnidadeDeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeDeTrabalhoService {
    private boolean system = true;
    private UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;

    public CrudUnidadeDeTrabalhoService(UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
        this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao de unidade deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action) {
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

    private void salvar(Scanner scanner) {
        System.out.println("Descricao da Unidade");
        String descricao = scanner.next();
        System.out.println("Endereco da Unidade");
        String endereco = scanner.next();

        UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
        unidadeDeTrabalho.setDescricao(descricao);
        unidadeDeTrabalho.setEndereco(endereco);

        unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Id da Unidade");
        Integer id = scanner.nextInt();
        System.out.println("Descricao da Unidade");
        String descricao = scanner.next();
        System.out.println("Endereco da Unidade");
        String endereco = scanner.next();

        UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
        unidadeDeTrabalho.setId(id);
        unidadeDeTrabalho.setDescricao(descricao);
        unidadeDeTrabalho.setEndereco(endereco);

        unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
        System.out.println("Atualizado");
    }

    private void visualizar() {
        Iterable<UnidadeDeTrabalho> unidades = unidadeDeTrabalhoRepository.findAll();
        unidades.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id");
        Integer id = scanner.nextInt();
        unidadeDeTrabalhoRepository.deleteById(id);
        System.out.println("Deletado");
    }
}