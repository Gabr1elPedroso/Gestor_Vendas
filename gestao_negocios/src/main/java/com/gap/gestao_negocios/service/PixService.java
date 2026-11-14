package com.gap.gestao_negocios.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PixService {
    public static String gerarPayloadPix(String chavePix, String nomeRecebedor, String cidade, BigDecimal valor, String txid) {
        DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        String valorStr = df.format(valor);

        StringBuilder payload = new StringBuilder();
        payload.append("000201");
        
        
        String merchantAccount = "0014br.gov.bcb.pix01" + formatarCampo(chavePix);
        payload.append("26").append(String.format("%02d", merchantAccount.length())).append(merchantAccount);
        
        payload.append("52040000");
        payload.append("5303986");
        
    
        payload.append("54").append(String.format("%02d", valorStr.length())).append(valorStr);
        
        payload.append("5802BR");
        
    
        payload.append("59").append(String.format("%02d", nomeRecebedor.length())).append(nomeRecebedor);
        
    
        payload.append("60").append(String.format("%02d", cidade.length())).append(cidade);
        
      
        String additionalData = "0503" + (txid != null && !txid.isEmpty() ? txid : "***");
        payload.append("62").append(String.format("%02d", additionalData.length())).append(additionalData);
        

        payload.append("6304");
        
        String crc = calcularCRC16(payload.toString());
        payload.append(crc);

        return payload.toString();
    }

    private static String formatarCampo(String valor) {
        return String.format("%02d", valor.length()) + valor;
    }

    private static String calcularCRC16(String payload) {
        int polinomio = 0x1021;
        int resultado = 0xFFFF;
        byte[] bytes = payload.getBytes();

        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i)) & 1) == 1;
                boolean c15 = ((resultado >> 15) & 1) == 1;
                resultado <<= 1;
                if (c15 ^ bit) {
                    resultado ^= polinomio;
                }
            }
        }
        resultado &= 0xFFFF;
        return String.format("%04X", resultado);
    }
}

