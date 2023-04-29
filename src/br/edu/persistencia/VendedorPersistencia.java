package br.edu.persistencia;

import java.util.ArrayList;

import br.edu.up.entidades.Vendedor;

public class VendedorPersistencia {
	private static ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
	
	public static void incluir(Vendedor vendedor) {
		vendedores.add(vendedor);
	}
	
	public static Vendedor procurar(Vendedor vendedor) {
		for(Vendedor item: vendedores) {
			if(item.getCpf().equals(vendedor.getCpf())) {
				return item;
			}
		}
		return  null;
	}

}
