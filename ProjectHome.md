<font size='6'><b>Equipe 6 - Construção de Compiladores 2012.1</b></font>

<b>Professor:</b>
Francisco Heron de Carvalho Junior

<b>Membros:</b>
Vladimir Portela Parente -
Filipe Francisco -
Francisco Fábio Melo -
Rebeca Benevides

<font size='5'><b>Objetivo</b></font>

A finalidade deste projeto é a criação de um compilador para a linguagem MiniJava na disciplina de Compiladores ministrada em 2012.1 usando a linguagem Java e ferramenta JavaCC.


<font size='5'><b>Módulos Implementados</b></font>


<font size='3'><b>Parte 1: Análise léxica e sintática</b></font>

<b>1.1 Análise léxica</b> (Vladimir, Rebeca, Filipe Francisco e Francisco Fábio)

<b>Descrição:</b> Definir os tokens reconhecidos pela linguagem, respeitando a sua prioridade.

<b>Status:</b> Implementação concluída e testes feitos com todos os códigos que o livro disponibilizou como exemplos. Funcionando corretamente.

<b>Dificuldades:</b> Instalar e se adaptar ao JavaCC. Também houveram algumas dificuldades com a utilização do SVN no início, pois o primeiro plugin utilizado no Eclipse não funcionou bem. Apenas no segundo plugin obtivemos o funcionamento esperado na SVN.

<b>1.2 Análise sintática</b> (Vladimir, Rebeca e Francisco Fábio)

<b>Descrição:</b> Fazer a análise sintática usando o Parser do JavaCC.
Observação de retirar recursões à esquerda e implementar as produções da gramática fornecidas pelo livro texto, com utilização de lookahead, quando necessário.

<b>Status:</b> Implementação concluída e testes feitos. Nenhum bug encontrado, mas para ter certeza de que a recursão à esquerda foi tirada, testamos a estrutura da árvore de sintaxe abstrata pelos visitors e vimos que está funcionando bem.

<b>Dificuldades:</b> Retirada de recursões à esquerda. Foi necessária a criação de uma regra adicional Explinha. Com isso, tivemos que adequar o que estávamos fazendo a 2 regras. O algoritmo é bem simples, o problema é que à primeira vista é algo estranho (passamos para outra regra algo que já foi verificado), o que precisa de algum tempo para ser entendido.

<font size='3'><b>Parte 2: Análise semântica</b></font>

<b>2.1 Sintaxe abstrata</b> (Vladimir e Filipe Francisco)

<b>Descrição:</b> Criar a árvore de sintaxe abstrata para tratar o código, a linguagem em si, de uma maneira mais simples. Fazemos a criação da árvore de sintaxe abstrata junto com a análise sintática (no Parser).

<b>Status:</b> Implementação concluída e testes feitos. Verificamos se a estrutura da árvore de sintaxe abstrata estava correta com os visitors (apenas percorrendo a árvore).

<b>Dificuldades:</b> A criação da árvore de sintaxe abstrata foi feita enquanto faziamos a análise léxica e sintática. Mais uma vez, por uma certa falta de experiência em relação à isso, vendo na primeira vez, parece algo bem complicado, o que logo se percebe que não é. A única dificuldade real está em entender que essa árvore realmente traduz o código em que se está trabalhando.

<b>2.2 Tabela de símbolos e checagem de tipos</b> (Vladimir e Francisco Fábio)

<b>Descrição:</b> Implementação da tabela de símbolos e visitors. A tabela foi criada de acordo com o livro. Temos uma tabela de classes e cada classe tem uma tabela de métodos e uma de variáveis (atributos). Cada método tem uma tabela de variáveis. Cada variável tem informações referentes a tipo e retorno. A checagem de tipos é feita levando em consideração a herança. Se duas variáveis têm o mesmo nome e uma é da superclasse, a de menor hierarquia (da subclasse) sempre é a referenciada por esse nome (mesmo para o método).

<b>Status:</b> Tabela de símbolos concluída e testada com sucesso. A checagem de tipos também ocorreu com sucesso.

<b>Dificuldades:</b> Em relação à tabela de símbolos, um dos problemas que só foi percebido mais tarde foi que cada tabela de variável deveria ter um mapeamento relativo ao frame atual. Isso foi implementado, mas umas das grandes questões que envolveram esse trabalho, principalmente no final (pesou muito), foi se esse mapeamento estava correto, e se estava correto, seria mesmo o melhor possível? O mapeamento realmente deveria ficar na variável, não guardado em algum outro lugar? Aparentemente essa solução pareceu correta.
Em relação à checagem de tipos, a única dificuldade foi na checagem de métodos da superclasse. Mas foi apenas uma questão de implementação, não de teoria.

<font size='3'><b>Parte 3: Registros de ativação e código intermediário</b></font>

<b>3.1 Adição do Frame (MipsFrame)</b> (Vladimir e Francisco Fábio)

<b>Descrição:</b> Criação do frame (guarda informações sobre a função chamada) para as funções do MiniJava. Com o frame já cedido pelo professor, o acoplamos ao projeto criando o pacote mips. Também criamos a classe abstrata Frame para conseguir manipular o Frame independentemente da arquitetura até o momento estritamente necessário (usamos na construção da árvore IR, por exemplo).

<b>Status:</b> Apesar de utilizado na criação da árvore IR e de estar contido em algum fragmento, há métodos do Frame que não estão sendo utilizados ainda (não chegamos na tradução da árvore IR para código de máquina) e , por causa disso, não temos certeza de que está totalmente funcional. Porém, até o momento, mostra-se correto.

<b>Dificuldades:</b> Todas as classes dadas pelo livro trabalham as listas como sendo Cabeça e Cauda. O frame trabalha como se fosse um vetor dinâmico. Apesar de não haver um problema grande com esssa conversão (temos até uma classe só para isso), torna-se a leitura um pouco mais difícil. Além disso, apesar de termos a classe do frame já toda feita, há uma dificuldade grande em entender pra que serve cada método. Os métodos procrEntryExit mesmo com o livro explicando, são de difícil compreensão. Ainda há boa parte da estrutura dele que não é explicada, nem comentada. Concluindo, a classe como está dificulta muito o seu entendimento.

<b>3.2 Geração do código intermediário</b> (Vladimir e Rebeca)

<b>Descrição:</b> Geração do código intermediário para tradução para o código da máquina alvo. Cada variável tem um atributo que indica sua posição em relação ao frame atual. Ou seja, construímos os endereços da variável em cada fragmento de maneira relativa. Isso será tratado quando estivermos vendo o mapeamento de registradores para variáveis.

<b>Status:</b> Árvore criada com sucesso, testes feitos, nenhuma construção deu algum erro fatal. Usamos a classe Print para debug. Aparentemente a árvore IR está correta, o que pode ocorrer é que houve um erro conceitual na tradução de algum nó, como if ou while.

<b>Dificuldades:</b> A principal dificuldade foi em relação ao mapeamento de variáveis. Apesar de não influenciar nessa fase diretamente, foi percebido que é essencial pensar nisso principalmente nessa fase, pois esse mapeamento relativo ao frame, vai servir para a alocação de registradores depois. Apesar de alguma dificuldade aparente no começo para traduzir cada parte para a árvore IR, com calma, viu-se que não era difícil.

<font size='3'><b>Parte 4: Blocos básicos, traços e seleções de instruções</b></font>

<b>4.1 Árvore Canônica, Blocos e Traços</b> (Vladimir e Filipe Francisco)

<b>Descrição:</b> Gerar a árvore IR sem ESEQ's e SEQ's (uma lista deles é gerada fora da árvore) e criar blocos e traços. As classes cedidas pelo livro foram adicionadas e a chamada dos métodos para transformação para árvore canônica e criação de blocos e traços foi adicionada no método principal

<b>Status:</b> Está funcionando perfeitamente.

<b>Dificuldades:</b> Nenhuma dificuldade encontrada, o livro já nos dá essa parte. Como foi bem entendido esse contéudo, a única coisa a ser feita foi verificar o resultado de cada árvore IR original par a canônica.

<b>4.2 Seleção de Instruções</b> (Vladimir)

<b>Descrição:</b> A partir da árvore IR canônica, traduzir para uma linguagem mais próxima do nível de máquina (sem lidar com o problema dos registradores ainda). O algoritmo Maximal Munch foi adicionado.

<b>Status:</b> A tradução está ocorrendo de forma correta. Provavelmente, qualquer erro que se encontre em uma fase posterior se deve com a interface do frame e com a tradução para a árvore IR.

<b>Dificuldades:</b> A tradução do CALL levando em conta cada argumento seu foi difícil, pois não era encontrada a fórmula correta de tradução. Além disso, a instrução CJUMP não teve uma tradução clara, ficando, provavelmente, apenas uma instrução que não é real.

<font size='3'><b>Parte 5: Análise de longevidade e alocação de registradores</b></font>

<b>5.1 Análise de Longevidade</b> (Vladimir)

<b>Descrição:</b> A partir do código que conseguimos da fase passada (seleção de instrução), devemos fazer análise do tempo de vida de cada variável, tendo como resultado, principalmente, o grafo de interferência.

<b>Status:</b> O grafo de fluxo foi criado. Testes foram feitos e viu-se alguns erros. O grafo de interferência também teve sua implementação iniciada.

<b>Dificuldades:</b> Foi iniciado a construção do grafo de interferência, mas como o grafo de fluxo estava com erros não foi possível continuar sua implementação (pela conectividade intima). Percebe-se nesse ponto que a seleção de instrução é essencial no mapeamento de variáveis de destino e fonte.

<b>5.2 Alocação de registradores</b> (Vladimir)

<b>Descrição:</b> A partir do grafo de interferência, utilizar o algoritmo de coloração para alocar registradores para as variáveis.

<b>Status:</b> As classes do livro foram adicionadas.

<b>Dificuldades:</b> Pelos problemas no grafo de fluxo, não foi possível continuar essa parte da implementação.

<font size='5'><b>Testes</b></font>

Todos os testes foram feitos com todos os códigos dados pelo livro. A única observação é que como o livro não aborda na parte básica como implementar herança, até a criação da árvore IR, testamos com todos, mas depois tiramos o único código (TreeVisitor).
A saída em todos os testes até o grafo de fluxo saiu como esperada (houve a checagem linha por linha realmente). No grafo, a dificuldade encontrada foi que os erros eram referentes a seleção de instrução. Erros simples até, mas como eram propagados tornavam-se de difícil detecção.

<font size='5'><b>Conclusão</b></font>

Esse trabalho foi essencial para o entendimento de um compilador. Apesar da teoria descrever muito bem o que deve ser feito e o porquê, esse trabalho nos mostra como realmente é implementado, pois trabalhamos com máquinas e, por causa disso, uma hora temos que fazer o mapeamento da linguagem que se deseja compilar para um código que a máquina realmente possa executar. Além disso, ver passo a passo como ocorre o processo de compilação esclare a matéria. Concluindo, é muito importante não só entender a teoria, mas, neste caso, ver realmente como funciona.