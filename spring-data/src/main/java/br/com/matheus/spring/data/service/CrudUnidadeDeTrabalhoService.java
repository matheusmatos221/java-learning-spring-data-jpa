package br.com.matheus.spring.data.service;

import br.com.matheus.spring.data.orm.UnidadeTrabalho;
import br.com.matheus.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeDeTrabalhoService {
    private boolean system = true;
    private UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeDeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {
        system = true;
        while (system) {
            System.out.println("Qual acao de unidade deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar(scanner);
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

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Id da Unidade");
        Integer id = scanner.nextInt();
        System.out.println("Descricao da Unidade");
        String descricao = scanner.next();
        System.out.println("Endereco da Unidade");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Atualizado");
    }

    private void visualizar(Scanner scanner) {
        System.out.println("Qual pagina voce deseja visualizar?");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.unsorted());

        Page<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll(pageable);

        System.out.println(unidades);
        unidades.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id");
        Integer id = scanner.nextInt();
        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Deletado");
    }
}