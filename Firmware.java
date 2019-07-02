import java.util.Map;
import java.util.HashMap;

public class Firmware {

  private UnidadeDeControle unidadeDeControle;
  private Map<String, Registrador> registradores = new HashMap<>();

  /* Quando o Firmware é instanciado, criamos um mapa com as referências dos registradores que estão na UC.
   * Assim, um código binário pode ser convertido em um objeto do tipo <Registrador>; */

  public Firmware(UnidadeDeControle unidadeDeControle) {
    this.unidadeDeControle = unidadeDeControle;
    registradores.put("0000", unidadeDeControle.PC);
    registradores.put("0001", unidadeDeControle.IR);
    registradores.put("0010", unidadeDeControle.MAR);
    registradores.put("0011", unidadeDeControle.MBR);
    registradores.put("0100", unidadeDeControle.AX);
    registradores.put("0101", unidadeDeControle.BX);
    registradores.put("0110", unidadeDeControle.CX);
    registradores.put("0111", unidadeDeControle.DX);
    registradores.put("1000", unidadeDeControle.MEMORIA);
  }

  /* Formato das Instruções: Uma string representa a linha de controle e as posições representam os registradores
   * {0-3} - R1, {4} - Sinal da Porta
   * {5-8} - R2, {9} - Sinal da Porta; */

  /* Ciclo de Busca */
  public String linhaDeBusca1 = "0000110001"; // {0000} {1} {1000} {1} | PC <- MEMÓRIA;
  public String linhaDeBusca2 = "0010100001"; // {0010} {1} {0000} {1} | MAR <- PC;
  public String linhaDeBusca3 = "0100100001"; // {0100} {1} {0000} {1} | AX <- PC;
  public String linhaDeBusca4 = "1000100101"; // {1000} {1} {0010} {1} | MEMÓRIA <- MAR;
  public String linhaDeBusca5 = "0011110001"; // {0011} {1} {1000} {1} | MBR <- MEMÓRIA;
  public String linhaDeBusca6 = "0001100111"; // {0001} {1} {0011} {1} | IR <- MBR;
  public String linhaDeBusca7 = "0000101001"; // {0000} {1} {0100} {1} | PC <- AX+1;

  /* Explicação:
   * O método <leituraLinhaDeControle> vai receber uma linha de controle e verificar as posições passadas,
   * do jeito atual, tudo que é passado no primeiro registrador, de {0} até {3}, vai receber o conteúdo passado
   * pelo segundo registrador, de {5} até {8};
   *
   * A razão da marcação dos sinais de portas, {4} e {9}, é para atualizar o registrador de que uma porta foi aberta; */

  public void leituraLinhaDeControle(String linhaDeControle) {
    if (linhaDeControle.substring(4)) {
      registradores.get(substring(0, 4)).valor = registradores.get(substring(5, 9)).valor;
    }
  }

}
