package br.edu.up.negocio;

import br.edu.up.entidades.Produto;

public class ProdutoNegocio {
	public static float calcularPrecoVenda(Produto produto) {
		return produto.getPrecoCompra() + (produto.getPrecoCompra() * produto.getMarkup()/100);
	}
}
