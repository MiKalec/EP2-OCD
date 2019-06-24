package br.com.ep.implementations;

import java.util.HashMap;
import java.util.Map;

public class Mux {
    private Map<String, RegCode> regs = new HashMap();
    private Map<String, byte[]> opcodes = new HashMap();
    private static Mux t;

    public static Mux instanceOf() {
        if (t == null) {
            t = new Mux();
        }

        return t;
    }

    private Mux() {
        this.regs.put("ax", new RegCode(new byte[3]));
        this.regs.put("bx", new RegCode(new byte[]{0, 0, 1}));
        this.regs.put("cx", new RegCode(new byte[]{0, 1, 0}));
        this.regs.put("dx", new RegCode(new byte[]{0, 1, 1}));
        this.regs.put("ds", new RegCode(new byte[]{1, 0, 0}));
        this.opcodes.put("add reg,reg", new byte[5]);
        this.opcodes.put("sub reg,reg", new byte[]{0, 0, 0, 0, 1});
        this.opcodes.put("mov reg,reg", new byte[]{0, 0, 0, 1, 0});
        this.opcodes.put("mul reg", new byte[]{0, 0, 0, 1, 1});
        this.opcodes.put("div reg", new byte[]{0, 0, 1, 0, 0});
        this.opcodes.put("inc reg", new byte[]{0, 0, 1, 0, 1});
        this.opcodes.put("dec reg", new byte[]{0, 0, 1, 1, 0});
        this.opcodes.put("add reg,num", new byte[]{0, 0, 1, 1, 1});
        this.opcodes.put("sub reg,num", new byte[]{0, 1, 0, 0, 0});
        this.opcodes.put("mov reg,num", new byte[]{0, 1, 0, 0, 1});
        this.opcodes.put("mov reg,[num]", new byte[]{0, 1, 0, 1, 0});
        this.opcodes.put("mov reg,[reg]", new byte[]{0, 1, 0, 1, 1});
        this.opcodes.put("mov [num],num", new byte[]{0, 1, 1, 0, 0});
        this.opcodes.put("mov [num],reg", new byte[]{0, 1, 1, 0, 1});
        this.opcodes.put("mov [reg],reg", new byte[]{0, 1, 1, 1, 0});
        this.opcodes.put("mov [reg],num", new byte[]{0, 1, 1, 1, 1});
        this.opcodes.put("jmp num", new byte[]{1, 0, 0, 0, 0});
        this.opcodes.put("jl num", new byte[]{1, 0, 0, 0, 1});
        this.opcodes.put("jle num", new byte[]{1, 0, 0, 1, 0});
        this.opcodes.put("jg num", new byte[]{1, 0, 0, 1, 1});
        this.opcodes.put("jge num", new byte[]{1, 0, 1, 0, 0});
        this.opcodes.put("jz num", new byte[]{1, 0, 1, 0, 1});
        this.opcodes.put("jnz num", new byte[]{1, 0, 1, 1, 0});
        this.opcodes.put("cmp num,num", new byte[]{1, 0, 1, 1, 1});
        this.opcodes.put("cmp reg,num", new byte[]{1, 1, 0, 0, 0});
        this.opcodes.put("cmp num,reg", new byte[]{1, 1, 0, 0, 1});
        this.opcodes.put("cmp reg,reg", new byte[]{1, 1, 0, 1, 0});
        this.opcodes.put("mul num", new byte[]{1, 1, 0, 1, 1});
        this.opcodes.put("div num", new byte[]{1, 1, 1, 0, 0});
    }

    public PalavraHorizontal traduzir(String assemblyLine) throws Exception {
        PalavraHorizontal resp;
        RegCode b = null;
        RegCode a = null;
        int aux2 = 0;
        int aux1 = 0;
        String palavra;
        boolean f2 = false;
        boolean f1 = false;
        assemblyLine = assemblyLine.trim();
        String[] linha = assemblyLine.split(" ");
        if (linha.length < 2) {
            throw new Exception("Comando inválido: " + assemblyLine);
        } else {
            String comando = linha[0];
            String[] params = linha[1].split(",");
            comando = comando.toLowerCase() + " ";
            if (params[0].contains("[")) {
                comando = comando + "[";
                params[0] = params[0].replace("[", "");
                params[0] = params[0].replace("]", "");
                f1 = true;
            }

            String var14;
            label204:
            {
                label203:
                {
                    switch ((var14 = params[0].toLowerCase()).hashCode()) {
                        case 3127:
                            if (!var14.equals("ax")) {
                                break label203;
                            }
                            break;
                        case 3158:
                            if (!var14.equals("bx")) {
                                break label203;
                            }
                            break;
                        case 3189:
                            if (!var14.equals("cx")) {
                                break label203;
                            }
                            break;
                        case 3215:
                            if (!var14.equals("ds")) {
                                break label203;
                            }
                            break;
                        case 3220:
                            if (!var14.equals("dx")) {
                                break label203;
                            }
                            break;
                        default:
                            break label203;
                    }

                    a = this.regs.get(params[0].toLowerCase());
                    comando = comando + "reg";
                    break label204;
                }

                aux1 = Integer.parseInt(params[0]);
                comando = comando + "num";
            }

            if (f1) {
                comando = comando + "]";
                f1 = false;
            }

            if (params.length == 2) {
                f2 = true;
                comando = comando + ",";
                if (params[1].contains("[")) {
                    comando = comando + "[";
                    params[1] = params[1].replace("[", "");
                    params[1] = params[1].replace("]", "");
                    f1 = true;
                }

                label187:
                {
                    label186:
                    {
                        switch ((var14 = params[1].toLowerCase()).hashCode()) {
                            case 3127:
                                if (!var14.equals("ax")) {
                                    break label186;
                                }
                                break;
                            case 3158:
                                if (!var14.equals("bx")) {
                                    break label186;
                                }
                                break;
                            case 3189:
                                if (!var14.equals("cx")) {
                                    break label186;
                                }
                                break;
                            case 3215:
                                if (!var14.equals("ds")) {
                                    break label186;
                                }
                                break;
                            case 3220:
                                if (!var14.equals("dx")) {
                                    break label186;
                                }
                                break;
                            default:
                                break label186;
                        }

                        b = this.regs.get(params[1].toLowerCase());
                        comando = comando + "reg";
                        break label187;
                    }

                    aux2 = Integer.parseInt(params[1]);
                    comando = comando + "num";
                }

                if (f1) {
                    comando = comando + "]";
                    f1 = false;
                }
            }

            byte[] code = this.opcodes.get(comando);
            byte[] p = new byte[32];
            if (code == null) {
                throw new Exception("Comando inválido " + assemblyLine + " - " + comando);
            } else {
                int i;
                for (i = 0; i < 5; ++i) {
                    p[i] = code[i];
                }

                int cont;
                if (a != null) {
                    for (cont = 0; i < 8; ++i) {
                        p[i] = a.getCode()[cont++];
                    }
                }

                if (a != null && b != null) {
                    for (cont = 0; i < 11; ++i) {
                        p[i] = b.getCode()[cont++];
                    }
                } else if (b != null) {
                    for (cont = 0; i < 8; ++i) {
                        p[i] = b.getCode()[cont++];
                    }
                }

                int lastI = i;
                if (a == null && b == null) {
                    if (f2) {
                        palavra = Integer.toBinaryString(aux1);
                        int fim = 17;
                        cont = palavra.length() - 1;

                        for (i = fim; i >= lastI && cont >= 0; --i) {
                            p[i] = Byte.parseByte(String.valueOf(palavra.charAt(cont--)));
                        }

                        lastI = fim + 1;
                        palavra = Integer.toBinaryString(aux2);
                        cont = palavra.length() - 1;

                        for (i = 31; i >= lastI && cont >= 0; --i) {
                            p[i] = Byte.parseByte(String.valueOf(palavra.charAt(cont--)));
                        }
                    } else {
                        palavra = Integer.toBinaryString(aux1);
                        cont = palavra.length() - 1;

                        for (i = 31; i >= lastI && cont >= 0; --i) {
                            p[i] = Byte.parseByte(String.valueOf(palavra.charAt(cont--)));
                        }
                    }
                } else if (a == null) {
                    palavra = Integer.toBinaryString(aux1);
                    cont = palavra.length() - 1;

                    for (i = 31; i >= lastI && cont >= 0; --i) {
                        p[i] = Byte.parseByte(String.valueOf(palavra.charAt(cont--)));
                    }
                } else if (b == null) {
                    palavra = Integer.toBinaryString(aux2);
                    cont = palavra.length() - 1;

                    for (i = 31; i >= lastI && cont >= 0; --i) {
                        p[i] = Byte.parseByte(String.valueOf(palavra.charAt(cont--)));
                    }
                }

                if (comando.contains("mov") && a != null && a.equals(b)) {
                    throw new Exception("mov não pode ser chamado com registradores iguais!\n" + a + " " + b);
                } else {
                    resp = new PalavraHorizontal(p);
                    return resp;
                }
            }
        }
    }
}
