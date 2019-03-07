function handleClose(xhr, status, args) {
    if(!args.validationFailed) {
        escondeCadastro();
    }
}

function mostraCadastro() {
    escondeBotaoNovo();
    mostraDivCadastro();
    escondeDivExclusao();
}

function escondeCadastro() {
    mostraBotaoNovo();
    escondeDivCadastro();
    escondeDivExclusao();
    mostraDivLista();
}

function mostraExcluir() {
    escondeBotaoNovo();
    escondeDivCadastro();
    mostraDivExclusao();
}

function escondeExcluir() {
    escondeDivExclusao();
    escondeDivCadastro();
    mostraBotaoNovo();
}

function escondeBotaoNovo() {
    mostraOuEsconde("#divBotaoNovo", true);
}

function mostraBotaoNovo() {
   mostraOuEsconde("#divBotaoNovo", false);
}

function mostraDivCadastro() {
    mostraOuEsconde("#divCadastro", false);
}

function escondeDivCadastro() {
    mostraOuEsconde("#divCadastro", true);
}

function mostraDivExclusao() {
    mostraOuEsconde("#divDelete", false);
}

function escondeDivExclusao() {
    mostraOuEsconde("#divDelete", true);
}

function mostraDivLista() {
    mostraOuEsconde("#divLista", false);
}

function escondeDivLista() {
    mostraOuEsconde("#divLista", true);
}

function mostraOuEsconde(divId, condition) {
    if($(divId).is(":visible") == condition) {
        $(divId).animate({height: 'toggle'});
    }
}