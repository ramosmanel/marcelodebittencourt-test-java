# language: pt
Funcionalidade: Comprar produtos
  Como um usuario logado
  Eu quero visualizar e escolher um produto
  Para concluir o pedido

  @validacaoinicial
  Cenario: Deve mostrar uma lista com 8 produtos na pagina inicial
    Dado que estou na pagina inicial
    Quando nao estou logado
    Entao visualize 8 produtos disponiveis
    E carrinho esta vazio

  @fluxopadrao
  Esquema do Cenario: Deve mostrar produto escolhido confirmado
    Dado que estou na pagina inicial
    Quando estou logado
    E seleciona um produto <posicao>
    E nome do produto na tela principal e na tela produto eh <nomeProduto>
    E preco do produto na tela principal e na tela produto eh <precoProduto>
    E adiciono produto no carrinho com tamanho <tamanhoProduto> cor <corProduto> e quantidade <quantidadeProduto>
    Entao o produto aparece na confirmacao com nome <nomeProduto> preco <precoProduto> tamanho <tamanhoProduto> cor <corProduto> e quantidade <quantidadeProduto>
  Exemplos:
  |posicao| precoProduto | tamanhoProduto | corProduto | quantidadeProduto |          nomeProduto         |
  |   0   |   "$19.12"   |      "L"       |  "Black"   |         2         |"Hummingbird Printed T-Shirt" |
  |   1   |   "$28.72"   |      "XL"      |   "N/A"    |         3         |"Hummingbird Printed Sweater" |