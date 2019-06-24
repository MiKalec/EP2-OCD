package br.com.ep.controller;

import br.com.ep.components.Porta;
import br.com.ep.components.PortaX;
import br.com.ep.components.cpu.*;
import br.com.ep.display.TelaPrincipal;
import br.com.ep.implementations.Mux;
import br.com.ep.implementations.RegCode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class Controller implements ActionListener {
    private br.com.ep.components.cpu.UC UC;
    private Memory memoria;
    private UnidadeLogicaAritimetica ula;
    private Registrador mar;
    private Registrador mbr;
    private Registrador ir;
    private Registrador p1;
    private Registrador p2;
    private Registrador x;
    private Registrador ac;
    private Registrador pc;
    private Registrador ax;
    private Registrador bx;
    private Registrador cx;
    private Registrador dx;
    private Registrador ds;
    private Mux t = Mux.instanceOf();
    private TelaPrincipal tela;

    public Controller() {
        this.initComponents();
        this.tela = new TelaPrincipal(this);
        this.tela.setMemoryModel(this.memoria);
        this.tela.getBtnExecutaInstrucao().setEnabled(false);
        this.tela.getBtnClearCodigo().setEnabled(false);
        this.atualizarExibicao();
    }

    private void atualizarExibicao() {
        JTextField[] var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[9].setText(String.valueOf(this.mar.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[10].setText(String.valueOf(this.mbr.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[4].setText(String.valueOf(this.ir.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[5].setText(String.valueOf(this.p1.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[6].setText(String.valueOf(this.p2.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[7].setText(String.valueOf(this.pc.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[0].setText(String.valueOf(this.ax.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[1].setText(String.valueOf(this.bx.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[2].setText(String.valueOf(this.cx.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[3].setText(String.valueOf(this.dx.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[8].setText(String.valueOf(this.ds.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[11].setText(String.valueOf(this.x.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[12].setText(String.valueOf(this.ac.getWord().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[13].setText(String.valueOf(this.ula.getNum1().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[14].setText(String.valueOf(this.ula.getNum2().getIntValue()));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[15].setText(String.valueOf(this.ula.flagZero() ? 1 : 0));
        var10000 = this.tela.getTxtRegistradores();
        this.tela.getClass();
        var10000[16].setText(String.valueOf(this.ula.flagSignal() ? 1 : 0));
        this.tela.atualizaSelecaoLinhaControle(this.UC.getPointer());
        this.tela.getTxtDescOperacao().setText("Linha " + this.UC.getPointer() + ": " + Firmware.instrucoes[this.UC.getPointer()].getDesc());
        this.tela.atualizaMem();
    }

    private void initComponents() {
        this.mar = new Registrador("MAR", "000001");
        this.mbr = new Registrador("MBR", "802903");
        this.p1 = new Registrador("P1", "006007");
        this.p2 = new Registrador("P2", "010011");
        this.ir = new IR("IR", "004005", this.p1, this.p2);
        this.pc = new Registrador("PC", "012013");
        this.x = new Registrador("X", "014015");
        this.ula = new UnidadeLogicaAritimetica("016017");
        this.ac = new Registrador("AC", "018019");
        this.ax = new RegistradorUtilizavel("AX", "020021", new RegCode(new byte[3]));
        this.bx = new RegistradorUtilizavel("BX", "022023", new RegCode(new byte[]{0, 0, 1}));
        this.cx = new RegistradorUtilizavel("CX", "024025", new RegCode(new byte[]{0, 1, 0}));
        this.dx = new RegistradorUtilizavel("DX", "026027", new RegCode(new byte[]{0, 1, 1}));
        this.memoria = new Memory("028029");
        this.ds = new RegistradorUtilizavel("DS", "030031", new RegCode(new byte[]{1, 0, 0}));
        List<RegistradorUtilizavel> regsU = new LinkedList();
        regsU.add((RegistradorUtilizavel) this.ax);
        regsU.add((RegistradorUtilizavel) this.bx);
        regsU.add((RegistradorUtilizavel) this.cx);
        regsU.add((RegistradorUtilizavel) this.dx);
        regsU.add((RegistradorUtilizavel) this.ds);
        this.UC = new UC((IR) this.ir, regsU, this.memoria, this.ula);
        Bus bUlaAC = new Bus();
        Bus bUlaX = new Bus();
        Bus bRegs = new Bus();
        Bus bExterno = new Bus();
        new Porta(false, 1, bExterno, this.mar, this.UC);
        new Porta(false, 3, bRegs, this.mbr, this.UC);
        new Porta(false, 9, bExterno, this.mbr, this.UC);
        new Porta(false, 5, bRegs, this.ir, this.UC);
        new Porta(false, 7, bRegs, this.p1, this.UC);
        new Porta(false, 11, bRegs, this.p2, this.UC);
        new Porta(false, 13, bRegs, this.pc, this.UC);
        new PortaX(15, bUlaX, this.x, this.ula, this.UC);
        new Porta(false, 17, bUlaAC, this.ula, this.UC);
        new Porta(false, 19, bRegs, this.ac, this.UC);
        new Porta(false, 21, bRegs, this.ax, this.UC);
        new Porta(false, 23, bRegs, this.bx, this.UC);
        new Porta(false, 25, bRegs, this.cx, this.UC);
        new Porta(false, 27, bRegs, this.dx, this.UC);
        new Porta(false, 29, bExterno, this.memoria, this.UC);
        new Porta(false, 31, bRegs, this.ds, this.UC);
        new Porta(true, 0, bRegs, this.mar, this.UC);
        new Porta(true, 2, bRegs, this.mbr, this.UC);
        new Porta(true, 8, bExterno, this.mbr, this.UC);
        new Porta(true, 4, bRegs, this.ir, this.UC);
        new Porta(true, 6, bRegs, this.p1, this.UC);
        new Porta(true, 10, bRegs, this.p2, this.UC);
        new Porta(true, 12, bRegs, this.pc, this.UC);
        new Porta(true, 14, bRegs, this.x, this.UC);
        new Porta(true, 16, bRegs, this.ula, this.UC);
        new Porta(true, 18, bUlaAC, this.ac, this.UC);
        new Porta(true, 20, bRegs, this.ax, this.UC);
        new Porta(true, 22, bRegs, this.bx, this.UC);
        new Porta(true, 24, bRegs, this.cx, this.UC);
        new Porta(true, 26, bRegs, this.dx, this.UC);
        new Porta(true, 28, bExterno, this.memoria, this.UC);
        new Porta(true, 30, bRegs, this.ds, this.UC);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action.hashCode()) {
            case -2018824143:
                if (action.equals("Limpar")) {
                    this.tela.getBtnExecutaInstrucao().setEnabled(false);
                    this.tela.getBtnClearCodigo().setEnabled(false);
                    this.resetAll();
                }
                break;
            case 1336370511:
                if (action.equals("Traduzir")) {
                    try {
                        if (this.tela.getCodigo().getText().trim().length() > 0) {
                            this.traduzir(this.tela.getCodigo().getText());
                            this.tela.getBtnExecutaInstrucao().setEnabled(true);
                            this.tela.getBtnClearCodigo().setEnabled(true);
                        }
                    } catch (Exception var5) {
                        JOptionPane.showMessageDialog(this.tela, var5.getClass().getName() + ": " + var5.getMessage(), "Erro na tradução", 0);
                    }
                }
                break;
            case 2107661121:
                if (action.equals("Executar")) {
                    try {
                        this.UC.advanceClock();
                    } catch (Exception var4) {
                        JOptionPane.showMessageDialog(this.tela, var4.getClass().getName() + ": " + var4.getMessage(), "Erro na execução", 0);
                    }
                }
        }

        this.atualizarExibicao();
    }

    private void resetAll() {
        this.UC.reset();
        this.memoria.reset();
        this.ula.reset();
        this.mar.reset();
        this.mbr.reset();
        this.ir.reset();
        this.p1.reset();
        this.p2.reset();
        this.x.reset();
        this.ac.reset();
        this.pc.reset();
        this.ax.reset();
        this.bx.reset();
        this.cx.reset();
        this.dx.reset();
        this.ds.reset();
    }

    private void traduzir(String text) throws Exception {
        String[] linhas = text.split("\n");
        int contador = 0;

        for (String linha : linhas) {
            String s = linha;
            s = s.trim();
            if (s.length() > 0) {
                this.memoria.insere((long) contador, this.t.traduzir(s));
                ++contador;
            }
        }

    }
}