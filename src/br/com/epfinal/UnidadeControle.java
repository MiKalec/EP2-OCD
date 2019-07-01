package br.com.epfinal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UnidadeControle {
    public static List<Registrador> registradores = new ArrayList<>();
    public Firmware firmware;
    private Scanner input = new Scanner(System.in);

    static {
        registradores.add(new Registrador("PC", 1, 2));
        registradores.add(new Registrador("IR", 14, 15));
        registradores.add(new Registrador("AX", 6, 7));
        registradores.add(new Registrador("BX", 8, 9));
        registradores.add(new Registrador("CX", 10, 11));
        registradores.add(new Registrador("DX", 12, 13));
        registradores.add(new Registrador("MAR", 3, 20));
        registradores.add(new Registrador("MBR", new int[]{4, 21}, new int[]{5, 22}));
    }

    public void executa() {
        List<String> comandos = new ArrayList<>();
        System.out.println("Nome do arquivo com as instruções");
        String nomeArquivo = input.nextLine();

        try {
            FileReader arq = new FileReader(nomeArquivo);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while (linha != null) {
                comandos.add(linha);
                linha = lerArq.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Algo de errado aconteceu ao ler o arquivo");
            e.printStackTrace();
        }

        executaComandos(comandos);
    }

    private void executaComandos(List<String> comandos) {
        //aqui vai a interação com o Firmware e execução do programa passado
    }
}
