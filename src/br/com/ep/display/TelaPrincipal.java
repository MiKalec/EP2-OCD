package br.com.ep.display;

import br.com.ep.components.cpu.Memory;
import br.com.ep.controller.Controller;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField[] txtRegistradores;
    public final int ax = 0;
    public final int bx = 1;
    public final int cx = 2;
    public final int dx = 3;
    public final int ir = 4;
    public final int p1 = 5;
    public final int p2 = 6;
    public final int pc = 7;
    public final int ds = 8;
    public final int mar = 9;
    public final int mbr = 10;
    public final int x = 11;
    public final int ac = 12;
    public final int ula1 = 13;
    public final int ula2 = 14;
    public final int zero = 15;
    public final int sinal = 16;
    private JPanel pnlComandos;
    private JPanel pnlRegistradores;
    private JPanel pnlMemoria;
    private JPanel pnlLinhasControle;
    private JButton btnTraduzir;
    private JButton btnClearCodigo;
    private JButton btnExecutaInstrucao;
    private JTextArea codigo;
    private JTextField txtDescOperacao;
    private JTable tabelaMemoria;
    private JTable tabelaControle;
    private Controller ctrl;
    private JPanel upperPanel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JLabel label;
    private JPanel middlePanel;
    private JPanel bottonPanel;
    private ImageIcon icone = new ImageIcon("/ocd-ico.ico");

    public TelaPrincipal(Controller ctrl) {
        this.setFont(new Font("Courier 10 Pitch", 0, 10));
        this.setBackground(Color.DARK_GRAY);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.ctrl = ctrl;
        this.iniciarComponentes();
        this.setVisible(true);
        this.setIconImage(this.icone.getImage());
    }

    public void iniciarComponentes() {
        Color color = new Color(133, 188, 69);
        this.setTitle("EP2 OCD");
        this.setDefaultCloseOperation(3);
        this.setSize(1100, 680);
        this.getContentPane().setLayout(new FlowLayout(1, 5, 5));
        this.upperPanel = new JPanel();
        this.upperPanel.setBackground(Color.DARK_GRAY);
        this.getContentPane().add(this.upperPanel);
        this.upperPanel.setLayout(new BoxLayout(this.upperPanel, 0));
        this.panel_1 = new JPanel();
        this.panel_1.setBackground(Color.DARK_GRAY);
        this.upperPanel.add(this.panel_1);
        this.pnlComandos = new JPanel(new FlowLayout(1, 10, 10));
        this.pnlComandos.setBackground(Color.DARK_GRAY);
        this.panel_1.add(this.pnlComandos);
        this.panel_1.setPreferredSize(new Dimension(580, 300));
        TitledBorder titleComands = BorderFactory.createTitledBorder("Comandos Assembly");
        titleComands.setTitleColor(color);
        this.pnlComandos.setBorder(titleComands);
        this.pnlComandos.setPreferredSize(new Dimension(580, 300));
        this.pnlComandos.setVisible(true);
        JPanel p = new JPanel(new BorderLayout());
        p.setPreferredSize(new Dimension(545, 200));
        this.codigo = new JTextArea("");
        this.codigo.setLineWrap(true);
        this.codigo.setWrapStyleWord(true);
        JScrollPane scrollCodigo = new JScrollPane(this.codigo);
        scrollCodigo.setPreferredSize(new Dimension(545, 200));
        scrollCodigo.setVerticalScrollBarPolicy(22);
        p.add(scrollCodigo, "First");
        this.btnTraduzir = new JButton("Traduzir");
        this.btnTraduzir.setActionCommand("Traduzir");
        this.btnTraduzir.addActionListener(this.ctrl);
        this.btnTraduzir.setSize(50, 20);
        this.btnExecutaInstrucao = new JButton("Executar Instrução");
        this.btnExecutaInstrucao.setActionCommand("Executar");
        this.btnExecutaInstrucao.addActionListener(this.ctrl);
        this.btnExecutaInstrucao.setSize(50, 20);
        this.btnClearCodigo = new JButton("Limpar Memória");
        this.btnClearCodigo.setActionCommand("Limpar");
        this.btnClearCodigo.addActionListener(this.ctrl);
        this.btnClearCodigo.setSize(50, 20);
        this.pnlComandos.add(p);
        this.pnlComandos.add(this.btnTraduzir);
        this.pnlComandos.add(this.btnExecutaInstrucao);
        this.pnlComandos.add(this.btnClearCodigo);
        this.panel_2 = new JPanel();
        this.panel_2.setBackground(Color.DARK_GRAY);
        this.upperPanel.add(this.panel_2);
        this.pnlRegistradores = new JPanel();
        this.pnlRegistradores.setBackground(Color.DARK_GRAY);
        this.panel_2.add(this.pnlRegistradores);
        this.panel_2.setPreferredSize(new Dimension(420, 300));
        TitledBorder titleComands2 = BorderFactory.createTitledBorder("Registradores");
        titleComands2.setTitleColor(color);
        this.pnlRegistradores.setBorder(titleComands2);
        this.pnlRegistradores.setPreferredSize(new Dimension(380, 300));
        this.pnlRegistradores.setVisible(true);
        this.pnlRegistradores.setLayout(new BoxLayout(this.pnlRegistradores, 0));
        GridLayout g = new GridLayout(0, 4);
        g.setVgap(10);
        JPanel regs = new JPanel(g);
        regs.setBackground(color);
        regs.setPreferredSize(new Dimension(500, 240));
        this.pnlRegistradores.add(regs);
        this.label = new JLabel("");
        this.getContentPane().add(this.label);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(4);
        this.middlePanel = new JPanel();
        this.middlePanel.setBackground(Color.DARK_GRAY);
        this.getContentPane().add(this.middlePanel);
        this.middlePanel.setLayout(new FlowLayout(1, 5, 5));
        this.pnlLinhasControle = new JPanel();
        this.pnlLinhasControle.setBackground(Color.DARK_GRAY);
        this.middlePanel.add(this.pnlLinhasControle);
        this.pnlLinhasControle.setLayout(new BoxLayout(this.pnlLinhasControle, 1));
        TitledBorder titleComands4 = BorderFactory.createTitledBorder("Linhas de Controle");
        titleComands4.setTitleColor(color);
        this.pnlLinhasControle.setBorder(titleComands4);
        this.pnlLinhasControle.setPreferredSize(new Dimension(1000, 160));
        this.pnlLinhasControle.setVisible(true);
        JPanel descLinhaControle = new JPanel(new BorderLayout());
        descLinhaControle.setBackground(Color.DARK_GRAY);
        descLinhaControle.setPreferredSize(new Dimension(545, 30));
        this.txtDescOperacao = new JTextField();
        this.txtDescOperacao.setEditable(false);
        descLinhaControle.add(this.txtDescOperacao);
        this.tabelaControle = new JTable(new LinhaControleModel());
        this.tabelaControle.getColumnModel().getColumn(0).setPreferredWidth(460);
        this.tabelaControle.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        this.tabelaControle.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        this.tabelaControle.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        this.tabelaControle.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        this.tabelaControle.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        this.tabelaControle.setEnabled(false);
        JScrollPane barraRolagem = new JScrollPane(this.tabelaControle);
        this.pnlLinhasControle.add(descLinhaControle);
        this.pnlLinhasControle.add(barraRolagem);
        this.bottonPanel = new JPanel();
        this.bottonPanel.setBackground(Color.DARK_GRAY);
        this.getContentPane().add(this.bottonPanel);
        this.pnlMemoria = new JPanel(new BorderLayout());
        this.pnlMemoria.setBackground(Color.DARK_GRAY);
        this.bottonPanel.add(this.pnlMemoria);
        TitledBorder titleComands3 = BorderFactory.createTitledBorder("Memória");
        titleComands3.setTitleColor(color);
        this.pnlMemoria.setBorder(titleComands3);
        this.pnlMemoria.setPreferredSize(new Dimension(1000, 150));
        this.pnlMemoria.setVisible(true);
        this.tabelaMemoria = new JTable();
        this.tabelaMemoria.setEnabled(false);
        JScrollPane barraRolagemMemoria = new JScrollPane(this.tabelaMemoria);
        JPanel jmpMemPanel = new JPanel(new FlowLayout());
        jmpMemPanel.setBackground(Color.DARK_GRAY);
        this.pnlMemoria.add(jmpMemPanel, "North");
        this.pnlMemoria.add(barraRolagemMemoria, "Center");
        this.txtRegistradores = new JTextField[17];
        JLabel[] lblRegs = new JLabel[17];
        String[] nomesRegs = new String[]{"ax", "bx", "cx", "dx", "ir", "p1", "p2", "pc", "ds", "mar", "mbr", "x", "ac", "UL1", "UL2", "Zero", "Sinal"};

        for (int i = 0; i <= 16; ++i) {
            JPanel aux = new JPanel();
            aux.setBackground(color);
            aux.setLayout(new BoxLayout(aux, 0));
            this.txtRegistradores[i] = new JTextField();
            this.txtRegistradores[i].setPreferredSize(new Dimension(55, 30));
            this.txtRegistradores[i].setEditable(false);
            this.txtRegistradores[i].setBackground(color);
            lblRegs[i] = new JLabel(nomesRegs[i].toUpperCase());
            lblRegs[i].setPreferredSize(new Dimension(30, 30));
            if (i > 14) {
                lblRegs[i].setPreferredSize(new Dimension(40, 30));
                lblRegs[i].setBackground(color);
            }

            aux.add(lblRegs[i]);
            aux.add(this.txtRegistradores[i]);
            regs.add(aux);
            JPanel auxPanel;
            if (i == 6) {
                auxPanel = new JPanel();
                auxPanel.setBackground(color);
                regs.add(auxPanel);
            } else if (i == 8 || i == 10 || i == 12) {
                auxPanel = new JPanel();
                JPanel auxPanel2 = new JPanel();
                auxPanel.setBackground(color);
                auxPanel2.setBackground(color);
                regs.add(auxPanel);
                regs.add(auxPanel2);
            }
        }

    }

    public void atualizaSelecaoLinhaControle(int ponteiro) {
        this.tabelaControle.setRowSelectionInterval(ponteiro, ponteiro);
        this.tabelaControle.scrollRectToVisible(new Rectangle(this.tabelaControle.getCellRect(ponteiro, 0, true)));
    }

    public static long getSerialversionuid() {
        return 1L;
    }

    public JTextField[] getTxtRegistradores() {
        return this.txtRegistradores;
    }

    public JPanel getPnlComandos() {
        return this.pnlComandos;
    }

    public JPanel getPnlRegistradores() {
        return this.pnlRegistradores;
    }

    public JPanel getPnlMemoria() {
        return this.pnlMemoria;
    }

    public JPanel getPnlLinhasControle() {
        return this.pnlLinhasControle;
    }

    public JButton getBtnTraduzir() {
        return this.btnTraduzir;
    }

    public JButton getBtnClearCodigo() {
        return this.btnClearCodigo;
    }

    public JButton getBtnExecutaInstrucao() {
        return this.btnExecutaInstrucao;
    }

    public JTextArea getCodigo() {
        return this.codigo;
    }

    public JTable getTabelaMemoria() {
        return this.tabelaMemoria;
    }

    public JTable getTabelaControle() {
        return this.tabelaControle;
    }

    public void setMemoryModel(Memory memoria) {
        this.tabelaMemoria.setModel(new MemoriaModel(memoria));
        this.tabelaMemoria.getColumnModel().getColumn(0).setPreferredWidth(70);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(4);
        this.tabelaMemoria.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        this.tabelaMemoria.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    }

    public void atualizaMem() {
        ((MemoriaModel) this.tabelaMemoria.getModel()).update();
        this.tabelaMemoria.repaint();
        this.tabelaMemoria.revalidate();
    }

    public JTextField getTxtDescOperacao() {
        return this.txtDescOperacao;
    }
}
