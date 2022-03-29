package br.com.matheus.spring.data;

import br.com.matheus.spring.data.service.CrudCargoService;
import br.com.matheus.spring.data.service.CrudFuncionarioService;
import br.com.matheus.spring.data.service.CrudUnidadeDeTrabalhoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService;

	private boolean system = true;

	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService,
								 CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService)
	{
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeDeTrabalhoService = unidadeDeTrabalhoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while(system){
			System.out.println("Qual ação você quer executar? ");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de Trabalho");

			int action = scanner.nextInt();

			switch (action) {
				case 1 -> cargoService.inicial(scanner);
				case 2 -> funcionarioService.inicial(scanner);
				case 3 -> unidadeDeTrabalhoService.inicial(scanner);
				default -> system = false;
			}
		}
	}
}
