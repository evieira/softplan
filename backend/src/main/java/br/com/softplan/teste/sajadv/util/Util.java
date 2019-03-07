package br.com.softplan.teste.sajadv.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    private static final int PARTS_TO_SHUFFLE = 4;

    public static String verificaCampoStringVazio(Object objeto) {
        try {
            if (objeto == null) {
                return "";
            } else {
                if (objeto.equals(null)) {
                    return "";
                } else {
                    return String.valueOf(objeto);
                }

            }
        } catch (Exception e) {
            return "";
        }
    }

    public static String formatarCPF(String cpf) {
        StringBuffer codigoFormatado = new StringBuffer(cpf);
        codigoFormatado.insert(3, '.');
        codigoFormatado.insert(7, '.');
        codigoFormatado.insert(11, '-');
        return codigoFormatado.toString();
    }

    public static String formataNumeroProcessoUnificado(String numeroProcesso) {
        StringBuffer codigoFormatado = new StringBuffer(numeroProcesso);
        codigoFormatado.insert(7, '-');
        codigoFormatado.insert(10, '.');
        codigoFormatado.insert(15, '.');
        codigoFormatado.insert(17, '.');
        codigoFormatado.insert(20, '.');
        return codigoFormatado.toString();
    }

    public static LocalDate stringToDate(String date) {
        if(date == null) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }

    public static String criptografaSenha(String senha) throws NoSuchAlgorithmException {
        if(verificaCampoStringVazio(senha).isEmpty())
            throw new NoSuchAlgorithmException();

        senha += "string adicionada a senha";
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        String retorno = hash.toString(16);
        if (retorno.length() % 2 != 0)
            retorno = "0" + retorno;
        return shuffle(retorno);
    }

    private static String shuffle(String encryptedString) {
        String[] partesEmOrdem = getMessageParts(encryptedString);

        StringBuffer retorno = new StringBuffer();
        retorno.append(partesEmOrdem[3]);
        retorno.append(partesEmOrdem[2]);
        retorno.append(partesEmOrdem[0]);
        retorno.append(partesEmOrdem[1]);
        return retorno.toString();
    }

    private static String[] getMessageParts(String encryptedString) {
        String partesEmOrdem[] = new String[PARTS_TO_SHUFFLE];

        int sizeMessage = encryptedString.length();
        // Tamanho de cada parte
        int sizePart = sizeMessage / PARTS_TO_SHUFFLE;
        // Indice para capturar a string
        int indice = 0;
        // Percorre mensagem - Por partes
        for (int i = 0; i < PARTS_TO_SHUFFLE; i++) {
            partesEmOrdem[i] = encryptedString.substring(indice, (sizePart + indice));
            indice += sizePart;
        }
        return partesEmOrdem;
    }
}
