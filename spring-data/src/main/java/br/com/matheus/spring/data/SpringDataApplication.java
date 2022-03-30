package br.com.matheus.spring.data;

import br.com.matheus.spring.data.service.CrudCargoService;
import br.com.matheus.spring.data.service.CrudFuncionarioService;
import br.com.matheus.spring.data.service.CrudUnidadeDeTrabalhoService;
import br.com.matheus.spring.data.service.RelatoriosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@EnableJpaRepositories
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService;
	private final RelatoriosService relatoriosService;

	private boolean system = true;

	public SpringDataApplication(CrudCargoService cargoService,
								 CrudFuncionarioService funcionarioService,
								 CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService,
								 RelatoriosService relatoriosService) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeDeTrabalhoService = unidadeDeTrabalhoService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		system = true;
		while(system){
			System.out.println("Qual ação você quer executar? ");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatorios");

			int action = scanner.nextInt();

			switch (action) {
				case 1 -> cargoService.inicial(scanner);
				case 2 -> funcionarioService.inicial(scanner);
				case 3 -> unidadeDeTrabalhoService.inicial(scanner);
				case 4 -> relatoriosService.inicial(scanner);
				default -> system = false;
			}
		}
	}
}
