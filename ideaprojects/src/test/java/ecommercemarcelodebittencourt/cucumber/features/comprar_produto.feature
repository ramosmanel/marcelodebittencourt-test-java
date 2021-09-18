# language: pt
Funcionalidade: Comprar produtos
  Como um usuario logado
  Eu quero visualizar e escolher um produto
  Para concluir o pedido

  Cenario: Deve mostrar uma lista com 8 produtos na pagina inicial
    Dado que estou na pagina inicial
    Quando nao estou logado
    Entao visualize 8 produtos disponiveis
    E carrinho esta vazio


  Cenario: Deve mostrar produto escolhido confirmado
    Dado que estou na pagina inicial
    Quando estou logado
    E seleciona um produto 1
    E nome do produto na tela principal eh "Hummingbird Printed Sweater"
    E preco do produto na tela principal eh "$28.72"
    E adiciono produto no carrinho com tamanho "L" cor "Black" e quantidade 2
    Entao o produto aparece na confirmacao com nome "Hummingbird Printed Sweater" preco "$28.72 " tamanho "L" cor "Black" e quantidade 2
