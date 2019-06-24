package br.com.ep.components.cpu;

import br.com.ep.interfaces.*;
import br.com.ep.interfaces.Observer;
import br.com.ep.implementations.LinhaControle;
import br.com.ep.implementations.OpCode;
import br.com.ep.implementations.RegCode;

import java.util.*;


public class UC implements Subject {
    private Firmware firm;
    private LinhaControle atual;
    private byte[] portas;
    private Map<RegCode, RegistradorUtilizavel> regsUtilizaveis;
    private IR ir;
    private OpCode operacao;
    private Memory mem;
    private UnidadeLogicaAritimetica ula;
    byte indirecao;
    private boolean flagUpdate;
    private static Map<OpCode, byte[]> CodeCfgs = new HashMap();
    private final byte busca = 0;
    private final byte inNumP2 = 4;
    private final byte inRegP2 = 8;
    private final byte inRegP1 = 9;
    private final byte execucao = 10;
    private List<Observer> observers;

    static {
        CodeCfgs.put(new OpCode(new byte[5]), new byte[]{11, 3, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 0, 0, 0, 1}), new byte[]{15, 3, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 0, 0, 1, 0}), new byte[]{19, 3, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 0, 0, 1, 1}), new byte[]{20, 1, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 0, 1, 0, 0}), new byte[]{24, 1, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 0, 1, 0, 1}), new byte[]{31, 1, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 0, 1, 1, 0}), new byte[]{34, 1, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 0, 1, 1, 1}), new byte[]{37, 1, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 1, 0, 0, 0}), new byte[]{41, 1, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 1, 0, 0, 1}), new byte[]{45, 1, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 1, 0, 1, 0}), new byte[]{45, 1, 1});
        CodeCfgs.put(new OpCode(new byte[]{0, 1, 0, 1, 1}), new byte[]{45, 3, 2});
        CodeCfgs.put(new OpCode(new byte[]{0, 1, 1, 0, 0}), new byte[]{46, 4, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 1, 1, 0, 1}), new byte[]{49, 2, 0});
        CodeCfgs.put(new OpCode(new byte[]{0, 1, 1, 1, 0}), new byte[]{49, 3, 3});
        CodeCfgs.put(new OpCode(new byte[]{0, 1, 1, 1, 1}), new byte[]{46, 1, 3});
        CodeCfgs.put(new OpCode(new byte[]{1, 0, 0, 0, 0}), new byte[]{50, 0, 0});
        CodeCfgs.put(new OpCode(new byte[]{1, 0, 0, 0, 1}), new byte[]{50, 0, 4, 0});
        CodeCfgs.put(new OpCode(new byte[]{1, 0, 0, 1, 0}), new byte[]{50, 0, 4, 1});
        CodeCfgs.put(new OpCode(new byte[]{1, 0, 0, 1, 1}), new byte[]{50, 0, 4, 2});
        CodeCfgs.put(new OpCode(new byte[]{1, 0, 1, 0, 0}), new byte[]{50, 0, 4, 3});
        CodeCfgs.put(new OpCode(new byte[]{1, 0, 1, 0, 1}), new byte[]{50, 0, 4, 4});
        CodeCfgs.put(new OpCode(new byte[]{1, 0, 1, 1, 0}), new byte[]{50, 0, 4, 5});
        CodeCfgs.put(new OpCode(new byte[]{1, 0, 1, 1, 1}), new byte[]{55, 4, 0});
        CodeCfgs.put(new OpCode(new byte[]{1, 1, 0, 0, 0}), new byte[]{57, 1, 0});
        CodeCfgs.put(new OpCode(new byte[]{1, 1, 0, 0, 1}), new byte[]{59, 2, 0});
        CodeCfgs.put(new OpCode(new byte[]{1, 1, 0, 1, 0}), new byte[]{61, 3, 0});
        CodeCfgs.put(new OpCode(new byte[]{1, 1, 0, 1, 1}), new byte[]{63, 0, 0});
        CodeCfgs.put(new OpCode(new byte[]{1, 1, 1, 0, 0}), new byte[]{65, 0, 0});
    }

    public UC(IR ir, Collection<RegistradorUtilizavel> regs, Memory mem, UnidadeLogicaAritimetica ula) {
        this.ir = ir;
        this.firm = new Firmware();
        this.mem = mem;
        this.ula = ula;
        this.observers = new LinkedList();
        this.regsUtilizaveis = new HashMap();
        Iterator var6 = regs.iterator();

        while (var6.hasNext()) {
            RegistradorUtilizavel i = (RegistradorUtilizavel) var6.next();
            this.regsUtilizaveis.put(i.getID(), i);
        }

    }

    public int getPointer() {
        return this.firm.getPointer();
    }

    private void executeMicroInstruction() throws Exception {
        this.atual = this.firm.getInstruction();
        this.portas = this.atual.getPortas();
        RegistradorUtilizavel a;
        RegistradorUtilizavel b;
        switch (this.atual.getDecode()) {
            case 1:
                a = (RegistradorUtilizavel) this.regsUtilizaveis.get(new RegCode(this.ir.getP1().getBits(29, 31)));
                if (a == null) {
                    throw new Exception("Registrador invalido: " + a);
                }

                this.portas[Integer.parseInt(a.getCodigo().substring(4, 6))] = 1;
                break;
            case 2:
                a = (RegistradorUtilizavel) this.regsUtilizaveis.get(new RegCode(this.ir.getP1().getBits(29, 31)));
                if (a == null) {
                    throw new Exception("Registrador invalido" + a);
                }

                this.portas[Integer.parseInt(a.getCodigo().substring(1, 3))] = 1;
                break;
            case 3:
                a = (RegistradorUtilizavel) this.regsUtilizaveis.get(new RegCode(this.ir.getP2().getBits(29, 31)));
                if (a == null) {
                    throw new Exception("Registrador invalido" + a);
                }

                this.portas[Integer.parseInt(a.getCodigo().substring(4, 6))] = 1;
                break;
            case 4:
                a = (RegistradorUtilizavel) this.regsUtilizaveis.get(new RegCode(this.ir.getP2().getBits(29, 31)));
                if (a == null) {
                    throw new Exception("Registrador invalido" + a);
                }

                this.portas[Integer.parseInt(a.getCodigo().substring(1, 3))] = 1;
                break;
            case 5:
                a = (RegistradorUtilizavel) this.regsUtilizaveis.get(new RegCode(this.ir.getP1().getBits(29, 31)));
                b = (RegistradorUtilizavel) this.regsUtilizaveis.get(new RegCode(this.ir.getP2().getBits(29, 31)));
                if (a == null || b == null) {
                    throw new Exception("Registrador invalido" + a + " " + b);
                }

                this.portas[Integer.parseInt(a.getCodigo().substring(1, 3))] = 1;
                this.portas[Integer.parseInt(b.getCodigo().substring(4, 6))] = 1;
                break;
            case 6:
                a = (RegistradorUtilizavel) this.regsUtilizaveis.get(new RegCode(this.ir.getP1().getBits(29, 31)));
                b = (RegistradorUtilizavel) this.regsUtilizaveis.get(new RegCode(this.ir.getP2().getBits(29, 31)));
                if (a == null || b == null) {
                    throw new Exception("Registrador invalido " + a + " " + b);
                }

                this.portas[Integer.parseInt(b.getCodigo().substring(1, 3))] = 1;
                this.portas[Integer.parseInt(a.getCodigo().substring(4, 6))] = 1;
        }

        this.mem.setFlags(this.atual.getRWAV());
        this.flagUpdate = false;
        this.notifyObservers();
        this.flagUpdate = true;
        this.notifyObservers();
        this.ula.setOperacao(this.atual.getULA(), this.firm.getPointer() != 0);
        byte ponteiro = this.atual.getProx();
        this.operacao = this.ir.getOpCode();
        byte[] cfg;
        if (ponteiro == -1) {
            cfg = (byte[]) CodeCfgs.get(this.operacao);
            if (cfg == null) {
                throw new Exception("OpCode invalido " + this.operacao);
            }

            switch (cfg[2]) {
                case 1:
                    ponteiro = 4;
                    break;
                case 2:
                    ponteiro = 8;
                    break;
                case 3:
                    ponteiro = 9;
                    break;
                default:
                    ponteiro = 10;
            }
        } else if (ponteiro == -2) {
            cfg = (byte[]) CodeCfgs.get(this.operacao);
            if (cfg == null) {
                throw new Exception("OpCode invalido " + this.operacao);
            }

            if (cfg[2] == 4) {
                ponteiro = cfg[0];
                switch (cfg[3]) {
                    case 0:
                        if (this.ula.flagSignal()) {
                            ponteiro = 0;
                        }
                        break;
                    case 1:
                        if (this.ula.flagSignal() && !this.ula.flagZero()) {
                            ponteiro = 0;
                        }
                        break;
                    case 2:
                        if (!this.ula.flagSignal() && !this.ula.flagZero()) {
                            ponteiro = 0;
                        }
                        break;
                    case 3:
                        if (!this.ula.flagSignal()) {
                            ponteiro = 0;
                        }
                        break;
                    case 4:
                        if (!this.ula.flagZero()) {
                            ponteiro = 0;
                        }
                        break;
                    case 5:
                        if (this.ula.flagZero()) {
                            ponteiro = 0;
                        }
                        break;
                    default:
                        ponteiro = 0;
                }
            } else {
                ponteiro = cfg[0];
            }
        }

        this.firm.setPointer(ponteiro);
    }

    public void advanceClock() throws Exception {
        this.executeMicroInstruction();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }


    public void notifyObservers() {
        Iterator var2 = this.observers.iterator();

        while (var2.hasNext()) {
            Observer o = (Observer) var2.next();
            o.notify(this);
        }

    }

    public boolean getStatus(int id) {
        return this.portas[id] == 1;
    }

    public static int getQtdRegs(OpCode opcode) {
        return ((byte[]) CodeCfgs.get(opcode))[1];
    }

    public void reset() {
        this.firm.setPointer(0);
    }

    public boolean podeAtualizar() {
        return this.flagUpdate;
    }
}

