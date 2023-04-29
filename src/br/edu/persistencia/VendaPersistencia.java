package br.edu.persistencia;

import java.util.ArrayList;

import br.edu.up.entidades.Venda;

public class VendaPersistencia {
	private static ArrayList<Venda> vendas = new ArrayList<Venda>();
	
	public static void incluir(Venda venda) {
		vendas.add(venda);
	}
	
	public static Venda procurar(Venda venda) {
		for(Venda item: vendas) {
			if(item.getId() == venda.getId()) {
				return item;
			}
		}
		return null;
	}
}
