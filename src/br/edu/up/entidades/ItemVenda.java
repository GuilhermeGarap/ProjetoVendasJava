package br.edu.up.entidades;

public class ItemVenda {
	private Produto produto;
	private int quantidade;
	private float unitario;
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public float getUnitario() {
		return unitario;
	}
	public void setUnitario(float unitario) {
		this.unitario = unitario;
	}
}
