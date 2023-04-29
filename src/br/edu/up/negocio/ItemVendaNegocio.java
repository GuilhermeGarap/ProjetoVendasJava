package br.edu.up.negocio;

import br.edu.up.entidades.ItemVenda;

public class ItemVendaNegocio {
	public static float calcularSubTotal(ItemVenda itemVenda) {
		return itemVenda.getQuantidade() * itemVenda.getUnitario();
	}
}
