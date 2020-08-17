package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Digite as informações do contrato");
		System.out.println("-----------------------------------------------");
		
		System.out.print("Número: ");
		Integer number = sc.nextInt();
		
		System.out.print("Data (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.next());
		
		System.out.print("Valor do contrato: ");
		double totalValue = sc.nextDouble();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("Digite o número de parcelas: ");
		int installments = sc.nextInt();
		
		ContractService cs = new ContractService(new PaypalService());

		cs.processContract(contract, installments);
		
		System.out.println("-----------------------------------------------");
		System.out.println("PARCELAS");
		for (Installment it : contract.getInstallments()) {
			System.out.println(it);
		}
		sc.close();
	}

}
