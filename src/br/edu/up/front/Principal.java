package br.edu.up.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.edu.persistencia.ClientePersistencia;
import br.edu.persistencia.ProdutoPersistencia;
import br.edu.persistencia.VendaPersistencia;
import br.edu.persistencia.VendedorPersistencia;
import br.edu.up.entidades.Cliente;
import br.edu.up.entidades.ItemVenda;
import br.edu.up.entidades.Produto;
import br.edu.up.entidades.Venda;
import br.edu.up.entidades.Vendedor;
import br.edu.up.negocio.ItemVendaNegocio;
import br.edu.up.negocio.Negocio;
import br.edu.up.negocio.ProdutoNegocio;

public class Principal {

	public static void main(String[] args) {
		
		int opc;
		Cliente objCliente;
		Vendedor objVendedor;
		Produto objProduto;
		ItemVenda objItem;
		boolean dataInvalida = true;
		String dataVenda, resposta;
		SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
		Date dataDate = null;
		float precoVenda;
		do {
			System.out.println("\n\n**** MENU PRINCIPAL ***");
			System.out.println("1 - Cadastrar cliente");
			System.out.println("2 - Cadastrar vendedor");
			System.out.println("3 - Cadastrar produto");
			System.out.println("4 - Registrar venda");
			System.out.println("5 - Consultar venda");
			System.out.println("6 - Sair");
			opc = Console.readInt("Informe a opção: ");
			
			switch(opc) {
				case 1:
					objCliente = new Cliente();
					objCliente.setCpf(Console.readString("\n\nInforme o CPF do cliente: "));
					if(Negocio.isCPF(objCliente.getCpf())) {
						if(ClientePersistencia.procurar(objCliente) == null) {
							objCliente.setNome(Console.readString("Informe o nome: "));
							ClientePersistencia.incluir(objCliente);
							System.out.println("\n\nCliente cadastrado...");
						}
						else {
							System.out.println("\n\nCliente já cadastrado...");
						}
					}
					else {
						System.out.println("\n\nCPF inválido...");
					}
					break;
				case 2:
					objVendedor = new Vendedor();
					objVendedor.setCpf(Console.readString("\n\nInforme o CPF do vendedor: "));					
					if(Negocio.isCPF(objVendedor.getCpf())) {
						if(VendedorPersistencia.procurar(objVendedor) == null) {
							objVendedor.setNome(Console.readString("Informe o nome: "));
							objVendedor.setComissao(Console.readFloat("Informe a taxa de comissão: "));
							VendedorPersistencia.incluir(objVendedor);
							System.out.println("\n\nVendedor cadastrado...");
						}
						else {
							System.out.println("\n\nVendedor já cadastrado...");
						}
					}
					else {
						System.out.println("\n\nCPF inválido...");
					}
					break;
				case 3:
					objProduto = new Produto();
					objProduto.setNome(Console.readString("\n\nInforme o nome do produto: "));
					if(ProdutoPersistencia.procurar(objProduto) == null) {
						objProduto.setPrecoCompra(Console.readFloat("Informe o preço de compra: "));
						objProduto.setMarkup(Console.readFloat("Informe o markup: "));
						ProdutoPersistencia.incluir(objProduto);
						System.out.println("\n\nProduto cadastrado...");
					}
					else {
						System.out.println("\n\nProduto já cadastrado...");
					}
					break;
				case 4:
					Venda objVenda = new Venda();
					objVenda.setId(Console.readInt("\n\nInforme o ID da venda: "));
					if(VendaPersistencia.procurar(objVenda) == null) {
						objCliente = new Cliente();
						objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
						if(Negocio.isCPF(objCliente.getCpf())) {
							objCliente = ClientePersistencia.procurar(objCliente);
							if(objCliente != null) {
								System.out.println("***Cliente -> " + objCliente.getNome());
								objVenda.setCliente(objCliente);
								objVendedor = new Vendedor();
								objVendedor.setCpf(Console.readString("Informe o CPF do vendedor: "));
								if(Negocio.isCPF(objVendedor.getCpf())) {
									objVendedor = VendedorPersistencia.procurar(objVendedor);
									if(objVendedor != null) {
										System.out.println("***Vendedor -> " + objVendedor.getNome());
										objVenda.setVendedor(objVendedor);
										do {
											dataVenda = Console.readString("Informe a data da venda: ");
											try{
												dataDate = (Date) formato.parse(dataVenda);
												dataInvalida = false;
											} 
											catch (ParseException e){		
												System.out.println("Data inválida. Informe novamente.");
											}
										}while(dataInvalida == true);
										objVenda.setDataVenda(dataDate);
										ArrayList<ItemVenda> itens = new ArrayList<ItemVenda>();
										do {
											objItem = new ItemVenda();
											objProduto = new Produto();
											objProduto.setNome(Console.readString("Informe o produto: "));
											objProduto = ProdutoPersistencia.procurar(objProduto);
											if(objProduto != null) {
												objItem.setProduto(objProduto);
												objItem.setQuantidade(Console.readInt("Informe a quantidade: "));
												precoVenda = ProdutoNegocio.calcularPrecoVenda(objProduto);
												objItem.setUnitario(precoVenda);
												System.out.println("Preço do produto: " + precoVenda);
												System.out.println("Subtotal: " + ItemVendaNegocio.calcularSubTotal(objItem));
												itens.add(objItem);
											}
											else {
												System.out.println("\n\nProduto não cadastrado...");
											}
											resposta = Console.readString("Mais produtos? ");
										}while(resposta.equals("SIM"));
										objVenda.setItens(itens);
										VendaPersistencia.incluir(objVenda);
										System.out.println("\n\nVenda realizada...");
									}
									else {
										System.out.println("\n\nVendedor não cadastrado...");
									}
								}else {
									System.out.println("\n\nCPF inválido...");
								}
							} else {
								System.out.println("\n\nCliente não cadastrado...");
							}
						} else {
							System.out.println("\n\nCPF inválido...");
						}
					} else {
						System.out.println("\n\nVenda já cadastrada...");
					}
					break;
				case 5:
					objVenda = new Venda();
					objVenda.setId(Console.readInt("\n\nInforme o ID da venda: "));
					objVenda = VendaPersistencia.procurar(objVenda);
					if(objVenda != null) {
						System.out.println("Cliente: " + objVenda.getCliente().getNome());
						System.out.println("Vendedor: " + objVenda.getVendedor().getNome());
						System.out.println("Data: " + formato.format(objVenda.getDataVenda()));
						System.out.println("\n\n***Produtos***\n\n");
						float subTotal;
						float totalGeral = 0;
						for(ItemVenda item: objVenda.getItens()) {
							System.out.println("Produto: " + item.getProduto().getNome());
							System.out.println("Quantidade: " + item.getQuantidade());
							System.out.println("Unitário: " + item.getUnitario());
							subTotal = ItemVendaNegocio.calcularSubTotal(item);
							totalGeral += subTotal;
							System.out.println("SubTotal: " + subTotal);
							System.out.println("-----------------------------------------");
						}
						System.out.println("Total da venda: " + totalGeral);
					}
					break;
			}
			
		}while(opc != 6);
		
	}
	
}
