function mostraEntidadesUsuario() {
    escondeBotaoNovo();
    escondeDivCadastro();
    escondeDivExclusao();
    escondeDivLista();
    mostraDivCadastroEntidadesUsuario();
    mostraDivListaEntidadesUsuario();
}

function escondeCadastroEntidadeUsuario() {
    mostraBotaoNovo();
    escondeDivCadastro();
    escondeDivExclusao();
    mostraDivLista();
    escondeDivCadastroEntidadesUsuario();
    escondeDivListaEntidadesUsuario();
}

function mostraExcluirEntidade() {
    escondeDivCadastroEntidadesUsuario();
    mostraDivExcluirEntidade();
}

function escondeExcluirEntidade() {
    escondeDivExcluirEntidade();
    mostraDivCadastroEntidadesUsuario();
}

function mostraDivListaEntidadesUsuario() {
    mostraOuEsconde("#divListaEntidadesUsuario", false);
}

function escondeDivListaEntidadesUsuario() {
    mostraOuEsconde("#divListaEntidadesUsuario", true);
}

function mostraDivCadastroEntidadesUsuario() {
    mostraOuEsconde("#divCadastroEntidadesUsuario", false);
}

function escondeDivCadastroEntidadesUsuario() {
    mostraOuEsconde("#divCadastroEntidadesUsuario", true);
}

function mostraDivExcluirEntidade() {
    mostraOuEsconde("#divDeleteEntidadeUsuario", false);
}

function escondeDivExcluirEntidade() {
    mostraOuEsconde("#divDeleteEntidadeUsuario", true);
}

function escondeDivExcluirEntidade() {
    mostraOuEsconde("#divDeleteEntidadeUsuario", true);
}