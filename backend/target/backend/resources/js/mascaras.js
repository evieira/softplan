/*
 * Script: Mascaras em Javascript
 * Autor: Matheus Biagini de Lima Dias
 * Data: 26/08/2008
 */
/*FunÃƒÂ§ÃƒÂ£o  Pai de Mascaras*/
function Mascara(object, func) {
	v_obj = object
	v_fun = func
	setTimeout("execmascara()", 1)
}

/*FunÃƒÂ§ÃƒÂ£o que Executa os objetos*/
function execmascara() {
	v_obj.value = v_fun(v_obj.value)
}

/*FunÃƒÂ§ÃƒÂ£o que Determina as expressÃƒÂµes regulares dos objetos*/
function leech(v) {
	v = v.replace(/o/gi, "0")
	v = v.replace(/i/gi, "1")
	v = v.replace(/z/gi, "2")
	v = v.replace(/e/gi, "3")
	v = v.replace(/a/gi, "4")
	v = v.replace(/s/gi, "5")
	v = v.replace(/t/gi, "7")
	return v
}

/*FunÃƒÂ§ÃƒÂ£o que permite apenas numeros*/
function Integer(v) {
	return v.replace(/\D/g, "")
}

/*FunÃƒÂ§ÃƒÂ£o que padroniza telefone (11) 4184-1241*/
function Telefone(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/^(\d\d)(\d)/g, "($1) $2")
	v = v.replace(/(\d{4})(\d)/, "$1-$2")
	return v
}

/*FunÃƒÂ§ÃƒÂ£o que padroniza telefone (11) 41841241*/
function TelefoneCall(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/^(\d\d)(\d)/g, "($1) $2")
	return v
}

/*FunÃƒÂ§ÃƒÂ£o que padroniza CPF*/
function Cpf(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/(\d{3})(\d)/, "$1.$2")
	v = v.replace(/(\d{3})(\d)/, "$1.$2")

	v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2")
	return v
}

/*FunÃƒÂ§ÃƒÂ£o que padroniza CEP*/
function Cep(v) {
	v = v.replace(/D/g, "")
	v = v.replace(/^(\d{5})(\d)/, "$1-$2")
	return v
}

/*FunÃƒÂ§ÃƒÂ£o que padroniza CNPJ*/
function Cnpj(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/^(\d{2})(\d)/, "$1.$2")
	v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3")
	v = v.replace(/\.(\d{3})(\d)/, ".$1/$2")
	v = v.replace(/(\d{4})(\d)/, "$1-$2")
	return v
}

/*FunÃƒÂ§ÃƒÂ£o que permite apenas numeros Romanos*/
function Romanos(v) {
	v = v.toUpperCase()
	v = v.replace(/[^IVXLCDM]/g, "")

	while (v.replace(
			/^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$/, "") != "")
		v = v.replace(/.$/, "")
	return v
}

/*FunÃƒÂ§ÃƒÂ£o que padroniza o Site*/
function Site(v) {
	v = v.replace(/^http:\/\/?/, "")
	dominio = v
	caminho = ""
	if (v.indexOf("/") > -1)
		dominio = v.split("/")[0]
	caminho = v.replace(/[^\/]*/, "")
	dominio = dominio.replace(/[^\w\.\+-:@]/g, "")
	caminho = caminho.replace(/[^\w\d\+-@:\?&=%\(\)\.]/g, "")
	caminho = caminho.replace(/([\?&])=/, "$1")
	if (caminho != "")
		dominio = dominio.replace(/\.+$/, "")
	v = "http://" + dominio + caminho
	return v
}

/*FunÃƒÂ§ÃƒÂ£o que padroniza DATA*/
function Data(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/(\d{2})(\d)/, "$1/$2")
	v = v.replace(/(\d{2})(\d)/, "$1/$2")
	return v
}

/*FunÃƒÂ§ÃƒÂ£o que padroniza DATA*/
function Hora(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/(\d{2})(\d)/, "$1:$2")
	return v
}

/*FunÃƒÂ§ÃƒÂ£o que padroniza valor monÃƒÂ©tario*/
function Valor(v) {
	v=v.replace(/\D/g,"");//Remove tudo o que nâ€¹o Å½ dâ€™gito  
    v=v.replace(/(\d)(\d{8})$/,"$1.$2");//coloca o ponto dos milhâ€ºes  
    v=v.replace(/(\d)(\d{5})$/,"$1.$2");//coloca o ponto dos milhares  
    v=v.replace(/(\d)(\d{2})$/,"$1,$2");//coloca a virgula antes dos 2 Å“ltimos dâ€™gitos
	return v;
}

/*FunÃƒÂ§ÃƒÂ£o que padroniza Area*/
function Area(v) {
	v = v.replace(/\D/g, "")
	v = v.replace(/(\d)(\d{2})$/, "$1.$2")
	return v

}

function Maiusculo(v) {
	v = v.toUpperCase();
	return v;
}